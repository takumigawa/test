package schedule.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="m_company")
public class MCompany {

	public Integer companyId;
	public String companyName;
	public String presidentName;
	public Integer classId;
	public String postCode;
	public String address1;
	public String address2;
	public String tel;
	public String fax;
	public String email;
	public String adddate;
	public Integer addid;
	public String upddate;
	public Integer updid;

}
