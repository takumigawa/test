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

	private static String mailTitel = "%s年%s度 給与明細";

	private static String mailBody1 = "%s様%s";
	private static String mailBody2 = "%s年%s月の給与明細を送付します。%s";
	private static String mailBody3 = "%s";
	private static String mailBody4 = "内容を確認してください。%s";

	public static void main(String[] args){

		//開始ログ　出力
		Log4JLogout.LogOut("spd002 main Start");

		BatchParam bp;
		StringBuilder mesBil;

		SibDBAccessSetting DBASet = new SibDBAccessSetting();

		//DB接続設定
		DBASet.setID("user1");
		DBASet.setPass("sib1962");
		DBASet.setSchema("sibpayadvice");
		DBASet.setURL("133.242.134.145");

		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);

		try {

			//パラメーターチェック
			bp = createParam(args);

			if (bp == null){
				throw new Exception("パラメータ不正");
			}

			TSendymDao ymDao = new TSendymDao();

			List<TSendym> ymList = ymDao.SelectALL();

	        //件数取得ログ
	        Log4JLogout.LogOut(String.format("送信年月 取得件数%d件", ymList.size()));
			for (TSendym ymItem : ymList) {

				bp.setYear(ymItem.getSendyear().toString());
				bp.setMonth(ymItem.getSendmonth().toString());

				//送信年
				Log4JLogout.LogOut("送信年:" + ymItem.getSendyear().toString());
				//送信月
				Log4JLogout.LogOut("送信月:" + ymItem.getSendmonth().toString());

				TSendPdfDao pdfDao = new TSendPdfDao();

				List<TSendPdf> itemList;

				itemList = pdfDao.SelectYM(bp);

		        //件数取得ログ
		        Log4JLogout.LogOut(String.format("送信PDF 取得件数%d件", itemList.size()));

				//送信PDF件数文ループ
				for (TSendPdf item : itemList) {

					File Sendfile = new File(item.getSendfile());

					//給与明細PDF存在確認
					if(Sendfile.exists()) {

						MailSetting pdfMail = createMailSetting();

						//サブジェクト作成
						pdfMail.setSubject(String.format(mailTitel, bp.getYear(), bp.getMonth()));

						//メッセージ設定
						 mesBil = new StringBuilder();

						mesBil.append(String.format(mailBody1, item.getEmpname(), JAVA_CR));
						mesBil.append(String.format(mailBody2, bp.getYear(), bp.getMonth(), JAVA_CR));
						mesBil.append(String.format(mailBody3, JAVA_CR));
						mesBil.append(String.format(mailBody4, JAVA_CR));

						//社員名
						Log4JLogout.LogOut("社員名:" + item.getEmpname());
						//メールアドレス
						Log4JLogout.LogOut("メールアドレス:" + item.getSendmail());

						//メール送信先アドレス
						pdfMail.setToMailAddress(item.getSendmail());

						//給与明細PDFの添付
						pdfMail.setAttachmentPath(item.getSendfile());

						//メッセージ
						pdfMail.setMessege(mesBil.toString());

						MailTransfer mailTo = new MailTransfer(pdfMail);

						//メール送信
						mailTo.sendMail();

						//PDF送信テーブル更新
						pdfDao.Update(item.getEmpid().toString(), bp.getYear(), bp.getMonth());

					}

				}

				//送信済み更新
				ymDao.Update(ymItem.getSendyear(), ymItem.getSendmonth());
			}

			//終了ログ　出力
			Log4JLogout.LogOut("spd002 main End");

		} catch(Exception ex){

			MailSetting errMail = createErrMailSetting();

			errMail.setSubject("自動送信メール　エラー通知");

			//メッセージ設定
			mesBil = new StringBuilder();

			mesBil.append("メール送信時にエラーが起きました。" + JAVA_CR);
			mesBil.append("エラー内容は以下です。確認してください。" + JAVA_CR);
			mesBil.append("------------------" + JAVA_CR);
			mesBil.append(ex.getMessage() + JAVA_CR);
			mesBil.append("" + JAVA_CR);
			mesBil.append(ex.getStackTrace() + JAVA_CR);

			errMail.setMessege(mesBil.toString());

			//標準出力へエラーログ出力
			Log4JLogout.ErrLogOut(mesBil.toString());

			MailTransfer mailTo = new MailTransfer(errMail);

			mailTo.sendMail();

		} finally {
			//データベースの接続解除
			try {
				DBA.Close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
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

		//送信年
		bp.setYear(args[0]);
		//送信月
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

		//メール送信ホスト
		iS.setHostName("sib.co.jp");
		iS.setFromMailAddress("sysbot@sib.co.jp");
		iS.setFromName("SIB自動メール送信システム");

		//メール送信先アドレス
		iS.setToMailAddress("taku_fujimoto@sib.co.jp");

		iS.setPortNumber("587");

		iS.setId("sysbot@sib.co.jp");
		iS.setPas("sysbot1962");

		iS.setSSlFlg(false);
		iS.setAuthFlg(true);

		return iS;

	}

}
