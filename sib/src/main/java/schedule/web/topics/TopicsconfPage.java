package schedule.web.topics;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import schedule.dao.TTipsDao;
import schedule.dto.FileListDto;
import schedule.dto.SessionDto;
import schedule.entity.TTips;
import schedule.logic.TipsconfLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;
import schedule.web.schedulelist.WeekSchedulePage;

/**
 * <p>トピックス削除確認画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class TopicsconfPage {

	/** 表示項目編集 */
	public String startDate;		// 掲載開始日
	public String endDate;			// 掲載終了日
	public String note;				// 内容
	public String createdEmpName;	// 作成者
	public String createdEmpID;		// 作成者ID
	public String priority;			// 優先順位
	public String fileName;			// ファイル名
	public int fileDeleteFlg;		// ファイル削除フラグ
	
	/** 選択されたファイルID */
	public String fileID;
	/** ファイルリスト */
	@PageScope
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
	
	/** トピックス削除確認画面ロジッククラス */
	public TipsconfLogic tipsConfLogic;
	
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
			int tipID = Integer.parseInt(tipsID);
			TTips ety_tip = tipsDao.selectById(tipID);
			
			// 取得したトピックス情報を表示項目に設定
			startDate		= ety_tip.startdate;							// 掲載開始日
			endDate			= ety_tip.enddate;								// 掲載終了日
			note			= ety_tip.note;									// 内容
			createdEmpID	= Integer.toString(ety_tip.inputemployeeId);	// 作成者ID
			createdEmpName	= tipsConfLogic.getEmpName(createdEmpID);		// 作成者名
			
			// 優先順位が設定されている場合、表示項目に設定
			if(ety_tip.priority != null) {
				priority	= Integer.toString(ety_tip.priority);			// 優先順位
			}
			
			// トピックスに添付されたファイル情報の取得
			fileIDItems = new FileListDto[5];
			tipsConfLogic.fileInfo(fileIDItems, ety_tip);
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>削除ボタン押下時処理</p>
	 * 
	 * @return　遷移元に遷移
	 */
	public Class<?> doDelete() {
		
		try {
			
			// トピックス削除処理
			tipsConfLogic.tipsDelete(fileIDItems, tipsID);
		
			// 遷移フラグが"List"の場合、トピックス一覧画面に遷移
			if(changeFlg.equals("List")) {
				return TopicslistPage.class;
			}
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return WeekSchedulePage.class;
	}
	
	/**
	 * <p>戻るボタン押下時処理</p>
	 * 
	 * @return トピックス詳細画面に遷移
	 */
	public Class<?> doCancel() {
		
		return TopicsviewPage.class;
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
	 * <p>添付ファイル自動削除済判定メソッド</p>
	 * 
	 * @return 添付ファイルが自動削除されている場合、trueを返す
	 * 　　　　　　　自動削除されていない場合、falseを返す
	 */
	public boolean isFileDelete() {
		
		// ファイル削除フラグが2の場合、trueを返す
		if(fileDeleteFlg == 2) {
			return true;
		}
		
		return false;
	}
}
