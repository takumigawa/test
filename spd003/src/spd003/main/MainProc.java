package spd003.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import logger.Log4JLogout;
import dto.MailSetting;
import sDBAccess.SibDBAccess;
import sDBAccess.SibDBAccessSetting;
import service.MailTransfer;
import sibXMLParser.SibXMLParser;
import spd003.dao.TSendLogDao;
import spd003.dao.TSendMailAddressDao;
import spd003.dao.TSendMailDao;
import spd003.dao.TSendTimeDao;
import spd003.dto.BatchParam;
import spd003.entity.TSendLog;
import spd003.entity.TSendMail;
import spd003.entity.TSendMailAddress;
import spd003.entity.TSendTime;

public class MainProc {

	private static String JAVA_CR = "\r";
	
	private static String R_YEAR = "${yyyy}";
	private static String R_MONTH = "${mm}";
	private static String R_DAY = "${dd}";
	private static String R_HOUR = "${HH}";
	private static String R_MINI = "${MM}";
	private static String R_SEC = "${SS}";
	
	/**
	 * @param args
	 */
	public static void main(String[] args){

		MailSetting mailSet;

		BatchParam bp;
		
		SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/DBA");

		SibDBAccessSetting DBASet = new SibDBAccessSetting();

		//DB�ڑ��ݒ�
		DBASet.setID(XMLpars.getValue("user"));
		DBASet.setPass(XMLpars.getValue("password"));
		DBASet.setSchema(XMLpars.getValue("schema"));
		DBASet.setURL(XMLpars.getValue("host"));

		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);

		try{
			//�p�����[�^�[�`�F�b�N
			bp = createParam(args);

			if (bp == null){
				throw new Exception("�p�����[�^�s��");
			}

			//DB�ʐM�m�F
			if (!DBA.isConnect()){
				throw new SQLException("SQL�T�[�o�[�R�l�N�V�����G���[");
			}

			//���[�����M����Dao�@�C���X�^���X
			TSendTimeDao timDao = new TSendTimeDao();
			//���M���[�����eDao�@�C���X�^���X
			TSendMailDao mailDao = new TSendMailDao();
			//���[�����M��Dao �C���X�^���X
			TSendMailAddressDao addDao = new TSendMailAddressDao();
			//���[�����M���ODao�@�C���X�^���X
			TSendLogDao logDao = new TSendLogDao();

			List<TSendTime> sendMailList = new ArrayList<TSendTime>();

			sendMailList = timDao.SelectToTime(bp);

	        //�����擾���O
	        Log4JLogout.LogOut(String.format("���[�����M �擾����%d��", sendMailList.size()));

			//���M���[�������̎擾���������[�v����
			for (TSendTime item : sendMailList) {

				TSendMail sendMailDetail = mailDao.selectById(item.getSendMailID());

				List<TSendMailAddress> toList = addDao.SelectToMailID(item.getSendMailID());

		        //���[��ID
		        Log4JLogout.LogOut("���[��ID:" + sendMailDetail.getID().toString());
		        //���[���^�C�g��
		        Log4JLogout.LogOut("���[���^�C�g��:" + sendMailDetail.getMailTitel());
		        //���M��
		        Log4JLogout.LogOut("���M��:" + sendMailDetail.getFromMailAddress());

				//���[�����M��̎擾���������[�v����B
				for (TSendMailAddress toItem : toList ) {
					mailSet = createMailSetting();

			        //���M��
			        Log4JLogout.LogOut("���M��:" + toItem.getToMailAddress());

					//���[���^�C�g���ݒ�
					mailSet.setSubject(formatString(sendMailDetail.getMailTitel()));

					//���[�����M�Ґݒ�
					mailSet.setFromMailAddress(sendMailDetail.getFromMailAddress());

					//���[�����M��ݒ�
					mailSet.setToMailAddress(toItem.getToMailAddress());

					//���[���{���ݒ�
					mailSet.setMessege(sendMailDetail.getMailDitail());

					//���[�����M
					MailTransfer mailTo = new MailTransfer(mailSet);

					mailTo.sendMail();

					TSendLog insLog = new TSendLog();

					//���M�惁�[���A�h���X
					insLog.setFromAddress(sendMailDetail.getFromMailAddress());
					//���M�����[���A�h���X
					insLog.setToAddress(toItem.getToMailAddress());
					//���[���^�C�g��
					insLog.setSendTitel(sendMailDetail.getMailTitel());
					//���[���{��
					insLog.setSendDetail(sendMailDetail.getMailDitail());

					//���[�����M���O
					logDao.Insert(insLog);

				}

			}

			if (sendMailList.size() > 0) {
				//���M���[���̍X�V
				timDao.Update(bp);
			}

		} catch(SQLException ex){

			MailSetting errMail = createErrMailSetting();

			errMail.setSubject("�������M���[���@�G���[�ʒm");

			//���b�Z�[�W�ݒ�
			StringBuilder mesBil = new StringBuilder();

			mesBil.append("���[�����M����SQL�G���[���N���܂����B" + JAVA_CR);
			mesBil.append("�G���[���e�͈ȉ��ł��B�m�F���Ă��������B" + JAVA_CR);
			mesBil.append("------------------" + JAVA_CR);
			mesBil.append(ex.getMessage() + JAVA_CR);
			mesBil.append("" + JAVA_CR);
			mesBil.append(ex.getStackTrace() + JAVA_CR);

			errMail.setMessege(mesBil.toString());

			//�W���o�͂փG���[�o��
			Log4JLogout.ErrLogOut(mesBil.toString());

			MailTransfer mailTo = new MailTransfer(errMail);

			mailTo.sendMail();

		} catch(Exception ex){

			MailSetting errMail = createErrMailSetting();

			errMail.setSubject("�������M���[���@�G���[�ʒm");

			//���b�Z�[�W�ݒ�
			StringBuilder mesBil = new StringBuilder();

			mesBil.append("���[�����M���ɃG���[���N���܂����B" + JAVA_CR);
			mesBil.append("�G���[���e�͈ȉ��ł��B�m�F���Ă��������B" + JAVA_CR);
			mesBil.append("------------------" + JAVA_CR);
			mesBil.append(ex.getMessage() + JAVA_CR);
			mesBil.append("" + JAVA_CR);
			mesBil.append(ex.getStackTrace() + JAVA_CR);

			errMail.setMessege(mesBil.toString());

			//�W���o�͂փG���[�o��
			Log4JLogout.ErrLogOut(mesBil.toString());

			MailTransfer mailTo = new MailTransfer(errMail);

			mailTo.sendMail();

		} finally {
			//�f�[�^�x�[�X�̐ڑ�����
			try {
				DBA.Close();
			} catch (SQLException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}

		}

	}

	/**
	 * @param args
	 */
	private static BatchParam createParam(String[] args) {

		if (args.length != 5) {
			return null;
		}

		BatchParam bp = new BatchParam();

		//���M�N
		bp.setYear(args[0]);
		//���M��
		bp.setMonth(args[1]);
		//���M��
		bp.setDay(args[2]);
		//���M����
		bp.setHour(args[3]);
		//���M��
		bp.setMinute(args[4]);

		return bp;

	}

	/**
	 * @param args
	 */
	private static MailSetting createMailSetting() {

		MailSetting iS = new MailSetting();
		
		SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/DBA");

		//�z�X�g���ݒ�
		iS.setHostName(XMLpars.getValue("hostname"));
		//�|�[�g�ԍ��ݒ�
		iS.setPortNumber(XMLpars.getValue("port"));
		//���M���[��ID�ݒ�
		iS.setId(XMLpars.getValue("sendid"));
		//���M���[���p�X���[�h�ݒ�
		iS.setPas(XMLpars.getValue("sendpas"));
		
		iS.setSSlFlg(false);
		iS.setAuthFlg(true);

		return iS;

	}

	/**
	 * @param args
	 */
	private static MailSetting createErrMailSetting() {

		MailSetting iS = new MailSetting();
		
		SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/DBA");

		//���[�����M�z�X�g
		//�z�X�g���ݒ�
		iS.setHostName(XMLpars.getValue("hostname"));
		//�|�[�g�ԍ��ݒ�
		iS.setPortNumber(XMLpars.getValue("port"));
		//���M���[��ID�ݒ�
		iS.setId(XMLpars.getValue("sendid"));
		//���M���[���p�X���[�h�ݒ�
		iS.setPas(XMLpars.getValue("sendpas"));
		//���[�����M���A�h���X�ݒ�
		iS.setFromMailAddress(XMLpars.getValue("errfromaddress"));
		//�G���[���[���^�C�g���ݒ�
		iS.setFromName(XMLpars.getValue("errstitel"));
		//���[�����M��A�h���X�ݒ�
		iS.setToMailAddress(XMLpars.getValue("errsendaddress"));

		iS.setSSlFlg(false);
		iS.setAuthFlg(true);

		return iS;

	}
	
	/**
	 * @param args
	 */
	private static String formatString(String iText) {
		
		Calendar cal = Calendar.getInstance();
		
		//�N�擾
		int ryear = cal.get(Calendar.YEAR);
		//���擾
		int rmonth = cal.get(Calendar.MONTH);
		//���擾
		int rday = cal.get(Calendar.DAY_OF_MONTH);
		//���擾
		int rhour = cal.get(Calendar.HOUR_OF_DAY);
		//���擾
		int rmini = cal.get(Calendar.MINUTE);
		//�b�擾
		int rsec = cal.get(Calendar.SECOND);
		
		//�N��ϊ�
		iText = iText.replace(R_YEAR, String.valueOf(ryear));
		//����ϊ�
		iText = iText.replace(R_MONTH, String.valueOf(rmonth));
		//����ϊ�
		iText = iText.replace(R_DAY, String.valueOf(rday));
		//����ϊ�
		iText = iText.replace(R_HOUR, String.valueOf(rhour));
		//����ϊ�
		iText = iText.replace(R_MINI, String.valueOf(rmini));
		//�b��ϊ�
		iText = iText.replace(R_SEC, String.valueOf(rsec));
		
		return iText;
		
	}
	
}
