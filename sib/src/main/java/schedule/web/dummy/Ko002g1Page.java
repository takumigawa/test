package schedule.web.dummy;

import schedule.dto.ListDto;
import schedule.pagebace.PageBaceClass;

public class Ko002g1Page extends PageBaceClass {

	private Integer kaihai;
	private ListDto[] kaihaiItems;

	private Integer sinemp;
	private ListDto[] sinempItems;

	public Integer getKaihai() {
		return kaihai;
	}
	public void setKaihai(Integer kaihai) {
		this.kaihai = kaihai;
	}

	public ListDto[] getKaihaiItems() {
		return kaihaiItems;
	}
	public void setKaihaiItems(ListDto[] kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}

	public Integer getSinemp() {
		return sinemp;
	}
	public void setSinemp(Integer sinemp) {
		this.sinemp = sinemp;
	}

	public ListDto[] getSinempItems() {
		return sinempItems;
	}
	public void setSinempItems(ListDto[] sinempItems) {
		this.sinempItems = sinempItems;
	}
	
	public Class<?> doSrech() {
		return null;
	}

	public Class<?> doSrechCancel() {
		return null;
	}

	public Class<?> doPjcodeAdd() {
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
