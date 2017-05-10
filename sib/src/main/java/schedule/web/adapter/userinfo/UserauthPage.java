package schedule.web.adapter.userinfo;

import schedule.dao.MEmployeeDao;
import schedule.entity.MEmployee;

public class UserauthPage {

	public MEmployeeDao empDao;
	
	private String userID;
	private String userPas;
	private String userAuth;
	private String userName;
	private String userCode;

	public String getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public void setUserPas(String userPas) {
		this.userPas = userPas;
	}
	
	/**
	 * 
	 * @return
	 */
	public Class<?> initialize() {
				
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> prerender() {

		MEmployee ety_emp = empDao.selectByIDPas(userID, userPas);
		
		if(ety_emp != null){
						
			userAuth = "true";
			userName = ety_emp.employeeName;
			userCode = ety_emp.employeeId.toString();
			
		}else{
			userAuth = "false";
		}	
		
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
	
}
