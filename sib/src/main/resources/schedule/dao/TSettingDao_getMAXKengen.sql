select
	MAX(integervalue)
from
	t_setting
where
	key1 = 'system'
and
	key2 = 'authority'
