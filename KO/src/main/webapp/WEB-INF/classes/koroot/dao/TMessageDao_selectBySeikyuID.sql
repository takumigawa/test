SELECT AddDate as hatudate
      ,input_empID as hatumono
      ,message as yarimessege
  FROM t_message
 WHERE seikyu_ID = /*SeikyuID*/
 ORDER BY AddDate