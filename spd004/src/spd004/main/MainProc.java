package spd004.main;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import dto.MailSetting;
import sDBAccess.SibDBAccess;
import sDBAccess.SibDBAccessSetting;
import service.MailTransfer;
import spd004.dao.TSettingDao;
import spd004.dto.BatchParam;
import spd004.entity.TSetting;

public class MainProc {

	private static String JAVA_CR = "\r";
	
	private static String mailTitel = "送信済　給与明細";
	
	private static String mailBody1 = "%s様%s";
	private static String mailBody2 = "%sに送信したすべての給与明細を送付します。%s";
	private static String mailBody3 = "%s";
	private static String mailBody4 = "内容を確認してください。%s";
	
    /**
     * @param args
     */
    public static void main(String[] args) {

        BatchParam bp;
        StringBuilder mesBil;
        
		SibDBAccessSetting DBASet = new SibDBAccessSetting();
		
		//DB接続設定
		DBASet.setID("user1");
		DBASet.setPass("sib1962");
		DBASet.setSchema("sibpayadvice");
		DBASet.setURL("133.242.134.145");
		
		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);
		
        try    {
        	
			//パラメーターチェック
			bp = createParam(args);
			
			if (bp == null){
				throw new Exception("パラメータ不正");
			}
            
			MailSetting pdfMail = createMailSetting();
			
			//サブジェクト作成
			pdfMail.setSubject(mailTitel);
			
			//メッセージ設定
			 mesBil = new StringBuilder();
						
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
			Date nowDate = new Date();
			
			TSettingDao setDao = new TSettingDao();
			
			TSetting ety_set = setDao.Select();
				
			mesBil.append(String.format(mailBody1, ety_set.getEmpname(), JAVA_CR));
			mesBil.append(String.format(mailBody3, JAVA_CR));
			mesBil.append(String.format(mailBody2, sdf.format(nowDate.getTime()) ,JAVA_CR));
			mesBil.append(String.format(mailBody3, JAVA_CR));
			mesBil.append(String.format(mailBody4, JAVA_CR));
			
			//メール送信先アドレス
			pdfMail.setToMailAddress(ety_set.getEmpmail());
			
			//給与明細PDFの添付
			pdfMail.setAttachmentPath(bp.getReportPath());
			
			//メッセージ
			pdfMail.setMessege(mesBil.toString());
			
			MailTransfer mailTo = new MailTransfer(pdfMail);
			
			//メール送信
			mailTo.sendMail();
			
        } catch (Exception e) {

			MailSetting errMail = createErrMailSetting();
			
			errMail.setSubject("自動送信メール　エラー通知");
			
			//メッセージ設定
			mesBil = new StringBuilder();
						
			mesBil.append("メール送信時にエラーが起きました。" + JAVA_CR);
			mesBil.append("エラー内容は以下です。確認してください。" + JAVA_CR);
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
    	
    	//送信年
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


