package schedule.web.schedulelist;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dto.AllDaySchDto;
import schedule.dto.AllEmpSchDto;
import schedule.dto.DateCalendarDto;
import schedule.dto.DaySchListDto;
import schedule.dto.EmpListDto;
import schedule.dto.ListDto;
import schedule.dto.SessionDto;
import schedule.dto.TimeScheduleDto;
import schedule.dto.TipsListDto;
import schedule.dto.WeekSchListDto;
import schedule.logic.WeekSchLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>週間スケジュール画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class WeekSchedulePage {

	/** ログイン社員情報 */
	@PageScope
	public String employee_id;				// 社員ID
	@PageScope
	public String employee_name;			// 社員名
	@PageScope
	public String employee_mail;			// メールアドレス
	
	/** 表示項目編集 */
	public String tipsID;					// トピックスID
	public String note;						// トピックス内容
	public String employeeName;				// 社員名
	public String matterTitel;				// 参画案件名
	public String weekScheduleTitel;		// 週間カレンダーのタイトル
	public String dateStr;					// カレンダー日付
	public String weekDay;					// 曜日
	public String scheduleDate;				// スケジュール日付
	public String allDaySchTitelStr;		// 終日スケジュール
	public String allDaySchTitelStrTitle;	// 終日スケジュールのツールチップ用
	public String timeSchTitelStr;			// 時間スケジュール
	public String timeSchTitelStrTitle;		// 時間スケジュールのツールチップ用
	public String startTimeStr;				// 時間スケジュールの開始時刻
	public String endTimeStr;				// 時間スケジュールの終了時刻
	
	/** トピックス情報のリスト */
	public TipsListDto[] tipsItems;
	/** トピックス情報のリストのインデックス */
	public int tipsIndex;
	
	/** グループ一覧 */
	@PageScope
	public ListDto[] groupIDStrItems;
	
	/** 週間スケジュール表示用リスト */
	public List<WeekSchListDto> weekScheduleItems;
	/** 週間カレンダー用日付リスト */
	public List<DateCalendarDto> dateCalendarItems;
	/** 週間カレンダー用日付リストのインデックス */
	public int dateCalendarIndex;
	/** 社員ごとの週間スケジュールリスト */
	@PageScope
	public List<AllEmpSchDto> allEmpSchItems;
	/** 社員ごとの週間スケジュールリストのインデックス */
	public int allEmpSchIndex;
	/** 案件情報リスト */
	public List<String> matterTitelItems;
	/** 日間スケジュールリスト */
	public List<DaySchListDto> dayScheduleItems;
	/** 日間スケジュールリストのインデックス */
	public int dayScheduleIndex;
	/** 終日スケジュールリスト */
	public List<AllDaySchDto> allDayScheduleItems;
	/** 時間スケジュールリスト */
	public List<TimeScheduleDto> timeScheduleItems;

	/** 社員情報リスト */
	public List<EmpListDto> employeeList;
	/** 週間スケジュール開始日 */
	@PageScope
	public String startDate;
	/** 週間スケジュール終了日 */
	@PageScope
	public String endDate;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** 選択社員ID */
	@SubapplicationScope
	public String employeeID;
	/** 選択グループID */
	@PageScope
	@Required
	public String groupIDStr;
	/** 遷移フラグ */
	public String changeFlg;
	/** 追加遷移フラグ */
	public String addChangeflg;
	/** 処理フラグ */
	public String processingFlg;
	
	/** 週間スケジュール画面ロジッククラス */
	public WeekSchLogic weekSchLogic;
	
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
		
			// 遷移フラグに"weekSch"を設定
			changeFlg = "weekSch";
			
			// ログイン情報
			employee_id 	= sessionDto.EmployeeID.toString();		// 社員ID
			employee_name	= sessionDto.EmployeeName;				// 社員名
			employee_mail	= sessionDto.EmployeeEmail;				// メールアドレス
			
			// グループ一覧の取得
			groupIDStrItems = weekSchLogic.groupList();
			
			// グループIDの初期化
			groupIDStr = weekSchLogic.gruIDInitialize(sessionDto.EmployeeID);
			
			// 今週の日付の取得
			String[] weekDayArray = weekSchLogic.nowWeekDays();
			startDate = weekDayArray[0];
			endDate = weekDayArray[1];
		
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
			
			// 週間スケジュールのタイトルの取得
			StringBuilder titel = new StringBuilder();
			titel.append("週間スケジュール　（");
			titel.append(startDate);
			titel.append("　～　");
			titel.append(endDate);
			titel.append("）");
			weekScheduleTitel = new String(titel);
			
			// トピックス一覧の取得
			tipsItems = weekSchLogic.topicsList();
			
			// 週間スケジュールリストの取得
			dateCalendarItems = new ArrayList<DateCalendarDto>();
			weekScheduleItems = new ArrayList<WeekSchListDto>();
			weekSchLogic.weekScheduleList(dateCalendarItems, weekScheduleItems, startDate, groupIDStr);
			
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
			
			// グループIDの初期化
			groupIDStr = weekSchLogic.gruIDInitialize(sessionDto.EmployeeID);
			
			// 今週の日付の取得
			String[] weekDayArray = weekSchLogic.nowWeekDays();
			startDate = weekDayArray[0];
			endDate = weekDayArray[1];
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>新規追加ボタン押下時処理</p>
	 * 
	 * @return スケジュール登録画面に遷移
	 */
	public Class<?> doScheduleAdd() {
		
		try {
			
			// フラグに値を設定
			processingFlg	= "NEW";	// 処理フラグ
			addChangeflg	= "week";	// 追加遷移フラグ
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return ScheduleInputPage.class;
	}
	
	/**
	 * <p>前週へボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doLastWeek() {
		
		try {
			
			// 先週の開始・終了日付の取得
			String[] weekDayArray = weekSchLogic.schWeekDays(-7, startDate);
			startDate = weekDayArray[0];
			endDate = weekDayArray[1];
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>今週ボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doThisWeek() {
		
		try {
			
			// 今週の開始・終了日付の取得
			String[] weekDayArray = weekSchLogic.schWeekDays(0, startDate);
			startDate = weekDayArray[0];
			endDate = weekDayArray[1];
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>次週へボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doNextWeek() {
		
		try {
			
			// 次週の開始・終了日付の取得
			String[] weekDayArray = weekSchLogic.schWeekDays(7, startDate);
			startDate = weekDayArray[0];
			endDate = weekDayArray[1];
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>スケジュール表示部セルのclass属性取得メソッド</p>
	 * 
	 * @return CSSのクラス名
	 * 　
	 */
	public String getBodyStyleClass() {
		
		// 土曜日なら、"Td01"を返す
		if(dayScheduleIndex == 6) {
			return "Td01";
			
		// 日曜日なら、"Td02"を返す
		}else if(dayScheduleIndex == 0) {
			return "Td02";
		}
		
		// 土日以外なら、"Td03"を返す
		return "Td03";
	}
	
	/**
	 * <p>日付表示のclass属性取得メソッド</p>
	 * 
	 * @return CSSのクラス名
	 * 
	 */
	public String getDateStrStyleClass() {
		
		// 土曜日なら、"Span01"を返す
		if(dateCalendarIndex == 7) {
			return "Span01";
			
		// 日曜日なら、"Span02"を返す
		}else if(dateCalendarIndex == 1) {
			return "Span02";
		}
		
		// 土日以外なら、class属性なし
		return null;
	}
	
	/**
	 * <p>曜日表示のclass属性取得メソッド</p>
	 * 
	 * @return　CSSのクラス名
	 * 
	 */
	public String getWeekDayStyleClass() {
		
		// 土曜日なら、"Span01"を返す
		if(dateCalendarIndex == 7) {
			return "Span01";
			
		// 日曜日なら、"Span02"を返す
		}else if(dateCalendarIndex == 1) {
			return "Span02";
		}
		
		// 土日以外なら、class属性なし
		return null;
	}
	
	/**
	 * <p>時間表示のツールチップ取得メソッド</p>
	 * 
	 * @return 時間スケジュールのツールチップ用
	 */
	public String getTimeSchTitle() {
		return timeSchTitelStrTitle;
	}
	
	/**
	 * <p>添付ファイル有画像制御メソッド</p>
	 * 
	 * @return 添付ファイルがある場合、true
	 * 		   ない場合、false
	 */
	public boolean isFile() {
		
		return tipsItems[tipsIndex].fileFlg;
	}
}
