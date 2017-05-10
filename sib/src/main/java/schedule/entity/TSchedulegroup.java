package schedule.entity;
import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_schedulegroup")
public class TSchedulegroup {

	public Integer scheduleId;
	public Integer employeeId;

}
