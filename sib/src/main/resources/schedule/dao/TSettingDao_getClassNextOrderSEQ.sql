select
	max(integerValue) + 10
from t_setting
where
	key1 = 'comblist'
and
	key2 = 'class'