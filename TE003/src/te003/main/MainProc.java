package te003.main;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import logger.Log4JLogout;
import dto.MailSetting;
import sDBAccess.SibDBAccess;
import sDBAccess.SibDBAccessSetting;
import service.MailTransfer;
import sibXMLParser.SibXMLParser;
import te003.dto.BatchParam;

public class MainProc {

	private static String JAVA_CR = "\r";
	
	private static String mailTitel = "�\���ς݁@��ʔ�\����";
	
	private static String mailBody1 = "%s�l%s";
	private static String mailBody2 = "%s�̌�ʔ�\�������𑗕t���܂��B%s";
	private static String mailBody3 = "%s";
	private static String mailBody4 = "���e���m�F���Ă��������B%s";
	
    /**
     * @param args
     */
    public static void main(String[] args) {

        //�J�n���O�@�o��
        Log4JLogout.LogOut("TE003 main Start");
        
        BatchParam bp;
        StringBuilder mesBil;
		
		SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/DBA");
		
		SibDBAccessSetting DBASet = new SibDBAccessSetting();

		//DB�ڑ��ݒ�
		DBASet.setID(XMLpars.getValue("user"));
		DBASet.setPass(XMLpars.getValue("password"));
		DBASet.setSchema(XMLpars.getValue("schema"));
		DBASet.setURL(XMLpars.getValue("host"));

		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);
		
        try    {
        	
			//�p�����[�^�[�`�F�b�N
			bp = createParam(args);
			
			if (bp == null){
				throw new Exception("�p�����[�^�s��");
			}
            
			MailSetting pdfMail = createMailSetting();
			
			//�T�u�W�F�N�g�쐬
			pdfMail.setSubject(mailTitel);
			
			//���b�Z�[�W�ݒ�
			 mesBil = new StringBuilder();
						
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy�NMM��dd�� HH��mm��");
			Date nowDate = new Date();
				
			mesBil.append(String.format(mailBody1, XMLpars.getValue("sendName"), JAVA_CR));
			mesBil.append(String.format(mailBody3, JAVA_CR));
			mesBil.append(String.format(mailBody2, sdf.format(nowDate.getTime()) ,JAVA_CR));
			mesBil.append(String.format(mailBody3, JAVA_CR));
			mesBil.append(String.format(mailBody4, JAVA_CR));
			
			//���[�����M��A�h���X
			pdfMail.setToMailAddress(XMLpars.getValue("sendMailAddress"));
			
			//���^����PDF�̓Y�t
			pdfMail.setAttachmentPath(bp.getReportPath());
			
			//���b�Z�[�W
			pdfMail.setMessege(mesBil.toString());
			
			MailTransfer mailTo = new MailTransfer(pdfMail);
			
			//���[�����M
			mailTo.sendMail();
			
        } catch (Exception e) {

			MailSetting errMail = createErrMailSetting();
			
			errMail.setSubject("�������M���[���@�G���[�ʒm");
			
			//���b�Z�[�W�ݒ�
			mesBil = new StringBuilder();
						
			mesBil.append("���[�����M���ɃG���[���N���܂����B" + JAVA_CR);
			mesBil.append("�G���[���e�͈ȉ��ł��B�m�F���Ă��������B" + JAVA_CR);
			mesBil.append("------------------" + JAVA_CR);
			mesBil.append(e.getMessage() + JAVA_CR);
			mesBil.append("" + JAVA_CR);
			mesBil.append(e.getStackTrace() + JAVA_CR);
			
			errMail.setMessege(mesBil.toString());
			
			MailTransfer mailTo = new MailTransfer(errMail);
			
			mailTo.sendMail();
            
        } finally {

            try {
				DBA.Close();
			} catch (SQLException e) {

			}
            
            //�J�n���O�@�o��
            Log4JLogout.LogOut("TE003 main End");
        }
    }
    
    /**
     * @param args
     */
    private static BatchParam createParam(String[] args) {
    	
    	if (args.length != 1) {
    		return null;
    	}
    	
    	BatchParam bp = new BatchParam();
    	
    	//���M�N
    	bp.setReportPath(args[0]);
    	
    	return bp;
    			
    }
    
	/**
	 * @param args
	 */
	private static MailSetting createMailSetting() {

		MailSetting iS = new MailSetting();
		
		iS.setHostName("sib.co.jp");
		
		iS.setPortNumber("587");
		
		iS.setFromMailAddress("sysbot@sib.co.jp");
		iS.setId("sysbot@sib.co.jp");
		iS.setPas("sysbot1962");
		
		iS.setSSlFlg(false);
		iS.setAuthFlg(true);
		
		return iS;
		
	}
		
	/**
	 * @param args
	 */
	private static MailSetting createErrMailSetting() {

		MailSetting iS = new MailSetting();
		
		//���[�����M�z�X�g
		iS.setHostName("sib.co.jp");
		iS.setFromMailAddress("sysbot@sib.co.jp");
		iS.setFromName("SIB�������[�����M�V�X�e��");
		
		//���[�����M��A�h���X
		iS.setToMailAddress("taku_fujimoto@sib.co.jp");
		
		iS.setPortNumber("587");
		
		iS.setId("sysbot@sib.co.jp");
		iS.setPas("sysbot1962");
		
		iS.setSSlFlg(false);
		iS.setAuthFlg(true);
		
		return iS;
		
	}
}


