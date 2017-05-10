package sibpayadvice.web.setpayadvice;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

public class DrvpayadvicePage {

	@SubapplicationScope
	private String userID;
	@SubapplicationScope
	private String userPas;
	@SubapplicationScope
	private String sendYear;
	@SubapplicationScope
	private String sendMonth;
	@SubapplicationScope
	private String empName;
	@SubapplicationScope
	private String empID;
	@SubapplicationScope
	private String sendMail;
	@SubapplicationScope
	private String wrokinghours;
	@SubapplicationScope
	private String attendancedday;
	@SubapplicationScope
	private String absentdays;
	@SubapplicationScope
	private String dependent;
	@SubapplicationScope
	private String useannoaldyas;
	@SubapplicationScope
	private String nonusedannualdays;
	@SubapplicationScope
	private String sumnnervacation;
	@SubapplicationScope
	private String base;
	@SubapplicationScope
	private String syaho;
	@SubapplicationScope
	private String teiki;
	@SubapplicationScope
	private String tuukinn;
	@SubapplicationScope
	private String nontax;
	@SubapplicationScope
	private String kenho;
	@SubapplicationScope
	private String kounenn;
	@SubapplicationScope
	private String koyoho;
	@SubapplicationScope
	private String syotax;
	@SubapplicationScope
	private String citytax;
	private String resultFlg;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPas() {
		return userPas;
	}

	public void setUserPas(String userPas) {
		this.userPas = userPas;
	}

	public String getSendYear() {
		return sendYear;
	}

	public void setSendYear(String sendYear) {
		this.sendYear = sendYear;
	}

	public String getSendMonth() {
		return sendMonth;
	}

	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getSendMail() {
		return sendMail;
	}

	public void setSendMail(String sendMail) {
		this.sendMail = sendMail;
	}

	public String getWrokinghours() {
		return wrokinghours;
	}

	public void setWrokinghours(String wrokinghours) {
		this.wrokinghours = wrokinghours;
	}

	public String getAttendancedday() {
		return attendancedday;
	}

	public void setAttendancedday(String attendancedday) {
		this.attendancedday = attendancedday;
	}

	public String getAbsentdays() {
		return absentdays;
	}

	public void setAbsentdays(String absentdays) {
		this.absentdays = absentdays;
	}

	public String getDependent() {
		return dependent;
	}

	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	public String getUseannoaldyas() {
		return useannoaldyas;
	}

	public void setUseannoaldyas(String useannoaldyas) {
		this.useannoaldyas = useannoaldyas;
	}

	public String getNonusedannualdays() {
		return nonusedannualdays;
	}

	public void setNonusedannualdays(String nonusedannualdays) {
		this.nonusedannualdays = nonusedannualdays;
	}

	public String getSumnnervacation() {
		return sumnnervacation;
	}

	public void setSumnnervacation(String sumnnervacation) {
		this.sumnnervacation = sumnnervacation;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getSyaho() {
		return syaho;
	}

	public void setSyaho(String syaho) {
		this.syaho = syaho;
	}

	public String getTeiki() {
		return teiki;
	}

	public void setTeiki(String teiki) {
		this.teiki = teiki;
	}

	public String getTuukinn() {
		return tuukinn;
	}

	public void setTuukinn(String tuukinn) {
		this.tuukinn = tuukinn;
	}

	public String getNontax() {
		return nontax;
	}

	public void setNontax(String nontax) {
		this.nontax = nontax;
	}

	public String getKenho() {
		return kenho;
	}

	public void setKenho(String kenho) {
		this.kenho = kenho;
	}

	public String getKounenn() {
		return kounenn;
	}

	public void setKounenn(String kounenn) {
		this.kounenn = kounenn;
	}

	public String getKoyoho() {
		return koyoho;
	}

	public void setKoyoho(String koyoho) {
		this.koyoho = koyoho;
	}

	public String getSyotax() {
		return syotax;
	}

	public void setSyotax(String syotax) {
		this.syotax = syotax;
	}

	public String getCitytax() {
		return citytax;
	}

	public void setCitytax(String citytax) {
		this.citytax = citytax;
	}

	public String getResultFlg() {
		return resultFlg;
	}

	public void setResultFlg(String resultFlg) {
		this.resultFlg = resultFlg;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		this.sendYear = "2012";
		this.sendMonth  = "10";
		this.userID = "takumigawa";
		this.userPas = "11";
		this.empID = "2";
		
		sendMail = "1";
		wrokinghours = "0.0";
		attendancedday = "1010";
		absentdays = "10";
		dependent = "10";
		useannoaldyas = "0";
		nonusedannualdays = "0";
		sumnnervacation = "0";
		base = "20";
		syaho = "0";
		teiki = "0";
		tuukinn = "0";
		nontax = "0";
		kenho = "0";
		kounenn = "0";
		koyoho = "0";
		syotax = "0";
		citytax = "0";

		
		return SetpayadvicePage.class ;
	}

}
