package spd003.entity;

public class TSendLog {

	private Integer ID;
	private String SendDate;
	private String SendTitel;
	private String SendDetail;
	private String toaddress;
	private String fromaddress;
	
	/** ID�@�Z�b�^�[�E�Q�b�^�[ */
	public void setId(Integer iID){
		this.ID = iID;
	}
	public Integer getID(){
		return ID;
	}
	
	/** ���M�N�����@�����@�Z�b�^�[�E�Q�b�^�[*/
	public void setSendDate(String iSendDate){
		this.SendDate = iSendDate;
	}
	public String getSendDate(){
		return this.SendDate;
	}
	
	/** ���M�^�C�g���@�Z�b�^�[�E�Q�b�^�[ */
	public void setSendTitel(String iSendTitel){
		this.SendTitel = iSendTitel;
	}
	public String getSendTitel(){
		return this.SendTitel;
	}
	
	/** ���M�{�� */
	public void setSendDetail(String iSendDetail){
		this.SendDetail = iSendDetail;
	}
	public String getSendDetail(){
		return this.SendDetail;
	}

	/** ���M���A�h���X */
	public void setToAddress(String iToAddress){
		this.toaddress = iToAddress;
	}
	public String getToAddress(){
		return this.toaddress;
	}
	
	/** ���M��A�h���X */
	public void setFromAddress(String iFromAddress){
		this.fromaddress = iFromAddress;
	}
	public String getFromAddress(){
		return this.fromaddress;
	}
	
}
