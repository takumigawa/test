package serviceadapter.entity;

public class MAdapter {

	public static final String TABLE = "m_adapter";
	private String servicetype;
	private String serviceurl;
	private String servicetitle;
	private Integer orderseq;
	private Integer kaihai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getServiceurl() {
		return serviceurl;
	}

	public void setServiceurl(String serviceurl) {
		this.serviceurl = serviceurl;
	}

	public String getServicetitle() {
		return servicetitle;
	}

	public void setServicetitle(String servicetitle) {
		this.servicetitle = servicetitle;
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
