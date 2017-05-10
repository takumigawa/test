package koroot.web.error;

import koroot.dto.SessionDto;
import koroot.web.login.LoginPage;
import org.seasar.framework.container.annotation.tiger.Binding;

/**
 * <p>未ログインユーザーPageクラス</p>
 * @author T.fujimoto
 *
 */
public class NonloginPage {

	/** セッションDto */
	@Binding
	public SessionDto sessionDto;
	
	public Class<?> initialize() {
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
