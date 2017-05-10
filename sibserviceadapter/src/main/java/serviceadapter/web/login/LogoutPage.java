package serviceadapter.web.login;

import org.seasar.framework.aop.annotation.RemoveSession;

import serviceadapter.bace.PageBaceClass;

public class LogoutPage extends PageBaceClass {

	@RemoveSession(name={ "sessionDto" })
	public Class<?> doLogout() {
		return LoginPage.class;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		return null;
	}

	public String getLayout(){
		return "/layout/loginlayout.html";
	}
	
}
