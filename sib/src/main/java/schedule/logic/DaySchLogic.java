package schedule.logic;

import java.util.ArrayList;
import java.util.List;

import schedule.dao.MEmployeeDao;
import schedule.dao.TScheduleDao;
import schedule.dto.AllDaySchDto;
import schedule.dto.AllTimeSchDto;
import schedule.dto.TimeScheduleDto;
import schedule.entity.MEmployee;

/**
 * <p>日間スケジュールロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class DaySchLogic {

	/** 社員マスタDao */
	public MEmployeeDao empDao;
	/** スケジュールテーブルDao */
	public TScheduleDao schDao;
	
	/**
	 * <p>社員名取得メソッド</p>
	 * 
	 * @param employeeID 社員ID
	 * @return 社員名
	 * @throws Exception 例外
	 */
	public String selectEmpName(String employeeID) throws Exception {
		
		// 選択された社員名の取得
		MEmployee ety_emp = new MEmployee();
		int empID = Integer.parseInt(employeeID);
		ety_emp = empDao.selectById(empID);
		return ety_emp.employeeName;
	}
	
	/**
	 * <p>日間スケジュール取得メソッド</p>
	 * 
	 * @param allDaySchList 編集用終日スケジュールリスト
	 * @param allTimeSchList 編集用時間スケジュールリスト
	 * @param employeeID 社員ID
	 * @param schDate 取得日
	 * @throws Exception 例外
	 */
	public void daysSchList(List<AllDaySchDto> allDaySchList,
			List<AllTimeSchDto> allTimeSchList, String employeeID, String schDate) throws Exception {
		
		// 終日スケジュールの取得
		List<AllDaySchDto> workAllDayList = schDao.selectGetAllDaySchTitel(employeeID, schDate);
		allDaySchList.addAll(workAllDayList);
		
		// 作業用時間スケジュールの取得
		List<TimeScheduleDto> workTimeSchList = schDao.selectGetTimeSchTitel(employeeID, schDate);
		
		// 取得した時間スケジュールを表示用（1日分のスケジュール）に変換
		for(int i = 0; i < workTimeSchList.size(); i++) {
			
			String startDateStr = workTimeSchList.get(i).startDate;
			String endDateStr = workTimeSchList.get(i).endDate;
			
			if(workTimeSchList.get(i).startTimeStr == null) {
				workTimeSchList.get(i).startTimeStr = "00:00";
			}
			
			if(workTimeSchList.get(i).endTimeStr == null) {
				workTimeSchList.get(i).endTimeStr = "24:00";
			}
			
			if(!startDateStr.equals(endDateStr)) {
				
				if(schDate.equals(startDateStr)) {
					workTimeSchList.get(i).endTimeStr = "24:00";
				} else if(schDate.equals(endDateStr)) {
					workTimeSchList.get(i).startTimeStr = "00:00";
				} else {
					workTimeSchList.get(i).startTimeStr = "00:00";
					workTimeSchList.get(i).endTimeStr = "24:00";
				}
			}
		}
		
		// 時間スケジュールの取得
		for(int i = 0; i < 24; i++) {
			for(int j = 0; j < 4; j++) {
				
				// 時間ごとのスケジュールリストの初期化
				ArrayList<TimeScheduleDto> timeSchList = new ArrayList<TimeScheduleDto>();
				
				// 時間の取得
				StringBuilder timeStrBuil = new StringBuilder();
				String intStr = Integer.toString(i);
				String timeStr = zeroPading(intStr, 2);
				timeStrBuil.append(timeStr);
				timeStrBuil.append(":");
				intStr = Integer.toString(j * 15);
				timeStr = zeroPading(intStr, 2);
				timeStrBuil.append(timeStr);
				timeStr = timeStrBuil.toString();
				
				// 取得した作業用時間スケジュールリストの件数分、処理を繰り返す
				for(int k = 0; k < workTimeSchList.size(); k++) {
					
					// 時間スケジュールDtoの初期化
					TimeScheduleDto timeSchDto = new TimeScheduleDto();
					
					// 取得した時間に時間スケジュールがある場合、時間ごとのスケジュールリストにスケジュールを設定
					if(timeStr.compareTo(workTimeSchList.get(k).startTimeStr) >= 0 
							&& timeStr.compareTo(workTimeSchList.get(k).endTimeStr) < 0) {
						timeSchDto = workTimeSchList.get(k);
						timeSchList.add(timeSchDto);
					}
				}
				
				// 時間スケジュールリストに時間ごとのスケジュールリストと時間を設定
				AllTimeSchDto allTimeSchDto = new AllTimeSchDto();
				allTimeSchDto.timeStr = timeStr.substring(0, 2);
				allTimeSchDto.timeScheduleItems = timeSchList;
				allTimeSchList.add(allTimeSchDto);
			}
		}
		
		// 時間ごとのスケジュールの最大サイズの取得
		int maxIndex = maxIndex(allTimeSchList);
		
		// 時間スケジュールリストを表示用に変換
		// インデックスの初期化
		int index = 0;
		
		// インデックスが時間ごとのスケジュールの最大サイズ未満の間、処理を繰り返す
		while(index < maxIndex) {
			
			// 時間スケジュールリストの件数分、処理を繰り返す 
			for(int i = 0; i < allTimeSchList.size(); i++) {
				
				// スケジュールID比較用文字列（空行）の取得
				String IDStr = allTimeSchList.get(i).timeStr+"Blank";
				
				// 時間ごとのスケジュールがない場合、空行を設定
				if(allTimeSchList.get(i).timeScheduleItems.size() == 0) {
					allTimeSchList.get(i).timeScheduleItems = new ArrayList<TimeScheduleDto>();
					TimeScheduleDto timeSchDto = new TimeScheduleDto();
					timeSchDto.scheduleID = IDStr;
					timeSchDto.timeSchTitelStr = "";
					allTimeSchList.get(i).timeScheduleItems.add(timeSchDto);
				}
				
				// 時間ごとのスケジュールリストのサイズが最大サイズ未満の場合、重複チェックを行う
				if(allTimeSchList.get(i).timeScheduleItems.size() < maxIndex) {
					
					// 時間スケジュールリストの件数分、処理を繰り返す
					for(int j = 0; j < allTimeSchList.get(i).timeScheduleItems.size(); j++) {
						
						// 空行以外、かつスケジュールが重複している場合、重複している件数になるように空行を設定
						if(allTimeSchList.get(i).timeScheduleItems.get(j).scheduleID.compareTo(IDStr) != 0 
								&& duplicateCheck(workTimeSchList, allTimeSchList.get(i).timeScheduleItems.get(j))){
							
							// 重複件数の取得
							int listCount = listIndex(workTimeSchList, allTimeSchList.get(i).timeScheduleItems.get(j));
							
							// 重複件数になるように空行を設定
							for(int k = allTimeSchList.get(i).timeScheduleItems.size(); k < listCount; k++) {
								TimeScheduleDto timeSchDto = new TimeScheduleDto();
								timeSchDto.scheduleID = allTimeSchList.get(i).timeStr + "Blank";
								timeSchDto.timeSchTitelStr = "";
								allTimeSchList.get(i).timeScheduleItems.add(timeSchDto);
							}
						}
					}
				}
				
				// スケジュールの最大インデックスの初期化
				int schMaxIndex = 0;
				
				// 時間ごとのスケジュールリストのサイズが時間ごとのスケジュールの最大サイズと同じなら、スケジュールの並び替えを行う
				if(allTimeSchList.get(i).timeScheduleItems.size() == maxIndex) {
					
					// 時間スケジュールリストの件数分、処理を繰り返す
					for(int k = 0; k < allTimeSchList.size(); k++) {
						
						// 時間ごとのスケジュールリストの件数分、処理を繰り返す
						for(int l = 0; l < allTimeSchList.get(k).timeScheduleItems.size(); l++) {
							
							// スケジュールIDが同じスケジュールの最大インデックスを取得
							if(allTimeSchList.get(i).timeScheduleItems.get(index).scheduleID.
									equals(allTimeSchList.get(k).timeScheduleItems.get(l).scheduleID)) {
							
								// インデックスと最大インデックスが1未満の場合、最大インデックスに1を設定
								if(index < l && schMaxIndex < l) {
									schMaxIndex = l;
								
								// インデックスが1より大きい、かつ最大インデックスがインデックス未満の場合か、
								// インデックスが1、かつ最大インデックスがインデックス未満の場合、最大インデックスにインデックスを代入
								} else if((index > l && schMaxIndex < index) || (index == l && schMaxIndex < index)) {
									schMaxIndex = index;
								}
							}
						}
					}
				
					// インデックスと最大インデックスが異なり、かつ空行以外の場合、最大インデックスにスケジュールを移動させる
					if(index != schMaxIndex 
							&& allTimeSchList.get(i).timeScheduleItems.get(index).scheduleID.compareTo(IDStr) != 0) {
						TimeScheduleDto timeSchDto = new TimeScheduleDto();
						timeSchDto = allTimeSchList.get(i).timeScheduleItems.get(index);
						allTimeSchList.get(i).timeScheduleItems.remove(index);
						allTimeSchList.get(i).timeScheduleItems.add(schMaxIndex, timeSchDto);
					}
				}
			}
		
			// インデックスを1加算
			index++;
		}
		
		// 時間ごとのスケジュールのサイズの最小公倍数の取得
		int leastCommon = leastCommon(allTimeSchList);
		
		// 時間スケジュールに結合する列のセル数を設定
		for(int i = 0; i < allTimeSchList.size(); i++) {
			int listSize = allTimeSchList.get(i).timeScheduleItems.size();
			int colspanInt = leastCommon / listSize;
			for(int j = 0; j < allTimeSchList.get(i).timeScheduleItems.size(); j++) {
				allTimeSchList.get(i).timeScheduleItems.get(j).timeSchColspan = colspanInt;
			}
		}
	}
	
	/**
	 * <p>スケジュール存在チェックメソッド</p>
	 * 
	 * @param employeeID 社員ID
	 * @param schDate 選択された日付
	 * @return 選択された日付にスケジュールがある場合、false
	 *         ない場合、true
	 * @throws Exception 例外
	 */
	public boolean scheduleChk(String employeeID, String schDate) throws Exception {
		
		// 終日スケジュールの取得
		List<AllDaySchDto> allDaySchList = schDao.selectGetAllDaySchTitel(employeeID, schDate);
		
		// 時間スケジュールの取得
		List<TimeScheduleDto> timeSchList = schDao.selectGetTimeSchTitel(employeeID, schDate);
		
		// 選択された日付にスケジュールがない場合、trueを返す
		if(allDaySchList.size() == 0 && timeSchList.size() == 0) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * <p>時間ごとのスケジュールリストの最小公倍数の最大数取得メソッド</p>
	 * 
	 * @param allTimeSchList 時間ごとのスケジュールリスト
	 * @return 最大数
	 * @throws Exception 例外
	 */
	public int leastCommon(List<AllTimeSchDto> allTimeSchList) throws Exception {
		
		// 最大数の初期化
		int leastCommon = 1;
		
		// 時間スケジュールリストの件数分、処理を繰り返す
		for(int i = 0; i < allTimeSchList.size(); i++) {
			for(int j = 0; j < allTimeSchList.size(); j++) {
				
				// 時間ごとのスケジュールリストのサイズを2つ取得
				int number1 = allTimeSchList.get(i).timeScheduleItems.size();
				int number2 = allTimeSchList.get(j).timeScheduleItems.size();
				
				// 最小公倍数の初期化
				int num = 0;
				
				// 取得した時間ごとのスケジュールリストのサイズが0以外の場合、最小公倍数を取得
				if (number1 != 0 && number2 != 0) {
					num = findLCM(number1,number2);
				}
				
				// 取得した最小公倍数が最大値より大きい場合、最大値に最小公倍数を代入
				if(num > leastCommon) {
					leastCommon = num;
				}
			}
		}
		return leastCommon;
	}
	
	/**
	 * <p>時間ごとのスケジュールリストの最大数取得メソッド</p>
	 * 
	 * @param allTimeSchList 時間ごとのスケジュールリスト
	 * @return 最大数
	 * @throws Exception 例外
	 */
	public int maxIndex(List<AllTimeSchDto> allTimeSchList) throws Exception {
		
		// 最大数の初期化
		int maxIndex = 1;
		
		// 時間スケジュールリストの件数分、処理を繰り返す
		for(int i = 0; i < allTimeSchList.size(); i++) {
			
			// 時間ごとのスケジュールリストのサイズの取得
			int index = allTimeSchList.get(i).timeScheduleItems.size();
			
			// 取得した時間ごとのスケジュールリストのサイズが、最大数より大きい場合、
			// 最大数に取得した時間ごとのスケジュールリストのサイズを代入
			if(maxIndex < index) {
				maxIndex = index;
			}
		}
		
		return maxIndex;
	}
	
	/**
	 * <p>重複数取得メソッド</p>
	 * 
	 * @param timeSchList　時間スケジュールリスト
	 * @param timeSchDto　チェック対象スケジュール
	 * @return 重複数
	 * @throws Exception 例外
	 */
	public int listIndex(List<TimeScheduleDto> timeSchList, TimeScheduleDto timeSchDto) throws Exception {
		
		// 重複数の取得
		int count = 0;
		
		// 時間スケジュールリストの件数分、処理を繰り返す
		for(int i = 0; i < timeSchList.size(); i++) {
			
			// 対象スケジュールと時間スケジュールリスト内のスケジュールが重複している場合、重複数を1加算する
			if((timeSchList.get(i).startTimeStr.compareTo(timeSchDto.startTimeStr) <= 0 
					&& timeSchList.get(i).endTimeStr.compareTo(timeSchDto.startTimeStr) > 0) 
				|| (timeSchList.get(i).startTimeStr.compareTo(timeSchDto.endTimeStr) < 0 
					&& timeSchList.get(i).endTimeStr.compareTo(timeSchDto.endTimeStr) >= 0)) {
				count++;
				
			} else if((timeSchDto.startTimeStr.compareTo(timeSchList.get(i).startTimeStr) <= 0
					&& timeSchDto.startTimeStr.compareTo(timeSchList.get(i).endTimeStr) <= 0)
				&& (timeSchDto.endTimeStr.compareTo(timeSchList.get(i).startTimeStr) >= 0
					&& timeSchDto.endTimeStr.compareTo(timeSchList.get(i).endTimeStr) >= 0)) {
				count++;
			}
		}
		
		return count;
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
	 * <p>最小公倍数取得メソッド</p>
	 * 
	 * @param number1 対象数1
	 * @param number2 対象数2
	 * @return 2つの対象数の最小公倍数
	 * @throws Exception 例外
	 */
	private int findLCM(int number1, int number2) throws Exception {
		
		int gcd = number1;
		
		//最大公約数を求める
		while((number1 % gcd) != 0 || (number2 % gcd) != 0) {
			gcd--;
		}

		// 最小公倍数を返す
		return (int) ((number1 * number2) / gcd);
	}
	
	/**
	 * <p>重複チェックメソッド</p>
	 * 
	 * @param timeSchList 時間スケジュールリスト
	 * @param timeSchDto チェック対象スケジュール
	 * @return 重複している場合、true
	 *         重複していない場合、false
	 * @throws Exception　例外
	 */
	private boolean duplicateCheck(List<TimeScheduleDto> timeSchList, TimeScheduleDto timeSchDto) throws Exception {
		
		// スケジュールの重複数の取得
		int count = listIndex(timeSchList, timeSchDto);
		
		// 重複している場合、trueを返す
		if(count >= 2) {
			return true;
		}
		
		// 重複していない場合、falseを返す
		return false;
	}
}
