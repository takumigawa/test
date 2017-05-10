package schedule.web.schedulelist;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dto.AllDaySchDto;
import schedule.dto.AllEmpSchDto;
import schedule.dto.DaySchListDto;
import schedule.dto.ListDto;
import schedule.dto.MonthlySchListDto;
import schedule.dto.SessionDto;
import schedule.dto.TimeScheduleDto;
import schedule.logic.MonthlySchLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>月間スケジュール画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class MonthlySchedulePage {

	/** ログイン社員情報 */
	@PageScope
	public String employee_id;
	@PageScope
	public String employee_name;
	@PageScope
	public String employee_mail;
	
	/** 表示項目編集 */
	public String monthlySchTitel;			// 月間カレンダーのタイトル
	public String weekDay;					// 曜日
	public String scheduleDate;				// スケジュール日付
	public String schMonth;					// スケジュール日付（月）
	public String dateStr;					// スケジュール日付（日）
	public String allDaySchTitelStr;		// 終日スケジュール
	public String allDaySchTitelStrTitle;	// 終日スケジュールのツールチップ用
	public String timeSchTitelStr;			// 時間スケジュール
	public String timeSchTitelStrTitle;		// 時間スケジュールのツールチップ用
	public String startTimeStr;				// 時間スケジュールの開始時刻
	public String endTimeStr;				// 時間スケジュールの終了時刻
	
	/** 年一覧 */
	@PageScope
	public ListDto[] yearItems;
	/** 月一覧 */
	@PageScope
	public ListDto[] monthItems;
	
	/** 月間スケジュール表示用リスト */
	public List<MonthlySchListDto> monthlySchItems;
	/** 月間カレンダー用曜日リスト */
	public String[] weekDayItems = {"日","月","火","水","木","金","土"};
	/** 月間カレンダー用曜日リストのインデックス */
	public int weekDayIndex;
	/** 週ごとのスケジュールリスト */
	public List<AllEmpSchDto> weekScheduleItems;
	/** 日間スケジュールリスト */
	public List<DaySchListDto> dayScheduleItems;
	/** 日間スケジュールリストのインデックス */
	public int dayScheduleIndex;
	/** 終日スケジュールリスト */
	public List<AllDaySchDto> allDayScheduleItems;
	/** 時間スケジュールリスト */
	public List<TimeScheduleDto> timeScheduleItems;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** 選択年 */
	@SubapplicationScope
	@Required
	public String year;
	/** 選択月 */
	@SubapplicationScope
	@Required
	public String month;
	/** 選択社員ID */
	@SubapplicationScope
	public String employeeID;
	/** 選択社員名 */
	@PageScope
	public String employeeNameStr;
	/** 遷移フラグ */
	public String changeFlg;
	/** 追加遷移フラグ */
	public String addChangeflg;
	/** 処理フラグ */
	public String processingFlg;
	
	/** 週間スケジュール画面ロジッククラス */
	public MonthlySchLogic monthlySchLogic;

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
			
			// 遷移フラグに"monthlySch"を設定
			changeFlg = "monthlySch";
			
			// ログイン情報
			employee_id 	= sessionDto.EmployeeID.toString();
			employee_name	= sessionDto.EmployeeName;
			employee_mail	= sessionDto.EmployeeEmail;
			
			// 選択された社員の名前の取得
			employeeNameStr = monthlySchLogic.selectEmpName(employeeID);
			
			// 現在の年・月の取得
			String[] dateArray = monthlySchLogic.dateInitialize();
			year = dateArray[0];
			month = dateArray[1];
			
			// 画面初期化処理
			yearItems = new ListDto[11];
			monthItems = new ListDto[12];
			monthlySchLogic.dateList(yearItems, monthItems, year);
			
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
		
			// 月間スケジュールタイトルの取得
			monthlySchTitel = monthlySchLogic.monthSchTitel(year, month);
			
			// 月間スケジュールリストの取得
			monthlySchItems	= new ArrayList<MonthlySchListDto>();
			monthlySchLogic.monthSchList(monthlySchItems, year, month, employeeID);
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>検索ボタン押下時処理</p>
	 * 
	 * @return　自画面に遷移
	 */
	public Class<?> doSearch() {
		
		return null;
	}
	
	/**
	 * <p>検索解除ボタン押下時処理</p>
	 * 
	 * @return　自画面に遷移
	 */
	public Class<?> doSearchCancel() {
		
		try {	
		
			// 現在の年・月の取得
			String[] dateArray = monthlySchLogic.dateInitialize();
			year = dateArray[0];
			month = dateArray[1];
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>新規追加ボタン押下時処理</p>
	 * 
	 * @return　スケジュール登録画面に遷移
	 */
	public Class<?> doScheduleAdd() {
		
		try {
		
			// フラグに値を設定
			processingFlg = "NEW";		// 処理フラグ
			addChangeflg = "monthly";	// 追加遷移フラグ
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return ScheduleInputPage.class;
	}
	
	/**
	 * <p>スケジュール表示部セルのclass属性取得メソッド</p>
	 * 
	 * @return CSSのクラス名
	 * 　
	 */
	public String getBodyStyleClass() {
		
		// 表示する月と異なる場合、"Td05"を返す
		if(!schMonth.equals(month)) {
			return "Td05";
		}
		
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
	 * <p>スケジュール日付のclass属性取得メソッド</p>
	 * 
	 * @return　CSSのクラス名
	 */
	public String getDateStrStyleClass() {
		
		// 土曜日なら、"Span01"を返す
		if(dayScheduleIndex == 6) {
			return "Span01";
			
		// 日曜日なら、"Span02"を返す
		}else if(dayScheduleIndex == 0) {
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
		if(weekDayIndex == 6) {
			return "Span01";
			
		// 日曜日なら、"Span02"を返す
		}else if(weekDayIndex == 0) {
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
	public String getTimeSchTitle(){
		return timeSchTitelStrTitle;
	}
}
