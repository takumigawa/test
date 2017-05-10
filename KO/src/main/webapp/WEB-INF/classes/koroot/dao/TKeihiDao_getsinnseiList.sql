SELECT 
   T1.seikyu_ID AS keihiID
  ,T1.seikyu_Date AS seikydate
  ,T1.PJ_CODE AS pjcode
  ,T1.State AS statusid
  ,' ' AS statusname
  ,M1.name AS kanjoname
  ,T1.Kingaku AS kin
  ,T1.UpdDate AS upddate
  ,T1.biko AS biko
 
   FROM t_keihi T1
      ,m_kanjo M1
      
 WHERE T1.seikyu_Date BETWEEN /*fromDate*/ AND /*toDate*/
   AND T1.Kanjo_ID = M1.kanjo_id	
 /*IF State	!= 9*/
   AND T1.State = /*State*/
 /*END*/
 /*IF kaihai	!= 2*/
   AND T1.kaihai = /*kaihai*/
 /*END*/
 /*IF kaihai	== 2*/
   AND T1.kaihai in (0,1)
 /*END*/  
 AND
	T1.seikyu_empID = /*seikyu_empID*/
 ORDER BY T1.seikyu_Date ,T1.seikyu_ID 