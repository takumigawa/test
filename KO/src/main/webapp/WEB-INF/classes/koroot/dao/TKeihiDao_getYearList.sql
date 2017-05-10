SELECT substring(seikyu_Date,1,4) AS value
      ,concat(substring(seikyu_Date,1,4),'”N') AS label
  FROM t_keihi
 WHERE seikyu_Date <= /*zenYear*/
 GROUP BY value,label
UNION
SELECT /*nowYear*/ AS value
      ,concat(/*nowYear*/,'”N') AS label
 FROM dual 