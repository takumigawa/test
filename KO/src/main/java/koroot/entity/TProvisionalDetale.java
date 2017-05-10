package koroot.entity;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;

@Bean(table="t_provisional_detale")
public class TProvisionalDetale {

	private Integer provisionalDetailId;
	private Integer provisionalHeadId;
	private Integer kanjoId;
	private Integer kingaku;
	private byte[] resitodata;
	private String biko;
	private String siyoudate;
	private Integer kaihai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	@Column("Provisional_Detail_ID")
	public Integer getProvisionalDetailId() {
		return provisionalDetailId;
	}

	public void setProvisionalDetailId(Integer provisionalDetailId) {
		this.provisionalDetailId = provisionalDetailId;
	}

	@Column("Provisional_Head_ID")
	public Integer getProvisionalHeadId() {
		return provisionalHeadId;
	}

	public void setProvisionalHeadId(Integer provisionalHeadId) {
		this.provisionalHeadId = provisionalHeadId;
	}

	@Column("Kanjo_ID")
	public Integer getKanjoId() {
		return kanjoId;
	}

	public void setKanjoId(Integer kanjoId) {
		this.kanjoId = kanjoId;
	}

	public Integer getKingaku() {
		return kingaku;
	}

	public void setKingaku(Integer kingaku) {
		this.kingaku = kingaku;
	}

	public byte[] getResitodata() {
		return resitodata;
	}

	public void setResitodata(byte[] resitodata) {
		this.resitodata = resitodata;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

	public String getSiyoudate() {
		return siyoudate;
	}

	public void setSiyoudate(String siyoudate) {
		this.siyoudate = siyoudate;
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
