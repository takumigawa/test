package schedule.web.privateinf;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.validator.Equal;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MEmployeeDao;
import schedule.entity.MEmployee;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>社員マスタ新規/更新Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class PrivatepasswordPage {

	/** セッション情報Dto */
	@Binding
	public schedule.dto.SessionDto sessionDto;
	
	/** ログイン社員情報 */
	public String employee_id;
	public String employee_name;
	public String employee_mail;
	
	/** 入力項目変数 */
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", minimum = 6, maximum = 20, minimumMessageId="err.NoMatchPassLength" ,maximumMessageId="err.NoMatchPassLength")
	public String loginPasswordStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", minimum = 6, maximum = 20, minimumMessageId="err.NoMatchPassLength" ,maximumMessageId="err.NoMatchPassLength")
	public String loginnewPasswordStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", minimum = 6, maximum = 20, minimumMessageId="err.NoMatchPassLength" ,maximumMessageId="err.NoMatchPassLength")
	@Equal(targetId = "loginnewPasswordStr")
	public String loginnewPasswordConfStr;
	
	public static final String chkPass_TEqualValidator = "targetId='loginPasswordStr'";
	public String chkPass;
	
	/** エラーメッセージ用 */
	public String retErrMess;

	/** 社員マスタDao */
	public MEmployeeDao empDao;
		
	/**
	 * <p>更新メソッド</p>
	 * @return 社員一覧画面遷移
	 *
	 */
	public Class<?> doFinishUpdate() {
		
		retErrMess = "";
		
		try{
			
			MEmployee ety_emp = empDao.selectById(sessionDto.EmployeeID);
			/*
			//現在のパスワードチェック
			if(!ety_emp.loginpassword.endsWith(loginPasswordStr)){
				retErrMess = "現在のパスワードが間違っています。";
				return null;
			}
			*/
			ety_emp.loginpassword = loginnewPasswordStr;
			
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
	 * <p>戻るメソッド</p>
	 * @return 社員一覧画面遷移
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
			
			// 未ログインユーザーをはじく処
			if(sessionDto.EmployeeID == null){
				return NonloginPage.class;
			}
			
			MEmployee ety_emp = empDao.selectById(sessionDto.EmployeeID);

			chkPass =ety_emp.loginpassword;
			
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
		
		//ログイン情報
		employee_id 	= sessionDto.EmployeeID.toString();
		employee_name	= sessionDto.EmployeeName;
		employee_mail	= sessionDto.EmployeeEmail;
		
		return null;
	}

}
