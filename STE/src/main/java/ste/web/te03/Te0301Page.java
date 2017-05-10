package ste.web.te03;

import ste.common.CommonUtil;
import ste.dao.TTightenDao;
import ste.entity.TTighten;

public class Te0301Page {

	//申請状況Dao
	public TTightenDao tenDao;
	
	private String empID;
	private String sendYear;
	private String sendMonth;
	private String empName;
	private String section;
	private String expDate;
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
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
		
		
		TTighten etyTen =  tenDao.selectById(Integer.valueOf(empID), Integer.valueOf(sendYear), Integer.valueOf(sendMonth));
		
		try {
			if ( etyTen == null ) {
				
				etyTen = new TTighten();
				
				//社員ID
				etyTen.setEmpid(Integer.valueOf(empID));
				//送信年
				etyTen.setEmpyear(Integer.valueOf(sendYear));
				//送信月
				etyTen.setEmpmonth(Integer.valueOf(sendMonth));
				//社員名
				etyTen.setEmpname(empName);
				//所属
				etyTen.setSection(section);
				//請求日
				etyTen.setExpdate(expDate);
				//申請状況フラグ
				etyTen.setTightenflg(1);
				//確認状況
				etyTen.setApprovalflg(0);
				//追加日次
				etyTen.setAdddate(CommonUtil.getNowUpdDate());
				//追加ID
				etyTen.setAddid(Integer.valueOf(empID));
				
				tenDao.insert(etyTen);
				
			} else {
				
				//社員ID
				etyTen.setEmpid(Integer.valueOf(empID));
				//送信年
				etyTen.setEmpyear(Integer.valueOf(sendYear));
				//送信月
				etyTen.setEmpmonth(Integer.valueOf(sendMonth));
				//社員名
				etyTen.setEmpname(empName);
				//所属
				etyTen.setSection(section);
				//請求日
				etyTen.setExpdate(expDate);
				//申請状況フラグ
				etyTen.setTightenflg(1);
				//確認状況
				etyTen.setApprovalflg(0);
				//追加日次
				etyTen.setUpddate(CommonUtil.getNowUpdDate());
				//追加ID
				etyTen.setUpdid(Integer.valueOf(empID));
				
				tenDao.update(etyTen);
				
			}
			
			resultFlg = "True";
			
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
