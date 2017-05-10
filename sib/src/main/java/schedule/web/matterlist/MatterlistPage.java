package schedule.web.matterlist;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MEmployeeDao;
import schedule.dao.TMatterDao;
import schedule.dao.TSettingDao;
import schedule.dto.ListDto;
import schedule.dto.MatListDto;
import schedule.dto.SessionDto;
import schedule.web.common.*;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;
import schedule.web.masterMaintenance.MastermaintePage;

/**
 * <p>社員マスタ一覧Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class MatterlistPage {

	/** ログイン社員情報 */
	public String employee_id;
	public String employee_name;
	public String employee_mail;
	
	/** 入力項目変数 */
	public String matEmployeeName;
	public String matMatterName;
	public String matMatterAddress;
	public String matBetween;
	public String matnote;
	public boolean matEnable;
	
	/** 案件一覧Index */
	public int matIndex;
	/** 案件一覧 */
	@PageScope
	public MatListDto[] matItems;
	
	/** 有効/無効値 */
	@PageScope
	@Required
	public Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	public ListDto[] kaihaiItems;

	/** 社員ID */
	@PageScope
	public String employeeID;
	/** 社員一覧 */
	@PageScope
	public ListDto[] employeeIDItems;
	
	/** 行絞り込み値 */
	@PageScope
	@Required
	public String kanaGyo;
	/** 行絞り込み一覧 */
	@PageScope
	public ListDto[] kanaGyoItems;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** 案件Dao */
	public TMatterDao matDao;
	
	/** 汎用登録テーブルDao */
	public TSettingDao setDao;

	/** 社員マスタDao */
	public MEmployeeDao empDao;
	
	/** 選択案件ＩＤ */
	@SubapplicationScope
	public Integer selectmatID;
	
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
			
			//検索条件-有効/無効コンボ
			kaihaiItems = setDao.selectByCombList(CommonConstants.COMB_kaihai);

			//検索条件-ひらがな検索コンボ
			kanaGyoItems = setDao.selectByCombList(CommonConstants.COMB_kana);
			
			//検索条件-社員検索コンボ
			employeeIDItems  = empDao.selectGetEmpCombList();
			
			//社員一覧
			matItems = matDao.selectGetMatList(null,null,CommonConstants.Kaihai_true);
			
		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}

	/**
	 * <p>事前画面描写メソッド</p>
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
	
	/**
	 * <p>一覧絞り込みメソッド/p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doSrech(){
		
		//社員一覧
		matItems = matDao.selectGetMatList(kanaGyo, employeeID, kaihai);
		return null;
	}
	
	/**
	 * <p>一覧絞り込み解除メソッド/p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doSrechCancel(){
		kaihai		= CommonConstants.Kaihai_true;
		kanaGyo 	= CommonConstants.COMB_kaihai_init;
		employeeID	= null;
		
		//社員一覧
		matItems = matDao.selectGetMatList(kanaGyo, employeeID, kaihai);
		return null;
	}
	
	/**
	 * <p>社員削除メソッド/p>
	 * @return 社員削除画面遷移
	 *
	 */
	public Class<?> doDelete(){
		
		try{
			
			selectmatID = Integer.parseInt(matItems[matIndex].matID );
			
		}catch(Exception e){
			return AbendPage.class;
		}

		return MatterdeletePage.class;
		
	}
		
	/**
	 * <p>社員編集メソッド/p>
	 * @return 社員編集/登録画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> doDetails(){
	
		try{
			selectmatID = Integer.parseInt(matItems[matIndex].matID );

		}catch(Exception e){
			return AbendPage.class;
		}
			
		return MatterdetailsPage.class;
	}

	/**
	 * <p>社員追加メソッド</p>
	 * @return 社員編集/登録画面遷移
	 *
	 */
	public Class<?> doEmployeeAdd(){
		selectmatID = -1;
		return MatterinputPage.class;
	}
	
	/**
	 * <p>前画面以降メソッド</p>
	 * @return マスターメンテナンス一覧画面遷移
	 *
	 */
	public Class<?> doReturn(){
		
		return MastermaintePage.class;
	}
}
