package ste.web.te03;

import ste.dao.TTransexpDao;

public class Te0302Page {

	public TTransexpDao trsDao;
	
	private String empID;
	private String sendYear;
	private String sendMonth;
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
			
			trsDao.deleteById(Integer.valueOf(empID), Integer.valueOf(sendYear), Integer.valueOf(sendMonth));
			
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
