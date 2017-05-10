SELECT substring(seikyu_Date,1,4) AS value
      ,concat(substring(seikyu_Date,1,4),'年') AS label
  FROM t_provisional_head
 WHERE seikyu_Date <= /*zenYear*/
 GROUP BY value,label
UNION
SELECT /*nowYear*/ AS value
      ,concat(/*nowYear*/,'年') AS label
 FROM dual 