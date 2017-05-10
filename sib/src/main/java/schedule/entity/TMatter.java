package schedule.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_matter")
public class TMatter {

	public Integer matterId;
	public Integer employeeId;
	public Integer customerId;
	public Integer commericalId;
	public String matterAddress1;
	public String matterAddress2;
	public String matterTitel;
	public String matterNote;
	public String matterStart;
	public String matterEnd;
	public Integer kaihai;
	public String adddate;
	public Integer addid;
	public String upddate;
	public Integer updid;
	
}
