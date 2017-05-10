select
	stringvalue  as empName,
	key3         as empID,
	key2		 as empemail,
	'有効'		 as empEnabled
from
	t_setting
where
	key1 = /*key1*/''
and
	key2 = /*key2*/''
order by
	integervalue