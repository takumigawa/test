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
	
	/** �����t�@*/
	public String getEmpmonth() {
		return empmonth;
	}
	public void setEmpmonth(String empmonth) {
		this.empmonth = empmonth;
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
	
	/** �����z�@*/
	public Integer getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}
}
