package schedule.web.masterMaintenance;

import org.seasar.framework.container.annotation.tiger.Binding;

import schedule.dto.SessionDto;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;
import schedule.web.kom002.Kom002g1Page;
import schedule.web.mast.classlist.ClasslistPage;
import schedule.web.mast.companyinf.CompanyinfPage;
import schedule.web.mast.customerlist.CustomerlistPage;
import schedule.web.mast.employeelist.EmployeelistPage;
import schedule.web.mast.grouplist.GroupattachPage;
import schedule.web.mast.grouplist.GrouplistPage;
import schedule.web.mast.kengen.KengenlistPage;
import schedule.web.mast.pjcode.PjcodelistPage;

/**
 * <p>マスタメンテナンスPageクラス</p>
 * @author T.fujimoto
 *
 */
public class MastermaintePage {

	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> initialize() throws Exception {
		
		// 未ログインユーザーをはじく処理
		if(sessionDto.EmployeeID == null){
			return NonloginPage.class;
		}
		try{
			
			if(sessionDto.MastermainteFlg != true){
				return NonauthorityPage.class;
			}
			
		}catch(Exception e){
			throw e;
		}
		
		return null;
	}

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		return null;
	}

	/**
	 * <p>顧客マスタ管理</p>
	 * @return 顧客一覧画面遷移
	 *
	 */
	public Class<?> doCustomer(){
		return CustomerlistPage.class;
	}
	
	/**
	 * <p>会社情報マスタ管理</p>
	 * @return 役職マスタ一覧画面遷移
	 *
	 */
	public Class<?> doCompany(){
		return CompanyinfPage.class;
	}
	
	/**
	 * <p>社員マスタ管理</p>
	 * @return 社員マスタ一覧画面遷移
	 *
	 */
	public Class<?> doEmployee(){
		return EmployeelistPage.class;
	}
	
	/**
	 * <p>役職マスタ管理</p>
	 * @return 役職マスタ一覧画面遷移
	 *
	 */
	public Class<?> doClass(){
		return ClasslistPage.class;
	}

	/**
	 * <p>グループマスタ管理</p>
	 * @return 役職マスタ一覧画面遷移
	 *
	 */
	public Class<?> doGroup(){
		return GrouplistPage.class;
	}
	
	/**
	 * <p>グループマスタ管理</p>
	 * @return 役職マスタ一覧画面遷移
	 *
	 */
	public Class<?> doGroupEdit(){
		return GroupattachPage.class;
	}

	/**
	 * <p>プロジェクトコード管理</p>
	 * @return プロジェクトコード画面遷移
	 *
	 */
	public Class<?> doPjcode(){
		return PjcodelistPage.class;
	}
	
	/**
	 * <p>プロジェクトコード管理</p>
	 * @return プロジェクトコード画面遷移
	 *
	 */
	public Class<?> doRPjcode(){
		return Kom002g1Page.class;
	}
	
	/**
	 * <p>権限管理</p>
	 * @return 権限画面遷移
	 *
	 */
	public Class<?> doKengen(){
		return KengenlistPage.class;
	}
	
}
