package schedule.web.adapter.employee;

import schedule.dao.MEmployeeDao;
import schedule.dto.ListDto;

public class GetemplistPage {

	public MEmployeeDao empDao;
	
	private String comKey;
	private String comLabel;

	public String getComKey() {
		return comKey;
	}

	public void setComKey(String comKey) {
		this.comKey = comKey;
	}

	public String getComLabel() {
		return comLabel;
	}

	public void setComLabel(String comLabel) {
		this.comLabel = comLabel;
	}


	/**
	 * 
	 * @return
	 */
	public Class<?> initialize() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> prerender() {

		System.out.println("GetcomblistPage prerender Start");
		
		ListDto[] combData = empDao.selectGetEmpCombList();
		
		comKey = "";
		comLabel = "";
		
		for (ListDto listItem : combData) {
			
			if (!"".equals(comKey)) {
				comKey += ",";
				comLabel += ",";
			}
			comKey += listItem.value;
			comLabel += listItem.label;
		}
		
		System.out.println("GetcomblistPage prerender End");
		
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
	
}
