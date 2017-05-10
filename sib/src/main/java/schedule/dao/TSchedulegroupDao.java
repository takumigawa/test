package schedule.dao;

import java.util.ArrayList;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.ListDto;
import schedule.entity.TSchedulegroup;


@S2Dao(bean=TSchedulegroup.class)
public interface TSchedulegroupDao {

	public TSchedulegroup[] selectAll();

	@Arguments( { "schedule_ID", "Employee_ID" })
	public TSchedulegroup selectById(Integer scheduleId, Integer employeeId);

	/**
	 * <p>スケジュール参加の社員名一覧取得</p>
	 * 
	 * @param scheduleId スケジュールID
	 * @return 参加社員名一覧
	 */
	@Arguments("schedule_ID")
	public String[] selectGetEmpNameList(Integer scheduleId);
	
	/**
	 * <p>スケジュール参加社員一覧取得</p>
	 * 
	 * @param scheduleId スケジュールID
	 * @return 参加社員一覧
	 */
	@Arguments("schedule_ID")
	public ArrayList<ListDto> selectGetEmpList(Integer scheduleId);
	
	/**
	 * <p>スケジュール不参加社員一覧取得</p>
	 * 
	 * @param scheduleId スケジュールID
	 * @return 不参加社員一覧
	 */
	@Arguments("schedule_ID")
	public ArrayList<ListDto> selectGetAbsenceEmpList(Integer scheduleId);
	
	public int insert(TSchedulegroup schedulegroup);

	/**
	 * <p>スケジュールグループ削除</p>
	 * 
	 * @param scheduleId スケジュールID
	 * @return 件数
	 */
	@Arguments("schedule_ID")
	public int deleteBySchgroup(Integer scheduleId);
	
	public int delete(TSchedulegroup schedulegroup);

}
