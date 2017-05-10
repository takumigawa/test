select
	stringValue  as className,
	key3         as classID,
	(case 
		when integerValue <> 0 then '有効'
		when integerValue = 0 then '無効'
	 end)		 as classEnabled,
	(case 
		when integerValue <> 0 then 0
		when integerValue = 0 then 1
	 end)		 as DispOrderSEQ
from
	t_setting
where
	key1 = 'comblist'
and
	key2 = 'class'
/*IF kaihai	== 0*/
and
	integerValue <> 0
/*END*/
/*IF kaihai	== 1*/
and
	integerValue = 0
/*END*/

Order by
	DispOrderSEQ,integerValue
