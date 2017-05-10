package sibpayadvice.web.setpayadvice;

import java.math.BigDecimal;

import sibpayadvice.common.CommonUtil;
import sibpayadvice.dao.TEmpDao;
import sibpayadvice.dao.TPayadviceDao;
import sibpayadvice.entity.TEmp;
import sibpayadvice.entity.TPayadvice;

public class SetpayadvicePage {

	private String userID;
	private String userPas;
	private String sendYear;
	private String sendMonth;
	private String empName;
	private String empID;
	private String sendMail;
	private String wrokinghours;
	private String attendancedday;
	private String absentdays;
	private String dependent;
	private String useannoaldyas;
	private String nonusedannualdays;
	private String sumnnervacation;
	private String base;
	private String syaho;
	private String teiki;
	private String tuukinn;
	private String nontax;
	private String kenho;
	private String kounenn;
	private String koyoho;
	private String syotax;
	private String citytax;
	
	private String resultFlg;
	
	public TEmpDao empDao;
	public TPayadviceDao payDao;

	/**　ユーザーID　セッター・ゲッター　*/
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**　ユーザーパス　セッター・ゲッター　*/
	public String getUserPas() {
		return userPas;
	}
	public void setUserPas(String userPas) {
		this.userPas = userPas;
	}

	/**　送信年度　セッター・ゲッター　*/
	public String getSendYear() {
		return sendYear;
	}
	public void setSendYear(String sendYear) {
		this.sendYear = sendYear;
	}

	/**　送信月度　セッター・ゲッター　*/
	public String getSendMonth() {
		return sendMonth;
	}
	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}

	/**　社員名　セッター・ゲッター　*/
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**　社員ID　セッター・ゲッター　*/
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}

	/**　送信メールアドレス　セッター・ゲッター　*/
	public String getSendMail() {
		return sendMail;
	}
	public void setSendMail(String sendMail) {
		this.sendMail = sendMail;
	}

	/**　労働日数　セッター・ゲッター　*/
	public String getWrokinghours() {
		return wrokinghours;
	}
	public void setWrokinghours(String wrokinghours) {
		this.wrokinghours = wrokinghours;
	}

	/**　出勤日数　セッター・ゲッター　*/
	public String getAttendancedday() {
		return attendancedday;
	}
	public void setAttendancedday(String attendancedday) {
		this.attendancedday = attendancedday;
	}

	/**　欠勤日数　セッター・ゲッター　*/
	public String getAbsentdays() {
		return absentdays;
	}
	public void setAbsentdays(String absentdays) {
		this.absentdays = absentdays;
	}

	/**　扶養　セッター・ゲッター　*/
	public String getDependent() {
		return dependent;
	}
	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	/**　年次休暇　使用日数　セッター・ゲッター　*/
	public String getUseannoaldyas() {
		return useannoaldyas;
	}
	public void setUseannoaldyas(String useannoaldyas) {
		this.useannoaldyas = useannoaldyas;
	}

	/**　年次休暇　残日数　セッター・ゲッター　*/
	public String getNonusedannualdays() {
		return nonusedannualdays;
	}
	public void setNonusedannualdays(String nonusedannualdays) {
		this.nonusedannualdays = nonusedannualdays;
	}

	/**　特別休暇　慶弔・夏季　セッター・ゲッター　*/
	public String getSumnnervacation() {
		return sumnnervacation;
	}
	public void setSumnnervacation(String sumnnervacation) {
		this.sumnnervacation = sumnnervacation;
	}

	/**　基本給　セッター・ゲッター　*/
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}

	/**　社保手当　セッター・ゲッター　*/
	public String getSyaho() {
		return syaho;
	}
	public void setSyaho(String syaho) {
		this.syaho = syaho;
	}

	/**　定期代過不足清算　セッター・ゲッター　*/
	public String getTeiki() {
		return teiki;
	}
	public void setTeiki(String teiki) {
		this.teiki = teiki;
	}

	/**　通勤手当　セッター・ゲッター　*/
	public String getTuukinn() {
		return tuukinn;
	}
	public void setTuukinn(String tuukinn) {
		this.tuukinn = tuukinn;
	}

	/**　非課税額　セッター・ゲッター　*/
	public String getNontax() {
		return nontax;
	}
	public void setNontax(String nontax) {
		this.nontax = nontax;
	}

	/**　健康保険　セッター・ゲッター　*/
	public String getKenho() {
		return kenho;
	}
	public void setKenho(String kenho) {
		this.kenho = kenho;
	}

	/**　厚生根金　セッター・ゲッター　*/
	public String getKounenn() {
		return kounenn;
	}
	public void setKounenn(String kounenn) {
		this.kounenn = kounenn;
	}

	/**　雇用保険　セッター・ゲッター　*/
	public String getKoyoho() {
		return koyoho;
	}
	public void setKoyoho(String koyoho) {
		this.koyoho = koyoho;
	}

	/**　所得税　セッター・ゲッター　*/
	public String getSyotax() {
		return syotax;
	}
	public void setSyotax(String syotax) {
		this.syotax = syotax;
	}
	
	/** 市町村民税　セッター・ゲッター　*/
	public String getCitytax() {
		return citytax;
	}
	public void setCitytax(String citytax) {
		this.citytax = citytax;
	}
	
	/**　結果フラグ　セッター・ゲッター　*/
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
				
		try {

			//
			Integer intSendYear = Integer.parseInt(sendYear);
			Integer intSendMonth = Integer.parseInt(sendMonth);
			Integer intEmpID = Integer.parseInt(empID);
			
			TEmp ety_emp = empDao.selectById(intEmpID, intSendYear, intSendMonth);
			
			if(ety_emp == null) {
				
				ety_emp = new TEmp();

				//送信年度
				ety_emp.setEmpyear(intSendYear);
				//送信月度
				ety_emp.setEmpmonth(intSendMonth);
				//社員ID
				ety_emp.setEmpid(intEmpID);
				//社員名
				ety_emp.setEmpname(empName);
				//メールアドレス
				ety_emp.setSendmail(sendMail);
				//労働時間
				ety_emp.setWorkinghours((new BigDecimal(wrokinghours)).doubleValue());
				//出勤日数
				ety_emp.setAttendancedays(Integer.parseInt(attendancedday));
				//欠勤日数
				ety_emp.setAbsentdays(Integer.parseInt(absentdays));
				//年次休暇　使用日数
				ety_emp.setUseannualdays(Integer.parseInt(useannoaldyas));
				//年次休暇　残日数
				ety_emp.setNonuseannualdays(Integer.parseInt(nonusedannualdays));
				//扶養
				ety_emp.setDependent(Integer.parseInt(dependent));
				//特別休暇　慶弔・夏休
				ety_emp.setSummervacation(Integer.parseInt(sumnnervacation));
				//追加年月日
				ety_emp.setAdddate(CommonUtil.getNowUpdDate());
				//追加ユーザーID
				ety_emp.setAddid(intEmpID);
				
				empDao.insert(ety_emp);
				
			} else {
			
				//送信年度
				ety_emp.setEmpyear(intSendYear);
				//送信月度
				ety_emp.setEmpmonth(intSendMonth);
				//社員ID
				ety_emp.setEmpid(intEmpID);
				//社員名
				ety_emp.setEmpname(empName);
				//メールアドレス
				ety_emp.setSendmail(sendMail);
				//労働時間
				ety_emp.setWorkinghours((new BigDecimal(wrokinghours)).doubleValue());
				//出勤日数
				ety_emp.setAttendancedays(Integer.parseInt(attendancedday));
				//欠勤日数
				ety_emp.setAbsentdays(Integer.parseInt(absentdays));
				//年次休暇　使用日数
				ety_emp.setUseannualdays(Integer.parseInt(useannoaldyas));
				//年次休暇　残日数
				ety_emp.setNonuseannualdays(Integer.parseInt(nonusedannualdays));
				//扶養
				ety_emp.setDependent(Integer.parseInt(dependent));
				//特別休暇　慶弔・夏休
				ety_emp.setSummervacation(Integer.parseInt(sumnnervacation));
				//更新年月日
				ety_emp.setUpddate(CommonUtil.getNowUpdDate());
				//更新ユーザーID
				ety_emp.setUpdid(intEmpID);
				
				empDao.update(ety_emp);
				
			}
					

			TPayadvice ety_Pay = payDao.selectById(intEmpID, intSendYear, intSendMonth);
			
			if (ety_Pay == null) {
				
				ety_Pay = new TPayadvice();
				
				//送信年度
				ety_Pay.setEmpyear(Integer.parseInt(sendYear));
				//送信月度
				ety_Pay.setEmpmonth(Integer.parseInt(sendMonth));
				//社員ID
				ety_Pay.setEmpid(Integer.parseInt(empID));
				//基本給
				ety_Pay.setBase(Integer.parseInt(base));
				//社保手当
				ety_Pay.setSyaho(Integer.parseInt(syaho));
				//定期代過不足清算
				ety_Pay.setTeiki(Integer.parseInt(teiki));
				//通勤手当
				ety_Pay.setTuukinn(Integer.parseInt(tuukinn));
				//非課税
				ety_Pay.setNontax(Integer.parseInt(nontax));
				//健康保険
				ety_Pay.setKenho(Integer.parseInt(kenho));
				//厚生年金
				ety_Pay.setKounenn(Integer.parseInt(kounenn));
				//雇用保険
				ety_Pay.setKoyoho(Integer.parseInt(koyoho));
				//所得税
				ety_Pay.setSyotax(Integer.parseInt(syotax));
				//市町村民税
				ety_Pay.setCitytax(Integer.parseInt(citytax));
				//追加年月日
				ety_Pay.setAdddate(CommonUtil.getNowUpdDate());
				//追加ユーザーID
				ety_Pay.setAddid(intEmpID);
				
				payDao.insert(ety_Pay);
				
			} else {
				
				//送信年度
				ety_Pay.setEmpyear(Integer.parseInt(sendYear));
				//送信月度
				ety_Pay.setEmpmonth(Integer.parseInt(sendMonth));
				//社員ID
				ety_Pay.setEmpid(Integer.parseInt(empID));
				//基本給
				ety_Pay.setBase(Integer.parseInt(base));
				//社保手当
				ety_Pay.setSyaho(Integer.parseInt(syaho));
				//定期代過不足清算
				ety_Pay.setTeiki(Integer.parseInt(teiki));
				//通勤手当
				ety_Pay.setTuukinn(Integer.parseInt(tuukinn));
				//非課税
				ety_Pay.setNontax(Integer.parseInt(nontax));
				//健康保険
				ety_Pay.setKenho(Integer.parseInt(kenho));
				//厚生年金
				ety_Pay.setKounenn(Integer.parseInt(kounenn));
				//雇用保険
				ety_Pay.setKoyoho(Integer.parseInt(koyoho));
				//所得税
				ety_Pay.setSyotax(Integer.parseInt(syotax));
				//市町村民税
				ety_Pay.setCitytax(Integer.parseInt(citytax));
				//追加年月日
				ety_Pay.setUpddate(CommonUtil.getNowUpdDate());
				//追加ユーザーID
				ety_Pay.setUpdid(intEmpID);
				
				payDao.update(ety_Pay);
				
			}
		
			resultFlg = "True";
			
		} catch (Exception ex) {
			resultFlg = "false";
		}
		
		return null;
	}

}
