package schedule.web.adapter.projectcode;

import schedule.dao.TProjectDao;
import schedule.dao.TRelationProjectEmployeeDao;
import schedule.dto.PJCodeDto;

public class GetprojectcodelistPage {

	private String comKey;
	private String comLabel;
	public PJCodeDto[] combData;
	

	public TProjectDao proDao;
	public TRelationProjectEmployeeDao relDao;
	
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

	public PJCodeDto[] getCombData() {
		return combData;
	}

	public void setCombData(PJCodeDto[] combData) {
		this.combData = combData;
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
//		PJCodeDto[] combData = new ArrayList<PJCodeDto>();
		
		
 		if (comKey != null && comLabel !=null ){
			//ログイン者が参画するPJのみを抽出対象とする場合			
			if (comKey.equals("employee_id"))
			{
			combData = proDao.selectGetuserPjcodeList(Integer.parseInt(comLabel),0);		
			}
			//すべてのPJを抽出対象とする場合
		}else{
		
		    combData = proDao.selectGetPjcodeList(0);
		}
 		
		comKey = "";
		comLabel = "";
		
		for (PJCodeDto listItem : combData) {
			
			if (!"".equals(comKey)) {
				comKey += ",";
				comLabel += ",";
			}
			comKey += listItem.projectCode;
			comLabel += listItem.projectName;
		}
		
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}

}
