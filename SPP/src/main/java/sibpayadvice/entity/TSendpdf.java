package sibpayadvice.entity;

public class TSendpdf {

	public static final String TABLE = "t_sendpdf";
	private Integer empid;
	private Integer sendyear;
	private Integer sendmonth;
	private String sendfile;
	private String sendmail;
	private Integer sendflg;
	private String senddate;

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public Integer getSendyear() {
		return sendyear;
	}

	public void setSendyear(Integer sendyear) {
		this.sendyear = sendyear;
	}

	public Integer getSendmonth() {
		return sendmonth;
	}

	public void setSendmonth(Integer sendmonth) {
		this.sendmonth = sendmonth;
	}

	public String getSendfile() {
		return sendfile;
	}

	public void setSendfile(String sendfile) {
		this.sendfile = sendfile;
	}

	public String getSendmail() {
		return sendmail;
	}

	public void setSendmail(String sendmail) {
		this.sendmail = sendmail;
	}

	public Integer getSendflg() {
		return sendflg;
	}

	public void setSendflg(Integer sendflg) {
		this.sendflg = sendflg;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

}
