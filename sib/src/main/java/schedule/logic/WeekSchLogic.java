package schedule.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import schedule.dao.MEmployeeDao;
import schedule.dao.MGroupDao;
import schedule.dao.TGroupattachDao;
import schedule.dao.TMatterDao;
import schedule.dao.TScheduleDao;
import schedule.dao.TTipsDao;
import schedule.dto.AllDaySchDto;
import schedule.dto.AllEmpSchDto;
import schedule.dto.DateCalendarDto;
import schedule.dto.DaySchListDto;
import schedule.dto.EmpListDto;
import schedule.dto.ListDto;
import schedule.dto.TimeScheduleDto;
import schedule.dto.TipsListDto;
import schedule.dto.WeekSchListDto;
import schedule.entity.MEmployee;
import schedule.web.common.CommonUtil;

/**
 * <p>週間スケジュールロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class WeekSchLogic {
	
	/** トピックステーブルDao */
	public TTipsDao tipsDao;
	/** グループマスタDao */
	public MGroupDao gruDao;
	/** 社員マスタDao */
	public MEmployeeDao empDao;
	/** スケジュールテーブルDao */
	public TScheduleDao schDao;
	/** グループ所属テーブルDao */
	public TGroupattachDao grDao;
	/** 案件情報テーブルDao */
	public TMatterDao matDao;
	
	/**
	 * <p>グループ一覧の取得メソッド</p>
	 * 
	 * @return グループ一覧
	 * @throws Exception 例外
	 */
	public ListDto[] groupList() throws Exception {
		
		// グループ検索用プルダウンの取得
		ListDto[] groupIdArray = gruDao.selectGetGroupCombList();
		ListDto listDto = new ListDto();
		listDto.label = "全社員表示";
		listDto.value = "0";
		ListDto[] gruArray = new ListDto[groupIdArray.length + 1];
		for(int i = groupIdArray.length; i >= 0; i--) {
			if(i == 0) {
				gruArray[i] = listDto;
			} else {
				gruArray[i] = groupIdArray[i - 1];
			}
		}
		
		return gruArray;
	}
	
	/**
	 * <p>トピックス一覧の取得メソッド</p>
	 * 
	 * @return トピックス一覧
	 * @throws Exception 例外
	 */
	public TipsListDto[] topicsList() throws Exception {
		
		// システム日付の取得
		String nowDate = CommonUtil.getNowUpdDate();
		
		// トピックス情報の取得
		TipsListDto[] tipsArray = tipsDao.selectGetTipsList(nowDate);
		
		// トピックス情報がない場合、トピックス情報無しを設定
		if(tipsArray.length == 0) {
			tipsArray = new TipsListDto[1];
			TipsListDto tipsDto = new TipsListDto();
			tipsDto.note = "連絡事項は、ありません。";
			tipsArray[0] = tipsDto;
		}
		
		// 添付ファイルの有無判定
		for(int i = 0; i < tipsArray.length; i++) {
			tipsArray[i].fileFlg = filePresence(tipsArray[i]);
		}
		
		return tipsArray;
	}
	
	/**
	 * <p>週間スケジュールリスト取得メソッド</p>
	 * 
	 * @param dateList 週間カレンダー日付リスト
	 * @param weekSchList 週間スケジュールリスト
	 * @param sDate　スケジュール開始日
	 * @param gruIDStr 所属グループID
	 * @throws Exception 例外
	 */
	public void weekScheduleList(List<DateCalendarDto> dateList, 
			List<WeekSchListDto> weekSchList, String sDate, String gruIDStr) throws Exception {
		
		// 週間スケジュール日付の取得
		String[] weekDaysArray = {"日","月","火","水","木","金","土"};
		for(int i = 0; i < 7; i++) {
			DateCalendarDto dateDto = new DateCalendarDto();
			String[] dayArray = weekDays(sDate,i);
			dateDto.dateCalendar = dayArray[0];
			dateDto.dateStr = dayArray[0].substring(5, 10);
			dateDto.weekDay = weekDaysArray[i];
			dateList.add(dateDto);
		}
		
		// 社員ごとの週間スケジュールリストのインスタンス生成
		ArrayList<AllEmpSchDto> allEmpSchList = new ArrayList<AllEmpSchDto>();
		
		// 社員情報の取得
		int groupID = Integer.parseInt(gruIDStr);
		List<EmpListDto> empList = grDao.selectGetEmpList(groupID);
		
		// 取得した社員数分、処理を繰り返す
		for(int i = 0; i < empList.size(); i++) {
			
			// 日間スケジュールの初期化
			ArrayList<DaySchListDto> daySchList = new ArrayList<DaySchListDto>();
			
			// 社員IDの取得
			String empID = empList.get(i).empID;
			
			// 社員ごとの週間スケジュールDtoの生成
			AllEmpSchDto allEmpSchDto = new AllEmpSchDto();
			
			// 社員ごとの週間スケジュールDtoに値を設定
			allEmpSchDto.employeeID			= empID;								// 社員番号
			allEmpSchDto.employeeName		= empList.get(i).empName;				// 社員名
			allEmpSchDto.matterTitelItems	= matDao.selectGetMatTitel(empID);		// 案件情報
			
			// スケジュールの取得
			for(int j = 0; j < 7; j++) {
				
				// 日間スケジュールDtoの初期化
				DaySchListDto daySchListDto = new DaySchListDto();
				
				// 取得するスケジュールの日付の設定
				String dateStr = dateList.get(j).dateCalendar;
				daySchListDto.scheduleDate = dateStr;
				
				// 終日スケジュールの取得
				List<AllDaySchDto> allDaySchList = schDao.selectGetAllDaySchTitel(empID, dateStr);
				
				// 終日スケジュールが存在しない場合、日間スケジュールDtoの初期化
				if(allDaySchList == null) {
					allDaySchList = new ArrayList<AllDaySchDto>();
				}
				
				// 時間スケジュールの取得
				List<TimeScheduleDto> timeSchList = schDao.selectGetTimeSchTitel(empID, dateStr);
				
				// 取得した時間スケジュールを表示用に変換
				for(int k = 0; k < timeSchList.size(); k++) {
					
					String startDateStr = timeSchList.get(k).startDate;
					String endDateStr = timeSchList.get(k).endDate;
					
					if(timeSchList.get(k).startTimeStr == null) {
						timeSchList.get(k).startTimeStr = "00:00";
					}
					
					if(timeSchList.get(k).endTimeStr == null) {
						timeSchList.get(k).endTimeStr = "24:00";
					}
					
					if(!startDateStr.equals(endDateStr)) {
						
						if(dateStr.equals(startDateStr)) {
							timeSchList.get(k).endTimeStr = "24:00";
						} else if(dateStr.equals(endDateStr)) {
							timeSchList.get(k).startTimeStr = "00:00";
						} else {
							timeSchList.get(k).startTimeStr = "00:00";
							timeSchList.get(k).endTimeStr = "24:00";
						}
					}
					
					// 開始時間と終了時間が同じ場合、時間スケジュールリストから削除
					if(timeSchList.get(k).startTimeStr.equals(timeSchList.get(k).endTimeStr)) {
						timeSchList.remove(k);
					}
				}
				
				// 日間スケジュールDtoに値を設定
				daySchListDto.allDayScheduleItems	= allDaySchList;	// 終日スケジュール
				daySchListDto.timeScheduleItems		= timeSchList;		// 時間スケジュール
				
				// 日間スケジュールDtoを日間スケジュールに追加
				daySchList.add(daySchListDto);
			}
			
			// 社員ごとの週間スケジュールDtoに日間スケジュールを設定
			allEmpSchDto.dayScheduleItems = daySchList;
			
			// 社員ごとの週間スケジュールリストの追加
			allEmpSchList.add(allEmpSchDto);
		}
		
		// 週間カレンダー用日付リストに空データを追加
		DateCalendarDto dateDto = new DateCalendarDto();
		dateList.add(0, dateDto);
		
		// 社員ごとの週間スケジュールを全体の週間スケジュールに追加
		WeekSchListDto weekSchListDto = new WeekSchListDto();
		weekSchListDto.allEmpSchItems = allEmpSchList;
		weekSchList.add(weekSchListDto);
	}
	
	/**
	 * <p>所属グループのID取得メソッド</p>
	 * 
	 * @param empID ログイン社員ID
	 * @return 所属グループID
	 * @throws Exception 例外
	 */
	public String gruIDInitialize(int empID) throws Exception {
		
		// ログインした社員の情報の取得
		MEmployee ety_emp = new MEmployee();
		ety_emp = empDao.selectById(empID);
		
		// 所属グループのIDの取得
		return ety_emp.defaultGroupId.toString();
	}
	
	/**
	 * <p>週の開始・終了日設定メソッド</p>
	 * 
	 * @param days 日数差
	 * @param sDate スケジュール開始日
	 * @return 開始・終了日が格納された配列
	 * @throws Exception 例外
	 */
	public String[] schWeekDays(int days, String sDate) throws Exception {
		
		String[] weekDayArray;
		
		// 日数差が0の場合、今週の開始・終了日を取得
		if(days == 0) {
			weekDayArray = nowWeekDays();
		
		// 日数差が0以外の場合、スケジュールの開始日+日数差した日付の週の開始・終了日を取得
		} else {
			weekDayArray = weekDays(sDate, days);
		}
		
		return weekDayArray;
	}
	
	/**
	 * <p>今週の開始・終了日の取得メソッド</p>
	 * 
	 * @return 開始・終了日が格納された配列
	 * @throws Exception 例外
	 */
	public String[] nowWeekDays() throws Exception {
	
		// 現在日付の取得
		Date date = new Date();
		
		// 今週の日曜日の日付を取得
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);	
		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		
		// 日付のフォーマット指定
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		// 今週の日曜日と土曜日の日付の取得
		String[] weekDayArray = new String[2];
		weekDayArray[0] = dateFormat.format(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, 6);
		weekDayArray[1] = dateFormat.format(cal.getTime());
		
		return weekDayArray;
	}
	
	/**
	 * <p>開始・終了日の取得メソッド</p>
	 * 
	 * @param sDate 日付
	 * @param days 開始日と日付の日数差
	 * @return 開始・終了日が格納された配列
	 * @throws Exception　例外
	 */
	private static String[] weekDays(String sDate , int days) throws Exception {
		
		// 日付+日数差した日付を取得
		int year = Integer.parseInt(sDate.substring(0,4));
	    int month = Integer.parseInt(sDate.substring(5,7));
	    int date = Integer.parseInt(sDate.substring(8,10)) + days;
	    Calendar cal = Calendar.getInstance();
	    cal.set(year, month - 1, date);
	    
	    // 日付のフォーマット指定
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    
	    // 開始・終了日を取得
	    String[] weekDayArray = new String[2];
		weekDayArray[0] = dateFormat.format(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, 6);
		weekDayArray[1] = dateFormat.format(cal.getTime());
		
		return weekDayArray;
	}
	
	/**
	 * <p>添付ファイル有無判定メソッド</p>
	 * 
	 * @param tipsDto トピックス情報
	 * @return 添付ファイルがある場合、true
	 * 		   ない場合、false
	 * @throws Exception 例外
	 */
	private boolean filePresence(TipsListDto tipsDto) throws Exception {
		
		// ファイルID1がnull以外の場合、trueを返す
		if(tipsDto.file1ID != null) {
			return true;
		}
		
		// ファイルID2がnull以外の場合、trueを返す
		if(tipsDto.file2ID != null) {
			return true;
		}
		
		// ファイルID3がnull以外の場合、trueを返す
		if(tipsDto.file3ID != null) {
			return true;
		}
		
		// ファイルID4がnull以外の場合、trueを返す
		if(tipsDto.file4ID != null) {
			return true;
		}
		
		// ファイルID5がnull以外の場合、trueを返す
		if(tipsDto.file5ID != null) {
			return true;
		}
		
		// 添付ファイルがない場合、falseを返す
		return false;
	}
}
