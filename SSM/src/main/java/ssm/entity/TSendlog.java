package ssm.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_sendlog")
public class TSendlog {

	private Integer id;
	private String senddate;
	private String sendtitel;
	private String senddetail;
	private String toaddress;
	private String fromaddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	public String getSendtitel() {
		return sendtitel;
	}

	public void setSendtitel(String sendtitel) {
		this.sendtitel = sendtitel;
	}

	public String getSenddetail() {
		return senddetail;
	}

	public void setSenddetail(String senddetail) {
		this.senddetail = senddetail;
	}

	public String getToaddress() {
		return toaddress;
	}

	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}

	public String getFromaddress() {
		return fromaddress;
	}

	public void setFromaddress(String fromaddress) {
		this.fromaddress = fromaddress;
	}

}
