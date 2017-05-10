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
	
	/** ID �Z�b�^�[�E�Q�b�^�[ */
	public void setID(Integer iID){
		this.ID = iID;
	}
	public Integer getID(){
		return this.ID;
	}
	
	/** ���M�^�C�v�@�Z�b�^�[�E�Q�b�^�[ */
	public void setSendType(String iSendType){
		this.SendType = iSendType;
	}
	public String getSendType(){
		return this.SendType;
	}
	
	/** ���M���[��ID�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setSendMailID(Integer iSendMailID){
		this.SendMailID = iSendMailID;
	}
	public Integer getSendMailID(){
		return this.SendMailID;
	}
	
	/** ���M�N�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setYear(String iYear){
		this.Year = iYear;
	}
	public String getYear(){
		return this.Year;
	}
	
	/** ���M���@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setMonth(String iMonth){
		this.Month = iMonth;
	}
	public String getMonth(){
		return this.Month;
	}
	
	/** ���M���@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setDay(String iDay){
		this.Day = iDay;
	}
	public String getDay(){
		return this.Day;
	}

	/** ���M���ԁ@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setHour(String iHour){
		this.Hour = iHour;
	}
	public String getHour(){
		return this.Hour;
	}
	
	/** ���M���@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setMinute(String iMinute){
		this.Minute = iMinute;
	}
	public String getMinute(){
		return this.Minute;
	}
	
	/** �X�V�敪 �Z�b�^�[�E�Q�b�^�[�@*/
	public void setUpdType(Integer iUpdType) {
		this.UpdType = iUpdType;
	}
	public Integer getUpdType(){
		return this.UpdType;
	}
	
	/** ���j���M�敪�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setWeekSunDay(String iSunDay) {
		this.WeekSunDay = iSunDay;
	}
	public String getWeekSunDay(){
		return this.WeekSunDay;
	}
	
	/** ���j���M�敪�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setWeekMonDay(String iMonDay) {
		this.WeekMonDay = iMonDay;
	}
	public String getWeekMonDay() {
		return this.WeekMonDay;
	}
		
	/** �Ηj���M�敪�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setWeekTuesDay(String iTuesDay) {
		this.WeekTuesDay = iTuesDay;
	}
	public String getWeekTuesDay() {
		return this.WeekTuesDay;
	}
	
	/** ���j���M�敪�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setWeekWednesDay(String iWednesDay) {
		this.WeekWednesDay = iWednesDay;
	}
	public String getWeekWednesDay() {
		return this.WeekWednesDay;
	}
	
	/** �ؗj���M�敪�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setWeekThursDay(String iThursDay) {
		this.WeekThursDay = iThursDay;
	}
	public String getWeekThursDay() {
		return this.WeekThursDay;
	}
	
	/** ���j���M�敪�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setWeekFriDay(String iFriDay) {
		this.WeekFriDay = iFriDay;
	}
	public String getWeekFriDay(){
		return this.WeekFriDay;
	}
	
	/** �y�j���M�敪�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setWeekSaturDay(String iSaturDay) {
		this.WeekSaturDay = iSaturDay;
	}
	public String getWeekSaturDay() {
		return this.WeekSaturDay;
	}
	
	/** �V�K�X�V��ID�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setAddID(Integer iAddID){
		this.AddID = iAddID;
	}
	public Integer getAddID(){
		return this.AddID;
	}
	
	/** �V�K�X�V�����@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setAddDate(String iAddDate){
		this.AddDate = iAddDate;
	}
	public String getAddDate(){
		return this.AddDate;
	}
	
	/** �ŐV�X�VID�@�Z�b�^�[�E�Q�b�^�[ */
	public void setUpdID(Integer iUpdID){
		this.UpdID = iUpdID;
	}
	public Integer getUpdID(){
		return this.UpdID;
	}
	
	/** �ŐV�X�V�����@�Z�b�^�[�E�Q�b�^�[ */
	public void setUpdDate(String iUpdDate){
		this.UpdDate = iUpdDate;
	}
	public String getUpdDate(){
		return this.UpdDate;
	}
	
}
