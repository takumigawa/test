SELECT
    T2.id as sendID
   ,CASE T1.sendtype
         WHEN '0' THEN '一度のみ'
         WHEN '1' THEN '毎日'
         WHEN '2' THEN CONCAT('曜日指定　'
                ,CASE WHEN weeksunday='1' THEN '日　' ELSE '' END
                ,CASE WHEN weekmonday='1' THEN '月　' ELSE '' END
                ,CASE WHEN weektuesday='1' THEN '火　' ELSE '' END
                ,CASE WHEN weekwednesday='1' THEN '水　' ELSE '' END
                ,CASE WHEN weekthursday='1' THEN '木　' ELSE '' END
                ,CASE WHEN weekfriday='1' THEN '金　' ELSE '' END
                ,CASE WHEN weeksaturday='1' THEN '土 ' ELSE '' END
              )
         WHEN '3' THEN CONCAT('毎月(',T1.day,'日)')
    END AS sendType
   ,CASE T1.sendtype
         WHEN '0' THEN CONCAT(T1.year,'/',T1.month,'/',T1.day,' ',T1.hour,':',T1.minute)
         ELSE CONCAT(T1.hour,':',T1.minute)
    END AS sendTime
   ,T2.mailtitel AS sendTitel
   ,T2.frommailaddress AS sendMailaddress
   ,CASE T1.updtype WHEN 0 THEN '有効' WHEN 1 THEN '送信済み' WHEN '9' THEN '廃止' END AS sendFlg
  FROM t_sendtime T1
      ,t_sendmail T2
 WHERE T1.sendmailid = T2.id
 	/*IF updType != 99*/
   AND T1.updtype =  /*updType*/
   /*END*/
  ORDER BY T2.id
