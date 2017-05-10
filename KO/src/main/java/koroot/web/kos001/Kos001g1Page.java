package koroot.web.kos001;

import koroot.exception.AnLoginException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonloginPage;

public class Kos001g1Page extends PageBaceClass {

	public Class<?> initialize() {
		try{
			
		    //セッション情報チェック
			this.chkSession();
			
			//ログイン情報表示
			this.setLoginInfo();
		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;
			
		}catch(Exception e){
			 //アベンド
			return AbendPage.class;
		} 
		
			return null;
	}

	public Class<?> prerender() {
		
		//ログイン情報表示		
		this.setLoginInfo();
		
		return null;
	}

}
