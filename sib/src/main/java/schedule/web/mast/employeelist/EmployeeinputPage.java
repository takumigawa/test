package schedule.web.mast.employeelist;

import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Email;
import org.seasar.teeda.extension.annotation.validator.Equal;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MEmployeeDao;
import schedule.dao.MGroupDao;
import schedule.dao.TGroupattachDao;
import schedule.dao.TSettingDao;
import schedule.dto.ListDto;
import schedule.entity.MEmployee;
import schedule.entity.TGroupattach;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.validator.NewUserRequierdPasValidator;
import schedule.validator.UseUserIDValidator;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;

/**
 * <p>社員マスタ新規/更新Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class EmployeeinputPage extends PageBaceClass {
	
	/** 選択社員ＩＤ */
	@SubapplicationScope
	public Integer selectempID;
	
	/** 入力項目変数 */
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 30)
	public String EmployeeNameStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 60)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_KANA, messageId = "err.NoMatchHANKANA")
	public String EmployeeNamekanaStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 30)
	@UseUserIDValidator(target="doFinishUpdate", targetId = "selectempID")
	public String loginIDStr;
	
	@NewUserRequierdPasValidator(target="doFinishUpdate", targetId = "selectempID")
	@Length(target="doFinishUpdate", minimum = 6, maximum = 20, minimumMessageId="err.NoMatchPassLength" ,maximumMessageId="err.NoMatchPassLength")
	public String loginPasswordStr;

	@NewUserRequierdPasValidator(target="doFinishUpdate", targetId = "selectempID")
	@Length(target="doFinishUpdate", minimum = 6, maximum = 20, minimumMessageId="err.NoMatchPassLength" ,maximumMessageId="err.NoMatchPassLength")
	@Equal(targetId = "loginPasswordStr")
	public String loginPasswordConfStr;
	
	public String PositionCd;
	public ListDto[] PositionCdItems;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	public String GroupId;
	public ListDto[] GroupIdItems;
	
	@Length(target="doFinishUpdate", maximum = 32)
	@Email(target="doFinishUpdate", messageId = "err.NoMatchEMailMes")
	public String EmployeeemailStr;

	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 6)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_SUJI, messageId = "err.NoMatchEISU")
	public String orderSEQStr;
	
	/** 有効/無効値 */
	@PageScope
	@Required
	private Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	private List<ListDto> kaihaiItems;
	
	/** 旧グループID */
	public int OldGroupId;
	
	/** 移籍フラグ */
	public boolean flg;
	
	/** 更新メッセージ表示用 */
	public String UpdateMes;
	
	/** HTML処理判別用 */
	public String dOru;
	
	/** エラーメッセージ用 */
	public String retErrMess;

	/** 汎用登録テーブルDao */
	public TSettingDao setDao;

	/** 社員マスタDao */
	public MEmployeeDao empDao;
	
	/** グループマスタDao */
	public MGroupDao gruDao;
	
	/** グループ所属テーブルDao */
	public TGroupattachDao tGruDao;
	
	/** 改廃index　セッタ/ゲッタ */
	public void setKaihai(Integer kaihai){
		this.kaihai = kaihai;
	}
	public Integer getKaihai(){
		return this.kaihai;
	}
	
	/** 改廃　セッタ/ゲッタ */
	public void setKaihaiItems(List<ListDto> kaihaiItems){
		this.kaihaiItems = kaihaiItems;
	}
	public List<ListDto> getKaihaiItems(){
		return this.kaihaiItems;
	}
	
	/** 選択ID */
	public void setSelectempID(String selectempID) {
		this.selectempID = Integer.parseInt(selectempID);
	}
	public String getSelectempID(){
		return selectempID.toString();
	}
	
	/**
	 * <p>更新メソッド</p>
	 * @return 社員一覧画面遷移
	 *
	 */
	public Class<?> doFinishUpdate() {
		
		try{
			
			MEmployee ety_emp;
			TGroupattach TGruatt;
			
			if(selectempID == -1){
				
				//社員情報新規作成
				
				ety_emp = new MEmployee();
				
				ety_emp.employeeName 		= EmployeeNameStr;
				ety_emp.employeeNamekana 	= EmployeeNamekanaStr;
				ety_emp.loginid 			= loginIDStr;
				ety_emp.loginpassword 		= loginPasswordStr;
				
				if(PositionCd != null){
					ety_emp.employeePositionId 	= Integer.parseInt(PositionCd);
				}else{
					ety_emp.employeePositionId 	= null;
				}
				
				ety_emp.defaultGroupId		= Integer.parseInt(GroupId);
				
				ety_emp.employeeEmail 		= EmployeeemailStr;
				
				ety_emp.orderseq 			= Integer.parseInt(orderSEQStr);
				
				ety_emp.adddate				= CommonUtil.getNowUpdDate();
				ety_emp.addid				= sessionDto.EmployeeID;
				
				empDao.insert(ety_emp);
				
				ety_emp = empDao.selectByIDPas(ety_emp.loginid, ety_emp.loginpassword);
				
				UpdateMes = "新規追加しました。";
				
			}else{
				
				//社員情報更新
				
				ety_emp = empDao.selectById(selectempID);
				
				ety_emp.employeeName 		= EmployeeNameStr;
				ety_emp.employeeNamekana 	= EmployeeNamekanaStr;
				ety_emp.loginid 			= loginIDStr;
				
				//パスワード採用チェック
				if(loginPasswordStr != null && loginPasswordStr != ""){
					ety_emp.loginpassword 		= loginPasswordStr;
				}
				
				if(PositionCd != null){
					ety_emp.employeePositionId 	= Integer.parseInt(PositionCd);
				}else{
					ety_emp.employeePositionId 	= null;
				}
				
				ety_emp.defaultGroupId		= Integer.parseInt(GroupId);
				
				ety_emp.employeeEmail 		= EmployeeemailStr;
				
				ety_emp.orderseq 			= Integer.parseInt(orderSEQStr);
				
				ety_emp.kaihai              = kaihai;
				
				ety_emp.upddate				= CommonUtil.getNowUpdDate();
				ety_emp.updid				= sessionDto.EmployeeID;
				
				empDao.update(ety_emp);
				
				//移籍の場合、グループ所属テーブルの所属の削除
				TGruatt = new TGroupattach(); 
				TGruatt = tGruDao.selectById(OldGroupId, ety_emp.employeeId);
				if (ety_emp.defaultGroupId != TGruatt.groupId) {
					System.out.println("ddd");
					if (flg && TGruatt != null) {
						System.out.println("eee");
						tGruDao.delete(TGruatt);
					}
				}
			}
			
			//グループ所属テーブルに所属の追加
			TGruatt = new TGroupattach();
			TGruatt = tGruDao.selectById(ety_emp.defaultGroupId, ety_emp.employeeId);
			
			if (TGruatt == null) {
				
				TGruatt = new TGroupattach();
				TGruatt.groupId				= ety_emp.defaultGroupId;
				TGruatt.employeeId			= ety_emp.employeeId;
				
				tGruDao.insert(TGruatt);
			}
			
			UpdateMes = "更新しました。";
			
		}catch(Exception e){
			return AbendPage.class;
			
		}

		return null;
		
	}

	/**
	 * <p>戻るメソッド</p>
	 * @return 社員一覧画面遷移
	 *
	 */
	public Class<?> doFinishReturn() {
		return EmployeelistPage.class;
	}

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {
		
		try{
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			kaihaiItems = CommonUtil.getServiceToCombData();
			
			//代表者の役職名リスト作成
			PositionCdItems = setDao.selectByCombList(CommonConstants.COMB_class);
			
			//グループ名リスト作成
			GroupIdItems = gruDao.selectGetGroupCombList();
			
			if(selectempID == -1){
				
				orderSEQStr = String.valueOf(empDao.getNextOrderSEQ());
				dOru = "Ins";
				
			}else{
				
				MEmployee ety_emp = empDao.selectById(selectempID);
				
				EmployeeNameStr 	= ety_emp.employeeName;
				EmployeeNamekanaStr	= ety_emp.employeeNamekana;
				loginIDStr			= ety_emp.loginid;
				if(ety_emp.employeePositionId != null){
					PositionCd		= String.valueOf(ety_emp.employeePositionId);
				}else{
					PositionCd		= null;
				}
				OldGroupId			= ety_emp.defaultGroupId;
				GroupId				= String.valueOf(OldGroupId);
				EmployeeemailStr	= ety_emp.employeeEmail;
				orderSEQStr 		= String.valueOf(ety_emp.orderseq);
				
				kaihai              = ety_emp.kaihai;
				
				flg					= true;
				
				dOru = "Upd";
				
			}
			
		} catch (AnLoginException e) {
			// 未ログインエラー
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			//マスタメンテ権限エラー
			return NonauthorityPage.class;
			
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

	/**
	 * <p>画面登録/新規切り替え判定メソッド</p>
	 * @return true 更新/false 新規
	 *
	 */
	public boolean isUpdate(){
		
		if(selectempID == -1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * マスタメンテナンス用レイアウト適用
	 * @return
	 */
	public String getLayout(){
		return CommonConstants.MAST_LAOUT_URL;
	}
}
