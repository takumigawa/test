package schedule.web.adapter.comblist;

import schedule.dao.TSettingDao;
import schedule.dto.ListDto;

public class GetcomblistPage {

	public TSettingDao setDao;
	
	private String comKey;
	private String comLabel;
	private String combName;

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

	public String getCombName() {
		return combName;
	}

	public void setCombName(String combName) {
		this.combName = combName;
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
		
		ListDto[] combData = setDao.selectByCombList(combName);
		
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
