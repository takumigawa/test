select
	Employee_Name  as label,
	Employee_ID    as value
from
	m_employee
where
	kaihai = 0
order by
	orderSEQ