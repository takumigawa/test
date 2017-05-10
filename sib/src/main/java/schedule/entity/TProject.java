package schedule.entity;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;

@Bean(table="t_project")
public class TProject {

	private String pjCode;
	private String pjName;
	private Integer leaderid;
	private Integer kaihai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	@Column("PJ_CODE")
	public String getPjCode() {
		return pjCode;
	}

	public void setPjCode(String pjCode) {
		this.pjCode = pjCode;
	}

	@Column("PJ_NAME")
	public String getPjName() {
		return pjName;
	}

	public void setPjName(String pjName) {
		this.pjName = pjName;
	}

	public Integer getLeaderid() {
		return leaderid;
	}

	public void setLeaderid(Integer leaderid) {
		this.leaderid = leaderid;
	}

	public Integer getKaihai() {
		return kaihai;
	}

	public void setKaihai(Integer kaihai) {
		this.kaihai = kaihai;
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
