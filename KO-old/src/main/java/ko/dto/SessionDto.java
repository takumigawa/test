package ko.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class SessionDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Integer EmployeeID;
	public String EmployeeName;
	public String EmployeeEmail;
	public String CompanyName;
	public String CompanyPostCode;
	public String CompanyAddress1;
	public String CompanyAddress2;
	public String CompanyTel;
	public String CompanyFax;
	
	public boolean MastermainteFlg;
	public boolean KogutiKanriFlg;
	
}
