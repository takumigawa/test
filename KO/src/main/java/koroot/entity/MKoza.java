package koroot.entity;

public class MKoza {

	public static final String TABLE = "m_koza";
	private Integer kozaId;
	public static final String kozaId_COLUMN = "KOZA_ID";
	private String pjCode;
	public static final String pjCode_COLUMN = "PJ_CODE";
	private Integer kozaType;
	public static final String kozaType_COLUMN = "KOZA_TYPE";
	private Integer HKingaku;
	public static final String HKingaku_COLUMN = "H_Kingaku";
	private String biko;
	private Integer kaihai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	public Integer getKozaId() {
		return kozaId;
	}

	public void setKozaId(Integer kozaId) {
		this.kozaId = kozaId;
	}

	public String getPjCode() {
		return pjCode;
	}

	public void setPjCode(String pjCode) {
		this.pjCode = pjCode;
	}

	public Integer getKozaType() {
		return kozaType;
	}

	public void setKozaType(Integer kozaType) {
		this.kozaType = kozaType;
	}

	public Integer getHKingaku() {
		return HKingaku;
	}

	public void setHKingaku(Integer hKingaku) {
		HKingaku = hKingaku;
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
