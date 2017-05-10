package sibpayadvice.web.sendpayadvice;

import sibpayadvice.dao.TSendymDao;
import sibpayadvice.entity.TSendym;

//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;

public class SendpayadvicePage {

//	private static String constParam = "sudo bash /bin/sib/spd001/bin/ki2.bash %s %s";
	
	private String userID;
	private String userPas;
	private String sendYear;
	private String sendMonth;
	private String resultFlg;
	private String processMes;
	
	public TSendymDao ymDao;

	/**　送信ユーザーID　セッター・ゲッター　*/
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**　送信ユーザーPAS　セッター・ゲッター　*/
	public String getUserPas() {
		return userPas;
	}
	public void setUserPas(String userPas) {
		this.userPas = userPas;
	}

	/**　送信年度　セッター・ゲッター　*/
	public String getSendYear() {
		return sendYear;
	}
	public void setSendYear(String sendYear) {
		this.sendYear = sendYear;
	}

	/**　送信月度　セッター・ゲッター　*/
	public String getSendMonth() {
		return sendMonth;
	}
	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}

	/**　結果フラグ　セッター・ゲッター　*/
	public String getResultFlg() {
		return resultFlg;
	}
	public void setResultFlg(String resultFlg) {
		this.resultFlg = resultFlg;
	}

	/**　結果メッセージ　セッター・ゲッター　*/
	public String getProcessMes() {
		return processMes;
	}
	public void setProcessMes(String processMes) {
		this.processMes = processMes;
	}
	
	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {

		try{
			
			TSendym ety_ym = ymDao.selectById(Integer.parseInt(sendYear), Integer.parseInt(sendMonth));
			
			if (ety_ym == null) {
			
				ety_ym = new TSendym();
				
				ety_ym.setSendyear(Integer.parseInt(sendYear));
				ety_ym.setSendmonth(Integer.parseInt(sendMonth));
				ety_ym.setSendflg(0);
				
				ymDao.insert(ety_ym);
				
			} else { 
				
				ety_ym.setSendflg(0);
				
				ymDao.update(ety_ym);
				
			}
			
			
//			StringBuilder tmpSbil = new StringBuilder();
//	        
//			String Param = String.format(constParam, sendYear, sendMonth);
//			
//			Process process = Runtime.getRuntime().exec(Param);
//			process.waitFor();
//			
//	        // 実行結果のストリームを取得        
//	        InputStream is = process.getInputStream();
//	        InputStreamReader isr = new InputStreamReader(is);
//	        BufferedReader br = new BufferedReader(isr);
//	        
//	        // 実行結果を出力        
//	        String line = "";
//	        while ((line = br.readLine()) != null) {            
//	        	tmpSbil.append(line + "\n\r");
//	        }         
//	        
//	        br.close();
//	        isr.close();
//	        is.close();
//	        process.destroy();
//			
//			if (process.exitValue() == 90 ) {
//				resultFlg = "false";
//			} else {
//				resultFlg = "true";
//			}
//			
		} catch(Exception ex) {
			
			resultFlg = "false";
					
		}
		
		return null;
	}


}
