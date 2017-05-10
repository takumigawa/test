select 
       me.Employee_Name  as label,
       me.Employee_ID    as value
  from m_employee me
 where me.kaihai = 0
   and not exists ( select * from m_group mg, t_groupattach tga
                          where mg.group_id = tga.group_id
                              and mg.kaihai =0
                              and tga.employee_id = me.employee_id
                              and mg.group_id = /*GroupID*/)
order by me.orderSEQ