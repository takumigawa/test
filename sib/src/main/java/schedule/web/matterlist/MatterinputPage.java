package schedule.web.matterlist;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MCustomerDao;
import schedule.dao.MEmployeeDao;
import schedule.dao.TCommericalDao;
import schedule.dao.TMatterDao;
import schedule.dao.TSettingDao;
import schedule.dto.ListDto;
import schedule.entity.TCommerical;
import schedule.entity.TMatter;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>社員マスタ新規/更新Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class MatterinputPage {

	/** セッション情報Dto */
	@Binding
	public schedule.dto.SessionDto sessionDto;
	
	/** 選択案件ＩＤ */
	@SubapplicationScope
	public Integer selectmatID;
	
	/** 入力項目変数 */
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 30)
	public String MatterTitleStr;
	public String MatteraddressStr;
	public String MatterStartStr;
	public String MatterEndStr;
	public String MatternoteStr;
	
	public String employeeID;
	public ListDto[] employeeIDItems;
	
	public String no1cus;
	public ListDto[] no1cusItems;
	public String no2cus;
	public ListDto[] no2cusItems;
	public String no3cus;
	public ListDto[] no3cusItems;
	public String no4cus;
	public ListDto[] no4cusItems;
	public String no5cus;
	public ListDto[] no5cusItems;
	public String no6cus;
	public ListDto[] no6cusItems;
	public String no7cus;
	public ListDto[] no7cusItems;
	public String no8cus;
	public ListDto[] no8cusItems;
	public String no9cus;
	public ListDto[] no9cusItems;
	public String no10cus;
	public ListDto[] no10cusItems;
	public String no11cus;
	public ListDto[] no11cusItems;

	public String EmployeeemailStr;
	public String orderSEQStr;
	
	/** 更新メッセージ表示用 */
	public String UpdateMes;
	
	/** HTML処理判別用 */
	public String dOru;
	
	/** エラーメッセージ用 */
	public String retErrMess;

	/** 社員マスタDao */
	public MEmployeeDao empDao;

	/** 顧客マスタDao */
	public MCustomerDao cusDao;

	/** 案件情報テーブルDao */
	public TMatterDao matDao;

	/** 商流情報テーブルDao */
	public TCommericalDao commDao;
	
	/** 汎用登録テーブルDao */
	public TSettingDao setDao;
	
	/**
	 * <p>更新メソッド</p>
	 * @return 社員一覧画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> doFinishUpdate() {
		
		try{
			
			TMatter ety_mat;
			
			if(selectmatID == -1){
				
				//社員情報新規作成
				
				ety_mat = new TMatter();
				
				ety_mat.employeeId		= Integer.valueOf(employeeID);
				ety_mat.customerId		= 0;
				ety_mat.matterAddress1	= MatteraddressStr;
				ety_mat.matterTitel		= MatterTitleStr;
				ety_mat.matterStart		= MatterStartStr;
				ety_mat.matterEnd		= MatterEndStr;
				ety_mat.matterNote		= MatternoteStr;
				
				ety_mat.kaihai			= CommonConstants.Kaihai_true;
				
				ety_mat.adddate 		= CommonUtil.getNowUpdDate();
				ety_mat.addid			= sessionDto.EmployeeID;
				
				matDao.insert(ety_mat);
				
			}else{
				
				//社員情報更新
				

			}
			
		}catch(Exception e){
			return AbendPage.class;
			
		}

		return MatterlistPage.class;
		
	}

	/**
	 * <p>戻るメソッド</p>
	 * @return 社員一覧画面遷移
	 *
	 */
	public Class<?> doFinishReturn() {
		return MatterlistPage.class;
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
			
			//社員名リスト作成
			employeeIDItems = empDao.selectGetEmpCombList();
			
			//顧客リスト作成
			ListDto comList[] = cusDao.selectGetCusCombList();
			
			no1cusItems 	= comList;
			no2cusItems 	= comList;
			no3cusItems 	= comList;
			no4cusItems 	= comList;
			no5cusItems 	= comList;
			no6cusItems 	= comList;
			no7cusItems 	= comList;
			no8cusItems 	= comList;
			no9cusItems 	= comList;
			no10cusItems 	= comList;
			no11cusItems 	= comList;
			
			if(selectmatID == -1){
				
				dOru = "Ins";
				
			}else{
				
				TMatter ety_mat	= matDao.selectById(selectmatID);
				
				employeeID			= String.valueOf(ety_mat.employeeId);
				MatteraddressStr	= ety_mat.matterAddress1;
				MatterTitleStr		= ety_mat.matterTitel;
				MatterStartStr		= ety_mat.matterStart;
				MatterEndStr		= ety_mat.matterEnd;
				MatternoteStr		= ety_mat.matterNote;
				
				if(ety_mat.commericalId != null){
					TCommerical ety_comm[] = commDao.selectByIdList(ety_mat.commericalId);

					for(int i = 0;i < ety_comm.length;i++){
						
						
						
					}
				}
				
				
			}
			
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
		
		if(selectmatID == -1){
			return false;
		}else{
			return true;
		}
	}
}
