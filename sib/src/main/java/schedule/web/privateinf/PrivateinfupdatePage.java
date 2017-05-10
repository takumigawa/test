package schedule.web.privateinf;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.validator.Email;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MEmployeeDao;
import schedule.entity.MEmployee;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>社員マスタ新規/更新Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class PrivateinfupdatePage {

	/** セッション情報Dto */
	@Binding
	public schedule.dto.SessionDto sessionDto;
	
	/** ログイン社員情報 */
	public String employee_id;
	public String employee_name;
	public String employee_mail;
	
	/** 入力項目変数 */
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 30)
	public String EmployeenameStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 60)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_KANA, messageId = "err.NoMatchHANKANA")
	public String EmployeenamekanaStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 30)
	public String EmployeeloginidStr;
	
	@Length(target="doFinishUpdate", maximum = 32)
	@Email(target="doFinishUpdate", messageId = "err.NoMatchEMailMes")
	public String EmployeeemailStr;
	
	@Length(target="doFinishUpdate", maximum = 8)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_POSTCODE_CHECK, messageId = "err.NoMatchPostCodeMes")
	public String EmployeepostcodeStr;
	
	@Length(target="doFinishUpdate", maximum = 60)
	public String Employeeaddress1Str;
	
	@Length(target="doFinishUpdate", maximum = 30)
	public String Employeeaddress2Str;
	
	@Length(target="doFinishUpdate", maximum = 20)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_TELNO_CHECK, messageId = "err.NoMatchTelNoMes")
	public String EmployeetelStr;
	
	@Length(target="doFinishUpdate", maximum = 20)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_TELNO_CHECK, messageId = "err.NoMatchTelNoMes")
	public String EmployeemphoneStr;
	
	@Length(target="doFinishUpdate", maximum = 20)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_TELNO_CHECK, messageId = "err.NoMatchTelNoMes")
	public String EmployeefaxStr;
	
	/** 更新メッセージ表示用 */
	public String UpdateMes;

	/** 社員マスタDao */
	public MEmployeeDao empDao;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> doFinishUpdate(){
		
		try{
			
			MEmployee ety_emp = empDao.selectById(sessionDto.EmployeeID);
			
			ety_emp.employeeName		= EmployeenameStr;
			ety_emp.employeeNamekana	= EmployeenamekanaStr;
			ety_emp.loginid				= EmployeeloginidStr;
			ety_emp.employeePostcode	= EmployeepostcodeStr;
			ety_emp.employeeAddress1	= Employeeaddress1Str;
			ety_emp.employeeAddress2	= Employeeaddress2Str;
			ety_emp.employeeTel			= EmployeetelStr;
			ety_emp.employeeMphone		= EmployeemphoneStr;
			ety_emp.employeeFax			= EmployeefaxStr;
						
			//更新日付、更新者ID設定
			ety_emp.upddate = CommonUtil.getNowUpdDate();
			ety_emp.updid	= sessionDto.EmployeeID;
			
			empDao.update(ety_emp);
			
		}catch(Exception e){
			return AbendPage.class;
			
		}
		
		return PrivateinfPage.class;
		
	}

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doFinishReturn() {
		return PrivateinfPage.class;
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
			
			MEmployee ety_emp = empDao.selectById(sessionDto.EmployeeID);
			
			EmployeenameStr		= ety_emp.employeeName;
			EmployeenamekanaStr	= ety_emp.employeeNamekana;
			EmployeeloginidStr	= ety_emp.loginid;
			EmployeeemailStr	= ety_emp.employeeEmail;
			EmployeepostcodeStr	= ety_emp.employeePostcode;
			Employeeaddress1Str	= ety_emp.employeeAddress1;
			Employeeaddress2Str	= ety_emp.employeeAddress2;
			EmployeetelStr		= ety_emp.employeeTel;
			EmployeemphoneStr	= ety_emp.employeeMphone;
			EmployeefaxStr		= ety_emp.employeeFax;
			
		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		
		//ログイン情報
		employee_id 	= sessionDto.EmployeeID.toString();
		employee_name	= sessionDto.EmployeeName;
		employee_mail	= sessionDto.EmployeeEmail;
		
		return null;
	}

}
