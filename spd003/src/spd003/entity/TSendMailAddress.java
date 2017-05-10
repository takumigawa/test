/**
 * 
 */
package spd003.entity;

/**
 * @author 匠川
 *
 */
public class TSendMailAddress {

	private Integer ID;
	private String ToMailAddress;
	private Integer SendMailID;
	private Integer ADddID;
	private String AddDate;
	private Integer UpdID;
	private String UpdDate;
	
	/** ID セッター・ゲッター */
	public void setID(Integer iID){
		this.ID = iID;
	}
	public Integer getID(){
		return this.ID;
	}
	
	/** 送信先メールアドレス　セッター・ゲッター */
	public void setToMailAddress(String iToMailAddress){
		this.ToMailAddress = iToMailAddress;
	}
	public String getToMailAddress(){
		return this.ToMailAddress;
	}
	
	/** メールID　セッター・ゲッター */
	public void setSendMailID(Integer iSendMailID){
		this.SendMailID = iSendMailID;
	}
	public Integer getSendMailID(){
		return this.SendMailID;
	}
	
	/** 新規更新者ID　セッター・ゲッター */
	public void setAddID(Integer iAddID){
		this.ADddID = iAddID;
	}
	public Integer getAddID(){
		return this.ADddID;
	}
	
	/** 新規更新日時　セッター・ゲッター */
	public void setAddDate(String iAddDate){
		this.AddDate = iAddDate;
	}
	public String getAddDate(){
		return this.AddDate;
	}
	
	/** 最新更新者ID　セッター・ゲッター */
	public void setUpdID(Integer iUpdID){
		this.UpdID = iUpdID;
	}
	public Integer getUpdID(){
		return this.UpdID;
	}
	
	/** 最新更新日時 　セッター・ゲッター */
	public void setUpdDate(String iUpdDate){
		this.UpdDate = iUpdDate;
	}
	public String getUpdDate(){
		return this.UpdDate;
	}
	
}
