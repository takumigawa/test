package schedule.web.schedulelist;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import schedule.dto.AllDaySchDto;
import schedule.dto.AllTimeSchDto;
import schedule.dto.SessionDto;
import schedule.dto.TimeScheduleDto;
import schedule.logic.DaySchLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>日間スケジュール画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class DaySchedulePage {

	/** ログイン社員情報 */
	@PageScope
	public String employee_id;
	@PageScope
	public String employee_name;
	@PageScope
	public String employee_mail;
	
	/** 表示項目編集 */
	@PageScope
	public String employeeNameStr;			// 社員名
	public String daySchTitel;				// 月間カレンダーのタイトル
	public String allDaySchTitelStr;		// 終日スケジュールタイトル
	public String allDaySchTitelStrTitle;	// 終日スケジュール内容
	public String timeStr;					// 時間
	public String timeSchTitelStr;			// 時間スケジュール
	public String timeSchTitelStrTitle;		// 時間スケジュールのツールチップ用
	public String TimeSchTitle;				// 時間スケジュールのツールチップ用(表示用)
	public String startTimeStr;				// 時間スケジュールの開始時刻
	public String endTimeStr;				// 時間スケジュールの終了時刻
	public int timeSchColspan;				// 時間スケジュールの結合値
	
	/** 終日スケジュールリスト */
	public List<AllDaySchDto> allDayScheduleItems;
	/** 終日スケジュールリストのインデックス */
	public int allDayScheduleIndex;
	/** 時間スケジュールリスト */
	public List<AllTimeSchDto> allTimeSchItems;
	/** 時間スケジュールリストのインデックス */
	public int allTimeSchIndex;
	/** 時間ごとのスケジュールリスト */
	public List<TimeScheduleDto> timeScheduleItems;
	/** 時間ごとのスケジュールリストのインデックス */
	public int timeScheduleIndex;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** 選択日付 */
	@SubapplicationScope
	public String scheduleDate;
	/** 選択社員ID */
	@SubapplicationScope
	public String employeeID;
	/** 選択スケジュールID */
	public String scheduleID;
	/** 遷移フラグ */
	@SubapplicationScope
	public String changeFlg;
	/** 追加遷移フラグ */
	public String addChangeflg;
	/** 処理フラグ */
	public String processingFlg;
	
	/** 週間スケジュール画面ロジッククラス */
	public DaySchLogic daySchLogic;

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
			
			// スケジュールが登録されていない場合、スケジュール登録画面に遷移
			if(daySchLogic.scheduleChk(employeeID, scheduleDate)) {
				
				// 処理フラグに"NEW"を設定
				processingFlg = "NEW";
				
				// 遷移フラグが"weekSch"の場合、追加遷移フラグに"week"を設定
				if(changeFlg.equals("weekSch")) {
					addChangeflg = "week";
					
				// 遷移フラグが"weekSch"以外の場合、追加遷移フラグに"monthly"を設定
				} else {
					addChangeflg = "monthly";
				}
				
				// スケジュール登録画面に遷移
				return ScheduleInputPage.class;
			}
			
			// ログイン情報
			employee_id 	= sessionDto.EmployeeID.toString();
			employee_name	= sessionDto.EmployeeName;
			employee_mail	= sessionDto.EmployeeEmail;
			
			// 選択された社員の名前の取得
			employeeNameStr = daySchLogic.selectEmpName(employeeID);
			
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
			
			// 日間スケジュールのタイトルの取得
			daySchTitel = scheduleDate;
			
			// 日間スケジュールの取得
			allDayScheduleItems = new ArrayList<AllDaySchDto>();
			allTimeSchItems = new ArrayList<AllTimeSchDto>();
			daySchLogic.daysSchList(allDayScheduleItems, allTimeSchItems, employeeID, scheduleDate);
			
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
	 * <p>新規追加ボタン押下時処理</p>
	 * 
	 * @return　新規登録画面に遷移
	 */
	public Class<?> doSchAdd() {
		
		try {
			
			// フラグに値を設定
			processingFlg	= "NEW";	// 処理フラグ
			addChangeflg	= "day";	// 追加遷移フラグ
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return ScheduleInputPage.class;
	}
	
	/**
	 * <p>戻るボタン押下時処理</p>
	 * 
	 * @return 遷移元画面に遷移
	 */
	public Class<?> doCancel() {
		
		try {
			
			// 週間スケジュール画面から遷移してきた場合、週間スケジュール画面に遷移
			if(changeFlg.equals("weekSch")) {
				return WeekSchedulePage.class;
			}
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		// 月間スケジュール画面から遷移してきた場合、月間スケジュール画面に遷移
		return MonthlySchedulePage.class;
	}
	
	/**
	 * <p>詳細ボタン押下時処理</p>
	 * 
	 * @return スケジュール詳細画面に遷移
	 */
	public Class<?> doView() {
		
		try {
			
			// 選択された行のスケジュールIDを取得
			scheduleID = allDayScheduleItems.get(allDayScheduleIndex).scheduleID;
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return ScheduleViewPage.class;
	}
	
	/**
	 * <p>時間表示の<td>制御メソッド</p>
	 * 
	 * @return 最初の行と現在の時間が直前の時間と異なる場合、true
	 * 		   現在の時間が直前の時間と同じ場合、false
	 */
	public boolean isTime() {
		
		// 最初の行の場合、<td>を表示する
		if (allTimeSchIndex == 0) {
			return true;
		}    
		
		//　現在の時間が直前の時間と異なる場合、<td>を表示する
		return !timeStr.equals(allTimeSchItems.get(allTimeSchIndex - 1).timeStr);
	}
	
	/**
	 * <p>時間表示の結合する行数取得メソッド</p>
	 * 
	 * @return 現在の時間と同じ時間が連続する行数
	 */
	public int getTimeRowspan() {

		// 行数の初期化
		int rowspan = 1;
		
		// 時間スケジュールリストの件数分、処理を繰り返す
		for (int i = allTimeSchIndex + 1; i < allTimeSchItems.size(); i++) {
			
			// 現在の時間と異なる場合、for文を終了させる
			if (!timeStr.equals(allTimeSchItems.get(i).timeStr)) {
				break;
			}
			
			// 行数を1加算
			rowspan++;
		}
		
		return rowspan;
	}
	
	/**
	 * <p>時間スケジュール表示の<td>制御メソッド</p>
	 * 
	 * @return 最初の行と現在のスケジュールIDが直前のスケジュールIDと異なる場合、true
	 * 		   現在のスケジュールIDが直前のスケジュールIDと同じ場合、false
	 */
	public boolean isTimeSch() {
		
		// 最初の行の場合、<td>を表示する
		if (allTimeSchIndex == 0) {
			return true;
		} 
		
		// 直線の時間ごとのスケジュールリストのサイズが現在の時間ごとのスケジュールリストのインデックス以下の場合、<td>を表示する
		if (allTimeSchItems.get(allTimeSchIndex - 1).timeScheduleItems.size() <= timeScheduleIndex) {
			return true;
		}
		
		//　現在のスケジュールIDが直前のスケジュールIDと異なる場合、<td>を表示する
		return !scheduleID.equals(allTimeSchItems.get(allTimeSchIndex - 1).timeScheduleItems.get(timeScheduleIndex).scheduleID);
	}
	
	/**
	 * <p>時間スケジュール表示の結合する行数取得メソッド</p>
	 * 
	 * @return 現在のスケジュールIDと同じスケジュールIDが連続する行数
	 */
	public int getTimeSchRowspan() {
		
		//現在のカテゴリ名と同じカテゴリ名が連続する行数を返す
		int rowspan = 1;
		
		// 時間スケジュールリストの件数分、処理を繰り返す
		for (int i = allTimeSchIndex + 1; i < allTimeSchItems.size(); i++) {
			
			// 現在のスケジュールIDと異なる場合、for文を終了させる
			if (allTimeSchItems.get(i).timeScheduleItems.size() <= timeScheduleIndex 
					|| !scheduleID.equals(allTimeSchItems.get(i).timeScheduleItems.get(timeScheduleIndex).scheduleID)) {
				break;
			}
			
			// 行数を1加算
			rowspan++;
		}
		
		return rowspan;
	}
	
	/**
	 * <p>時間スケジュール表示のスタイル属性の取得メソッド</p>
	 * 
	 * @return　スタイル属性
	 */
	public String getTimeSchStyle() {
		
		// セルの横幅の取得
		int widthNumber = 100 / allTimeSchItems.get(allTimeSchIndex).timeScheduleItems.size();
		
		// 優先順位で背景色を変更
		String priority = allTimeSchItems.get(allTimeSchIndex).timeScheduleItems.get(timeScheduleIndex).priority;
		String bcColor = "#FFF8E5";	
		if(priority != null) {
			if(priority.equals("0")) {
				bcColor = "#C2CCFF";
			} else if(priority.equals("1")) {
				bcColor = "#C2EBFF";
			} else if(priority.equals("2")) {
				bcColor = "#C2FFD6";
			} else if(priority.equals("3")) {
				bcColor = "#CCFFC2";
			} else if(priority.equals("4")) {
				bcColor = "#FFD6C2";
			} else if(priority.equals("5")) {
				bcColor = "#FFC2CC";
			}
		}
		
		// スタイル属性の取得
		StringBuilder styleStr = new StringBuilder();
		styleStr.append("width:");
		styleStr.append(widthNumber);
		styleStr.append("%;background-color:");
		styleStr.append(bcColor);
		styleStr.append(";");
		
		return styleStr.toString();	
	}
	
	/**
	 * <p>時間スケジュールのツールチップ取得メソッド</p>
	 * 
	 * @return 時間スケジュールのツールチップ用
	 */
	public String getTimeSchTitle() {
		return timeSchTitelStrTitle;
	}
}
