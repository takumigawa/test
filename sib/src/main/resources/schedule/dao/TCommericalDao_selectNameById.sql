select
	mc.name  as userName
from
	t_commerical tc, m_customer mc
where
	tc.cd_ID = /*cdId*/''
and
	tc.userID = mc.customer_ID
order by
	tc.comSEQ
