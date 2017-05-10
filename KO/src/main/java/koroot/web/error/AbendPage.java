package koroot.web.error;

import koroot.dto.SessionDto;
import koroot.web.login.LogoutPage;

import org.seasar.framework.container.annotation.tiger.Binding;



/**
 * <p>イレギュラーエラーPageクラス</p>
 * @author T.fujimoto
 *
 */
public class AbendPage {

	public String errMes;

	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	public Class<?> initialize() {
		
		// 未ログインユーザーをはじく処理
		if(sessionDto.EmployeeID == null){
			return NonloginPage.class;
		}
		
		errMes = "重大かつ致命的かつのっぴきならないまたは緊急的かつ致死的または楽観的なエラーが発生しました。";
		
		return null;
	}

	public Class<?> prerender() {
		return null;
	}

	public Class<?> doLogout(){
		return LogoutPage.class;
	}
	
	public String getLayout(){
		return "/layout/loginlayout.html";
	}
	
}
