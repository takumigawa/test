select
	t_project.PJ_CODE    		as projectCode,
	t_project.PJ_NAME			as projectName,
	m_employee.Employee_Name    as projectLeader,
	(case 
		when t_project.kaihai = 0 then '有効'
		when t_project.kaihai = 1 then '無効'
	 end)		 as projectEnabled
from
	t_project,m_employee
where
	t_project.LeaderID = m_employee.Employee_ID
	and 

/*IF kaihai	!= 2*/
	t_project.kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
	t_project.kaihai in (0,1)
/*END*/
