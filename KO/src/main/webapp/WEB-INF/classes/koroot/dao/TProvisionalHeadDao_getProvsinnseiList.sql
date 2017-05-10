SELECT 
   T1.Provisional_Head_ID AS keihiID
  ,T1.seikyu_Date AS seikydate
  ,T1.karibarai_Date AS karibaraidate
  ,T1.PJ_CODE AS pjcode
  ,T1.State AS statusid
  ,' ' AS statusname
  ,T1.Kingaku AS kin
  ,T1.UpdDate AS upddate
  ,T1.Detail_entry_flag AS detailentryflag
  ,' ' AS meisai
 
   FROM t_provisional_head T1
      
 WHERE T1.seikyu_Date BETWEEN /*fromDate*/ AND /*toDate*/
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
 ORDER BY T1.seikyu_Date ,T1.Provisional_Head_ID 