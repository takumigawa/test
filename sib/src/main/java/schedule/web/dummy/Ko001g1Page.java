package schedule.web.dummy;

import schedule.dto.ListDto;
import schedule.pagebace.PageBaceClass;

public class Ko001g1Page extends PageBaceClass {
	
	private Integer kaihai;
	private ListDto[] kaihaiItems;

	private int projectIndex;
	private ListDto[] projectItems;

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

	public int getProjectIndex() {
		return projectIndex;
	}

	public void setProjectIndex(int projectIndex) {
		this.projectIndex = projectIndex;
	}

	public ListDto[] getProjectItems() {
		return projectItems;
	}

	public void setProjectItems(ListDto[] projectItems) {
		this.projectItems = projectItems;
	}

	public Class<?> doDetails() {
		return null;
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
