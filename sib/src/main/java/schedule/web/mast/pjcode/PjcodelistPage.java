package schedule.web.mast.pjcode;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.TProjectDao;
import schedule.dao.TSettingDao;
import schedule.dto.ListDto;
import schedule.dto.PJCodeDto;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.web.common.CommonConstants;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;
import schedule.web.masterMaintenance.MastermaintePage;

public class PjcodelistPage extends PageBaceClass {


	/** 有効/無効値 */
	@PageScope
	@Required
	public Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	public ListDto[] kaihaiItems;
	

	private int projectIndex;
	@PageScope
	private PJCodeDto[] projectItems;
	
	private String projectCode;
	private String projectName;
	private String projectEnabled;

	/** 汎用登録テーブルDao */
	public TSettingDao setDao;
	
	public TProjectDao proDao;
	
	/** 改廃index セッタ/ゲッタ */
	public Integer getKaihai() {
		return kaihai;
	}
	public void setKaihai(Integer kaihai) {
		this.kaihai = kaihai;
	}

	/** 改廃 セッタ/ゲッタ */
	public ListDto[] getKaihaiItems() {
		return kaihaiItems;
	}
	public void setKaihaiItems(ListDto[] kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}

	/** プロジェクトindex セッタ/ゲッタ */
	public int getProjectIndex() {
		return projectIndex;
	}
	public void setProjectIndex(int projectIndex) {
		this.projectIndex = projectIndex;
	}

	/** プロジェクト セッタ/ゲッタ */
	public PJCodeDto[] getProjectItems() {
		return projectItems;
	}
	public void setProjectItems(PJCodeDto[] projectItems) {
		this.projectItems = projectItems;
	}

	/** プロジェクトコード セッタ/ゲッタ */
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	/** プロジェクト名 セッタ/ゲッタ */
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/** 有効無効 セッタ/ゲッタ */
	public String getProjectEnabled() {
		return projectEnabled;
	}
	public void setProjectEnabled(String projectEnabled) {
		this.projectEnabled = projectEnabled;
	}

	/** 選択サービスＩＤ */
	@SubapplicationScope
	private String selectID;
	
	/** 選択サービスＩＤ セッタ/ゲッタ */
	public String getSelectID() {
		return this.selectID;
	}
	public void setSelectID(String selectID) {
		this.selectID = selectID;
	}
	
	/**
	 * 
	 * @return
	 */
	public Class<?> doDetails() {
		selectID = projectItems[projectIndex].projectCode;
		return PjcodeinputPage.class;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> doSrech() {
		
		//プロジェクトコード一覧　取得
		projectItems = proDao.selectGetPjcodeList(kaihai);
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> doSrechCancel() {
		
		kaihai		= CommonConstants.Kaihai_true;
		projectItems = proDao.selectGetPjcodeList(0);
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> doPjcodeAdd() {
		
		selectID = "";
		
		return PjcodeinputPage.class;
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
	public Class<?> initialize() {
		
		System.out.println("ListPage initialize Start");
		
		try {
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			kaihaiItems = setDao.selectByCombList(CommonConstants.COMB_kaihai);
			
			//プロジェクトコード一覧　取得
			projectItems = proDao.selectGetPjcodeList(0);

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
