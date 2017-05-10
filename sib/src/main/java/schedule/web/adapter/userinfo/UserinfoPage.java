package schedule.web.adapter.userinfo;

import schedule.dao.MEmployeeDao;
import schedule.dao.MGroupDao;
import schedule.dao.TSettingDao;
import schedule.dto.GroupListDto;
import schedule.entity.MEmployee;
import schedule.entity.TSetting;
import schedule.web.common.CommonConstants;

public class UserinfoPage {

	public MEmployeeDao empDao;
	public MGroupDao gruDao;
	public TSettingDao setDao;
	public  GroupListDto[] gruList;
	
	private String employeeIDStr;
	private String employeeNameStr;
	private String employeeNamekanaStr;
	private String loginIDStr;
	private String loginPasswordStr;
	private String employeePositionIDStr;
	private String defaultGroupIDStr;
	private String employeeEmailStr;
	private String employeeEmail2Str;
	private String employeeEmail3Str;
	private String employeePostcodeStr;
	private String employeeAddress1Str;
	private String employeeAddress2Str;
	private String employeeTelStr;
	private String employeeMphoneStr;
	private String employeeFaxStr;
	private String mastermainteFlg;
	private String kogutiKanriFlg;
	private String pjLeaderFlg;
	
	private String userID;
	private String userPas;

	/** 社員ID */
	public String getEmployeeIDStr() {
		return employeeIDStr;
	}
	public void setEmployeeIDStr(String employeeIDStr) {
		this.employeeIDStr = employeeIDStr;
	}

	/** 社員名 */
	public String getEmployeeNameStr() {
		return employeeNameStr;
	}
	public void setEmployeeNameStr(String employeeNameStr) {
		this.employeeNameStr = employeeNameStr;
	}

	/** 社員名（カナ） */
	public String getEmployeeNamekanaStr() {
		return employeeNamekanaStr;
	}
	public void setEmployeeNamekanaStr(String employeeNamekanaStr) {
		this.employeeNamekanaStr = employeeNamekanaStr;
	}

	/** ログインID*/
	public String getLoginIDStr() {
		return loginIDStr;
	}
	public void setLoginIDStr(String loginIDStr) {
		this.loginIDStr = loginIDStr;
	}

	/** ログインパスワード */
	public String getLoginPasswordStr() {
		return loginPasswordStr;
	}
	public void setLoginPasswordStr(String loginPasswordStr) {
		this.loginPasswordStr = loginPasswordStr;
	}

	/** 役職ＩＤ */
	public String getEmployeePositionIDStr() {
		return employeePositionIDStr;
	}
	public void setEmployeePositionIDStr(String employeePositionIDStr) {
		this.employeePositionIDStr = employeePositionIDStr;
	}

	/** デフォルトグループ */
	public String getDefaultGroupIDStr() {
		return defaultGroupIDStr;
	}
	public void setDefaultGroupIDStr(String defaultGroupIDStr) {
		this.defaultGroupIDStr = defaultGroupIDStr;
	}

	/** メールアドレス１ */
	public String getEmployeeEmailStr() {
		return employeeEmailStr;
	}
	public void setEmployeeEmailStr(String employeeEmailStr) {
		this.employeeEmailStr = employeeEmailStr;
	}

	/** メールアドレス２ */
	public String getEmployeeEmail2Str() {
		return employeeEmail2Str;
	}
	public void setEmployeeEmail2Str(String employeeEmail2Str) {
		this.employeeEmail2Str = employeeEmail2Str;
	}

	/** メールアドレス３ */
	public String getEmployeeEmail3Str() {
		return employeeEmail3Str;
	}
	public void setEmployeeEmail3Str(String employeeEmail3Str) {
		this.employeeEmail3Str = employeeEmail3Str;
	}

	/** 郵便番号 */
	public String getEmployeePostcodeStr() {
		return employeePostcodeStr;
	}
	public void setEmployeePostcodeStr(String employeePostcodeStr) {
		this.employeePostcodeStr = employeePostcodeStr;
	}

	/** 住所１ */
	public String getEmployeeAddress1Str() {
		return employeeAddress1Str;
	}
	public void setEmployeeAddress1Str(String employeeAddress1Str) {
		this.employeeAddress1Str = employeeAddress1Str;
	}

	/** 住所２ */
	public String getEmployeeAddress2Str() {
		return employeeAddress2Str;
	}
	public void setEmployeeAddress2Str(String employeeAddress2Str) {
		this.employeeAddress2Str = employeeAddress2Str;
	}

	/** 電話番号 */
	public String getEmployeeTelStr() {
		return employeeTelStr;
	}
	public void setEmployeeTelStr(String employeeTelStr) {
		this.employeeTelStr = employeeTelStr;
	}

	/** 携帯番号 */
	public String getEmployeeMphoneStr() {
		return employeeMphoneStr;
	}
	public void setEmployeeMphoneStr(String employeeMphoneStr) {
		this.employeeMphoneStr = employeeMphoneStr;
	}

	/** FAX */
	public String getEmployeeFaxStr() {
		return employeeFaxStr;
	}
	public void setEmployeeFaxStr(String employeeFaxStr) {
		this.employeeFaxStr = employeeFaxStr;
	}

	/** マスタメンテフラグ */
	public String getMastermainteFlg() {
		return mastermainteFlg;
	}
	public void setMastermainteFlg(String mastermainteFlg) {
		this.mastermainteFlg = mastermainteFlg;
	}
	
	/** 小口管理フラグ */
	public String getKogutiKanriFlg() {
		return kogutiKanriFlg;
	}
	public void setKogutiKanriFlg(String kogutiKanriFlg) {
		this.kogutiKanriFlg = kogutiKanriFlg;
	}
	
	/** プロジェクト管理フラグ */
	public String getPjLeaderFlg() {
		return pjLeaderFlg;
	}
	public void setPjLeaderFlg(String pjLeaderFlg) {
		this.pjLeaderFlg = pjLeaderFlg;
	}
	
	/** ユーザーID */
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/** ユーザーパスワード */
	public String getUserPas() {
		return userPas;
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
		
		employeeIDStr = ety_emp.employeeId.toString();
		employeeNameStr = ety_emp.employeeName;
		employeeNamekanaStr = ety_emp.employeeNamekana;
		loginIDStr = ety_emp.loginid;
		loginPasswordStr = ety_emp.loginpassword;
		employeePositionIDStr = ety_emp.employeePositionId.toString();
		defaultGroupIDStr = ety_emp.defaultGroupId.toString();
		employeeEmailStr = ety_emp.employeeEmail;
		employeeEmail2Str = ety_emp.employeeEmail2;
		employeeEmail3Str = ety_emp.employeeEmail3;
		employeePostcodeStr = ety_emp.employeePostcode;
		employeeAddress1Str = ety_emp.employeeAddress1;
		employeeAddress2Str = ety_emp.employeeAddress2;
		employeeTelStr = ety_emp.employeeTel;
		employeeMphoneStr = ety_emp.employeeMphone;
		employeeFaxStr = ety_emp.employeeFax;
		
		try {
			if (chkAuthority(ety_emp.employeeId,CommonConstants.k3_mastermainte)) {
				mastermainteFlg = CommonConstants.STR_TRUE;
			} else {
				mastermainteFlg = CommonConstants.STR_FALSE;
			}
			
			if (chkAuthority(ety_emp.employeeId,CommonConstants.k3_mastermainte)) {
				kogutiKanriFlg = CommonConstants.STR_TRUE;
			} else {
				kogutiKanriFlg = CommonConstants.STR_FALSE;
			}
			
			if (chkAuthority(ety_emp.employeeId,CommonConstants.k3_pjleader)) {
				pjLeaderFlg = CommonConstants.STR_TRUE;
			} else {
				pjLeaderFlg = CommonConstants.STR_FALSE;
			}
			
		} catch (Exception e) {
			mastermainteFlg = CommonConstants.STR_FALSE;
			kogutiKanriFlg = CommonConstants.STR_FALSE;
		}
		
		return null;
	}

	public boolean chkAuthority(Integer iEmpID, String i_chkAutho) throws Exception{
		
		gruList = gruDao.selectByEmployeeId(iEmpID);
		
		if(gruList == null || gruList.length == 0){
			return false;
		}
		
		TSetting ety_set = setDao.selectById(CommonConstants.k1_system, 
				                             CommonConstants.k2_authority, 
				                             CommonConstants.k3_groupmarge);
		
		TSetting ety_set2 = setDao.selectById(CommonConstants.k1_system, 
				                              CommonConstants.k2_authority, 
				                              i_chkAutho);
		
		Integer AuthoValue = 0;	
		
		switch(ety_set.integervalue ){
			case 0:
				AuthoValue = Integer.parseInt(gruList[0].groupauthority);
				
				break;
				
			case 1:
				for(int i = 0;i < gruList.length;i++){
					AuthoValue = AuthoValue | Integer.parseInt(gruList[i].groupauthority);
				}
				
				break;
				
			case 2:
				for(int i = 0;i < gruList.length;i++){
					if(i == 0){
						AuthoValue = Integer.parseInt(gruList[i].groupauthority);
					}else{
						AuthoValue = AuthoValue & Integer.parseInt(gruList[i].groupauthority);
					}
					
				}
				break;
				
			default :
				break;
		}
		
		if((AuthoValue & ety_set2.integervalue ) == ety_set2.integervalue){
			return true;
		}else{
			return false;
		}
	
	}
	
	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
}
