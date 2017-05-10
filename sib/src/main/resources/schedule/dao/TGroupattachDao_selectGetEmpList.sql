select distinct
	m_employee.Employee_ID   as empID,
	m_employee.Employee_Name as empName
from 
	t_groupattach,m_employee
where 
/*BEGIN*/
	/*IF GroupID != 0*/
	t_groupattach.group_ID = /*GroupID*/
and
	/*END*/
/*END*/ 
	t_groupattach.Employee_ID = m_employee.Employee_ID
and 
	m_employee.kaihai = 0
order by 
	m_employee.orderSEQ;