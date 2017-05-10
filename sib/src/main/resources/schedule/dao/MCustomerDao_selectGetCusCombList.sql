select
	Name  as label,
	Customer_ID    as value
from
	m_customer
where
	kaihai = 0
order by
	orderSEQ