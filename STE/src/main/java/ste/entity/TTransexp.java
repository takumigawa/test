package ste.entity;

import java.io.Serializable;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_transexp")
public class TTransexp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer empid;
	private Integer empyear;
	private Integer empmonth;
	private Integer branchno;
	private String applicationdate;
	private String summary;
	private String sectionfrom;
	private String sectionto;
	private Integer money;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public Integer getEmpyear() {
		return empyear;
	}

	public void setEmpyear(Integer empyear) {
		this.empyear = empyear;
	}

	public Integer getEmpmonth() {
		return empmonth;
	}

	public void setEmpmonth(Integer empmonth) {
		this.empmonth = empmonth;
	}

	public Integer getBranchno() {
		return branchno;
	}

	public void setBranchno(Integer branchno) {
		this.branchno = branchno;
	}

	public String getApplicationdate() {
		return applicationdate;
	}

	public void setApplicationdate(String applicationdate) {
		this.applicationdate = applicationdate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSectionfrom() {
		return sectionfrom;
	}

	public void setSectionfrom(String sectionfrom) {
		this.sectionfrom = sectionfrom;
	}

	public String getSectionto() {
		return sectionto;
	}

	public void setSectionto(String sectionto) {
		this.sectionto = sectionto;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	public Integer getAddid() {
		return addid;
	}

	public void setAddid(Integer addid) {
		this.addid = addid;
	}

	public String getUpddate() {
		return upddate;
	}

	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}

	public Integer getUpdid() {
		return updid;
	}

	public void setUpdid(Integer updid) {
		this.updid = updid;
	}

}
