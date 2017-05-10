select
	tips_ID			as tipsID,
	startDate,
	endDate,
    note,
    inputemployeeID	as empID,
    file1_ID		as file1ID,
    file2_ID		as file2ID,
    file3_ID		as file3ID,
    file4_ID		as file4ID,
    file5_ID		as file5ID,
    priority
from
    t_tips
/*BEGIN*/
where
    /*IF sDate != null*/
    startDate >= /*sDate*/
    /*END*/
    /*IF sDate != null && (eDate != null || empID != null)*/
    and
    /*END*/
    /*IF eDate != null*/
    endDate <= /*eDate*/
    /*END*/
    /*IF eDate != null && empID != null*/
    and
    /*END*/
    /*IF empID != null*/
    inputemployeeID = /*empID*/
    /*END*/
/*END*/
order by 
	tips_ID;