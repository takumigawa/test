package spd005;

import java.text.SimpleDateFormat;
import java.util.Date;

import logger.Log4JLogout;
import service.MailTransfer;
import sibXMLParser.SibXMLParser;
import spd005.dto.BatchParam;
import dto.MailSetting;

public class MainProc {

	private static String JAVA_CR = "\r";
	
	private static String mailTitel = "�d�C���f���[�^�[";
	
	private static String mailBody1 = "%s�l%s";
	private static String mailBody2 = "%s�̓d�C���f���[�^�[�摜�𑗕t���܂��B%s";
	private static String mailBody3 = "%s";
	private static String mailBody4 = "���e���m�F���Ă��������B%s";
	
	public static void main(String[] args) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
        //�J�n���O�@�o��
        Log4JLogout.LogOut("SPD005 main Start");
        
        BatchParam bp;
        StringBuilder mesBil;
		
        SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/MAILL");
        
        try    {
        	
			//�p�����[�^�[�`�F�b�N
			bp = createParam(args);
			
			if (bp == null){
				throw new Exception("�p�����[�^�s��");
			}
            
			MailSetting imgMail = createMailSetting(XMLpars);
			
			//�T�u�W�F�N�g�쐬
			imgMail.setSubject(mailTitel);
			
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
			imgMail.setToMailAddress(XMLpars.getValue("sendMailAddress"));
			
			//�B�e�摜�̓Y�t
			imgMail.setAttachmentPath(bp.getReportPath());
			
			//���b�Z�[�W
			imgMail.setMessege(mesBil.toString());
			
			MailTransfer mailTo = new MailTransfer(imgMail);
			
			//���[�����M
			mailTo.sendMail();
			
        } catch (Exception e) {

			MailSetting errMail = createErrMailSetting(XMLpars);
			
			errMail.setSubject("�d�C���f���[�^�[�@�G���[�ʒm");
			
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
            
            //�J�n���O�@�o��
            Log4JLogout.LogOut("SPD005 main End");
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
    	
    	//�Y�t�t�@�C���p�X
    	bp.setReportPath(args[0]);
    	
    	return bp;
    			
    }
    
	/**
	 * @param args
	 */
	private static MailSetting createMailSetting(SibXMLParser iXML) {

		MailSetting iS = new MailSetting();
		
		iS.setHostName(iXML.getValue("hostname"));
		
		iS.setPortNumber(iXML.getValue("port"));
		
		iS.setFromMailAddress(iXML.getValue("sendid"));
		iS.setId(iXML.getValue("sendid"));
		iS.setPas(iXML.getValue("sendpas"));
		
		iS.setSSlFlg(false);
		iS.setAuthFlg(true);
		
		return iS;
		
	}
		
	/**
	 * @param args
	 */
	private static MailSetting createErrMailSetting(SibXMLParser iXML) {

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
