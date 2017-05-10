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
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>顧客マスタ詳細Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class MatterdetailsPage {

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
	public Class<?> doUpdate() {
			
		return MatterinputPage.class;
		
	}

	/**
	 * <p>戻るボタン押下メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doReturn() {
		return MatterlistPage.class;
	}

	/**
	 * <p>有/無ボタン押下メソッド</p>
	 * @return 有効/無効切り替え画面遷移
	 *
	 */
	public Class<?> doDelete() {
		return MatterdeletePage.class;
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

}
