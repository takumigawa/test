package schedule.web.mast.kengen;

import java.util.ArrayList;
import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.TSettingDao;
import schedule.dto.KengenListDto;
import schedule.entity.TSetting;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.web.common.CommonConstants;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;
import schedule.web.masterMaintenance.MastermaintePage;

public class KengenlistPage extends PageBaceClass {

	@PageScope
	@Required
	private int kengenIndex;
	@PageScope
	private List<KengenListDto> kengenItems;
	
	
	private String kengenCode;
	private String kengenName;
	private String kengenButuName;

	/** プロジェクトindex セッタ/ゲッタ　*/
	public int getKengenIndex() {
		return kengenIndex;
	}
	public void setKengenIndex(int kengenIndex) {
		this.kengenIndex = kengenIndex;
	}

	/** プロジェクトindex セッタ/ゲッタ　*/
	public List<KengenListDto> getKengenItems() {
		return kengenItems;
	}
	public void setKengenItems(List<KengenListDto> kengenItems) {
		this.kengenItems = kengenItems;
	}

	/** プロジェクトindex セッタ/ゲッタ　*/
	public String getKengenCode() {
		return kengenCode;
	}
	public void setKengenCode(String kengenCode) {
		this.kengenCode = kengenCode;
	}

	/** プロジェクトindex セッタ/ゲッタ　*/
	public String getKengenName() {
		return kengenName;
	}
	public void setKengenName(String kengenName) {
		this.kengenName = kengenName;
	}

	/** プロジェクトindex セッタ/ゲッタ　*/
	public String getKengenButuName() {
		return kengenButuName;
	}
	public void setKengenButuName(String kengenButuName) {
		this.kengenButuName = kengenButuName;
	}
	
	/** 選択権限スＩＤ */
	@SubapplicationScope
	private String selectID;
	
	/** 選択権限ID セッタ/ゲッタ */
	public String getSelectID() {
		return this.selectID;
	}
	public void setSelectID(String selectID) {
		this.selectID = selectID;
	}
	
	//汎用設定Dao
	public TSettingDao setDao;

	/**
	 * 
	 * @return
	 */
	public Class<?> doDetails() {
		
		selectID = kengenItems.get(kengenIndex).kengenButuName;
		return KengeninputPage.class;
		
	}

	/**
	 * <p>前画面以降メソッド</p>
	 * @return マスターメンテナンス一覧画面遷移
	 *
	 */
	public Class<?> doReturn(){
		
		return MastermaintePage.class;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> doKengenAdd() {
		
		selectID = "";
		return KengeninputPage.class;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> initialize() {

		System.out.println("ListPage initialize Start");
		
		try {
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();

			List<KengenListDto> retList = new ArrayList<KengenListDto>();
			
			TSetting[] list = setDao.selectByTwoId(CommonConstants.k1_system, CommonConstants.k2_authority);
			
			
			for (int i = 0;i < list.length;i++) {
				
				if (!list[i].integervalue.equals(0)) {
					KengenListDto addItem = new KengenListDto();
					
					addItem.kengenCode = list[i].integervalue.toString();
					addItem.kengenName = list[i].stringvalue;
					addItem.kengenButuName = list[i].key3;
					
					retList.add(addItem);
				}

			}
			
			kengenItems = retList;			
			
		} catch (AnLoginException e) {
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			return NonauthorityPage.class;
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			return AbendPage.class;
			
		}
		
		System.out.println("ListPage initialize End");
		
		return null;
		
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> prerender() {
		
		//ユーザー情報設定
		this.setLoginInfo();
		
		return null;
	}

	/**
	 * マスタメンテナンス用レイアウト適用
	 * @return
	 */
	public String getLayout(){
		return CommonConstants.MAST_LAOUT_URL;
	}
}
