package schedule.dto;

import java.io.Serializable;
import java.util.List;

public class AllEmpSchDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String employeeID;	
	public String employeeName;
	public List<String> matterTitelItems;
	public List<DaySchListDto> dayScheduleItems;
}
