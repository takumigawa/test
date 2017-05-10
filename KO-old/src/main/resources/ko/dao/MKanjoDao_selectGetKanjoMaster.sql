SELECT Kanjo_ID as kanjo
       ,name as name
       ,OderSEQ as orderSEQ
       ,(case 
				when kaisai = 0 then '有効'
				when kaisai = 1 then '無効'
			 end) as kaihai
  FROM m_kanjo
   WHERE 
 /*IF kaisai	!= 2*/
	kaisai = /*kaisai*/
/*END*/
/*IF kaisai	== 2*/
	kaisai in (0,1)
/*END*/
order by orderSEQ