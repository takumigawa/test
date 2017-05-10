package schedule.dto;

import java.io.Serializable;

public class TimeScheduleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String scheduleID;
	public String timeSchTitelStr;
	public String timeSchTitelStrTitle;
	public String startDate;
	public String startTimeStr;
	public String endDate;
	public String endTimeStr;
	public String priority;
	public int timeSchColspan;
}
