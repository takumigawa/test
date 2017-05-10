package spd001.entity;

public class TSendPdf {

	private Integer empid;
	private Integer sendyear;
	private Integer sendmonth;
	private String sendfile;
	private String sendmail;
	private Integer sendflg;
	private String senddate;
	
	/** 社員ID　セッター・ゲッター */
	public void setEmpid(Integer empId){
		this.empid = empId;
	}
	public Integer getEmpid(){
		return this.empid;
	}
	
	/** 送信年　セッター・ゲッター　*/
	public void setSendyear(Integer Sendyear){
		this.sendyear = Sendyear;
	}
	public Integer getSendyear(){
		return this.sendyear;
	}
	
	/** 送信月　セッター・ゲッター　*/
	public void setSendmonth(Integer Sendmonth){
		this.sendmonth = Sendmonth;
	}
	public Integer getSendmonth(){
		return this.sendmonth;
	}
	
	/** 送信ファイル　セッター・ゲッター　*/
	public void setSendfile(String Sendfile){
		this.sendfile = Sendfile;
	}
	public String getSendfile() {
		return this.sendfile;
	}
	
	/** 送信メールアドレス　セッター・ゲッター　*/
	public void setSendmail(String Sendmail) {
		this.sendmail = Sendmail;
	}
	public String getSendmail() {
		return this.sendmail;
	}
	
	/** 送信フラグ　セッター・ゲッター　*/
	public void setSendflg(Integer Sendflg) {
		this.sendflg = Sendflg;
	}
	public Integer getSendflg() {
		return this.sendflg;
	}
	
	/** 送信日付　セッター・ゲッター　*/
	public void setSenddate(String Senddate) {
		this.senddate = Senddate;
	}
	public String getSenddate() {
		return this.senddate;
	}
}
