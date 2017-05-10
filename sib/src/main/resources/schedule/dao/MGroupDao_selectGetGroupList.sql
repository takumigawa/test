select
	name  as groupName,
	Group_ID    as groupID,
	authority as groupauthority,
	orderSEQ as groupSEQ,
	(case 
		when kaihai = 0 then '有効'
		when kaihai = 1 then '無効'
	 end)		 as groupEnabled
from
	m_group
where
/*IF kaihai	!= 2*/
	kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
	kaihai in (0,1)
/*END*/
/*IF aGyo != null*/ 
	and (substring(name_kana,1,1) /*$aGyo*/)
/*END*/ 
order by orderSEQ