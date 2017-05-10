package ko.web.adaptest;

import java.util.HashMap;
import java.util.Map;

import ko.common.CommonConstants;
import ko.pagebace.PageBaceClass;

public class AdaptestPage extends PageBaceClass {

	private String namename;

	/** 社員名 */
	public String getNamename() {
		return namename;
	}
	public void setNamename(String namename) {
		this.namename = namename;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {

		Map<String, String> reqParam = new HashMap<String, String>();
		Map<String, String> resParam;

		try {


			reqParam.put("userID", "takumigawa");
			reqParam.put("userPas", "ryuunosuke");

			resParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_USERINFO,reqParam);


			namename = resParam.get("employeeNameStr");


		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return null;
	}

}
