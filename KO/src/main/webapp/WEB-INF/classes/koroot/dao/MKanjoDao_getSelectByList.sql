select
	kanjo_ID    			as kanjoID,
	name  					as kanjoName,
	orderSEQ			as orderSEQ,
	(case 
		when kaihai = 0 then '有効'
		when kaihai = 1 then '無効'
	 end)		 as kanjoEnabled
from
	m_kanjo
where
/*IF kaihai	!= 2*/
	kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
	kaihai in (0,1)
/*END*/
order by orderSEQ