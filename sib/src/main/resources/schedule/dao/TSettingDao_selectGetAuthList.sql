SELECT 
    stringValue AS label
   ,integerValue as value
 FROM t_setting
WHERE key1 = 'system'
  AND key2 = 'authority'
  AND integerValue > 0
Order by integerValue