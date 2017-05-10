package schedule.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="m_customer")
public class MCustomer {

	public Integer customerId;
	public String name;
	public String nameKana;
	public String postcode;
	public String address1;
	public String address2;
	public String tel;
	public String fax;
	public String email;
	public String postcode2;
	public String address12;
	public String address22;
	public String tel2;
	public String fax2;
	public String email2;
	public Integer orderseq;
	public Integer kaihai;
	public String adddate;
	public Integer addid;
	public String upddate;
	public Integer updid;

}
