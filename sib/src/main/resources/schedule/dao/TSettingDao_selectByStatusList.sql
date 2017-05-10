select
	stringvalue  as label,
	key3         as value
from
	t_setting
where
	key1 = 'status'
and
	key2 = /*key2*/''
and
	integervalue > 0
order by
	integervalue