select
	Employee_Name  as empName,
	Employee_ID    as empID,
	Employee_email as empemail,
	(case 
		when kaihai = 0 then '有効'
		when kaihai = 1 then '無効'
	 end)		 as empEnabled
from
	m_employee
where
/*IF kaihai	!= 2*/
	kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
	kaihai in (0,1)
/*END*/
/*IF aGyo != null*/ 
	and (substring(Employee_Name_kana,1,1) /*$aGyo*/)
/*END*/ 
order by orderSEQ