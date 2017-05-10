package schedule.web.login;

import org.seasar.framework.container.annotation.tiger.Binding;

import schedule.dao.MEmployeeDao;
import schedule.dao.MGroupDao;
import schedule.dao.TSettingDao;
import schedule.dto.GroupListDto;
import schedule.dto.SessionDto;
import schedule.entity.MEmployee;
import schedule.entity.TSetting;
import schedule.validator.UserAuthValidator;
import schedule.web.common.CommonConstants;
import schedule.web.error.AbendPage;
import schedule.web.schedulelist.WeekSchedulePage;

/**
 * <p>ログインPageクラス</p>
 * @author T.fujimoto
 *
 */
public class LoginPage {
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	public String loginIDStr;
	
	@UserAuthValidator(targetId = "loginIDStr")
	public String loginPasswordStr;
	
	public MEmployeeDao empDao;
	
	public MGroupDao gruDao;
	
	public TSettingDao setDao;
	
	public  GroupListDto[] gruList;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {

		return null;
	}

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		return null;
	}
	
	public Class<?> doLogin(){
		
		try{

			MEmployee ety_emp = empDao.selectByIDPas(loginIDStr, loginPasswordStr);
			
			sessionDto.EmployeeID 		= ety_emp.employeeId;
			sessionDto.EmployeeName 	= ety_emp.employeeName;
			sessionDto.EmployeeEmail 	= ety_emp.employeeEmail;
			sessionDto.MastermainteFlg	= chkAuthority(CommonConstants.k3_groupmarge);
			sessionDto.KogutiKanriFlg   = chkAuthority(CommonConstants.k3_groupmarge);
						
			return WeekSchedulePage.class;
			
		}catch(Exception e){
			return AbendPage.class;
		}

	}
	
	
	public boolean chkAuthority(String i_chkAutho) throws Exception{
		
		gruList = gruDao.selectByEmployeeId(sessionDto.EmployeeID);
		
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
	
	public String getLayout(){
		return "/layout/loginlayout.html";
	}
	
}
