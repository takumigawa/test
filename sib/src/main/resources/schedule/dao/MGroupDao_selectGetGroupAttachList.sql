select
	me.Employee_Name  as label,
	me.Employee_ID    as value
from
	t_groupattach mga, m_employee me, m_group mg
where
	me.kaihai = 0
and
	mg.kaihai = 0
and
	mga.employee_ID = me.employee_ID
and
	mga.group_ID  = mg.group_ID
and
	mga.group_ID   = /*GroupID*/
order by
	me.orderSEQ