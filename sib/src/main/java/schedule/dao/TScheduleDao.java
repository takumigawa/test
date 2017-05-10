package schedule.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.AllDaySchDto;
import schedule.dto.TimeScheduleDto;
import schedule.entity.TSchedule;

@S2Dao(bean=TSchedule.class)
public interface TScheduleDao {

	public TSchedule[] selectAll();

	@Arguments("work_ID")
	public TSchedule selectById(Integer workId);

	/**
	 * <p>時間スケジュール一覧取得</p>
	 * 
	 * @param employeeID 社員ID
	 * @param dateStr 対象日付
	 * @return 時間スケジュール一覧
	 */
	@Arguments({ "employeeID", "date" })
	public List<TimeScheduleDto> selectGetTimeSchTitel(String employeeID,String dateStr);
	
	/**
	 * <p>終日スケジュール一覧取得</p>
	 * 
	 * @param employeeID 社員ID
	 * @param dateStr 対象日付
	 * @return 終日スケジュール一覧
	 */
	@Arguments({ "employeeID", "date" })
	public List<AllDaySchDto> selectGetAllDaySchTitel(String employeeID,String dateStr);
	
	public int insert(TSchedule schedule);

	public int update(TSchedule schedule);

	public int delete(TSchedule schedule);

}
