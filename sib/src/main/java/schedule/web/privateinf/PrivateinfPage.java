package schedule.web.privateinf;

import org.seasar.framework.container.annotation.tiger.Binding;

import schedule.dto.SessionDto;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>社員個人情報編集Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class PrivateinfPage {

	/** ログイン社員情報 */
	public String employee_id;
	public String employee_name;
	public String employee_mail;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
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
	 * <p>社員ログインパスワード変更メソッド/p>
	 * @return ログインパスワード編集画面遷移
	 *
	 */
	public Class<?> doPassword(){
			
		return PrivatepasswordPage.class;
	}
	
	/**
	 * <p>社員個人情報編集メソッド</p>
	 * @return 個人情報編集画面遷移
	 *
	 */
	public Class<?> doUpdate(){
		
		return PrivateinfupdatePage.class;
	}
}
