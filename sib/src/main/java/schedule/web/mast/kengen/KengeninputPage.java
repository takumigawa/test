package schedule.web.mast.kengen;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.TSettingDao;
import schedule.entity.TSetting;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.validator.UseKengenCodeValidator;
import schedule.web.common.CommonConstants;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;

/**
 * 
 * @author taku_fujimoto
 *
 */
public class KengeninputPage extends PageBaceClass {

	@PageScope
	private String kengenCodeStr;
	
	@PageScope
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String kengenNameStr;
	
	@PageScope
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_ESUJI, messageId = "err.NoMatchEISU")
	@UseKengenCodeValidator(target="doFinishUpdate", messageId= "err.UseKengenCode")
	private String kengenButuNameStr;
	
	private String updateMes;

	/** 権限値　セッタ/ゲッタ　*/
	public String getKengenCodeStr() {
		return kengenCodeStr;
	}
	public void setKengenCodeStr(String kengenCodeStr) {
		this.kengenCodeStr = kengenCodeStr;
	}

	/** 権限名　セッタ/ゲッタ　*/
	public String getKengenNameStr() {
		return kengenNameStr;
	}
	public void setKengenNameStr(String kengenNameStr) {
		this.kengenNameStr = kengenNameStr;
	}

	/** 権限名(権限)　セッタ/ゲッタ　*/
	public String getKengenButuNameStr() {
		return kengenButuNameStr;
	}
	public void setKengenButuNameStr(String kengenButuNameStr) {
		this.kengenButuNameStr = kengenButuNameStr;
	}
	
	/** 更新メッセージ　セッタ/ゲッタ　*/
	public String getUpdateMes() {
		return updateMes;
	}
	public void setUpdateMes(String updateMes) {
		this.updateMes = updateMes;
	}

	/** 選択プロジェクトＩＤ */
	@SubapplicationScope
	private String selectID;
	
	/** 選択プロジェクトＩＤ セッタ/ゲッタ */
	public String getSelectID() {
		return this.selectID;
	}
	public void setSelectID(String selectID) {
		this.selectID = selectID;
	}
	
	public TSettingDao setDao;
	
	/**
	 * 
	 * @return
	 */
	public Class<?> doFinishReturn() {
		
		
		return KengenlistPage.class;
	}
	
	/**
	 * 
	 * @return
	 */
	public Class<?> doFinishUpdate() {
		
		if (this.isUpdate()) {
			//更新
			TSetting ety_set = setDao.selectById(CommonConstants.k1_system, CommonConstants.k2_authority, selectID);
			
			ety_set.stringvalue = kengenNameStr;
			
			setDao.update(ety_set);
			
			updateMes = "更新しました。";
			
		} else {
			//新規
			TSetting ety_set = new TSetting();
			
			ety_set.key1 = CommonConstants.k1_system;
			ety_set.key2 = CommonConstants.k2_authority;
			ety_set.key3 = kengenButuNameStr;
			
			ety_set.stringvalue = kengenNameStr;
			ety_set.integervalue = Integer.parseInt(kengenCodeStr);
			
			setDao.insert(ety_set);
			
			updateMes = "追加しました。";
			
		}
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> initialize() {
		
		try {
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			if (this.isUpdate()) {
				//更新
				TSetting ety_set = setDao.selectById(CommonConstants.k1_system, CommonConstants.k2_authority, selectID);
				
				this.kengenCodeStr = ety_set.integervalue.toString();
				this.kengenNameStr = ety_set.stringvalue;
				this.kengenButuNameStr = ety_set.key3;
				
			} else {
				
				Integer MaxTi = setDao.getMAXKengen() * 2;
				this.kengenCodeStr = MaxTi.toString() ;
				
			}

		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			//権限エラー
			return NonauthorityPage.class;
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			return AbendPage.class;
			
		}
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> prerender() {
		
		this.setLoginInfo();
		return null;
	}

	/**
	 * <p>画面登録/新規切り替え判定メソッド</p>
	 * @return true 更新/false 新規
	 *
	 */
	public boolean isUpdate(){
		
		if("".equals(selectID)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * マスタメンテナンス用レイアウト適用
	 * @return
	 */
	public String getLayout(){
		return CommonConstants.MAST_LAOUT_URL;
	}

}
