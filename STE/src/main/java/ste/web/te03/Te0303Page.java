package ste.web.te03;

import ste.common.CommonUtil;
import ste.dao.TTransexpDao;
import ste.entity.TTransexp;

public class Te0303Page {

	public TTransexpDao trsDao;
	
	private String empID;
	private String sendYear;
	private String sendMonth;
	private String addDay;
	private String sectionFrom;
	private String sectionTo;
	private String summary;
	private String money;
	private String resultFlg;

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
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

	public String getAddDay() {
		return addDay;
	}

	public void setAddDay(String addDay) {
		this.addDay = addDay;
	}

	public String getSectionFrom() {
		return sectionFrom;
	}

	public void setSectionFrom(String sectionFrom) {
		this.sectionFrom = sectionFrom;
	}

	public String getSectionTo() {
		return sectionTo;
	}

	public void setSectionTo(String sectionTo) {
		this.sectionTo = sectionTo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
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
		
		try {
			
			TTransexp etyExp = new TTransexp();
			
			//社員ID
			etyExp.setEmpid(Integer.valueOf(empID));
			//送信年
			etyExp.setEmpyear(Integer.valueOf(sendYear));
			//送信月
			etyExp.setEmpmonth(Integer.valueOf(sendMonth));
			//枝番
			etyExp.setBranchno(trsDao.selectByIdNextBrounch(Integer.valueOf(empID), Integer.valueOf(sendYear), Integer.valueOf(sendMonth)));
			//日付
			etyExp.setApplicationdate(addDay);
			//摘要
			etyExp.setSummary(summary);
			//区間開始
			etyExp.setSectionfrom(sectionFrom);
			//区間終了
			etyExp.setSectionto(sectionTo);
			//金額
			etyExp.setMoney(Integer.valueOf(money));
			//追加日付
			etyExp.setAdddate(CommonUtil.getNowUpdDate());
			//追加ＩＤ
			etyExp.setAddid(Integer.valueOf(empID));
			
			trsDao.insert(etyExp);
			
			resultFlg = "true";
			
		} catch (Exception e) {
			resultFlg = "false";
		}
		
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
}
