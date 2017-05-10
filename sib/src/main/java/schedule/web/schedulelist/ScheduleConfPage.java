package schedule.web.schedulelist;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import schedule.dao.MEmployeeDao;
import schedule.dao.TScheduleDao;
import schedule.dao.TSchedulegroupDao;
import schedule.dto.SessionDto;
import schedule.entity.MEmployee;
import schedule.entity.TSchedule;
import schedule.logic.SchConfLogic;
import schedule.web.error.AbendPage;
import schedule.web.error.NonloginPage;

/**
 * <p>スケジュール削除確認画面ページクラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class ScheduleConfPage {

	/** ログイン社員情報 */
	@PageScope
	public String employee_id;
	@PageScope
	public String employee_name;
	@PageScope
	public String employee_mail;
	
	/** 表示項目編集 */
	public String schEmpNameStr;		// 起案者名
	public String startDateStr;			// 開始日付
	public String startTimeStr;			// 開始時間
	public String endDateStr;			// 終了日付
	public String endTimeStr;			// 終了時間
	public String titelStr;				// タイトル
	public String noteStr;				// 内容
	public String joinEmpName;			// 参加者名
	public String priorityStr;			// 優先順位
	
	/** スケジュール参加者リスト */
	public String[] joinEmpNameItems;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** 社員マスタDao */
	public MEmployeeDao empDao;
	/** スケジュールテーブルDao */
	public TScheduleDao schDao;
	/** スケジュールグループテーブルDao */
	public TSchedulegroupDao schgruDao;
	
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

	
	/** スケジュール削除確認画面ロジッククラス */
	public SchConfLogic schConfLogic;
	
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
			
			// スケジュールに参加している社員の取得
			int workID = Integer.parseInt(scheduleID);
			joinEmpNameItems = schgruDao.selectGetEmpNameList(workID);
			
			// スケジュールの取得
			TSchedule ety_sch = new TSchedule();
			ety_sch = schDao.selectById(workID);
			
			// 起案者の社員情報を取得
			MEmployee ety_emp = new MEmployee();
			ety_emp = empDao.selectById(ety_sch.employeeId);
			
			// 表示項目に値を設定
			schEmpNameStr	= ety_emp.employeeName;					// 起案者名
			startDateStr	= ety_sch.startDate;					// 開始日付
			startTimeStr	= ety_sch.startTime;					// 開始時間
			endDateStr		= ety_sch.endDate;						// 終了日付
			endTimeStr		= ety_sch.endTime;						// 終了時間
			titelStr		= ety_sch.titel;						// タイトル
			noteStr			= ety_sch.note;							// 内容
			if(ety_sch.priority != null) {
				priorityStr	= Integer.toString(ety_sch.priority);	// 優先順位
			}
		
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return null;
	}
	
	/**
	 * <p>削除ボタン押下時処理</p>
	 * 
	 * @return 日間スケジュール画面に遷移
	 */
	public Class<?> doDelete() {
		
		try {
			
			// 削除処理
			schConfLogic.deleteSch(scheduleID);
			
			// 選択日付にスケジュールが存在しない場合、月間スケジュール画面に遷移
			if(schConfLogic.scheduleChk(employeeID, scheduleDate)) {
				return MonthlySchedulePage.class;
			}
			
		} catch(Exception e) {
			return AbendPage.class;
		}
		
		return DaySchedulePage.class;
	}
	
	/**
	 * <p>戻るボタン押下時処理</p>
	 * 
	 * @return　スケジュール詳細画面に遷移
	 */
	public Class<?> doCancel() {
		
		return ScheduleViewPage.class;
	}
}
