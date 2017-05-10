SELECT 
   T1.Provisional_Detail_ID AS meisaiID
  ,T1.Provisional_Head_ID AS karibaraiID
  ,T1.siyouDate AS siyouday
  ,M1.name AS kanjoname
  ,T1.Kingaku AS kin
  ,T1.biko AS biko
 
   FROM t_provisional_detale T1
      ,m_kanjo M1
      
 WHERE T1.Provisional_Head_ID = /*selectID*/
   AND T1.Kanjo_ID = M1.kanjo_id
 /*IF kaihai	!= 2*/
   AND T1.kaihai = /*kaihai*/
 /*END*/
 /*IF kaihai	== 2*/
   AND T1.kaihai in (0,1)
 /*END*/  
 ORDER BY T1.siyouDate ,T1.Provisional_Detail_ID 