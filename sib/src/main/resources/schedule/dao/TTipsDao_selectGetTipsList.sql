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
where
    /*nowDate*/
        between 
            startDate 
        and
            endDate
order by 
	priority DESC;