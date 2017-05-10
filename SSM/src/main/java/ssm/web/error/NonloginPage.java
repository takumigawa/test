package ssm.web.error;

import org.seasar.framework.container.annotation.tiger.Binding;

import ssm.dto.SessionDto;

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
		return null;
	}
	
	public String getLayout(){
		return "/layout/loginlayout.html";
	}

}
