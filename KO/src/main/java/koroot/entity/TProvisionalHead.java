package koroot.entity;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;

@Bean(table="t_provisional_head")
public class TProvisionalHead {

	private Integer provisionalHeadId;
	private String pjCode;
	private Integer state;
	private Integer kingaku;
	private String karibaraiDate;
	private String detailEntryDate;
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
	private String seisanDate;
	private Integer seisanEmpid;
	private String simeDate;
	private Integer simeEmpid;
	private String biko;
	private Integer kaihai;
	private Integer detailEntryFlag;
	private String detailEntryFlagDate;
	private Integer detailEntryFlagEmpid;
	private Integer paymentFlag;
	private String paymentFlagDate;
	private Integer paymentFlagEmpid;
	private Integer tighteningFlag;
	private String tighteningFlagDate;
	private Integer tighteningFlagEmpid;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	@Column("Provisional_Head_ID")
	public Integer getProvisionalHeadId() {
		return provisionalHeadId;
	}

	public void setProvisionalHeadId(Integer provisionalHeadId) {
		this.provisionalHeadId = provisionalHeadId;
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

	public Integer getKingaku() {
		return kingaku;
	}

	public void setKingaku(Integer kingaku) {
		this.kingaku = kingaku;
	}

	@Column("karibarai_Date")
	public String getKaribaraiDate() {
		return karibaraiDate;
	}

	public void setKaribaraiDate(String karibaraiDate) {
		this.karibaraiDate = karibaraiDate;
	}

	@Column("Detail_entry_Date")
	public String getDetailEntryDate() {
		return detailEntryDate;
	}

	public void setDetailEntryDate(String detailEntryDate) {
		this.detailEntryDate = detailEntryDate;
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

	@Column("Detail_entry_flag")
	public Integer getDetailEntryFlag() {
		return detailEntryFlag;
	}

	public void setDetailEntryFlag(Integer detailEntryFlag) {
		this.detailEntryFlag = detailEntryFlag;
	}

	@Column("Detail_entry_flag_Date")
	public String getDetailEntryFlagDate() {
		return detailEntryFlagDate;
	}

	public void setDetailEntryFlagDate(String detailEntryFlagDate) {
		this.detailEntryFlagDate = detailEntryFlagDate;
	}

	@Column("Detail_entry_flag_empID")
	public Integer getDetailEntryFlagEmpid() {
		return detailEntryFlagEmpid;
	}

	public void setDetailEntryFlagEmpid(Integer detailEntryFlagEmpid) {
		this.detailEntryFlagEmpid = detailEntryFlagEmpid;
	}

	@Column("payment_flag")
	public Integer getPaymentFlag() {
		return paymentFlag;
	}

	public void setPaymentFlag(Integer paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	@Column("payment_flag_Date")
	public String getPaymentFlagDate() {
		return paymentFlagDate;
	}

	public void setPaymentFlagDate(String paymentFlagDate) {
		this.paymentFlagDate = paymentFlagDate;
	}

	@Column("payment_flag_empID")
	public Integer getPaymentFlagEmpid() {
		return paymentFlagEmpid;
	}

	public void setPaymentFlagEmpid(Integer paymentFlagEmpid) {
		this.paymentFlagEmpid = paymentFlagEmpid;
	}

	@Column("tightening_flag")
	public Integer getTighteningFlag() {
		return tighteningFlag;
	}

	public void setTighteningFlag(Integer tighteningFlag) {
		this.tighteningFlag = tighteningFlag;
	}

	@Column("tightening_flag_Date")
	public String getTighteningFlagDate() {
		return tighteningFlagDate;
	}

	public void setTighteningFlagDate(String tighteningFlagDate) {
		this.tighteningFlagDate = tighteningFlagDate;
	}

	@Column("tightening_flag_empID")
	public Integer getTighteningFlagEmpid() {
		return tighteningFlagEmpid;
	}

	public void setTighteningFlagEmpid(Integer tighteningFlagEmpid) {
		this.tighteningFlagEmpid = tighteningFlagEmpid;
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
