package ssm.web.error;

import org.seasar.framework.container.annotation.tiger.Binding;

import ssm.dto.SessionDto;


/**
 * <p>未ログインユーザーPageクラス</p>
 * @author T.fujimoto
 *
 */
public class NonauthorityPage {

	/** セッションDto */
	@Binding
	public SessionDto sessionDto;
	
	public Class<?> initialize() {
		
		// 未ログインユーザーをはじく処理
		if(sessionDto.EmployeeID == null){
			return NonloginPage.class;
		}
		
		return null;
	}

	public Class<?> prerender() {
		return null;
	}

}
