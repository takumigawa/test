package koroot.entity;

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
	private byte[] resitodata;
	private String biko;
	private Integer kaihai;
	private Integer paymentFlag;
	private String paymentDate;
	private Integer tighteningFlag;
	private String adddate;
	private Integer paymentEmpid;
	private Integer addid;
	private String upddate;
	private String tighteningDate;
	private Integer updid;
	private Integer tighteningEmpid;

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

	@Column("payment_flag")
	public Integer getPaymentFlag() {
		return paymentFlag;
	}

	public void setPaymentFlag(Integer paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	@Column("payment_Date")
	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column("tightening_flag")
	public Integer getTighteningFlag() {
		return tighteningFlag;
	}

	public void setTighteningFlag(Integer tighteningFlag) {
		this.tighteningFlag = tighteningFlag;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	@Column("payment_empID")
	public Integer getPaymentEmpid() {
		return paymentEmpid;
	}

	public void setPaymentEmpid(Integer paymentEmpid) {
		this.paymentEmpid = paymentEmpid;
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

	@Column("tightening_Date")
	public String getTighteningDate() {
		return tighteningDate;
	}

	public void setTighteningDate(String tighteningDate) {
		this.tighteningDate = tighteningDate;
	}

	public Integer getUpdid() {
		return updid;
	}

	public void setUpdid(Integer updid) {
		this.updid = updid;
	}

	@Column("tightening_empID")
	public Integer getTighteningEmpid() {
		return tighteningEmpid;
	}

	public void setTighteningEmpid(Integer tighteningEmpid) {
		this.tighteningEmpid = tighteningEmpid;
	}

}
