package sibpayadvice.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_emp")
public class TEmp {

	private Integer empid;
	private Integer empyear;
	private Integer empmonth;
	private String empname;
	private Double workinghours;
	private Integer attendancedays;
	private Integer absentdays;
	private Integer useannualdays;
	private Integer nonuseannualdays;
	private Integer dependent;
	private Integer summervacation;
	private String sendmail;
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

	public Double getWorkinghours() {
		return workinghours;
	}

	public void setWorkinghours(Double workinghours) {
		this.workinghours = workinghours;
	}

	public Integer getAttendancedays() {
		return attendancedays;
	}

	public void setAttendancedays(Integer attendancedays) {
		this.attendancedays = attendancedays;
	}

	public Integer getAbsentdays() {
		return absentdays;
	}

	public void setAbsentdays(Integer absentdays) {
		this.absentdays = absentdays;
	}

	public Integer getUseannualdays() {
		return useannualdays;
	}

	public void setUseannualdays(Integer useannualdays) {
		this.useannualdays = useannualdays;
	}

	public Integer getNonuseannualdays() {
		return nonuseannualdays;
	}

	public void setNonuseannualdays(Integer nonuseannualdays) {
		this.nonuseannualdays = nonuseannualdays;
	}

	public Integer getDependent() {
		return dependent;
	}

	public void setDependent(Integer dependent) {
		this.dependent = dependent;
	}

	public Integer getSummervacation() {
		return summervacation;
	}

	public void setSummervacation(Integer summervacation) {
		this.summervacation = summervacation;
	}

	public String getSendmail() {
		return sendmail;
	}

	public void setSendmail(String sendmail) {
		this.sendmail = sendmail;
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
