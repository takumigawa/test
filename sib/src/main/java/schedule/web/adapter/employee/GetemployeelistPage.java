package schedule.web.adapter.employee;

import schedule.dao.MEmployeeDao;
import schedule.dto.EmpListDto;

public class GetemployeelistPage {

	private int empIndex;
	private EmpListDto[] empItems;
	private String empID;
	private String empName;
	private String empemail;
	
	/** 社員マスタDao */
	public MEmployeeDao empDao;

	public int getEmpIndex() {
		return empIndex;
	}

	public void setEmpIndex(int empIndex) {
		this.empIndex = empIndex;
	}

	public EmpListDto[] getEmpItems() {
		return empItems;
	}

	public void setEmpItems(EmpListDto[] empItems) {
		this.empItems = empItems;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpemail() {
		return empemail;
	}

	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		empItems = empDao.selectGetEmpList(null, 0);
		
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
	
}
