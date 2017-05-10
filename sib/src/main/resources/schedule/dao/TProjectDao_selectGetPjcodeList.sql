select
	PJ_CODE    		as projectCode,
	PJ_NAME			as projectName,
	(case 
		when kaihai = 0 then '有効'
		when kaihai = 1 then '無効'
	 end)		 as projectEnabled
from
	t_project
where
/*IF kaihai	!= 2*/
	kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
	kaihai in (0,1)
/*END*/
