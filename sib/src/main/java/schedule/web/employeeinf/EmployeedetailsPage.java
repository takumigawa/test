package schedule.web.employeeinf;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import schedule.dao.MEmployeeDao;
import schedule.dao.TSettingDao;
import schedule.entity.MEmployee;
import schedule.entity.TSetting;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>社員マスタ新規/更新Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class EmployeedetailsPage {

	/** セッション情報Dto */
	@Binding
	public schedule.dto.SessionDto sessionDto;
	
	/** 選択社員ＩＤ */
	@SubapplicationScope
	public Integer selectempID;
	
	/** 表示項目変数 */
	public String EmployeeNameStr;
	public String EmployeeNamekanaStr;
	public String EmployeeclassStr;
	public String EmployeeemailStr;
	public String Employeeemail2Str;
	public String Employeeemail3Str;
	public String EmployeepostcodeStr;
	public String Employeeaddress1Str;
	public String Employeeaddress2Str;
	public String EmployeetelStr;
	public String EmployeemphoneStr;
	public String EmployeefaxStr;

	/** 汎用登録テーブルDao */
	public TSettingDao setDao;

	/** 社員マスタDao */
	public MEmployeeDao empDao;
	
	/**
	 * <p>戻るメソッド</p>
	 * @return 社員一覧画面遷移
	 *
	 */
	public Class<?> doFinishReturn() {
		return EmployeeinfPage.class;
	}

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {
		
		try{
			
			// 未ログインユーザーをはじく処理
			if(sessionDto.EmployeeID == null){
				return NonloginPage.class;
			}
			
			MEmployee ety_emp = empDao.selectById(selectempID);
			TSetting ety_set = setDao.selectById("comblist", "class", String.valueOf(ety_emp.employeePositionId) );
			
			EmployeeNameStr			= ety_emp.employeeName;
			EmployeeNamekanaStr		= ety_emp.employeeNamekana;
			
			if(ety_emp.employeePositionId != null){
				EmployeeclassStr		= ety_set.stringvalue;
			}else{
				EmployeeclassStr		= "";
			}
			
			EmployeeemailStr		= ety_emp.employeeEmail;
			Employeeemail2Str		= ety_emp.employeeEmail2;
			Employeeemail3Str		= ety_emp.employeeEmail3;
			
			if(ety_emp.employeePostcode != null){
				EmployeepostcodeStr		= ety_emp.employeePostcode;
			}else{
				EmployeepostcodeStr		= "　";
			}
			if(ety_emp.employeeAddress1 != null){
				Employeeaddress1Str		= ety_emp.employeeAddress1;
			}else{
				Employeeaddress1Str		= "　";
			}
			if(ety_emp.employeeAddress2 != null){
				Employeeaddress2Str		= ety_emp.employeeAddress2;
			}else{
				Employeeaddress2Str		= "　";
			}
			
			EmployeetelStr			= ety_emp.employeeTel;
			EmployeemphoneStr		= ety_emp.employeeMphone;
			EmployeefaxStr			= ety_emp.employeeFax;

			
			
		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}

	/**
	 * <p>事前描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		return null;
	}

}
