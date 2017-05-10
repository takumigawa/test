select
	m_employee.Employee_ID		as value,
	m_employee.Employee_Name	as label
from
	t_schedulegroup,m_employee
where
	t_schedulegroup.Employee_ID = m_employee.Employee_ID
and
	t_schedulegroup.schedule_ID = /*schedule_ID*/;