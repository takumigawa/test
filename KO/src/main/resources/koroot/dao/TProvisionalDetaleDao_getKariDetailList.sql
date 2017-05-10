SELECT
   T1.Provisional_Detail_ID as keihiID
  ,T1.siyouDate as lsiyoudate
  ,M1.name as lkanjoname
  ,Kingaku as lkin
  FROM t_provisional_detale T1
      ,m_kanjo M1
 WHERE T1.Provisional_Head_ID = /*seikyuID*/
   AND M1.kanjo_id = T1.Kanjo_ID