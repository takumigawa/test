package sibpayadvice.web.sendpayadvice;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

public class DrvpayadvicePage {

	private String userID;
	private String userPas;
	@SubapplicationScope
	private String sendYear;
	@SubapplicationScope
	private String sendMonth;
	private String resultFlg;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPas() {
		return userPas;
	}

	public void setUserPas(String userPas) {
		this.userPas = userPas;
	}

	public String getSendYear() {
		return sendYear;
	}

	public void setSendYear(String sendYear) {
		this.sendYear = sendYear;
	}

	public String getSendMonth() {
		return sendMonth;
	}

	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}

	public String getResultFlg() {
		return resultFlg;
	}

	public void setResultFlg(String resultFlg) {
		this.resultFlg = resultFlg;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		sendYear = "2012";
		sendMonth = "10";
		
		return SendpayadvicePage.class;
	}

}
