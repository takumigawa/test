SELECT *
  FROM
  (SELECT 
   T1.seikyu_ID AS keihiID
  ,'0' AS rowtype
  ,T1.PJ_CODE AS pjcode
  ,T1.State AS statusid
  ,' ' AS statusname
  ,M1.name AS kanjoname
  ,T1.seikyu_Date AS seikydate
  ,T1.Kingaku AS kin
  ,T1.kyoka_pjl_Date AS sindate
  FROM t_koguti T1
      ,m_kanjo M1
 WHERE T1.seikyu_Date BETWEEN /*fromDate*/ AND /*toDate*/
   AND T1.Kanjo_ID = M1.kanjo_id
 /*IF inStatus	!= 9*/
   AND T1.State = /*inStatus*/
 /*END*/
 /*IF inPJCode	!= "!"*/
   AND T1.PJ_CODE = /*inPJCode*/
 /*END*/
   AND T1.payment_flag = 0
UNION  ALL 
SELECT 
   T2.Provisional_Head_ID AS keihiID
  ,'1' AS rowtype
  ,T2.PJ_CODE AS pjcode
  ,T2.State AS statusid
  ,' ' AS statusname
  ,'仮払い' AS kanjoname
  ,T2.seikyu_Date AS seikydate
  ,T2.Kingaku AS kin
  ,T2.kyoka_pjl_Date AS sindate
  FROM t_provisional_head T2
 WHERE T2.seikyu_Date BETWEEN /*fromDate*/ AND /*toDate*/
 /*IF inStatus	!= 9*/
   AND T2.State = /*inStatus*/
 /*END*/
 /*IF inPJCode	!= "!"*/
   AND T2.PJ_CODE = /*inPJCode*/
 /*END*/
   AND T2.payment_flag = 0 ) T3
 ORDER BY T3.seikydate ,T3.keihiID ,T3.rowtype 