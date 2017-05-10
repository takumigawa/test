select
	Name        as label,
	Group_ID    as value
from
	m_group
where
	kaihai = 0
order by
	orderSEQ