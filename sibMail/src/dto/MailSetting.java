package dto;

public class MailSetting {

	//送信先アドレス
	private String toMailAddress;
	public void setToMailAddress(String iAddress){
		toMailAddress = iAddress;
	}
	public String getToMailAddress() {
		return toMailAddress;
	}
	
	//送信メールアドレス
	private String fromMailAddress;
	public void setFromMailAddress(String iFromMailAddress){
		fromMailAddress = iFromMailAddress;
	}
	public String getFromMailAddress() {
		return fromMailAddress;
	}
	
	//送信名
	private String fromName;
	public void setFromName(String iFromName){
		fromName = iFromName;
	}
	public String getFromName() {
		return fromName;
	}
	
	//SMTPホスト名
	private String hostName;
	public void setHostName(String iHostName){
		hostName = iHostName;
	}
	public String getHostName() {
		return hostName;
	}

	//ポ�?ト番号
	private String PortNumber;
	public void setPortNumber(String iPortNumber){
		PortNumber = iPortNumber;
	}
	public String getPortNumber() {
		return PortNumber;
	}
	
	//ユーザーID
	private String id;
	public void setId(String iId){
		id = iId;
	}
	public String getId() {
		return id;
	}
	
	//パスワード
	private String pas;
	public void setPas(String iPas){
		pas = iPas;
	}
	public String getPas() {
		return pas;
	}
	
	//認証フラグ
	private String authFlg;
	public void setAuthFlg(boolean iAuthFlg){
		if (iAuthFlg){
			authFlg = "true";
		} else {
			authFlg = "false";
		}
		
	}
	public boolean getAuthFlg() {
		if ("true".equals(authFlg)) {
			return true;
		} else {
			return false;
		}
	}
	
	//SSL通信フラグ
	private boolean sslFlg;
	public void setSSlFlg(boolean iSslFlg){
		sslFlg = iSslFlg;
	}
	public boolean getSSlFlg() {
		return sslFlg;
	}
	
	//メールタイトル
	private String subject;
	public void setSubject(String iSubject){
		subject = iSubject;
	}
	public String getSubject() {
		return subject;
	}
	
	//メール本�?
	private String messege;
	public void setMessege(String iMessege){
		messege = iMessege;
	}
	public String getMessege() {
		return messege;
	}
	
	//ファイルパス
	private String AttachmentPath;
	public void setAttachmentPath(String iAttachmentPath) {
		AttachmentPath = iAttachmentPath;
	}
	public String getAttachmentPath (){
		return AttachmentPath;
	}
	
}
