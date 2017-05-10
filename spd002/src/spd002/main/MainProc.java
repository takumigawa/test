package spd002.main;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import logger.Log4JLogout;

import dto.MailSetting;
import sDBAccess.SibDBAccess;
import sDBAccess.SibDBAccessSetting;
import service.MailTransfer;
import spd002.dao.TSendPdfDao;
import spd002.dao.TSendymDao;
import spd002.dto.BatchParam;
import spd002.entity.TSendPdf;
import spd002.entity.TSendym;


public class MainProc {

	private static String JAVA_CR = "\r";

	private static String mailTitel = "%s�N%s�x ���^����";

	private static String mailBody1 = "%s�l%s";
	private static String mailBody2 = "%s�N%s���̋��^���ׂ𑗕t���܂��B%s";
	private static String mailBody3 = "%s";
	private static String mailBody4 = "���e���m�F���Ă��������B%s";

	public static void main(String[] args){

		//�J�n���O�@�o��
		Log4JLogout.LogOut("spd002 main Start");

		BatchParam bp;
		StringBuilder mesBil;

		SibDBAccessSetting DBASet = new SibDBAccessSetting();

		//DB�ڑ��ݒ�
		DBASet.setID("user1");
		DBASet.setPass("sib1962");
		DBASet.setSchema("sibpayadvice");
		DBASet.setURL("133.242.134.145");

		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);

		try {

			//�p�����[�^�[�`�F�b�N
			bp = createParam(args);

			if (bp == null){
				throw new Exception("�p�����[�^�s��");
			}

			TSendymDao ymDao = new TSendymDao();

			List<TSendym> ymList = ymDao.SelectALL();

	        //�����擾���O
	        Log4JLogout.LogOut(String.format("���M�N�� �擾����%d��", ymList.size()));
			for (TSendym ymItem : ymList) {

				bp.setYear(ymItem.getSendyear().toString());
				bp.setMonth(ymItem.getSendmonth().toString());

				//���M�N
				Log4JLogout.LogOut("���M�N:" + ymItem.getSendyear().toString());
				//���M��
				Log4JLogout.LogOut("���M��:" + ymItem.getSendmonth().toString());

				TSendPdfDao pdfDao = new TSendPdfDao();

				List<TSendPdf> itemList;

				itemList = pdfDao.SelectYM(bp);

		        //�����擾���O
		        Log4JLogout.LogOut(String.format("���MPDF �擾����%d��", itemList.size()));

				//���MPDF���������[�v
				for (TSendPdf item : itemList) {

					File Sendfile = new File(item.getSendfile());

					//���^����PDF���݊m�F
					if(Sendfile.exists()) {

						MailSetting pdfMail = createMailSetting();

						//�T�u�W�F�N�g�쐬
						pdfMail.setSubject(String.format(mailTitel, bp.getYear(), bp.getMonth()));

						//���b�Z�[�W�ݒ�
						 mesBil = new StringBuilder();

						mesBil.append(String.format(mailBody1, item.getEmpname(), JAVA_CR));
						mesBil.append(String.format(mailBody2, bp.getYear(), bp.getMonth(), JAVA_CR));
						mesBil.append(String.format(mailBody3, JAVA_CR));
						mesBil.append(String.format(mailBody4, JAVA_CR));

						//�Ј���
						Log4JLogout.LogOut("�Ј���:" + item.getEmpname());
						//���[���A�h���X
						Log4JLogout.LogOut("���[���A�h���X:" + item.getSendmail());

						//���[�����M��A�h���X
						pdfMail.setToMailAddress(item.getSendmail());

						//���^����PDF�̓Y�t
						pdfMail.setAttachmentPath(item.getSendfile());

						//���b�Z�[�W
						pdfMail.setMessege(mesBil.toString());

						MailTransfer mailTo = new MailTransfer(pdfMail);

						//���[�����M
						mailTo.sendMail();

						//PDF���M�e�[�u���X�V
						pdfDao.Update(item.getEmpid().toString(), bp.getYear(), bp.getMonth());

					}

				}

				//���M�ςݍX�V
				ymDao.Update(ymItem.getSendyear(), ymItem.getSendmonth());
			}

			//�I�����O�@�o��
			Log4JLogout.LogOut("spd002 main End");

		} catch(Exception ex){

			MailSetting errMail = createErrMailSetting();

			errMail.setSubject("�������M���[���@�G���[�ʒm");

			//���b�Z�[�W�ݒ�
			mesBil = new StringBuilder();

			mesBil.append("���[�����M���ɃG���[���N���܂����B" + JAVA_CR);
			mesBil.append("�G���[���e�͈ȉ��ł��B�m�F���Ă��������B" + JAVA_CR);
			mesBil.append("------------------" + JAVA_CR);
			mesBil.append(ex.getMessage() + JAVA_CR);
			mesBil.append("" + JAVA_CR);
			mesBil.append(ex.getStackTrace() + JAVA_CR);

			errMail.setMessege(mesBil.toString());

			//�W���o�͂փG���[���O�o��
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

		if (args.length != 2) {
			return null;
		}

		BatchParam bp = new BatchParam();

		//���M�N
		bp.setYear(args[0]);
		//���M��
		bp.setMonth(args[1]);


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
