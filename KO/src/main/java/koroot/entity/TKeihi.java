package koroot.entity;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;

@Bean(table="t_keihi")
public class TKeihi {

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
	private String kyokaPjlDate;
	private Integer kyokaPjlEmpid;
	private String fukyokaPjlDate;
	private Integer fukyokaPjlEmpid;
	private String kyokaKeiriDate;
	private Integer kyokaKeiriEmpid;
	private String fukyokaKeiriDate;
	private Integer fukyokaKeiriEmpid;
	private Integer karibaraiId;
	private Integer shiharaiFlag;
	private String shiharaiDate;
	private Integer shiharaiEmpid;
	private Integer simeFlag;
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

	@Column("kyoka_pjl_Date")
	public String getKyokaPjlDate() {
		return kyokaPjlDate;
	}

	public void setKyokaPjlDate(String kyokaPjlDate) {
		this.kyokaPjlDate = kyokaPjlDate;
	}

	@Column("kyoka_pjl_empID")
	public Integer getKyokaPjlEmpid() {
		return kyokaPjlEmpid;
	}

	public void setKyokaPjlEmpid(Integer kyokaPjlEmpid) {
		this.kyokaPjlEmpid = kyokaPjlEmpid;
	}

	@Column("fukyoka_pjl_Date")
	public String getFukyokaPjlDate() {
		return fukyokaPjlDate;
	}

	public void setFukyokaPjlDate(String fukyokaPjlDate) {
		this.fukyokaPjlDate = fukyokaPjlDate;
	}

	@Column("fukyoka_pjl_empID")
	public Integer getFukyokaPjlEmpid() {
		return fukyokaPjlEmpid;
	}

	public void setFukyokaPjlEmpid(Integer fukyokaPjlEmpid) {
		this.fukyokaPjlEmpid = fukyokaPjlEmpid;
	}

	@Column("kyoka_keiri_Date")
	public String getKyokaKeiriDate() {
		return kyokaKeiriDate;
	}

	public void setKyokaKeiriDate(String kyokaKeiriDate) {
		this.kyokaKeiriDate = kyokaKeiriDate;
	}

	@Column("kyoka_keiri_empID")
	public Integer getKyokaKeiriEmpid() {
		return kyokaKeiriEmpid;
	}

	public void setKyokaKeiriEmpid(Integer kyokaKeiriEmpid) {
		this.kyokaKeiriEmpid = kyokaKeiriEmpid;
	}

	@Column("fukyoka_keiri_Date")
	public String getFukyokaKeiriDate() {
		return fukyokaKeiriDate;
	}

	public void setFukyokaKeiriDate(String fukyokaKeiriDate) {
		this.fukyokaKeiriDate = fukyokaKeiriDate;
	}

	@Column("fukyoka_keiri_empID")
	public Integer getFukyokaKeiriEmpid() {
		return fukyokaKeiriEmpid;
	}

	public void setFukyokaKeiriEmpid(Integer fukyokaKeiriEmpid) {
		this.fukyokaKeiriEmpid = fukyokaKeiriEmpid;
	}

	@Column("karibarai_ID")
	public Integer getKaribaraiId() {
		return karibaraiId;
	}

	public void setKaribaraiId(Integer karibaraiId) {
		this.karibaraiId = karibaraiId;
	}

	@Column("shiharai_flag")
	public Integer getShiharaiFlag() {
		return shiharaiFlag;
	}

	public void setShiharaiFlag(Integer shiharaiFlag) {
		this.shiharaiFlag = shiharaiFlag;
	}

	@Column("shiharai_Date")
	public String getShiharaiDate() {
		return shiharaiDate;
	}

	public void setShiharaiDate(String shiharaiDate) {
		this.shiharaiDate = shiharaiDate;
	}

	@Column("shiharai_empID")
	public Integer getShiharaiEmpid() {
		return shiharaiEmpid;
	}

	public void setShiharaiEmpid(Integer shiharaiEmpid) {
		this.shiharaiEmpid = shiharaiEmpid;
	}

	@Column("sime_flag")
	public Integer getSimeFlag() {
		return simeFlag;
	}

	public void setSimeFlag(Integer simeFlag) {
		this.simeFlag = simeFlag;
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
