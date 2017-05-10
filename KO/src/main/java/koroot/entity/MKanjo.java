package koroot.entity;

public class MKanjo {

	public static final String TABLE = "m_kanjo";
	private Integer kanjoId;
	public static final String kanjoId_COLUMN = "Kanjo_ID";
	private String name;
	private Integer orderseq;
	private Integer kaihai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	public Integer getKanjoId() {
		return kanjoId;
	}

	public void setKanjoId(Integer kanjoId) {
		this.kanjoId = kanjoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderseq() {
		return orderseq;
	}

	public void setOrderseq(Integer orderseq) {
		this.orderseq = orderseq;
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
