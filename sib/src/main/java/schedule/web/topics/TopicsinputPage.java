package schedule.web.topics;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.internal.FacesMessageUtil;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;
import org.seasar.teeda.extension.util.UploadedFile;

import schedule.dao.TTipsDao;
import schedule.dto.ListDto;
import schedule.dto.SessionDto;
import schedule.dto.UploadfileDto;
import schedule.entity.TTips;
import schedule.logic.TipsinputLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>トピックス登録画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class TopicsinputPage {
	
	/** 入力項目編集 */
	@PageScope
	public String startDateStr;			// 掲載開始日
	@PageScope
	public String endDateStr;			// 掲載終了日
	@PageScope
	public String noteStr;				// 内容
	public UploadedFile uploadedFile;	// アップロードファイル
	public String fileID;				// ファイルID
	public String fileName;				// ファイル名
	public int fileDeleteFlg;			// ファイル削除フラグ
	public boolean autoDeleteFlg;		// 自動削除可フラグ
	
	/** 選択された優先順位 */
	@PageScope
	@Required
	public String priorityStr;
	/** 優先順位リスト */
	@PageScope
	public ListDto[] priorityStrItems;
	
	/** 添付ファイルリスト */
	@PageScope
	public List<UploadfileDto> fileListItems;
	/** 添付ファイルリストのインデックス */
	public int fileListIndex;
	
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
	
	/** エラーメッセージ */
	public String errorMessage;
	
	/** トピックス一覧画面ロジッククラス */
	public TipsinputLogic tipsInputLogic;
	
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
		
			// 優先順位リストの生成
			priorityStrItems = new ListDto[7];
			tipsInputLogic.getPriorityList(priorityStrItems);
			
			// 添付ファイルリストの生成
			fileListItems = new ArrayList<UploadfileDto>();
			tipsInputLogic.getFileList(fileListItems);
			
			// トピックス情報の取得
			TTips ety_tip = tipsInputLogic.tipsInfo(fileListItems, tipsID);
			
			// 取得したトピックス情報を表示項目に設定
			startDateStr	= ety_tip.startdate;					// 掲載開始日
			endDateStr		= ety_tip.enddate;						// 掲載終了日
			noteStr			= ety_tip.note;							// 内容
			
			// 優先順位がnull以外の場合、表示項目に設定
			if(ety_tip.priority != null) {
				priorityStr = Integer.toString(ety_tip.priority);	// 優先順位
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
			
			// バリデーションエラーがある場合、エラー内容をエラーメッセージに設定する
			FacesMessage[] fMessageArray = FacesMessageUtil.getErrorMessages();
			if(fMessageArray.length != 0) {
				errorMessage = fMessageArray[0].getDetail();
			}
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>添付ファイルの削除ボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doFileEdit() {
		
		try {
			
			// 添付ファイルを擬似的に削除
			tipsInputLogic.fileEdit(fileListItems, fileListIndex);
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>更新・追加ボタン押下時処理</p>
	 * 
	 * @return 遷移元へ遷移
	 */
	public Class<?> doConfirm() {
		
		try {
			
			// トピックスのエンティティの生成
			TTips ety_tip = new TTips();
			
			// 編集の場合、トピックス情報を取得
			if(!tipsID.equals("-1")) {
				int tipsInt = Integer.parseInt(tipsID);
				ety_tip = tipsDao.selectById(tipsInt);
			} else {
				ety_tip.tipsId = Integer.parseInt(tipsID);
			}
			
			// 入力された値をエンティティに設定
			ety_tip.startdate		= startDateStr;						// 掲載開始日
			ety_tip.enddate			= endDateStr;						// 掲載終了日
			ety_tip.note			= noteStr;							// 内容
			ety_tip.inputemployeeId	= sessionDto.EmployeeID;			// 入力社員ID
			
			// 優先順位に数値が選択されている場合、エンティティに数値を設定
			if(!priorityStr.equals("No")) {
				ety_tip.priority	= Integer.parseInt(priorityStr);	// 優先順位
			} else {
				ety_tip.priority	= null;
			}
			
			// トピックス登録処理
			errorMessage = tipsInputLogic.tipsConfirm(fileListItems, ety_tip, sessionDto);
		
			// エラーメッセージが設定されている場合、自画面へ遷移
			if(errorMessage != null && !errorMessage.equals("")) {
				return null;
			}
		
			// 新規登録の場合、トピックス一覧画面へ遷移
			if(tipsID.equals("-1")) {
				return TopicslistPage.class;
			}
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		// 編集の場合、トピックス詳細画面へ遷移
		return TopicsviewPage.class;
	}
	
	/**
	 * <p>戻るボタン押下時処理</p>
	 * 
	 * @return　遷移元へ遷移
	 */
	public Class<?> doCancel() {
		
		try {
			// 新規登録の場合、トピックス一覧画面へ遷移
			if(tipsID.equals("-1")) {
				return TopicslistPage.class;
			}
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		// 編集の場合、トピックス詳細画面へ遷移
		return TopicsviewPage.class;
	}
	
	/**
	 * <p>添付ファイル制御メソッド</p>
	 * 
	 * @return 添付ファイルがある場合、true
	 *         ない場合、false
	 */
	public boolean isFile() {
		
		// ファイルIDがnull以外の場合、trueを返す
		if(fileListItems.get(fileListIndex).fileID != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * <p>更新・追加ボタン制御メソッド</p>
	 * 
	 * @return 編集の場合、true
	 * 		   追加の場合、false
	 */
	public boolean isEdit() {
		
		// 新規登録の場合、falseを返す
		if(tipsID.equals("-1")) {
			return false;
		}
		
		// 編集の場合、trueを返す
		return true;
	}
	
	/**
	 * <p>自動削除チェックボックス制御メソッド</p>
	 * 
	 * @return 添付ファイルが自動削除済以外の場合、trueを返す
	 * 　　　　　　　自動削除済の場合、falseを返す
	 */
	public boolean isAutoDelete() {
		
		// ファイル削除フラグが2の場合、falseを返す
		if(fileDeleteFlg == 2) {
			return false;
		}
		
		return true;
	}
}
