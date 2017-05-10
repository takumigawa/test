package ssm.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_sendmail")
public class TSendmail {

	private Integer id;
	private String mailtitel;
	private String maildetail;
	private String frommailaddress;
	private byte[] atachdfile;
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

	public String getMailtitel() {
		return mailtitel;
	}

	public void setMailtitel(String mailtitel) {
		this.mailtitel = mailtitel;
	}

	public String getMaildetail() {
		return maildetail;
	}

	public void setMaildetail(String maildetail) {
		this.maildetail = maildetail;
	}

	public String getFrommailaddress() {
		return frommailaddress;
	}

	public void setFrommailaddress(String frommailaddress) {
		this.frommailaddress = frommailaddress;
	}

	public byte[] getAtachdfile() {
		return atachdfile;
	}

	public void setAtachdfile(byte[] atachdfile) {
		this.atachdfile = atachdfile;
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
