/**
 * 
 */
package spd003.entity;

/**
 * @author ����
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
	
	/** ID �Z�b�^�[�E�Q�b�^�[ */
	public void setID(Integer iID){
		this.ID = iID;
	}
	public Integer getID(){
		return this.ID;
	}
	
	/** ���M�惁�[���A�h���X�@�Z�b�^�[�E�Q�b�^�[ */
	public void setToMailAddress(String iToMailAddress){
		this.ToMailAddress = iToMailAddress;
	}
	public String getToMailAddress(){
		return this.ToMailAddress;
	}
	
	/** ���[��ID�@�Z�b�^�[�E�Q�b�^�[ */
	public void setSendMailID(Integer iSendMailID){
		this.SendMailID = iSendMailID;
	}
	public Integer getSendMailID(){
		return this.SendMailID;
	}
	
	/** �V�K�X�V��ID�@�Z�b�^�[�E�Q�b�^�[ */
	public void setAddID(Integer iAddID){
		this.ADddID = iAddID;
	}
	public Integer getAddID(){
		return this.ADddID;
	}
	
	/** �V�K�X�V�����@�Z�b�^�[�E�Q�b�^�[ */
	public void setAddDate(String iAddDate){
		this.AddDate = iAddDate;
	}
	public String getAddDate(){
		return this.AddDate;
	}
	
	/** �ŐV�X�V��ID�@�Z�b�^�[�E�Q�b�^�[ */
	public void setUpdID(Integer iUpdID){
		this.UpdID = iUpdID;
	}
	public Integer getUpdID(){
		return this.UpdID;
	}
	
	/** �ŐV�X�V���� �@�Z�b�^�[�E�Q�b�^�[ */
	public void setUpdDate(String iUpdDate){
		this.UpdDate = iUpdDate;
	}
	public String getUpdDate(){
		return this.UpdDate;
	}
	
}
