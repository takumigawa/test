package schedule.web.employeeinf;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MEmployeeDao;
import schedule.dao.TSettingDao;
import schedule.dto.EmpListDto;
import schedule.dto.ListDto;
import schedule.dto.SessionDto;
import schedule.web.common.*;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;
import schedule.web.masterMaintenance.MastermaintePage;

/**
 * <p>社員マスタ一覧Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class EmployeeinfPage {

	/** ログイン社員情報 */
	public String employee_id;
	public String employee_name;
	public String employee_mail;
	
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
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
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
			
			// 未ログインユーザーをはじく処理
			if(sessionDto.EmployeeID == null){
				return NonloginPage.class;
			}
			
			//検索条件-有効/無効コンボ
			kanaGyoItems = setDao.selectByCombList(CommonConstants.COMB_kana);
			
			//社員一覧
			empItems = empDao.selectGetEmpList(kanaGyo, CommonConstants.Kaihai_true);
			
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
		employee_id 	= sessionDto.EmployeeID.toString();
		employee_name	= sessionDto.EmployeeName;
		employee_mail	= sessionDto.EmployeeEmail;
		
		return null;
	}
	
	/**
	 * <p>一覧絞り込みメソッド/p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doSrech(){
		
		try{
			empItems = empDao.selectGetEmpList(kanaGyo, 0);

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
			kanaGyo 	= CommonConstants.COMB_kaihai_init;
			empItems 	= empDao.selectGetEmpList(null, CommonConstants.Kaihai_true);

		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>前画面以降メソッド</p>
	 * @return マスターメンテナンス一覧画面遷移
	 *
	 */
	public Class<?> doReturn(){
		
		return MastermaintePage.class;
	}
	
	public Class<?> doDetails(){
		
		selectempID = Integer.parseInt(empItems[empIndex].empID );
		
		return EmployeedetailsPage.class;
	}
	
	
}
