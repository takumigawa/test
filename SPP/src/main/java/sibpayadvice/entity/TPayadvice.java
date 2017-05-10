package sibpayadvice.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_payadvice")
public class TPayadvice {

	private Integer empid;
	private Integer empyear;
	private Integer empmonth;
	private Integer base;
	private Integer syaho;
	private Integer teiki;
	private Integer tuukinn;
	private Integer nontax;
	private Integer kenho;
	private Integer kounenn;
	private Integer koyoho;
	private Integer syotax;
	private Integer citytax;
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

	public Integer getBase() {
		return base;
	}

	public void setBase(Integer base) {
		this.base = base;
	}

	public Integer getSyaho() {
		return syaho;
	}

	public void setSyaho(Integer syaho) {
		this.syaho = syaho;
	}

	public Integer getTeiki() {
		return teiki;
	}

	public void setTeiki(Integer teiki) {
		this.teiki = teiki;
	}

	public Integer getTuukinn() {
		return tuukinn;
	}

	public void setTuukinn(Integer tuukinn) {
		this.tuukinn = tuukinn;
	}

	public Integer getNontax() {
		return nontax;
	}

	public void setNontax(Integer nontax) {
		this.nontax = nontax;
	}

	public Integer getKenho() {
		return kenho;
	}

	public void setKenho(Integer kenho) {
		this.kenho = kenho;
	}

	public Integer getKounenn() {
		return kounenn;
	}

	public void setKounenn(Integer kounenn) {
		this.kounenn = kounenn;
	}

	public Integer getKoyoho() {
		return koyoho;
	}

	public void setKoyoho(Integer koyoho) {
		this.koyoho = koyoho;
	}

	public Integer getSyotax() {
		return syotax;
	}

	public void setSyotax(Integer syotax) {
		this.syotax = syotax;
	}

	public Integer getCitytax() {
		return citytax;
	}

	public void setCitytax(Integer citytax) {
		this.citytax = citytax;
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
