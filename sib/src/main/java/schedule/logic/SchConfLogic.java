package schedule.logic;

import java.util.List;

import schedule.dao.TScheduleDao;
import schedule.dao.TSchedulegroupDao;
import schedule.dto.AllDaySchDto;
import schedule.dto.TimeScheduleDto;
import schedule.entity.TSchedule;

/**
 * <p>スケジュール削除確認ロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class SchConfLogic {

	/** スケジュールテーブルDao */
	public TScheduleDao schDao;
	/** スケジュールグループテーブルDao */
	public TSchedulegroupDao schgruDao;
	
	/**
	 * <p>スケジュール削除メソッド</p>
	 * 
	 * @param scheduleID スケジュールID
	 * @throws Exception 例外
	 */
	public void deleteSch(String scheduleID) throws Exception {
		
		// スケジュールの削除
		int workID = Integer.parseInt(scheduleID);
		schgruDao.deleteBySchgroup(workID);
		TSchedule ety_sch = new TSchedule();
		ety_sch.workId = workID;
		schDao.delete(ety_sch);
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
}
