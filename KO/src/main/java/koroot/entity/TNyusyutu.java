package koroot.entity;

public class TNyusyutu {

	public static final String TABLE = "t_nyusyutu";
	private Integer nyusyutuId;
	public static final String nyusyutuId_COLUMN = "nyusyutu_ID";
	private Integer kozaId;
	public static final String kozaId_COLUMN = "koza_ID";
	private Integer kanjoId;
	public static final String kanjoId_COLUMN = "kanjo_ID";
	private Integer nyusyutuFlg;
	public static final String nyusyutuFlg_COLUMN = "nyusyutu_flg";
	private Integer kinngaku;
	private String nyusyutuDate;
	public static final String nyusyutuDate_COLUMN = "nyusyutu_Date";
	private Integer simeFlg;
	public static final String simeFlg_COLUMN = "sime_flg";
	private String biko;
	private Integer kaihai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	public Integer getNyusyutuId() {
		return nyusyutuId;
	}

	public void setNyusyutuId(Integer nyusyutuId) {
		this.nyusyutuId = nyusyutuId;
	}

	public Integer getKozaId() {
		return kozaId;
	}

	public void setKozaId(Integer kozaId) {
		this.kozaId = kozaId;
	}

	public Integer getKanjoId() {
		return kanjoId;
	}

	public void setKanjoId(Integer kanjoId) {
		this.kanjoId = kanjoId;
	}

	public Integer getNyusyutuFlg() {
		return nyusyutuFlg;
	}

	public void setNyusyutuFlg(Integer nyusyutuFlg) {
		this.nyusyutuFlg = nyusyutuFlg;
	}

	public Integer getKinngaku() {
		return kinngaku;
	}

	public void setKinngaku(Integer kinngaku) {
		this.kinngaku = kinngaku;
	}

	public String getNyusyutuDate() {
		return nyusyutuDate;
	}

	public void setNyusyutuDate(String nyusyutuDate) {
		this.nyusyutuDate = nyusyutuDate;
	}

	public Integer getSimeFlg() {
		return simeFlg;
	}

	public void setSimeFlg(Integer simeFlg) {
		this.simeFlg = simeFlg;
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
