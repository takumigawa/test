package schedule.web.schedulelist;

import java.util.ArrayList;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.TScheduleDao;
import schedule.dto.ListDto;
import schedule.dto.SessionDto;
import schedule.entity.TSchedule;
import schedule.logic.SchInputLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>スケジュール登録画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class ScheduleInputPage {
	
	/** 入力項目編集 */
	public String startDate;	// 開始日付
	public String startTime;	// 開始時間
	public String endDate;		// 終了日付
	public String endTime;		// 終了時間
	public String titel;		// タイトル
	public String note;			// 内容
	
	/** 選択開始時間(時) */
	@Required
	public String startHour;
	/** 開始時間(時)リスト */
	public ListDto[] startHourItems;
	
	/** 選択開始時間(分) */
	@Required
	public String startMin;
	/** 開始時間(分)リスト */
	public ListDto[] startMinItems;
	
	/** 選択終了時間(時) */
	@Required
	public String endHour;
	/** 終了時間(時)リスト */
	public ListDto[] endHourItems;
	
	/** 選択終了時間(分) */
	@Required
	public String endMin;
	/** 終了時間(分)リスト */
	public ListDto[] endMinItems;
	
	/** 選択グループ名 */
	@Required
	public String groupName;
	/** グループ名リスト */
	public ListDto[] groupNameItems;
	
	/** 選択参加社員名 */
	public String[] joinEmp;
	/** 参加社員名リスト */
	@PageScope
	public ArrayList<ListDto> joinEmpItems;
	
	/** 選択不参加社員名 */
	public String[] absenceEmp;
	/** 不参加社員名リスト */
	@PageScope
	public ArrayList<ListDto> absenceEmpItems;
	
	/** 選択優先順位 */
	@Required
	public String priority;
	/** 優先順位リスト */
	public ListDto[] priorityItems;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** スケジュールテーブルDao */
	public TScheduleDao schDao;
	
	/** 選択日付 */
	@SubapplicationScope
	public String scheduleDate;
	/** 選択社員ID */
	@SubapplicationScope
	public String employeeID;
	/** 選択スケジュールID */
	@SubapplicationScope
	public String scheduleID;
	/** 遷移フラグ */
	@SubapplicationScope
	public String changeFlg;
	/** 追加遷移フラグ */
	@PageScope
	public String addChangeflg;
	/** 処理フラグ */
	@PageScope
	public String processingFlg;
	
	/** スケジュール登録画面ロジッククラス */
	public SchInputLogic schInputLogic;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {
		
		try {
			
			// 未ログインユーザーをはじく処理
			if(sessionDto.EmployeeID == null){
				return NonloginPage.class;
			}
			
			// 開始時間(時)リストの取得
			startHourItems = new ListDto[25];
			schInputLogic.getTimeList(startHourItems, "hour");
			
			// 開始時間(分)リストの取得
			startMinItems = new ListDto[5];
			schInputLogic.getTimeList(startMinItems, "min");
			
			// 終了時間(時)リストの取得
			endHourItems = new ListDto[25];
			schInputLogic.getTimeList(endHourItems, "hour");
			
			// 終了時間(分)リストの取得
			endMinItems = new ListDto[5];
			schInputLogic.getTimeList(endMinItems, "min");
			
			// 優先順位リストの取得
			priorityItems = schInputLogic.getPriorityList();
			
			// グループ名リストの取得
			groupNameItems = schInputLogic.getGroupList();
		
			// 参加・不参加リストの取得
			joinEmpItems = new ArrayList<ListDto>();
			absenceEmpItems = new ArrayList<ListDto>();
			schInputLogic.getEmpList(joinEmpItems, absenceEmpItems, scheduleID, processingFlg);
			
			// 編集の場合、対象のスケジュール情報を取得する
			if(processingFlg.equals("EDIT")) {
				
				// 編集対象のスケジュールの取得
				TSchedule ety_sch = new TSchedule();
				int workID = Integer.parseInt(scheduleID);
				ety_sch = schDao.selectById(workID);
				
				// 取得したスケジュールを表示項目に設定
				startDate		= ety_sch.startDate;					// 開始日付
				endDate			= ety_sch.endDate;						// 終了日付
				titel			= ety_sch.titel;						// タイトル
				if(ety_sch.startTime != null) {
					startHour	= ety_sch.startTime.substring(0, 2);	// 開始時間(時)
					startMin	= ety_sch.startTime.substring(3, 5);	// 開始時間(分)
				}
				if(ety_sch.endTime != null) {
					endHour		= ety_sch.endTime.substring(0, 2);		// 終了時間(時)
					endMin		= ety_sch.endTime.substring(3, 5);		// 終了時間(分)
				}
				if(ety_sch.note != null) {
					note		= ety_sch.note;							// 内容
				}
				if(ety_sch.priority != null) {
					priority	= Integer.toString(ety_sch.priority);	// 優先順位
				}
			}
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		
		return null;
	}
	
	/**
	 * <p>追加・更新ボタン押下時処理</p>
	 * 
	 * @return 遷移元に遷移
	 */
	public Class<?> doConfirm() {
		
		try {
			
			// スケジュールテーブルのエンティティの生成
			TSchedule ety_sch = new TSchedule();
			
			// 編集の場合、対象のスケジュールの取得
			if(processingFlg.equals("EDIT")) {
				int workID = Integer.parseInt(scheduleID);
				ety_sch = schDao.selectById(workID);
			}
			
			// エンティティに値を設定
			ety_sch.startDate		= startDate;					// 開始日付
			ety_sch.endDate			= endDate;						// 終了日付
			ety_sch.titel			= titel;						// タイトル
			ety_sch.note			= note;							// 内容
			
			// 開始時間(時)と開始時間(分)の両方が選択されている場合、エンティティに値を設定
			if(!startHour.equals("99") && !startMin.equals("99")) {
				StringBuilder timeStr = new StringBuilder();
				timeStr.append(startHour);
				timeStr.append(":");
				timeStr.append(startMin);	
				ety_sch.startTime	= timeStr.toString();			// 開始時間
				
			// 開始時間(時)、開始時間(分)が選択されていない場合、エンティティにnullを設定
			} else {
				ety_sch.startTime	= null;
			}
			
			// 終了時間(時)と終了時間(分)の両方が選択されている場合、エンティティに値を設定
			if(!endHour.equals("99") && !endMin.equals("99")) {
				StringBuilder timeStr = new StringBuilder();
				timeStr.append(endHour);
				timeStr.append(":");
				timeStr.append(endMin);
				ety_sch.endTime		= timeStr.toString();			// 終了時間
				
			// 終了時間(時)、終了時間(分)が選択されていない場合、エンティティにnullを設定
			} else {
				ety_sch.endTime		= null;
			}
			
			// 優先順位の数が選択されている場合、エンティティに値を設定
			if(!priority.equals("No")) {
				ety_sch.priority	= Integer.parseInt(priority);	// 優先順位
			
			// 優先順位の数が選択されていない場合、エンティティにnullを設定
			} else {
				ety_sch.priority	= null;
			}
			
			// スケジュール登録処理
			schInputLogic.schConfirm(ety_sch, joinEmpItems, sessionDto, processingFlg);
			
			// 遷移元に遷移
			if(processingFlg.equals("NEW")) {
				if(addChangeflg.equals("week")) {
					return WeekSchedulePage.class;
				} else if(addChangeflg.equals("monthly")) {
					return MonthlySchedulePage.class;
				} else {
					return DaySchedulePage.class;
				}
			}
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		// 編集の場合、スケジュール詳細画面に遷移
		return ScheduleViewPage.class;
	}
	
	/**
	 * <p>参加者のグループ指定変更時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doCombChange() {
		
		try {
			
			// 不参加者のグループ変更
			schInputLogic.combChange(joinEmpItems, absenceEmpItems, groupName);
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>参加者の追加ボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doJoin() {
		
		try {
			
			// 参加者の追加
			schInputLogic.join(joinEmpItems, absenceEmpItems, absenceEmp);
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>参加者の削除ボタン押下時処理</p>
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> doAbsence() {
		
		try {
			
			// 参加者の削除
			schInputLogic.absence(joinEmpItems, absenceEmpItems, groupName, joinEmp);
			
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
		
		try {
			
			// 追加の場合、遷移元に遷移
			if(processingFlg.equals("NEW")) {
				if(addChangeflg.equals("week")) {
					return WeekSchedulePage.class;
				} else if(addChangeflg.equals("monthly")) {
					return MonthlySchedulePage.class;
				} else {
					return DaySchedulePage.class;
				}
			}
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		// 編集の場合、スケジュール詳細画面に遷移
		return ScheduleViewPage.class;
	}
	
	/**
	 * <p>更新・追加ボタン制御メソッド</p>
	 * 
	 * @return 編集の場合、true
	 * 		   追加の場合、false
	 */
	public boolean isEdit() {
		
		// 編集の場合、trueを返す
		if(processingFlg.equals("EDIT")) {
			return true;
		}
		
		return false;
	}
}
