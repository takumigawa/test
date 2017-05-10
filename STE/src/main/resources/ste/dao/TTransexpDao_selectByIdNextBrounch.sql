select ifnull(MAX(branchno),0) + 1
  from t_transexp
  where empid = /*empid*/
    and empyear = /*empyear*/
    and empmonth = /*empmonth*/
