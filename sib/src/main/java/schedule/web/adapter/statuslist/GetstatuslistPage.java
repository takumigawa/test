package schedule.web.adapter.statuslist;

import schedule.dao.TSettingDao;
import schedule.dto.ListDto;

public class GetstatuslistPage {

	public TSettingDao setDao;
	
	private String comKey;
	private String comLabel;
	private String statusName;

	/** キー　セッター・ゲッター　*/
	public String getComKey() {
		return comKey;
	}
	public void setComKey(String comKey) {
		this.comKey = comKey;
	}

	/**　ラベル　セッター・ゲッター　*/
	public String getComLabel() {
		return comLabel;
	}
	public void setComLabel(String comLabel) {
		this.comLabel = comLabel;
	}

	/**　ステータス名　セッター・ゲッター　*/
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		System.out.println("GetStatuslistPage prerender Start");
		
		ListDto[] combData = setDao.selectByStatusList(statusName);
		
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
		
		System.out.println("GetStatuslistPage prerender End");
		
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
	
}
