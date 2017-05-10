package ste.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_tighten")
public class TTighten {

	private Integer empid;
	private Integer empyear;
	private Integer empmonth;
	private String empname;
	private String section;
	private String expdate;
	private Integer tightenflg;
	private Integer approvalflg;
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

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public Integer getTightenflg() {
		return tightenflg;
	}

	public void setTightenflg(Integer tightenflg) {
		this.tightenflg = tightenflg;
	}

	public Integer getApprovalflg() {
		return approvalflg;
	}

	public void setApprovalflg(Integer approvalflg) {
		this.approvalflg = approvalflg;
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
