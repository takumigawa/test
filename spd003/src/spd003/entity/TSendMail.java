package spd003.entity;

public class TSendMail {

	private Integer ID;
	private String MailTitel;
	private String MailDitail;
	private String FromMailAddress;
	//private String AtachFile;
	private Integer AddID;
	private String AddDate;
	private Integer UpdID;
	private String UpdDate;

	/** ID　セッター・ゲッター*/
	public void setID(Integer iID){
		this.ID = iID;
	}
	public Integer getID(){
		return this.ID;
	}

	/** メールタイトル　セッター・ゲッター */
	public void setMailTitel(String iMailTitel){
		this.MailTitel = iMailTitel;
	}
	public String getMailTitel(){
		return this.MailTitel;
	}

	/** メール本文　セッター・ゲッター*/
	public void setMailDitail(String iMailDitail){
		this.MailDitail = iMailDitail;
	}
	public String getMailDitail(){
		return this.MailDitail;
	}

	/** メール送信元アドレス　セッター・ゲッター*/
	public void setFromMailAddress(String iFromMailAddress){
		this.FromMailAddress = iFromMailAddress;
	}
	public String getFromMailAddress(){
		return this.FromMailAddress;
	}

	/** 添付ファイル */

	/** 新規更新ID　セッター・ゲッター */
	public void setAddID(Integer iAddID){
		this.AddID = iAddID;
	}
	public Integer getAddID(){
		return this.AddID;
	}

	/** 新規更新日時　セッター・ゲッター */
	public void setAddDate(String iAddDate){
		this.AddDate = iAddDate;
	}
	public String getAddDate(){
		return this.AddDate;
	}

	/** 最終更新者ID セッター・ゲッター */
	public void setUpdID(Integer iUpdID){
		this.UpdID = iUpdID;
	}
	public Integer getUpdID(){
		return this.UpdID;
	}

	/** 最終更新日時 セッター・ゲッター */
	public void setUpdDate(String iUpdDate){
		this.UpdDate = iUpdDate;
	}
	public String getUpdDate(){
		return this.UpdDate;
	}

}

