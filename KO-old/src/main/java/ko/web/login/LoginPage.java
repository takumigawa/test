package ko.web.login;

import java.util.HashMap;
import java.util.Map;

import org.seasar.teeda.extension.annotation.validator.Required;
import ko.validator.UserAuthValidator;
import ko.pagebace.PageBaceClass;
import ko.common.CommonConstants;
//import ko.entity.MAdapter;
//import ko.service.SibServiceAdapter;
//import ko.web.input.ListPage;
import ko.web.ko001.Ko001g1Page;

public class LoginPage extends PageBaceClass {


	@Required
	private String loginIDStr;

	@Required
	@UserAuthValidator(targetId="loginIDStr")
	private String loginPasswordStr;

//	private String contents;
//	private String c_pad;
//	private String loginIDStr;
//	private String loginPasswordStr;


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

//		MAdapter ety_Adp = adpDao.selectById(CommonConstants.SERVICE_TYPE_USERINFO);



		try {
				Map<String, String> reqParam = new HashMap<String, String>();
				Map<String, String> resParam;

				reqParam.put("userID", loginIDStr);
				reqParam.put("userPas", loginPasswordStr);

				resParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_USERINFO,reqParam);

				this.sessionDto.EmployeeID  = Integer.parseInt(resParam.get("employeeIDStr"));
				this.sessionDto.EmployeeName = resParam.get("employeeNameStr");
				this.sessionDto.EmployeeEmail = resParam.get("employeeEmailStr");

				this.sessionDto.MastermainteFlg = CommonConstants.STR_TRUE.equals(resParam.get("mastermainteFlg"));
				this.sessionDto.MastermainteFlg = CommonConstants.STR_TRUE.equals(resParam.get("kogutiKanriFlg"));


				return Ko001g1Page.class;

			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			return null ;
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


