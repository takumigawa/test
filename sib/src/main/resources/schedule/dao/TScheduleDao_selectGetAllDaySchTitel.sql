select 
	t_schedule.work_ID	as scheduleID,
	t_schedule.titel 	as allDaySchTitelStr,
	t_schedule.note  	as allDaySchTitelStrTitle,
	t_schedule.priority as priority
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
	t_schedule.start_time is null
and
	t_schedule.end_time is null	
order by 
	t_schedule.start_time,t_schedule.priority DESC;