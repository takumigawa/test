select
	tm.Matter_id as matID,
	me.Employee_name as matEmployeeName,
	(case 
		when tm.kaihai = 1 then concat(tm.Matter_titel,'  �i�I���j')
		else ifnull(tm.Matter_titel,'参加案件無し') 
	 end) as matMatterName,
    tm.Matter_Address1 as matMatterAddress,
	(case 
		when tm.Matter_end is null then tm.Matter_start
		else concat(tm.Matter_start,concat(' ～ ',tm.Matter_end))
	 end) as matBetween,
	tm.matter_note as matnote,
	(case 
		when tm.Matter_titel is null then false
		else true
	 end) as matEnable
from
	m_employee me 
left join t_matter tm
	on tm.Employee_ID = me.Employee_ID
/*IF kaihai	== 0*/
and
	tm.kaihai = 0
/*END*/
/*IF kaihai	== 1*/
and
	tm.kaihai = 1
/*END*/

where
	me.kaihai = 0
/*IF empID != null*/
and
	me.Employee_ID = /*empID*/''
/*END*/
/*IF aGyo != null*/ 
	and (substring(Employee_Name_kana,1,1) /*$aGyo*/)
/*END*/
/*IF kaihai	== 1*/
and
	tm.Matter_titel is not null
/*END*/
order by me.orderSEQ, tm.Matter_ID