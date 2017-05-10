package ssm.web.sample;

import ssm.pagebace.PageBaceClass;

public class SamplePage extends PageBaceClass {

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		//ログインユーザIDを設定する。
		this.setLoginInfo();
		
		return null;
	}

}
