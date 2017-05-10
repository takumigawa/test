package schedule.dto;

import java.io.Serializable;
import java.util.List;

public class AllTimeSchDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String timeStr;
	public List<TimeScheduleDto> timeScheduleItems;
}
