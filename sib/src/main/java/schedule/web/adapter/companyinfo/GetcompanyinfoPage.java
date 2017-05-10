package schedule.web.adapter.companyinfo;

import schedule.dao.MCompanyDao;
import schedule.entity.MCompany;

public class GetcompanyinfoPage {

	public MCompanyDao comDao;
	
	private String companyNameStr;
	private String companyPostCodeStr;
	private String companyAddress1Str;
	private String companyAddress2Str;
	private String companyTelStr;
	private String companyFaxStr;

	public String getCompanyNameStr() {
		return companyNameStr;
	}

	public void setCompanyNameStr(String companyNameStr) {
		this.companyNameStr = companyNameStr;
	}

	public String getCompanyPostCodeStr() {
		return companyPostCodeStr;
	}

	public void setCompanyPostCodeStr(String companyPostCodeStr) {
		this.companyPostCodeStr = companyPostCodeStr;
	}

	public String getCompanyAddress1Str() {
		return companyAddress1Str;
	}

	public void setCompanyAddress1Str(String companyAddress1Str) {
		this.companyAddress1Str = companyAddress1Str;
	}

	public String getCompanyAddress2Str() {
		return companyAddress2Str;
	}

	public void setCompanyAddress2Str(String companyAddress2Str) {
		this.companyAddress2Str = companyAddress2Str;
	}

	public String getCompanyTelStr() {
		return companyTelStr;
	}

	public void setCompanyTelStr(String companyTelStr) {
		this.companyTelStr = companyTelStr;
	}

	public String getCompanyFaxStr() {
		return companyFaxStr;
	}

	public void setCompanyFaxStr(String companyFaxStr) {
		this.companyFaxStr = companyFaxStr;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> initialize() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> prerender() {
		
		
		MCompany ety_com = comDao.selectById(1);
		
		companyNameStr = ety_com.companyName;
		companyPostCodeStr = ety_com.postCode;
		companyAddress1Str = ety_com.address1;
		companyAddress2Str = ety_com.address2;
		companyTelStr = ety_com.tel;
		companyFaxStr = ety_com.fax;
		
		return null;
	}
	
	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
	
}
