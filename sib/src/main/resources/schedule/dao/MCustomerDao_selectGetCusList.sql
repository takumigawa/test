select
	customer_ID    			as cusID,
	name  					as cusName,
	concat(Address1,concat(' ',Address2)) as cusAddress,
	tel 					as custel,
	email 			as cusemail,
	(case 
		when kaihai = 0 then '有効'
		when kaihai = 1 then '無効'
	 end)		 as cusEnabled
from
	m_customer
where
/*IF kaihai	!= 2*/
	kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
	kaihai in (0,1)
/*END*/
/*IF aGyo != null*/ 
	and (substring(Name_kana,1,1) /*$aGyo*/)
/*END*/ 
order by orderSEQ