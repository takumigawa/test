package schedule.dto;

import java.io.Serializable;
import java.util.List;

public class DaySchListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String dateStr;
	public String scheduleDate;
	public String schMonth;
	public List<AllDaySchDto> allDayScheduleItems;
	public List<TimeScheduleDto> timeScheduleItems;
}
