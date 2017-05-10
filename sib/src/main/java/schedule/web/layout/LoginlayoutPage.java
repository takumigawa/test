package schedule.web.layout;

import org.seasar.framework.container.annotation.tiger.Binding;

import schedule.dao.MCompanyDao;
import schedule.dto.SessionDto;
import schedule.entity.MCompany;
import schedule.web.common.CommonConstants;

/**
 * <p>レイアウトPageクラス</p>
 * @author T.fujimoto
 *
 */
public class LoginlayoutPage {

	public String CompanyNameStr;
	public String CompanyPostCodeStr;
	public String CompanyAddress1Str;
	public String CompanyAddress2Str;
	public String CompanyTelStr;
	public String CompanyFaxStr;

	@Binding
	public SessionDto sessionDto;
	
	public MCompanyDao comDao;
	
	public Class<?> initialize() {
		
		return null;
	}

	public Class<?> prerender() {

		//セッション情報に会社情報がない場合、取得して設定
		if(sessionDto.CompanyName == null){
			
			MCompany ety_com[] = comDao.selectAll();
			
			sessionDto.CompanyName 		= ety_com[0].companyName;
			sessionDto.CompanyPostCode 	= ety_com[0].postCode;
			sessionDto.CompanyAddress1 	= ety_com[0].address1;
			sessionDto.CompanyAddress2 	= ety_com[0].address2;
			sessionDto.CompanyTel		= ety_com[0].tel;
			sessionDto.CompanyFax		= ety_com[0].fax;
			
		}
		
		//会社名
		CompanyNameStr 		= sessionDto.CompanyName;
		
		//郵便番号
		if(sessionDto.CompanyPostCode != null){
			CompanyPostCodeStr	= CommonConstants.Moji_PostMark + sessionDto.CompanyPostCode;
		}else {
			CompanyPostCodeStr = CommonConstants.Moji_Empty;
		}
		
		//住所
		CompanyAddress1Str	= sessionDto.CompanyAddress1;
		//建物
		CompanyAddress2Str	= sessionDto.CompanyAddress2;
		
		//電話番号
		if(sessionDto.CompanyTel != null){
			CompanyTelStr	= CommonConstants.Moji_TEL + ":" + sessionDto.CompanyTel;
		}else{
			CompanyTelStr	= CommonConstants.Moji_Empty;
		}
		
		//FAX番号
		if(sessionDto.CompanyFax != null){
			CompanyFaxStr	= CommonConstants.Moji_FAX + ":" + sessionDto.CompanyFax;
		}else{
			CompanyFaxStr	= CommonConstants.Moji_Empty;
		}
		
		return null;
	}

}
