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

	/** ID�@�Z�b�^�[�E�Q�b�^�[*/
	public void setID(Integer iID){
		this.ID = iID;
	}
	public Integer getID(){
		return this.ID;
	}

	/** ���[���^�C�g���@�Z�b�^�[�E�Q�b�^�[ */
	public void setMailTitel(String iMailTitel){
		this.MailTitel = iMailTitel;
	}
	public String getMailTitel(){
		return this.MailTitel;
	}

	/** ���[���{���@�Z�b�^�[�E�Q�b�^�[*/
	public void setMailDitail(String iMailDitail){
		this.MailDitail = iMailDitail;
	}
	public String getMailDitail(){
		return this.MailDitail;
	}

	/** ���[�����M���A�h���X�@�Z�b�^�[�E�Q�b�^�[*/
	public void setFromMailAddress(String iFromMailAddress){
		this.FromMailAddress = iFromMailAddress;
	}
	public String getFromMailAddress(){
		return this.FromMailAddress;
	}

	/** �Y�t�t�@�C�� */

	/** �V�K�X�VID�@�Z�b�^�[�E�Q�b�^�[ */
	public void setAddID(Integer iAddID){
		this.AddID = iAddID;
	}
	public Integer getAddID(){
		return this.AddID;
	}

	/** �V�K�X�V�����@�Z�b�^�[�E�Q�b�^�[ */
	public void setAddDate(String iAddDate){
		this.AddDate = iAddDate;
	}
	public String getAddDate(){
		return this.AddDate;
	}

	/** �ŏI�X�V��ID �Z�b�^�[�E�Q�b�^�[ */
	public void setUpdID(Integer iUpdID){
		this.UpdID = iUpdID;
	}
	public Integer getUpdID(){
		return this.UpdID;
	}

	/** �ŏI�X�V���� �Z�b�^�[�E�Q�b�^�[ */
	public void setUpdDate(String iUpdDate){
		this.UpdDate = iUpdDate;
	}
	public String getUpdDate(){
		return this.UpdDate;
	}

}

