package schedule.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_schedule")
public class TSchedule {

	public static final String workId_ID = "identity";
	public Integer workId;
	public Integer employeeId;
	public String startDate;
	public String startTime;
	public String endDate;
	public String endTime;
	public String titel;
	public String note;
	public Integer priority;
	public String adddate;
	public Integer addid;
	public String upddate;
	public Integer updid;

}
