package schedule.web.error;

import org.seasar.framework.container.annotation.tiger.Binding;

import schedule.dto.SessionDto;
import schedule.web.login.LoginPage;

/**
 * <p>イレギュラーエラーPageクラス</p>
 * @author T.fujimoto
 *
 */
public class LoginerrPage {

	public String contents;
	public String c_pad;
	public String errMes;

	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	public Class<?> initialize() {
		
		// 未ログインユーザーをはじく処理
		if(sessionDto.EmployeeID == null){
			return NonloginPage.class;
		}
		
		errMes = "ログイン認証に失敗しました。　ID、パスワードを確認して再度入力してください。";
		
		return null;
	}

	public Class<?> prerender() {
		return null;
	}

	public Class<?> doLogin(){
		return LoginPage.class;
	}
	
	public String getLayout(){
		return "/layout/loginlayout.html";
	}
	
}
