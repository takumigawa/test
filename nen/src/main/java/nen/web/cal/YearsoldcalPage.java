package nen.web.cal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import nen.dto.ListDto;

public class YearsoldcalPage {

	@Required
	private String years;
	private List<ListDto> yearsItems;

	@Required
	private String month;
	private List<ListDto> monthItems;

	@Required
	private String days;
	private List<ListDto> daysItems;

	@Required
	private String hopeyears;
	private List<ListDto> hopeyearsItems;

	@PageScope
	public String UpdMes;

	//年
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}

	public List<ListDto> getYearsItems() {
		return yearsItems;
	}
	public void setYearsItems(List<ListDto> yearsItems) {
		this.yearsItems = yearsItems;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<ListDto> getMonthItems() {
		return monthItems;
	}

	public void setMonthItems(List<ListDto> monthItems) {
		this.monthItems = monthItems;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public List<ListDto> getDaysItems() {
		return daysItems;
	}

	public void setDaysItems(List<ListDto> daysItems) {
		this.daysItems = daysItems;
	}

	public String getHopeyears() {
		return hopeyears;
	}

	public void setHopeyears(String hopeyears) {
		this.hopeyears = hopeyears;
	}

	public List<ListDto> getHopeyearsItems() {
		return hopeyearsItems;
	}

	public void setHopeyearsItems(List<ListDto> hopeyearsItems) {
		this.hopeyearsItems = hopeyearsItems;
	}
/*
	//メッセージ
	public String getUpdMes() {
		return this.UpdMes;
	}
	public void setUpdMes(String UpdMes) {
		this.UpdMes = UpdMes;
	}
*/

	public Class<?> doCal() {

		Calendar sCal = Calendar.getInstance();

		Date nowDate = new Date();
		Date baseDate;

		//誕生日設定
		sCal.set(Integer.parseInt(this.years) , Integer.parseInt(this.month),Integer.parseInt(this.days) );

		sCal.add(Calendar.YEAR, Integer.parseInt(this.hopeyears));

		baseDate = sCal.getTime();

		this.UpdMes = "あなたの年齢は<br/>";
		this.UpdMes += this.hopeyears + "歳と<br/>";
		this.UpdMes += "　　<font color='red'>" + String.valueOf(differenceMonth(nowDate,baseDate)) + "ヵ月</font><br/>";
		this.UpdMes += "または、<br/>";
		this.UpdMes += "　　<font color='red'>" + String.valueOf(differenceDays(nowDate,baseDate)) + "日</font><br/>";
		this.UpdMes += "です。<br/><br/>";
		this.UpdMes += "悪あがきはやめましょう。<br/>";

		return null;
	}

	public Class<?> initialize() {

		Calendar cal = Calendar.getInstance();
		Date nowDate = new Date();

		cal.setTime(nowDate);

		//年コンボ作成
		int iyears;

		this.yearsItems = new ArrayList<ListDto>();

		for (iyears = 1950;iyears <= cal.get(Calendar.YEAR);iyears++ ) {

			ListDto tmpItem = new ListDto();

			tmpItem.label = String.valueOf(iyears);
			tmpItem.value = String.valueOf(iyears);

			this.yearsItems.add(tmpItem);

			years = String.valueOf(iyears);

		}

		//月コンボ作成
		int imonth;

		this.monthItems = new ArrayList<ListDto>();

		for (imonth = 1;imonth <= 12;imonth++ ) {

			ListDto tmpItem = new ListDto();

			tmpItem.label = String.valueOf(imonth);
			tmpItem.value = String.valueOf(imonth);

			this.monthItems.add(tmpItem);

			month = String.valueOf(cal.get(Calendar.MONTH));

		}


		//日コンボ作成
		int iDays;

		this.daysItems = new ArrayList<ListDto>();

		for (iDays = 1;iDays <= 31;iDays++ ) {

			ListDto tmpItem = new ListDto();

			tmpItem.label = String.valueOf(iDays);
			tmpItem.value = String.valueOf(iDays);

			this.daysItems.add(tmpItem);

			days = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));

		}

		//年コンボ作成
		int iHyears;

		this.hopeyearsItems = new ArrayList<ListDto>();

		for (iHyears = 15;iHyears <= 40;iHyears++ ) {

			ListDto tmpItem = new ListDto();

			tmpItem.label = String.valueOf(iHyears) + "歳";
			tmpItem.value = String.valueOf(iHyears);

			this.hopeyearsItems.add(tmpItem);

			hopeyears = String.valueOf(18);

		}

		return null;
	}

	public Class<?> prerender() {

		return null;
	}

	private int differenceDays(Date date1,Date date2) {
	    long datetime1 = date1.getTime();
	    long datetime2 = date2.getTime();
	    long one_date_time = 1000 * 60 * 60 * 24;
	    long diffDays = (datetime1 - datetime2) / one_date_time;
	    return (int)diffDays;
	}


	/**
	 * 2つの日付の月数の差を求めます。
	 * java.util.Date 型の日付 date1 - date2 が何ヵ月かを整数で返します。
	 * ※端数の日数は無視します。
	 *
	 * @param date1    日付1 java.util.Date
	 * @param date2    日付2 java.util.Date
	 * @return 2つの日付の月数の差
	 */
	private int differenceMonth(Date date1, Date date2) {
	    Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(date1);
	    cal1.set(Calendar.DATE, 1);
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setTime(date2);
	    cal2.set(Calendar.DATE, 1);
	    int count = 0;
	    if (cal1.before(cal2)) {
	        while (cal1.before(cal2)) {
	            cal1.add(Calendar.MONTH, 1);
	            count--;
	        }
	    } else {
	        count--;
	        while (!cal1.before(cal2)) {
	            cal1.add(Calendar.MONTH, -1);
	            count++;
	        }
	    }
	    return count;
	}

}
