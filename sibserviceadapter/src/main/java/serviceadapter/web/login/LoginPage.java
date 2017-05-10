package serviceadapter.web.login;

import java.util.HashMap;
import java.util.Map;

import org.seasar.teeda.extension.annotation.validator.Required;

import serviceadapter.bace.PageBaceClass;
import serviceadapter.common.CommonConstants;
import serviceadapter.dao.MAdapterDao;
import serviceadapter.entity.MAdapter;
import serviceadapter.service.SibServiceAdapter;
import serviceadapter.validator.UserAuthValidator;
import serviceadapter.web.input.ListPage;

public class LoginPage extends PageBaceClass {
	
	@Required
	private String loginIDStr;
	
	@Required
	@UserAuthValidator(targetId="loginIDStr")
	private String loginPasswordStr;
	
	public MAdapterDao adpDao;
	
	public String getLoginIDStr() {
		return loginIDStr;
	}

	public void setLoginIDStr(String loginIDStr) {
		this.loginIDStr = loginIDStr;
	}

	public String getLoginPasswordStr() {
		return loginPasswordStr;
	}

	public void setLoginPasswordStr(String loginPasswordStr) {
		this.loginPasswordStr = loginPasswordStr;
	}

	public Class<?> doLogin() {
		
		MAdapter ety_Adp = adpDao.selectById(CommonConstants.SERVICE_TYPE_USERINFO);
				
		try {
			
			Map<String, String> reqParam = new HashMap<String, String>();
			Map<String, String> resParam;
			
			reqParam.put("userID", loginIDStr);
			reqParam.put("userPas", loginPasswordStr);
			
			SibServiceAdapter SAA = new SibServiceAdapter();
			
			SAA.setServiceProvider(ety_Adp.getServiceurl());
			
			resParam = SAA.postData(reqParam);
			
			System.out.println();
			System.out.println("-----------------------------");
			System.out.println(resParam.get("employeeIDStr"));
			System.out.println(resParam.get("employeeNameStr"));
			System.out.println(resParam.get("employeeEmailStr"));
			
			this.sessionDto.EmployeeID = Integer.parseInt(resParam.get("employeeIDStr"));
			this.sessionDto.EmployeeName = resParam.get("employeeNameStr");
			this.sessionDto.EmployeeEmail = resParam.get("employeeEmailStr");
			
			this.sessionDto.MastermainteFlg = CommonConstants.STR_TRUE.equals(resParam.get("mastermainteFlg"));
			this.sessionDto.MastermainteFlg = CommonConstants.STR_TRUE.equals(resParam.get("kogutiKanriFlg"));
			
			return ListPage.class;
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return null;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		return null;
	}

	//レイアウトページ指定
	public String getLayout(){
		return "/layout/loginlayout.html";
	}
}
