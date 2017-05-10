package spd001.entity;

import java.math.BigDecimal;

public class TEmp {

	private Integer empid;
	private Integer empyear;
	private Integer empmonth;
	private String empname;
	private BigDecimal workinghours;
	private Integer attendancedays;
	private Integer Absentdays;
	private Integer useannualdays;
	private Integer nonuseannualdays;
	private Integer dependent;
	private Integer summervacation;
	private String sendmail;
	
	/** */
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	
	/** */
	public Integer getEmpyear() {
		return empyear;
	}
	public void setEmpyear(Integer empyear) {
		this.empyear = empyear;
	}
	
	/** */
	public Integer getEmpmonth() {
		return empmonth;
	}
	public void setEmpmonth(Integer empmonth) {
		this.empmonth = empmonth;
	}
	
	/** */
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	/** */
	public BigDecimal getWorkinghours() {
		return workinghours;
	}
	public void setWorkinghours(BigDecimal workinghours) {
		this.workinghours = workinghours;
	}
	
	/** */
	public Integer getAttendancedays() {
		return attendancedays;
	}
	public void setAttendancedays(Integer attendancedays) {
		this.attendancedays = attendancedays;
	}
	
	/** */
	public Integer getAbsentdays() {
		return Absentdays;
	}
	public void setAbsentdays(Integer absentdays) {
		Absentdays = absentdays;
	}
	
	/** */
	public Integer getUseannualdays() {
		return useannualdays;
	}
	public void setUseannualdays(Integer useannualdays) {
		this.useannualdays = useannualdays;
	}
	
	/** */
	public Integer getNonuseannualdays() {
		return nonuseannualdays;
	}
	public void setNonuseannualdays(Integer nonuseannualdays) {
		this.nonuseannualdays = nonuseannualdays;
	}
	
	/** */
	public Integer getDependent() {
		return dependent;
	}
	public void setDependent(Integer dependent) {
		this.dependent = dependent;
	}
	
	/** */
	public Integer getSummervacation() {
		return summervacation;
	}
	public void setSummervacation(Integer summervacation) {
		this.summervacation = summervacation;
	}
	
	/** */
	public String getSendmail() {
		return sendmail;
	}
	public void setSendmail(String sendmail) {
		this.sendmail = sendmail;
	}
	
}
