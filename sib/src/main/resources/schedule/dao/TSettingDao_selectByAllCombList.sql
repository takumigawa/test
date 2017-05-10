select
	stringvalue  as label,
	key3         as value,
	integervalue as orderseq,
	(case 
		when integervalue = 0 then '無効'
		else  '有効'
	 end)		 as kaihai
from
	t_setting
where
	key1 = 'comblist'
and
	key2 = /*key2*/''
order by
	key3