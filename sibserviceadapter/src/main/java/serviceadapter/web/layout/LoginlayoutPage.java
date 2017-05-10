package serviceadapter.web.layout;

import java.util.HashMap;
import java.util.Map;

import serviceadapter.bace.PageBaceClass;
import serviceadapter.common.CommonConstants;
import serviceadapter.dao.MAdapterDao;
import serviceadapter.entity.MAdapter;
import serviceadapter.service.SibServiceAdapter;

public class LoginlayoutPage extends PageBaceClass {

	public MAdapterDao adpDao;
	
	private String companyNameStr;
	private String companyPostCodeStr;
	private String companyAddress1Str;
	private String companyAddress2Str;
	private String companyTelStr;
	private String companyFaxStr;

	private static String TYPE_COMPANY = "companyInfo";
	
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

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		
		//セッションの会社情報がnullだった場合、取得する。
		if (null == this.sessionDto.CompanyName) {
			MAdapter ety_Adp = adpDao.selectById(TYPE_COMPANY);
			
			Map<String, String> reqParam = new HashMap<String, String>();
			Map<String, String> resParam;
			
			SibServiceAdapter SSA = new SibServiceAdapter();
			
			SSA.setServiceProvider(ety_Adp.getServiceurl());
			
			try {
				
				resParam = SSA.postData(reqParam);
				
				sessionDto.CompanyName 		= resParam.get("companyNameStr");
				sessionDto.CompanyPostCode 	= resParam.get("companyPostCodeStr");
				sessionDto.CompanyAddress1 	= resParam.get("companyAddress1Str");
				sessionDto.CompanyAddress2 	= resParam.get("companyAddress2Str");
				sessionDto.CompanyTel		= resParam.get("companyTelStr");
				sessionDto.CompanyFax		= resParam.get("companyFaxStr");
				
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
		}
		
		//会社名
		companyNameStr 		= sessionDto.CompanyName;
		
		//郵便番号
		if(sessionDto.CompanyPostCode != null){
			companyPostCodeStr	= CommonConstants.Moji_PostMark + sessionDto.CompanyPostCode;
		}else {
			companyPostCodeStr = CommonConstants.Moji_Empty;
		}
		
		//住所
		companyAddress1Str	= sessionDto.CompanyAddress1;
		//建物
		companyAddress2Str	= sessionDto.CompanyAddress2;
		
		//電話番号
		if(sessionDto.CompanyTel != null){
			companyTelStr	= CommonConstants.Moji_TEL + ":" + sessionDto.CompanyTel;
		}else{
			companyTelStr	= CommonConstants.Moji_Empty;
		}
		
		//FAX番号
		if(sessionDto.CompanyFax != null){
			companyFaxStr	= CommonConstants.Moji_FAX + ":" + sessionDto.CompanyFax;
		}else{
			companyFaxStr	= CommonConstants.Moji_Empty;
		}
		
		return null;
	}

}
