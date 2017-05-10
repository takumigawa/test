package schedule.web.topics;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dto.ListDto;
import schedule.dto.SessionDto;
import schedule.dto.TipsListDto;
import schedule.logic.TipslistLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>トピックス一覧画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class TopicslistPage {

	/** ログイン社員情報 */
	@PageScope
	public String employee_id;
	@PageScope
	public String employee_name;
	@PageScope
	public String employee_mail;
	
	/** 表示項目編集 */
	public String tipsID;		// トピックスID
	public String startDate;	// 掲載開始日
	public String endDate;		// 掲載終了日
	public String note;			// 内容
	public String priority;		// 優先順位
	
	/** 選択された開始日 */
	public String searchStartDate;
	/** 選択された終了日 */
	public String searchEndDate;
	
	/** 選択された社員 */
	@Required
	public String empName;
	/** 社員一覧 */
	public ListDto[] empNameItems;
	
	/** トピックス一覧 */
	public TipsListDto[] topicsItems;
	/** トピックス一覧インデックス */
	public int topicsIndex;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** ファイル有無フラグ */
	public boolean fileFlg;
	/** 遷移フラグ */
	@SubapplicationScope
	public String changeFlg;

	/** トピックス一覧画面ロジッククラス */
	public TipslistLogic tipsListLogic;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> initialize() {
		
		try {
			
			// 未ログインユーザーをはじく処理
			if(sessionDto.EmployeeID == null){
				return NonloginPage.class;
			}
			
			// 遷移フラグに"List"を設定
			changeFlg = "List";
			
			// ログイン情報
			employee_id 	= sessionDto.EmployeeID.toString();
			employee_name	= sessionDto.EmployeeName;
			employee_mail	= sessionDto.EmployeeEmail;
			
			// 社員一覧の取得
			empNameItems = tipsListLogic.getEmpList();
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}

	/**
	 * <p>事前描写メソッド</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> prerender() {
		
		try {
		
			// 事前描写処理
			topicsItems = tipsListLogic.getTipsList(searchStartDate, searchEndDate, empName);
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}

	/**
	 * <p>検索ボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doSearch() {
		
		return null;
	}
	
	/**
	 * <p>検索解除ボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doSearchCancel() {
		
		try {
			
			// 検索項目の初期化
			searchStartDate	= null;		// 開始日
			searchEndDate	= null;		// 終了日
			empName			= null;		// 選択された社員
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>新規追加ボタン押下時処理</p>
	 * 
	 * @return トピックス登録画面に遷移
	 */
	public Class<?> doTopicsAdd() {
		
		try {
			
			// トピックスIDに"-1"を設定
			tipsID = "-1";
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return TopicsinputPage.class;
	}
	
	/**
	 * <p>詳細ボタン押下時処理</p>
	 * 
	 * @return トピックス登録画面に遷移
	 */
	public Class<?> doView() {
		
		try {
			
			// 選択された行のトピックスIDを取得
			tipsID = topicsItems[topicsIndex].tipsID;
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return TopicsviewPage.class;
	}
	
	/**
	 * <p>添付ファイル有画像制御処理</p>
	 * 
	 * @return　ファイルフラグ
	 */
	public boolean isFile() {
		
		return fileFlg;
	}
}
