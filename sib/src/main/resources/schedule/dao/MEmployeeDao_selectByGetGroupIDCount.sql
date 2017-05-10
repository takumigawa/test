select
	count(*)
from
	m_employee me, t_groupattach tga, m_group mg
where
	mg.group_id = /*GroupID*/
and
	mg.group_id = tga.group_id
and
	me.employee_id = tga.employee_id
and
	me.kaihai = 0
