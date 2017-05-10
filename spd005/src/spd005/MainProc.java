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
	
	private static String mailTitel = "電気検診メーター";
	
	private static String mailBody1 = "%s様%s";
	private static String mailBody2 = "%sの電気検診メーター画像を送付します。%s";
	private static String mailBody3 = "%s";
	private static String mailBody4 = "内容を確認してください。%s";
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        //開始ログ　出力
        Log4JLogout.LogOut("SPD005 main Start");
        
        BatchParam bp;
        StringBuilder mesBil;
		
        SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/MAILL");
        
        try    {
        	
			//パラメーターチェック
			bp = createParam(args);
			
			if (bp == null){
				throw new Exception("パラメータ不正");
			}
            
			MailSetting imgMail = createMailSetting(XMLpars);
			
			//サブジェクト作成
			imgMail.setSubject(mailTitel);
			
			//メッセージ設定
			 mesBil = new StringBuilder();
						
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
			Date nowDate = new Date();
				
			mesBil.append(String.format(mailBody1, XMLpars.getValue("sendName"), JAVA_CR));
			mesBil.append(String.format(mailBody3, JAVA_CR));
			mesBil.append(String.format(mailBody2, sdf.format(nowDate.getTime()) ,JAVA_CR));
			mesBil.append(String.format(mailBody3, JAVA_CR));
			mesBil.append(String.format(mailBody4, JAVA_CR));
			
			//メール送信先アドレス
			imgMail.setToMailAddress(XMLpars.getValue("sendMailAddress"));
			
			//撮影画像の添付
			imgMail.setAttachmentPath(bp.getReportPath());
			
			//メッセージ
			imgMail.setMessege(mesBil.toString());
			
			MailTransfer mailTo = new MailTransfer(imgMail);
			
			//メール送信
			mailTo.sendMail();
			
        } catch (Exception e) {

			MailSetting errMail = createErrMailSetting(XMLpars);
			
			errMail.setSubject("電気検診メーター　エラー通知");
			
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
            
            //開始ログ　出力
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
    	
    	//添付ファイルパス
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
