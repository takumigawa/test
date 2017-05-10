select
	t_schedule.work_ID		as scheduleID,
	t_schedule.titel		as timeSchTitelStr,
	t_schedule.note			as timeSchTitelStrTitle,
	t_schedule.start_Date	as startDate,
	t_schedule.Start_Time	as startTimeStr,
	t_schedule.end_Date		as endDate,
	t_schedule.end_Time		as endTimeStr,
	t_schedule.priority		as priority
from 
	t_schedule,t_schedulegroup
where 
	t_schedule.work_ID = t_schedulegroup.schedule_ID
and 
	t_schedulegroup.Employee_ID = /*employeeID*/
and 
	/*date*/
        between 
            t_schedule.start_Date 
        and
            t_schedule.end_Date
and
	(
		( 
			t_schedule.start_Time is not null 
		or 
			t_schedule.end_Time is not null
		)
	or
		( 
			t_schedule.start_Time is not null 
		and 
			t_schedule.end_Time is not null
		)
	)
order by 
	t_schedule.start_time,t_schedule.priority DESC;