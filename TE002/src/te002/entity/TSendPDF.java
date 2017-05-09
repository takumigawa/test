package te002.entity;

public class TSendPDF {
	
	private String empname;
	private String section;
	private String empmonth;
	private String appday;
	private String summary;
	private String sectionFrom;
	private String sectionTo;
	private Integer Money;
	private String expDate;
	private Integer totalMoney;
	
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
	
	/** 請求付　*/
	public String getEmpmonth() {
		return empmonth;
	}
	public void setEmpmonth(String empmonth) {
		this.empmonth = empmonth;
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
	
	/** 請求額　*/
	public Integer getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}
}
