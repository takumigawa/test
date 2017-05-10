package schedule.web.topics;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import schedule.dao.TTipsDao;
import schedule.dto.FileListDto;
import schedule.dto.SessionDto;
import schedule.entity.TTips;
import schedule.logic.TipsviewLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;
import schedule.web.schedulelist.WeekSchedulePage;

/**
 * <p>トピックス詳細画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class TopicsviewPage {
	
	/** 表示項目編集 */
	public String startDate;		// 掲載開始日
	public String endDate;			// 掲載終了日
	public String note;				// 内容
	public String empID;			// 作成者の社員ID
	public String createdEmpName;	// 作成者名
	public String priority;			// 優先順位
	public String fileName;			// ファイル名
	public int fileDeleteFlg;		// ファイル削除フラグ
	
	/** 選択されたファイルID */
	public String fileID;
	/** ファイルリスト */
	public FileListDto[] fileIDItems;
	/** ファイルリストのインデックス */
	public int fileIDIndex;
	
	/** 選択されたトピックスID */
	@SubapplicationScope
	public String tipsID;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** トピックステーブルDao */
	public TTipsDao tipsDao;
	
	/** 遷移フラグ */
	@SubapplicationScope
	public String changeFlg;
	
	/** HttpServletResponse */
	public HttpServletResponse response;
	/** FacesContext */
	public FacesContext facesContext;
	
	
	/** トピックス詳細画面ロジッククラス */
	public TipsviewLogic tipsViewLogic;

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
			
			// トピックス情報の取得
			TTips ety_tip = new TTips();
			int tipID = Integer.parseInt(tipsID);
			ety_tip = tipsDao.selectById(tipID);
			
			// 表示項目に値を設定
			startDate		= ety_tip.startdate;							// 掲載開始日
			endDate			= ety_tip.enddate;								// 掲載終了日
			note			= ety_tip.note;									// 内容
			empID			= Integer.toString(ety_tip.inputemployeeId);	// 作成者ID
			if(ety_tip.priority != null) {
				priority	= Integer.toString(ety_tip.priority);			// 優先順位
			}
			
			// ファイル情報の取得
			fileIDItems = new FileListDto[5];
			tipsViewLogic.fileInfo(fileIDItems, ety_tip);
			
			// 事前描写処理
			createdEmpName = tipsViewLogic.getEmpName(empID);
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>編集ボタン押下時処理</p>
	 * 
	 * @return トピックス登録画面に遷移
	 */
	public Class<?> doEdit() {
		
		return TopicsinputPage.class;
	}

	/**
	 * <p>削除ボタン押下時処理</p>
	 * 
	 * @return トピックス削除確認画面に遷移
	 */
	public Class<?> doDelete() {
		
		return TopicsconfPage.class;
	}
	
	/**
	 * <p>Downloadボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doDownload() {
		
		try {
			
			// ファイルダウンロード処理
			tipsViewLogic.fileDownload(response, facesContext, fileIDItems, fileIDIndex);
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>戻るボタン押下時処理</p>
	 * 
	 * @return 遷移元に遷移
	 */
	public Class<?> doCancel() {
		
		// 遷移フラグが"List"の場合、トピックス一覧画面に遷移
		if(changeFlg.equals("List")) {
			return TopicslistPage.class;
		}
		
		return WeekSchedulePage.class;
	}
	
	/**
	 * <p>ボタン制御処理</p>
	 * <p>管理者および、作成者のみ編集・削除ボタンを表示</p>
	 * 
	 * @return ログインした社員が管理者・作成者の場合、true
	 * 		　　管理者・作成者以外の場合、false
	 */
	public boolean isAuthority() {
		
		// ログインした社員が管理者の場合、trueを返す
		if(sessionDto.MastermainteFlg ) {
			return true;
		}
		
		// ログインした社員が作成者の場合、trueを返す
		if(empID.equals(sessionDto.EmployeeID)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * <p>添付ファイル有無判定処理</p>
	 * 
	 * @return　添付ファイルがある場合、true
	 * 		　　  ない場合、false
	 */
	public boolean isFile() {
		
		// ファイルIDがnull以外の場合、trueを返す
		if(fileID != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * <p>添付ファイル有画像制御メソッド</p>
	 * 
	 * @return 添付ファイルが自動削除されている場合、trueを返す
	 * 　　　　　　　自動削除されていない場合、falseを返す
	 */
	public boolean isFileDelete() {
		
		// ファイル削除フラグが2以外の場合、trueを返す
		if(fileDeleteFlg != 2) {
			return true;
		}
		
		return false;
	}
}
