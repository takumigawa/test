package te001.entity;

public class TSendPDF {
	
	private Integer empid;
	private Integer empyear;
	private Integer empmonth;
	private Integer branchno;
	private String empname;
	private String expDate;
	private String appday;
	private String section;
	private String summary;
	private String sectionFrom;
	private String sectionTo;
	private Integer Money;
	
	/** �����t�@*/
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	/** �����t�@*/
	public Integer getEmpyear() {
		return empyear;
	}
	public void setEmpyear(Integer empyear) {
		this.empyear = empyear;
	}
	/** �����t�@*/
	public Integer getBranchno() {
		return branchno;
	}
	public void setBranchno(Integer branchno) {
		this.branchno = branchno;
	}
	/** �����t�@*/
	public Integer getEmpmonth() {
		return empmonth;
	}
	public void setEmpmonth(Integer empmonth) {
		this.empmonth = empmonth;
	}
	
	/** ���O�@*/
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	/** �����@*/
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

	/** �������@*/
	public String getAppday() {
		return appday;
	}
	public void setAppday(String appday) {
		this.appday = appday;
	}
	
	/** �E�v�@*/
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/** ��ԁi�J�n�j�@*/
	public String getSectionFrom() {
		return sectionFrom;
	}
	public void setSectionFrom(String sectionFrom) {
		this.sectionFrom = sectionFrom;
	}
	
	/** ��ԁi�I���j�@*/
	public String getSectionTo() {
		return sectionTo;
	}
	public void setSectionTo(String sectionTo) {
		this.sectionTo = sectionTo;
	}
	
	/** �����z�@*/
	public Integer getMoney() {
		return Money;
	}
	public void setMoney(Integer Money) {
		this.Money = Money;
	}
	
	/** �����z�@*/
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
}
