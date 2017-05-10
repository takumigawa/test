select
	mg.name  as groupName,
	mg.Group_ID    as groupID,
	mg.authority as groupauthority,
	mg.orderSEQ as groupSEQ,
	'—LŒø'		 as groupEnabled
from
	m_group mg, t_groupattach tga
where
	mg.kaihai = 0
and
	mg.group_id = tga.group_id
and
	tga.employee_id = /*empID*/
	
order by mg.orderSEQ