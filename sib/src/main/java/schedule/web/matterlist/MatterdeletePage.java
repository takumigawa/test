package schedule.web.matterlist;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;

import schedule.dao.MEmployeeDao;
import schedule.dao.TCommericalDao;
import schedule.dao.TMatterDao;
import schedule.dto.ComListDto;
import schedule.entity.MEmployee;
import schedule.entity.TMatter;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>顧客マスタ詳細Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class MatterdeletePage {

	/** セッション情報Dto */
	@Binding
	public schedule.dto.SessionDto sessionDto;
	
	/** 選択顧客ＩＤ */
	@PageScope
	public Integer selectmatID;
	
	/** 入力項目変数 */
	public String EmployeeNameStr;
	public String MatterTitleStr;
	public List<ComListDto> cdItems;
	public String userName;
	public String MatteraddressStr;
	public String MatterbetweenStr;
	public String MatternoteStr;

	/** 有効/無効判断用 */
	public boolean bolEnable;
	
	/** 社員マスタDao */
	public MEmployeeDao empDao;
	
	/** 案件テーブルDao */
	public TMatterDao matDao;

	/** 商流テーブルDao */
	public TCommericalDao commDao;
	
	/**
	 * <p>編集ボタン押下メソッド</p>
	 * @return 更新画面遷移
	 *
	 */
	public Class<?> doFinishUpdate() {
		
		try{
			
			TMatter ety_mat = matDao.selectById(selectmatID);
			
			//更新日付、更新者ID
			ety_mat.upddate	= CommonUtil.getNowUpdDate();
			ety_mat.updid	= sessionDto.EmployeeID;
			
			//改廃設定
			if(ety_mat.kaihai == 0){
				ety_mat.kaihai = CommonConstants.Kaihai_false;
			}else{
				ety_mat.kaihai = CommonConstants.Kaihai_true;
			}
			
			matDao.update(ety_mat);
			
		}catch(Exception e){
			return AbendPage.class;
		}
	
		return MatterlistPage.class;
		
	}

	/**
	 * <p>戻るボタン押下メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doFinishReturn() {
		return MatterlistPage.class;
	}
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> initialize() {
		
		try{
			
			// 未ログインユーザーをはじく処理
			if(sessionDto.EmployeeID == null){
				return NonloginPage.class;
			}
			
			TMatter ety_mat = matDao.selectById(selectmatID);
			
			//案件情報表示処理
			MatteraddressStr 	= ety_mat.matterAddress1;
			MatterTitleStr		= ety_mat.matterTitel;
			
			//期間表示
			StringBuilder sb = new StringBuilder();
			
			if(ety_mat.matterEnd != null){
				sb.append(ety_mat.matterStart);
				sb.append("　～　");
				sb.append(ety_mat.matterEnd);
			}else{
				sb.append(ety_mat.matterStart);
			}
			MatterbetweenStr	= sb.toString();
			
			//案件内容
			MatternoteStr		= ety_mat.matterNote;
			
			//社員情報表示
			MEmployee ety_emp	= empDao.selectById(ety_mat.employeeId);
			
			EmployeeNameStr		= ety_emp.employeeName;
			
			//商流情報表示
			cdItems = new ArrayList<ComListDto>();
			
			ComListDto comli[] = commDao.selectNameById(ety_mat.commericalId);
						
			int i;
			ComListDto comItem;
			String tmpStt = "";
			
			for(i = 0;i < comli.length;i++){
				comItem = new ComListDto();

				comItem.userName = tmpStt + comli[i].userName;
				
				if(i == 0){
					tmpStt = "　┗　";
				}else{
					tmpStt = "　　" + tmpStt;
				}
				
				cdItems.add(comItem);
				
			}

			//有効/無効判断用フラグ設定
			if(ety_mat.kaihai == 0){
				bolEnable = true;
			}else{
				bolEnable = false;
			}
			
			
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
		return null;
	}

	/**
	 * <p>有効/無効判定メソッド</p>
	 * @return boolean true:有効　false:無効
	 *
	 */
	public boolean isEnable(){
		return bolEnable;
	}
	
}
