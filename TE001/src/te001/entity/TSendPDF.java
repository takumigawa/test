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
	
	/** 請求付　*/
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	/** 請求付　*/
	public Integer getEmpyear() {
		return empyear;
	}
	public void setEmpyear(Integer empyear) {
		this.empyear = empyear;
	}
	/** 請求付　*/
	public Integer getBranchno() {
		return branchno;
	}
	public void setBranchno(Integer branchno) {
		this.branchno = branchno;
	}
	/** 請求付　*/
	public Integer getEmpmonth() {
		return empmonth;
	}
	public void setEmpmonth(Integer empmonth) {
		this.empmonth = empmonth;
	}
	
	/** 名前　*/
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	/** 所属　*/
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

	/** 請求日　*/
	public String getAppday() {
		return appday;
	}
	public void setAppday(String appday) {
		this.appday = appday;
	}
	
	/** 摘要　*/
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/** 区間（開始）　*/
	public String getSectionFrom() {
		return sectionFrom;
	}
	public void setSectionFrom(String sectionFrom) {
		this.sectionFrom = sectionFrom;
	}
	
	/** 区間（終了）　*/
	public String getSectionTo() {
		return sectionTo;
	}
	public void setSectionTo(String sectionTo) {
		this.sectionTo = sectionTo;
	}
	
	/** 請求額　*/
	public Integer getMoney() {
		return Money;
	}
	public void setMoney(Integer Money) {
		this.Money = Money;
	}
	
	/** 請求額　*/
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
}
