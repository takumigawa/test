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

		//DB接続設定
		DBASet.setID(XMLpars.getValue("user"));
		DBASet.setPass(XMLpars.getValue("password"));
		DBASet.setSchema(XMLpars.getValue("schema"));
		DBASet.setURL(XMLpars.getValue("host"));

		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);

		try{
			//パラメーターチェック
			bp = createParam(args);

			if (bp == null){
				throw new Exception("パラメータ不正");
			}

			//DB通信確認
			if (!DBA.isConnect()){
				throw new SQLException("SQLサーバーコネクションエラー");
			}

			//メール送信時刻Dao　インスタンス
			TSendTimeDao timDao = new TSendTimeDao();
			//送信メール内容Dao　インスタンス
			TSendMailDao mailDao = new TSendMailDao();
			//メール送信先Dao インスタンス
			TSendMailAddressDao addDao = new TSendMailAddressDao();
			//メール送信ログDao　インスタンス
			TSendLogDao logDao = new TSendLogDao();

			List<TSendTime> sendMailList = new ArrayList<TSendTime>();

			sendMailList = timDao.SelectToTime(bp);

	        //件数取得ログ
	        Log4JLogout.LogOut(String.format("メール送信 取得件数%d件", sendMailList.size()));

			//送信メール時刻の取得件数分ループする
			for (TSendTime item : sendMailList) {

				TSendMail sendMailDetail = mailDao.selectById(item.getSendMailID());

				List<TSendMailAddress> toList = addDao.SelectToMailID(item.getSendMailID());

		        //メールID
		        Log4JLogout.LogOut("メールID:" + sendMailDetail.getID().toString());
		        //メールタイトル
		        Log4JLogout.LogOut("メールタイトル:" + sendMailDetail.getMailTitel());
		        //送信元
		        Log4JLogout.LogOut("送信元:" + sendMailDetail.getFromMailAddress());

				//メール送信先の取得件数分ループする。
				for (TSendMailAddress toItem : toList ) {
					mailSet = createMailSetting();

			        //送信元
			        Log4JLogout.LogOut("送信先:" + toItem.getToMailAddress());

					//メールタイトル設定
					mailSet.setSubject(formatString(sendMailDetail.getMailTitel()));

					//メール送信者設定
					mailSet.setFromMailAddress(sendMailDetail.getFromMailAddress());

					//メール送信先設定
					mailSet.setToMailAddress(toItem.getToMailAddress());

					//メール本文設定
					mailSet.setMessege(sendMailDetail.getMailDitail());

					//メール送信
					MailTransfer mailTo = new MailTransfer(mailSet);

					mailTo.sendMail();

					TSendLog insLog = new TSendLog();

					//送信先メールアドレス
					insLog.setFromAddress(sendMailDetail.getFromMailAddress());
					//送信元メールアドレス
					insLog.setToAddress(toItem.getToMailAddress());
					//メールタイトル
					insLog.setSendTitel(sendMailDetail.getMailTitel());
					//メール本文
					insLog.setSendDetail(sendMailDetail.getMailDitail());

					//メール送信ログ
					logDao.Insert(insLog);

				}

			}

			if (sendMailList.size() > 0) {
				//送信メールの更新
				timDao.Update(bp);
			}

		} catch(SQLException ex){

			MailSetting errMail = createErrMailSetting();

			errMail.setSubject("自動送信メール　エラー通知");

			//メッセージ設定
			StringBuilder mesBil = new StringBuilder();

			mesBil.append("メール送信時にSQLエラーが起きました。" + JAVA_CR);
			mesBil.append("エラー内容は以下です。確認してください。" + JAVA_CR);
			mesBil.append("------------------" + JAVA_CR);
			mesBil.append(ex.getMessage() + JAVA_CR);
			mesBil.append("" + JAVA_CR);
			mesBil.append(ex.getStackTrace() + JAVA_CR);

			errMail.setMessege(mesBil.toString());

			//標準出力へエラー出力
			Log4JLogout.ErrLogOut(mesBil.toString());

			MailTransfer mailTo = new MailTransfer(errMail);

			mailTo.sendMail();

		} catch(Exception ex){

			MailSetting errMail = createErrMailSetting();

			errMail.setSubject("自動送信メール　エラー通知");

			//メッセージ設定
			StringBuilder mesBil = new StringBuilder();

			mesBil.append("メール送信時にエラーが起きました。" + JAVA_CR);
			mesBil.append("エラー内容は以下です。確認してください。" + JAVA_CR);
			mesBil.append("------------------" + JAVA_CR);
			mesBil.append(ex.getMessage() + JAVA_CR);
			mesBil.append("" + JAVA_CR);
			mesBil.append(ex.getStackTrace() + JAVA_CR);

			errMail.setMessege(mesBil.toString());

			//標準出力へエラー出力
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

		if (args.length != 5) {
			return null;
		}

		BatchParam bp = new BatchParam();

		//送信年
		bp.setYear(args[0]);
		//送信月
		bp.setMonth(args[1]);
		//送信日
		bp.setDay(args[2]);
		//送信時間
		bp.setHour(args[3]);
		//送信分
		bp.setMinute(args[4]);

		return bp;

	}

	/**
	 * @param args
	 */
	private static MailSetting createMailSetting() {

		MailSetting iS = new MailSetting();
		
		SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/DBA");

		//ホスト名設定
		iS.setHostName(XMLpars.getValue("hostname"));
		//ポート番号設定
		iS.setPortNumber(XMLpars.getValue("port"));
		//送信メールID設定
		iS.setId(XMLpars.getValue("sendid"));
		//送信メールパスワード設定
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

		//メール送信ホスト
		//ホスト名設定
		iS.setHostName(XMLpars.getValue("hostname"));
		//ポート番号設定
		iS.setPortNumber(XMLpars.getValue("port"));
		//送信メールID設定
		iS.setId(XMLpars.getValue("sendid"));
		//送信メールパスワード設定
		iS.setPas(XMLpars.getValue("sendpas"));
		//メール送信元アドレス設定
		iS.setFromMailAddress(XMLpars.getValue("errfromaddress"));
		//エラーメールタイトル設定
		iS.setFromName(XMLpars.getValue("errstitel"));
		//メール送信先アドレス設定
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
		
		//年取得
		int ryear = cal.get(Calendar.YEAR);
		//月取得
		int rmonth = cal.get(Calendar.MONTH);
		//日取得
		int rday = cal.get(Calendar.DAY_OF_MONTH);
		//時取得
		int rhour = cal.get(Calendar.HOUR_OF_DAY);
		//分取得
		int rmini = cal.get(Calendar.MINUTE);
		//秒取得
		int rsec = cal.get(Calendar.SECOND);
		
		//年を変換
		iText = iText.replace(R_YEAR, String.valueOf(ryear));
		//月を変換
		iText = iText.replace(R_MONTH, String.valueOf(rmonth));
		//日を変換
		iText = iText.replace(R_DAY, String.valueOf(rday));
		//時を変換
		iText = iText.replace(R_HOUR, String.valueOf(rhour));
		//分を変換
		iText = iText.replace(R_MINI, String.valueOf(rmini));
		//秒を変換
		iText = iText.replace(R_SEC, String.valueOf(rsec));
		
		return iText;
		
	}
	
}
