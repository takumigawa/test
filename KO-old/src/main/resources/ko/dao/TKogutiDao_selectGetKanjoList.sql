SELECT  seikyu_ID   as seikyu
       ,seikyu_Date as seikyu_Date
       ,PJ_CODE     as PJ_CODE
       ,State       as state
       ,t_koguti.Kanjo_ID
       ,m_kanjo.name as kamoku
       ,Kingaku     as kingaku
       ,t_koguti.UpdDate as upd_date
       ,biko as bikou
  FROM t_koguti left join m_kanjo on t_koguti.Kanjo_ID = m_kanjo.Kanjo_ID
WHERE

/*IF kaihai	!= 2*/
	kaihai = /*kaihai*/
/*END*/
/*IF kaihai	== 2*/
	kaihai in (0,1)
/*END*/
and
    (substring(seikyu_Date,1,4) = /*year*/)
and
    (substring(seikyu_Date,6,2) = /*month*/)
/*IF State != 0*/
and
	State = /*state*/
/*END*/
and
	seikyu_empID = /*employee*/
