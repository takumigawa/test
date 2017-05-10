package serviceadapter.web.error;

import serviceadapter.bace.PageBaceClass;
import serviceadapter.web.login.LoginPage;

public class NonloginPage extends PageBaceClass {

	public Class<?> doLogin() {
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
