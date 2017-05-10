package schedule.web.adapter.comblist;

import schedule.dao.TSettingDao;
import schedule.dto.CombListDto;

public class GetallcomblistPage {

	public TSettingDao setDao;
	
	private String comId;
	private String comLabel;
	private String comKaihai;
	private String comOrderSEQ;
	private String combName;

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getComLabel() {
		return comLabel;
	}

	public void setComLabel(String comLabel) {
		this.comLabel = comLabel;
	}

	public String getComKaihai() {
		return comKaihai;
	}

	public void setComKaihai(String comKaihai) {
		this.comKaihai = comKaihai;
	}

	public String getComOrderSEQ() {
		return comOrderSEQ;
	}

	public void setComOrderSEQ(String comOrderSEQ) {
		this.comOrderSEQ = comOrderSEQ;
	}

	public String getCombName() {
		return combName;
	}

	public void setCombName(String combName) {
		this.combName = combName;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		System.out.println("GetallcomblistPage prerender Start");
		
		CombListDto[] combData = setDao.selectByAllCombList(combName);
		
		comId = "";
		comLabel = "";
		comKaihai = "";
		comOrderSEQ = "";		
		
		for (CombListDto listItem : combData) {
			
			if (!"".equals(comId)) {
				comId += ",";
				comLabel += ",";
				comKaihai += ",";
				comOrderSEQ += ",";
			}
			comId += listItem.value;
			comLabel += listItem.label;
			comKaihai += listItem.kaihai;
			comOrderSEQ += listItem.orderseq;
			
		}
		
		System.out.println("GetallcomblistPage prerender End");
		
		return null;
		
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
	
}
