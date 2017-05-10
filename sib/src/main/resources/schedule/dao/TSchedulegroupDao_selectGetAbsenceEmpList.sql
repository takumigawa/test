select
	Employee_ID		as value,
	Employee_Name	as label
from
	m_employee
where
	Employee_ID not in
	(
		select
			m_employee.Employee_ID
		from
			t_schedulegroup,m_employee
		where
			t_schedulegroup.Employee_ID = m_employee.Employee_ID
		and
			t_schedulegroup.schedule_ID = /*schedule_ID*/
	)
and
	kaihai = 0;