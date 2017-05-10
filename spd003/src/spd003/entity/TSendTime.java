package spd003.entity;

public class TSendTime {

	private Integer ID;
	private String SendType;
	private Integer SendMailID;
	private String Year;
	private String Month;
	private String Day;
	private String Hour;
	private String Minute;
	private Integer UpdType;
	private String WeekSunDay;
	private String WeekMonDay;
	private String WeekTuesDay;
	private String WeekWednesDay;
	private String WeekThursDay;
	private String WeekFriDay;
	private String WeekSaturDay;
	private Integer AddID;
	private String AddDate;
	private Integer UpdID;
	private String UpdDate;
	
	/** ID セッター・ゲッター */
	public void setID(Integer iID){
		this.ID = iID;
	}
	public Integer getID(){
		return this.ID;
	}
	
	/** 送信タイプ　セッター・ゲッター */
	public void setSendType(String iSendType){
		this.SendType = iSendType;
	}
	public String getSendType(){
		return this.SendType;
	}
	
	/** 送信メールID　セッター・ゲッター　*/
	public void setSendMailID(Integer iSendMailID){
		this.SendMailID = iSendMailID;
	}
	public Integer getSendMailID(){
		return this.SendMailID;
	}
	
	/** 送信年　セッター・ゲッター　*/
	public void setYear(String iYear){
		this.Year = iYear;
	}
	public String getYear(){
		return this.Year;
	}
	
	/** 送信月　セッター・ゲッター　*/
	public void setMonth(String iMonth){
		this.Month = iMonth;
	}
	public String getMonth(){
		return this.Month;
	}
	
	/** 送信日　セッター・ゲッター　*/
	public void setDay(String iDay){
		this.Day = iDay;
	}
	public String getDay(){
		return this.Day;
	}

	/** 送信時間　セッター・ゲッター　*/
	public void setHour(String iHour){
		this.Hour = iHour;
	}
	public String getHour(){
		return this.Hour;
	}
	
	/** 送信分　セッター・ゲッター　*/
	public void setMinute(String iMinute){
		this.Minute = iMinute;
	}
	public String getMinute(){
		return this.Minute;
	}
	
	/** 更新区分 セッター・ゲッター　*/
	public void setUpdType(Integer iUpdType) {
		this.UpdType = iUpdType;
	}
	public Integer getUpdType(){
		return this.UpdType;
	}
	
	/** 日曜送信区分　セッター・ゲッター　*/
	public void setWeekSunDay(String iSunDay) {
		this.WeekSunDay = iSunDay;
	}
	public String getWeekSunDay(){
		return this.WeekSunDay;
	}
	
	/** 月曜送信区分　セッター・ゲッター　*/
	public void setWeekMonDay(String iMonDay) {
		this.WeekMonDay = iMonDay;
	}
	public String getWeekMonDay() {
		return this.WeekMonDay;
	}
		
	/** 火曜送信区分　セッター・ゲッター　*/
	public void setWeekTuesDay(String iTuesDay) {
		this.WeekTuesDay = iTuesDay;
	}
	public String getWeekTuesDay() {
		return this.WeekTuesDay;
	}
	
	/** 水曜送信区分　セッター・ゲッター　*/
	public void setWeekWednesDay(String iWednesDay) {
		this.WeekWednesDay = iWednesDay;
	}
	public String getWeekWednesDay() {
		return this.WeekWednesDay;
	}
	
	/** 木曜送信区分　セッター・ゲッター　*/
	public void setWeekThursDay(String iThursDay) {
		this.WeekThursDay = iThursDay;
	}
	public String getWeekThursDay() {
		return this.WeekThursDay;
	}
	
	/** 金曜送信区分　セッター・ゲッター　*/
	public void setWeekFriDay(String iFriDay) {
		this.WeekFriDay = iFriDay;
	}
	public String getWeekFriDay(){
		return this.WeekFriDay;
	}
	
	/** 土曜送信区分　セッター・ゲッター　*/
	public void setWeekSaturDay(String iSaturDay) {
		this.WeekSaturDay = iSaturDay;
	}
	public String getWeekSaturDay() {
		return this.WeekSaturDay;
	}
	
	/** 新規更新者ID　セッター・ゲッター　*/
	public void setAddID(Integer iAddID){
		this.AddID = iAddID;
	}
	public Integer getAddID(){
		return this.AddID;
	}
	
	/** 新規更新日時　セッター・ゲッター　*/
	public void setAddDate(String iAddDate){
		this.AddDate = iAddDate;
	}
	public String getAddDate(){
		return this.AddDate;
	}
	
	/** 最新更新ID　セッター・ゲッター */
	public void setUpdID(Integer iUpdID){
		this.UpdID = iUpdID;
	}
	public Integer getUpdID(){
		return this.UpdID;
	}
	
	/** 最新更新日時　セッター・ゲッター */
	public void setUpdDate(String iUpdDate){
		this.UpdDate = iUpdDate;
	}
	public String getUpdDate(){
		return this.UpdDate;
	}
	
}
