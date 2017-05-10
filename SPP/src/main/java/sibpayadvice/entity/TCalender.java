package sibpayadvice.entity;

public class TCalender {

	public static final String TABLE = "t_calender";
	private Integer year;
	private Integer month;
	private Integer endday;
	private Integer jobdays;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getEndday() {
		return endday;
	}

	public void setEndday(Integer endday) {
		this.endday = endday;
	}

	public Integer getJobdays() {
		return jobdays;
	}

	public void setJobdays(Integer jobdays) {
		this.jobdays = jobdays;
	}

}
