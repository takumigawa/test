select
	max(CAST(key3 as SIGNED)) + 1
from t_setting
where
	key1 = 'comblist'
and
	key2 = 'class'