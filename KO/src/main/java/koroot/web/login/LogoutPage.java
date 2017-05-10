package koroot.web.login;

import koroot.dto.SessionDto;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.container.annotation.tiger.Binding;

/**
 * <p>ログインPageクラス</p>
 * @author T.fujimoto
 *
 */
public class LogoutPage {
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {

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
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	@RemoveSession(name={ "sessionDto" })
	public Class<?> doLogout(){
		return LoginPage.class;
	}
		
}
