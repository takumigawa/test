package ko.entity;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;

@Bean(table="t_koguti")
public class TKoguti {

	private Integer seikyuId;
	private String pjCode;
	private Integer state;
	private Integer kanjoId;
	private Integer kingaku;
	private String siyouDate;
	private String seikyuDate;
	private Integer seikyuEmpid;
	private String torikesiDate;
	private Integer torikesiEmpid;
	private String kyokaDate;
	private Integer kyokaEmpid;
	private String fukyokaDate;
	private Integer fukyokaEmpid;
	private String seisanDate;
	private Integer seisanEmpid;
	private String simeDate;
	private Integer simeEmpid;
	private byte[] resitodata;
	private String biko;
	private Integer kaihai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	@Column("seikyu_ID")
	public Integer getSeikyuId() {
		return seikyuId;
	}

	public void setSeikyuId(Integer seikyuId) {
		this.seikyuId = seikyuId;
	}

	@Column("PJ_CODE")
	public String getPjCode() {
		return pjCode;
	}

	public void setPjCode(String pjCode) {
		this.pjCode = pjCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	@Column("siyou_Date")
	public String getSiyouDate() {
		return siyouDate;
	}

	public void setSiyouDate(String siyouDate) {
		this.siyouDate = siyouDate;
	}

	@Column("seikyu_Date")
	public String getSeikyuDate() {
		return seikyuDate;
	}

	public void setSeikyuDate(String seikyuDate) {
		this.seikyuDate = seikyuDate;
	}

	@Column("seikyu_empID")
	public Integer getSeikyuEmpid() {
		return seikyuEmpid;
	}

	public void setSeikyuEmpid(Integer seikyuEmpid) {
		this.seikyuEmpid = seikyuEmpid;
	}

	@Column("torikesi_Date")
	public String getTorikesiDate() {
		return torikesiDate;
	}

	public void setTorikesiDate(String torikesiDate) {
		this.torikesiDate = torikesiDate;
	}

	@Column("torikesi_empID")
	public Integer getTorikesiEmpid() {
		return torikesiEmpid;
	}

	public void setTorikesiEmpid(Integer torikesiEmpid) {
		this.torikesiEmpid = torikesiEmpid;
	}

	@Column("kyoka_Date")
	public String getKyokaDate() {
		return kyokaDate;
	}

	public void setKyokaDate(String kyokaDate) {
		this.kyokaDate = kyokaDate;
	}

	@Column("kyoka_empID")
	public Integer getKyokaEmpid() {
		return kyokaEmpid;
	}

	public void setKyokaEmpid(Integer kyokaEmpid) {
		this.kyokaEmpid = kyokaEmpid;
	}

	@Column("fukyoka_Date")
	public String getFukyokaDate() {
		return fukyokaDate;
	}

	public void setFukyokaDate(String fukyokaDate) {
		this.fukyokaDate = fukyokaDate;
	}

	@Column("fukyoka_empID")
	public Integer getFukyokaEmpid() {
		return fukyokaEmpid;
	}

	public void setFukyokaEmpid(Integer fukyokaEmpid) {
		this.fukyokaEmpid = fukyokaEmpid;
	}

	@Column("seisan_Date")
	public String getSeisanDate() {
		return seisanDate;
	}

	public void setSeisanDate(String seisanDate) {
		this.seisanDate = seisanDate;
	}

	@Column("seisan_empID")
	public Integer getSeisanEmpid() {
		return seisanEmpid;
	}

	public void setSeisanEmpid(Integer seisanEmpid) {
		this.seisanEmpid = seisanEmpid;
	}

	@Column("sime_Date")
	public String getSimeDate() {
		return simeDate;
	}

	public void setSimeDate(String simeDate) {
		this.simeDate = simeDate;
	}

	@Column("sime_empID")
	public Integer getSimeEmpid() {
		return simeEmpid;
	}

	public void setSimeEmpid(Integer simeEmpid) {
		this.simeEmpid = simeEmpid;
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
