package ko.web.sample;

import ko.pagebace.PageBaceClass;

public class SamplePage extends PageBaceClass {

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		//ログイン情報の設定
		this.setLoginInfo();
		
		return null;
	}

}
