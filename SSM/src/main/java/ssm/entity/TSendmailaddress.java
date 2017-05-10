package ssm.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_sendmailaddress")
public class TSendmailaddress {

	private Integer id;
	private Integer sendmailid;
	private String tomailaddress;
	private Integer addid;
	private String adddate;
	private Integer updid;
	private String upddate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSendmailid() {
		return sendmailid;
	}

	public void setSendmailid(Integer sendmailid) {
		this.sendmailid = sendmailid;
	}

	public String getTomailaddress() {
		return tomailaddress;
	}

	public void setTomailaddress(String tomailaddress) {
		this.tomailaddress = tomailaddress;
	}

	public Integer getAddid() {
		return addid;
	}

	public void setAddid(Integer addid) {
		this.addid = addid;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	public Integer getUpdid() {
		return updid;
	}

	public void setUpdid(Integer updid) {
		this.updid = updid;
	}

	public String getUpddate() {
		return upddate;
	}

	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}

}
