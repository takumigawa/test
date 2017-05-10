select
	serviceType    			as serviceType,
	serviceTitle			as serviceTitle,
	orderSEQ 			as orderSEQ,
	(case 
		when kaihai = 0 then '有効'
		when kaihai = 1 then '無効'
	 end)		 as serviceEnabled
from
	m_adapter
where
/*IF kaihai	!= 2*/
	kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
	kaihai in (0,1)
/*END*/

order by orderSEQ