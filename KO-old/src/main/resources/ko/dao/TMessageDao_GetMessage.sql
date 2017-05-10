SELECT  AddDate     as transday_id
       ,input_empID as  sender_id
       ,message     as message_id
  FROM t_message l
WHERE
	kaihai =  /*kaihai*/
and
    seikyu_id = /*seikyu_id*/
