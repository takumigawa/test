package spd001.entity;

public class TSendPdf {

	private Integer empid;
	private Integer sendyear;
	private Integer sendmonth;
	private String sendfile;
	private String sendmail;
	private Integer sendflg;
	private String senddate;
	
	/** �Ј�ID�@�Z�b�^�[�E�Q�b�^�[ */
	public void setEmpid(Integer empId){
		this.empid = empId;
	}
	public Integer getEmpid(){
		return this.empid;
	}
	
	/** ���M�N�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setSendyear(Integer Sendyear){
		this.sendyear = Sendyear;
	}
	public Integer getSendyear(){
		return this.sendyear;
	}
	
	/** ���M���@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setSendmonth(Integer Sendmonth){
		this.sendmonth = Sendmonth;
	}
	public Integer getSendmonth(){
		return this.sendmonth;
	}
	
	/** ���M�t�@�C���@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setSendfile(String Sendfile){
		this.sendfile = Sendfile;
	}
	public String getSendfile() {
		return this.sendfile;
	}
	
	/** ���M���[���A�h���X�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setSendmail(String Sendmail) {
		this.sendmail = Sendmail;
	}
	public String getSendmail() {
		return this.sendmail;
	}
	
	/** ���M�t���O�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setSendflg(Integer Sendflg) {
		this.sendflg = Sendflg;
	}
	public Integer getSendflg() {
		return this.sendflg;
	}
	
	/** ���M���t�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setSenddate(String Senddate) {
		this.senddate = Senddate;
	}
	public String getSenddate() {
		return this.senddate;
	}
}
