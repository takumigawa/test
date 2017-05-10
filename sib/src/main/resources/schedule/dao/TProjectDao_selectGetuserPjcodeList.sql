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
	PJ_CODE IN (select
	PJ_CODE   
from
	t_relation_project_employee
where
	Employee_ID = /*Employee_ID*/ )
/*IF kaihai	!= 2*/
and	kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
and	kaihai in (0,1)
/*END*/
