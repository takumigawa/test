SELECT  seikyu_ID as seikyuId
       ,seikyu_Date as seikyudate
       ,PJ_CODE as pjcode
       ,State as state
       ,seikyu_empID as seikyuemp
       ,Kanjo_ID as kanjoid
       ,Kingaku as kingaku
       ,UpdDate as upddate
       ,biko as biko
FROM t_koguti
WHERE
  kaihai in (0,1)
