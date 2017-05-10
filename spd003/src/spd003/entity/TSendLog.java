package spd003.entity;

public class TSendLog {

	private Integer ID;
	private String SendDate;
	private String SendTitel;
	private String SendDetail;
	private String toaddress;
	private String fromaddress;
	
	/** ID　セッター・ゲッター */
	public void setId(Integer iID){
		this.ID = iID;
	}
	public Integer getID(){
		return ID;
	}
	
	/** 送信年月日　日分　セッター・ゲッター*/
	public void setSendDate(String iSendDate){
		this.SendDate = iSendDate;
	}
	public String getSendDate(){
		return this.SendDate;
	}
	
	/** 送信タイトル　セッター・ゲッター */
	public void setSendTitel(String iSendTitel){
		this.SendTitel = iSendTitel;
	}
	public String getSendTitel(){
		return this.SendTitel;
	}
	
	/** 送信本文 */
	public void setSendDetail(String iSendDetail){
		this.SendDetail = iSendDetail;
	}
	public String getSendDetail(){
		return this.SendDetail;
	}

	/** 送信元アドレス */
	public void setToAddress(String iToAddress){
		this.toaddress = iToAddress;
	}
	public String getToAddress(){
		return this.toaddress;
	}
	
	/** 送信先アドレス */
	public void setFromAddress(String iFromAddress){
		this.fromaddress = iFromAddress;
	}
	public String getFromAddress(){
		return this.fromaddress;
	}
	
}
