package ssm.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_sendtime")
public class TSendtime {

	private Integer id;
	private String sendtype;
	private Integer sendmailid;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private Integer updtype;
	private String weeksunday;
	private String weekmonday;
	private String weektuesday;
	private String weekwednesday;
	private String weekthursday;
	private String weekfriday;
	private String weeksaturday;
	private Integer addid;
	private String adddate;
	private Integer updid;
	private String upddate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSendtype() {
		return sendtype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}

	public Integer getSendmailid() {
		return sendmailid;
	}

	public void setSendmailid(Integer sendmailid) {
		this.sendmailid = sendmailid;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public Integer getUpdtype() {
		return updtype;
	}

	public void setUpdtype(Integer updtype) {
		this.updtype = updtype;
	}

	public String getWeeksunday() {
		return weeksunday;
	}

	public void setWeeksunday(String weeksunday) {
		this.weeksunday = weeksunday;
	}

	public String getWeekmonday() {
		return weekmonday;
	}

	public void setWeekmonday(String weekmonday) {
		this.weekmonday = weekmonday;
	}

	public String getWeektuesday() {
		return weektuesday;
	}

	public void setWeektuesday(String weektuesday) {
		this.weektuesday = weektuesday;
	}

	public String getWeekwednesday() {
		return weekwednesday;
	}

	public void setWeekwednesday(String weekwednesday) {
		this.weekwednesday = weekwednesday;
	}

	public String getWeekthursday() {
		return weekthursday;
	}

	public void setWeekthursday(String weekthursday) {
		this.weekthursday = weekthursday;
	}

	public String getWeekfriday() {
		return weekfriday;
	}

	public void setWeekfriday(String weekfriday) {
		this.weekfriday = weekfriday;
	}

	public String getWeeksaturday() {
		return weeksaturday;
	}

	public void setWeeksaturday(String weeksaturday) {
		this.weeksaturday = weeksaturday;
	}

	public Integer getAddid() {
		return addid;
	}

	public void setAddid(Integer addid) {
		this.addid = addid;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	public Integer getUpdid() {
		return updid;
	}

	public void setUpdid(Integer updid) {
		this.updid = updid;
	}

	public String getUpddate() {
		return upddate;
	}

	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}

}
