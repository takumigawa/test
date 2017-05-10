package schedule.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="m_employee")
public class MEmployee {

	public Integer employeeId;
	public String employeeName;
	public String employeeNamekana;
	public String loginid;
	public String loginpassword;
	public Integer employeePositionId;
	public Integer defaultGroupId;
	public String employeeEmail;
	public String employeeEmail2;
	public String employeeEmail3;
	public String employeePostcode;
	public String employeeAddress1;
	public String employeeAddress2;
	public String employeeTel;
	public String employeeMphone;
	public String employeeFax;
	public Integer orderseq;
	public Integer kaihai;
	public String adddate;
	public Integer addid;
	public String upddate;
	public Integer updid;

}
