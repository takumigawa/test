select T1.empyear
      ,T1.empmonth
      ,T1.tightenflg
      ,T1.approvalflg
      ,ifnull(T2.totalMo,0) as totalMo
  from t_tighten T1 left join
      (select sum(Money) as totalMo
              ,empid
              ,empyear
              ,empmonth
          from t_transexp 
          group by empid,empyear,empmonth) T2
    on T1.empid = T2.empid
   and T1.empyear = T2.empyear
   and T1.empmonth = T2.empmonth
 where T1.empid = /*empid*/
/*IF kaihai	!= 2*/
   and T1.tightenflg = /*kaihai*/
/*END*/
 order by T1.empyear DESC, T1.empmonth DESC
   
