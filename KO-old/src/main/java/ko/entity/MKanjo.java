package ko.entity;

import org.seasar.dao.annotation.tiger.Bean;
//import org.seasar.dao.annotation.tiger.Column;
import org.seasar.dao.annotation.tiger.Column;

@Bean(table="m_kanjo")
public class MKanjo {

	private Integer kanjo_Id;
	private String name;
	private String oderseq;
	private Integer kaisai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	@Column("Kanjo_ID")
	public Integer getKanjo_Id() {
		return kanjo_Id;
	}
	public void setKanjo_Id(Integer kanjo_Id) {
		this.kanjo_Id = kanjo_Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOderseq() {
		return oderseq;
	}
	public void setOderseq(String oderseq) {
		this.oderseq = oderseq;
	}
	public Integer getKaisai() {
		return kaisai;
	}
	public void setKaisai(Integer kaisai) {
		this.kaisai = kaisai;
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
