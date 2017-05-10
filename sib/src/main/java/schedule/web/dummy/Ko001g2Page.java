package schedule.web.dummy;

import java.util.List;

import schedule.pagebace.PageBaceClass;

public class Ko001g2Page extends PageBaceClass {

	private String kaihai;
	private List<String> kaihaiItems;
	private String serviceURLStr;
	private String biko;
	private String updateMes;
	private String dOru;

	public String getKaihai() {
		return kaihai;
	}

	public void setKaihai(String kaihai) {
		this.kaihai = kaihai;
	}

	public List<String> getKaihaiItems() {
		return kaihaiItems;
	}

	public void setKaihaiItems(List<String> kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}

	public String getServiceURLStr() {
		return serviceURLStr;
	}

	public void setServiceURLStr(String serviceURLStr) {
		this.serviceURLStr = serviceURLStr;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

	public String getUpdateMes() {
		return updateMes;
	}

	public void setUpdateMes(String updateMes) {
		this.updateMes = updateMes;
	}

	public String getdOru() {
		return dOru;
	}

	public void setdOru(String dOru) {
		this.dOru = dOru;
	}

	public Class<?> doFinishReturn() {
		return null;
	}

	public Class<?> doPjcodeAdd() {
		return null;
	}

	public Class<?> doFinishUpdate() {
		return null;
	}

	public Class<?> initialize() {
		
		this.setEmployee_id("1");
		this.setEmployee_name("絵巣愛　微意");
		this.setEmployee_mail("bii_esuai@sib.co.jp");
		
		return null;
	}

	public Class<?> prerender() {
		return null;
	}

}
