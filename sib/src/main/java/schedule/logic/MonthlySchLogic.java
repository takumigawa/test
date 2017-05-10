package schedule.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import schedule.dao.MEmployeeDao;
import schedule.dao.TScheduleDao;
import schedule.dto.AllDaySchDto;
import schedule.dto.AllEmpSchDto;
import schedule.dto.DaySchListDto;
import schedule.dto.ListDto;
import schedule.dto.MonthlySchListDto;
import schedule.dto.TimeScheduleDto;
import schedule.entity.MEmployee;
import schedule.web.common.CommonUtil;

/**
 * <p>月間スケジュールロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class MonthlySchLogic {

	/** 社員マスタDao */
	public MEmployeeDao empDao;
	/** スケジュールテーブルDao */
	public TScheduleDao schDao;
	
	/**
	 * <p>社員名取得メソッド</p>
	 * 
	 * @param employeeID 社員ID
	 * @return　社員名
	 * @throws Exception 例外
	 */
	public String selectEmpName(String employeeID) throws Exception {
		
		// 社員名の取得
		MEmployee ety_emp = new MEmployee();
		int empID = Integer.parseInt(employeeID);
		ety_emp = empDao.selectById(empID);
		
		return ety_emp.employeeName;
	}
	
	/**
	 * <p>年・月リスト取得メソッド</p>
	 * 
	 * @param yearArray 編集用年リスト
	 * @param monthArray 編集用月リスト
	 * @param yearStr 現在の年
	 * @throws Exception 例外
	 */
	public void dateList(ListDto[] yearArray, ListDto[] monthArray, String yearStr) throws Exception {
		
		// 現在の年±5の年リストの取得
		int yearInt = Integer.parseInt(yearStr) - 5;
		for(int i = 0; i < 11; i++) {
			ListDto listDto = new ListDto();
			listDto.label = zeroPading(Integer.toString(yearInt + i), 4);
			listDto.value = zeroPading(Integer.toString(yearInt + i), 4);
			yearArray[i] = listDto;
		}
		
		// 月リストの取得
		for(int i = 0; i < 12; i++) {
			ListDto listDto = new ListDto();
			listDto.label = zeroPading(Integer.toString(i + 1), 2);
			listDto.value = zeroPading(Integer.toString(i + 1), 2);
			monthArray[i] = listDto;
		}
	}
	
	/**
	 * <p>月間スケジュールタイトル取得メソッド</p>
	 * 
	 * @param yearStr 年
	 * @param monthStr 月
	 * @return 年と月に対応したタイトル
	 * @throws Exception 例外
	 */
	public String monthSchTitel(String yearStr, String monthStr) throws Exception {
		
		// 月間スケジュールのタイトルの取得
		StringBuilder titel = new StringBuilder();
		String monthEnglish = monthEnglish(monthStr);
		titel.append(monthEnglish); 
		titel.append("　　");
		titel.append(yearStr);
		return titel.toString();
	}
	
	/**
	 * <p>月間スケジュールリスト取得メソッド</p>
	 * 
	 * @param monthlySchList 編集用月間スケジュールリスト
	 * @param yearStr 年
	 * @param monthStr 月
	 * @param empID 社員ID
	 * @throws Exception 例外
	 */
	public void monthSchList(List<MonthlySchListDto> monthlySchList,
			String yearStr, String monthStr, String empID) throws Exception {
		
		// リストのインスタンス生成	
		ArrayList<AllEmpSchDto> weekSchList	= new ArrayList<AllEmpSchDto>();		// 週ごとのスケジュールリスト
		
		
		// 選択された月の最終週を取得
		Calendar cal = Calendar.getInstance();
		int yearInt = Integer.parseInt(yearStr);
		int monthInt = Integer.parseInt(monthStr);
		cal.set(yearInt, monthInt - 1, 1);
		int dateInt = cal.getActualMaximum(Calendar.DATE);
		cal.set(yearInt, monthInt - 1, dateInt);
		System.out.println(cal);
		int weekInt = cal.get(Calendar.WEEK_OF_MONTH);
		
		// 月間スケジュールの取得
		for(int i = 0; i < weekInt; i++) {
			
			// 日間スケジュールリストの初期化 
			ArrayList<DaySchListDto> daySchList = new ArrayList<DaySchListDto>();		
			
			String[] dayArray = weekDays(yearInt,monthInt,i + 1);
			
			for(int j = 0; j < dayArray.length; j++) {
				
				// 日間スケジュールDtoの初期化
				DaySchListDto daySchListDto = new DaySchListDto();
				
				// 取得するスケジュールの日付の設定
				daySchListDto.scheduleDate	= dayArray[j];						// 日付
				daySchListDto.schMonth		= dayArray[j].substring(5, 7);		// 月
				daySchListDto.dateStr		= dayArray[j].substring(8, 10);		// 日
				
				// 終日スケジュールの取得
				List<AllDaySchDto> allDaySchList = schDao.selectGetAllDaySchTitel(empID, dayArray[j]);
				
				// 終日スケジュールが存在しない場合、日間スケジュールDtoの初期化
				if(allDaySchList == null) {
					allDaySchList = new ArrayList<AllDaySchDto>();
				}
				
				// 時間スケジュールの取得
				List<TimeScheduleDto> timeSchList = schDao.selectGetTimeSchTitel(empID, dayArray[j]);
				
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
						
						if(dayArray[j].equals(startDateStr)) {
							timeSchList.get(k).endTimeStr = "24:00";
						} else if(dayArray[j].equals(endDateStr)) {
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
			
			AllEmpSchDto allEmpSchDto = new AllEmpSchDto();
			allEmpSchDto.employeeID = empID;
			allEmpSchDto.dayScheduleItems = daySchList;
			weekSchList.add(allEmpSchDto);
		}
		MonthlySchListDto monthSchListDto = new MonthlySchListDto();
		monthSchListDto.weekScheduleItems = weekSchList;
		monthlySchList.add(monthSchListDto);
	}
	
	
	/**
	 * <p>現在の年・月の取得メソッド</p>
	 * 
	 * @return 現在の年・月が格納された配列
	 * @throws Exception 例外
	 */
	public String[] dateInitialize() throws Exception {
		
		// システム日付の取得
		String nowDate = CommonUtil.getNowUpdDate();
		
		// システム日付を年・月に分割
		String[] dateArray = new String[2];
		dateArray[0] = nowDate.substring(0, 4);
		dateArray[1] = nowDate.substring(5, 7);
		
		return dateArray;
	}
	
	/**
	 * <p>週の日付取得メソッド</p>
	 * 
	 * @param year 指定年
	 * @param month　指定月
	 * @param week　指定週
	 * @return　指定週の日付配列
	 * @throws Exception 例外
	 */
	private String[] weekDays(int year, int month, int week) throws Exception {
		
		String[] dayArray = new String[7];
		
		// 指定された週の日曜日の日付を取得
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1); 
		cal.set(Calendar.WEEK_OF_MONTH, week);
		System.out.println(cal);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		// 日付のフォーマット指定
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		// 指定された週の日付（日曜～土曜までの日付）の取得
		for(int i = 0; i < 7; i++) {
			dayArray[i] = dateFormat.format(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return dayArray;
	}
	
	/**
	 * <p>先頭0埋め処理メソッド</p>
	 * <p>対象文字列を指定桁数まで先頭0埋めする</p>
	 * 
	 * @param str　編集対象文字列
	 * @param num 指定桁数
	 * @return　編集後文字列
	 * @throws Exception 例外
	 */
	private static String zeroPading(String str, int num) throws Exception {
		
		// 文字列の長さを取得
		int strLength = str.length();
		
		// 0用文字列の生成
		StringBuffer sbZero = new StringBuffer();
		
		// 指定の桁数まで0の文字列を作成する
		for (int i = 0; i < num - strLength; i++) {
			sbZero.append("0");
		}

		// 0用文字列と編集対象文字列を結合
		StringBuffer sb = null;
		sb = new StringBuffer(sbZero);
		sb.append(str);

		return sb.toString();
	}
	
	/**
	 * <p>月の英語表記取得メソッド</p>
	 * 
	 * @param month　該当月
	 * @return 該当月の英語表記
	 * @throws Exception 例外
	 */
	private String monthEnglish(String month) throws Exception {
		
		// 該当月の英語表記を返す
		if(month.equals("01")) {
			return "January";
			
		} else if(month.equals("02")) {
			return "February";
			
		} else if(month.equals("03")) {
			return "March";
			
		} else if(month.equals("04")) {
			return "April";
			
		} else if(month.equals("05")) {
			return "May";
			
		} else if(month.equals("06")) {
			return "June";
			
		} else if(month.equals("07")) {
			return "July";
			
		} else if(month.equals("08")) {
			return "August";
			
		} else if(month.equals("09")) {
			return "September";
			
		} else if(month.equals("10")) {
			return "October";
			
		} else if(month.equals("11")) {
			return "November";
			
		} else if(month.equals("12")) {
			return "December";
		}
		
		return "NoMonth";
	}
}
