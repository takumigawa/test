package schedule.web.adapter.comblist;

import schedule.dao.TSettingDao;
import schedule.entity.TSetting;

public class UpdatecomblistPage {

	public TSettingDao setDao;
	
	private String comAuth;
	private String combName;
	private String updTYPE;
	private String comId;
	private String comLabel;
	private String comKaihai;
	private String comOrderSEQ;

	public String getComAuth() {
		return comAuth;
	}

	public void setComAuth(String comAuth) {
		this.comAuth = comAuth;
	}

	public String getCombName() {
		return combName;
	}

	public void setCombName(String combName) {
		this.combName = combName;
	}

	public String getUpdTYPE() {
		return updTYPE;
	}

	public void setUpdTYPE(String updTYPE) {
		this.updTYPE = updTYPE;
	}

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

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		try{
			
			TSetting ety_set;
			
			if(updTYPE.equals("NEW")){
				
				//役職情報新規作成
				
				ety_set = new TSetting();
				
				ety_set.key1			= "comblist";
				ety_set.key2			= combName;
				ety_set.key3			= comId;

				ety_set.stringvalue 	= comLabel;
				
				if(!comKaihai.equals("0")){
					ety_set.integervalue	= Integer.parseInt(comOrderSEQ);
				}else{
					ety_set.integervalue	= 0;
				}
				
				setDao.insert(ety_set);
				
			}else{
				
				//役職情報更新
				
				ety_set = setDao.selectById("comblist",combName,comId);
				
				ety_set.stringvalue 	= comLabel;
				
				if (comKaihai.equals(0)) {
					ety_set.integervalue	= Integer.parseInt(comOrderSEQ);					
				} else {
					ety_set.integervalue	= 0;
				}
				
				setDao.update(ety_set);
			}
			
			comAuth = "True";
			
		}catch(Exception e){
			comAuth = "false";
			
		}
		
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
	
}
