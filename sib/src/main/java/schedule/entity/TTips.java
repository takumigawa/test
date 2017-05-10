package schedule.entity;
import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_tips")
public class TTips {

	public Integer tipsId;
	public String startdate;
	public String enddate;
	public String note;
	public Integer inputemployeeId;
	public Integer file1Id;
	public Integer file2Id;
	public Integer file3Id;
	public Integer file4Id;
	public Integer file5Id;
	public Integer priority;
}
