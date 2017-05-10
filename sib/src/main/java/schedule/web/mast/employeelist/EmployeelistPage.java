package schedule.web.mast.employeelist;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MEmployeeDao;
import schedule.dao.TSettingDao;
import schedule.dto.EmpListDto;
import schedule.dto.ListDto;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.web.common.*;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;
import schedule.web.masterMaintenance.MastermaintePage;

/**
 * <p>社員マスタ一覧Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class EmployeelistPage extends PageBaceClass {
	
	/** 入力項目変数 */
	public String empID;
	public String empName;
	public String empemail;
	public String empEnabled;

	/** 顧客一覧Index */
	public int empIndex;
	/** 顧客一覧 */
	@PageScope
	public EmpListDto[] empItems;
	
	/** 有効/無効値 */
	@PageScope
	@Required
	public Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	public ListDto[] kaihaiItems;

	/** 行絞り込み値 */
	@PageScope
	@Required
	public String kanaGyo;
	/** 行絞り込み一覧 */
	@PageScope
	public ListDto[] kanaGyoItems;
	
	/** 社員マスタDao */
	public MEmployeeDao empDao;
	
	/** 汎用登録テーブルDao */
	public TSettingDao setDao;
	
	/** 選択社員ＩＤ */
	@SubapplicationScope
	public Integer selectempID;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {
				
		try{
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			kaihaiItems = setDao.selectByCombList(CommonConstants.COMB_kaihai);

			//検索条件-有効/無効コンボ
			kanaGyoItems = setDao.selectByCombList(CommonConstants.COMB_kana);
			
			//社員一覧
			empItems = empDao.selectGetEmpList(kanaGyo, CommonConstants.Kaihai_true);
			
		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			//未マスタメンテ権限エラー
			return NonauthorityPage.class;
			
		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}

	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		
		//ログイン情報
		this.setLoginInfo();
		
		return null;
	}
	
	/**
	 * <p>一覧絞り込みメソッド/p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doSrech(){
		
		try{
			empItems = empDao.selectGetEmpList(kanaGyo, kaihai);

		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>一覧絞り込み解除メソッド/p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doSrechCancel(){

		try{
			kaihai		= CommonConstants.Kaihai_true;
			kanaGyo 	= CommonConstants.COMB_kaihai_init;
			empItems 	= empDao.selectGetEmpList(null, CommonConstants.Kaihai_true);

		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>社員編集メソッド/p>
	 * @return 社員編集/登録画面遷移
	 *
	 */
	public Class<?> doEdit(){
	
		try{
			selectempID = Integer.parseInt(empItems[empIndex].empID);

		}catch(Exception e){
			return AbendPage.class;
		}
			
		return EmployeeinputPage.class;
	}

	/**
	 * <p>社員追加メソッド</p>
	 * @return 社員編集/登録画面遷移
	 *
	 */
	public Class<?> doEmployeeAdd(){
		selectempID = -1;
		return EmployeeinputPage.class;
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
	 * マスタメンテナンス用レイアウト適用
	 * @return
	 */
	public String getLayout(){
		return CommonConstants.MAST_LAOUT_URL;
	}
}
