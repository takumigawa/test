package serviceadapter.web.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;


import serviceadapter.bace.PageBaceClass;
import serviceadapter.common.CommonConstants;
import serviceadapter.common.CommonUtil;
import serviceadapter.dao.MAdapterDao;
import serviceadapter.dto.ListDto;
import serviceadapter.dto.ServiceListDto;
import serviceadapter.entity.MAdapter;
import serviceadapter.exception.AnLoginException;
import serviceadapter.exception.NoMastaMenteAuthException;
import serviceadapter.service.SibServiceAdapter;
import serviceadapter.web.error.NonauthorityPage;
import serviceadapter.web.error.NonloginPage;

public class ListPage extends PageBaceClass {
	
	public MAdapterDao adpDao;
	
	/** 有効/無効値 */
	@PageScope
	@Required
	public Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	public List<ListDto> kaihaiItems;
	
	private int serviceIndex;
	@PageScope
	private ServiceListDto[] serviceItems;	
	
	private String serviceTitle;
	private String serviceType;
	private String orderSEQ;
	private String serviceEnabled;
	
	public String getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}
	
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public String getOrderSEQ() {
		return orderSEQ;
	}

	public void setOrderSEQ(String orderSEQ) {
		this.orderSEQ = orderSEQ;
	}
	
	public String getServiceEnabled() {
		return serviceEnabled;
	}

	public void setServiceEnabled(String serviceEnabled) {
		this.serviceEnabled = serviceEnabled;
	}

	public Integer getKaihai() {
		return kaihai;
	}

	public void setKaihai(Integer kaihai) {
		this.kaihai = kaihai;
	}

	public List<ListDto> getKaihaiItems() {
		return kaihaiItems;
	}

	public void setKaihaiItems(List<ListDto> kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}

	public int getServiceIndex() {
		return serviceIndex;
	}

	public void setServiceIndex(int serviceIndex) {
		this.serviceIndex = serviceIndex;
	}

	public ServiceListDto[] getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(ServiceListDto[] serviceItems) {
		this.serviceItems = serviceItems;
	}
	
	/** 選択サービスＩＤ */
	@SubapplicationScope
	private String selectID;
	
	public String getSelectID() {
		return this.selectID;
	}
	public void setSelectID(String selectID) {
		this.selectID = selectID;
	}

	public Class<?> doDetails() {
		selectID = serviceItems[serviceIndex].serviceType;
		return InputPage.class;
	}

	public Class<?> doSrech() {
		
		serviceItems = adpDao.selectGetServiceList(kaihai);
		return null;
	}

	public Class<?> doSrechCancel() {
		
		kaihai		= CommonConstants.Kaihai_true;
		serviceItems = adpDao.selectGetServiceList(0);
		
		return null;
	}

	public Class<?> doEmployeeAdd() {
		
		selectID = "";
		
		return InputPage.class;
	}

	public Class<?> initialize() {
		
		System.out.println("ListPage initialize Start");
		
		try {
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			SibServiceAdapter SSA = new SibServiceAdapter();
			
			MAdapter ety_Adp = adpDao.selectById(CommonConstants.SERVICE_TYPE_COMBLIST);
			
			Map<String, String> reqParam = new HashMap<String, String>();
			Map<String, String> resParam;
			
			SSA.setServiceProvider(ety_Adp.getServiceurl());
			
			reqParam.put("combName", CommonConstants.k2_kaihai);
			
			resParam = SSA.postData(reqParam);
						
			kaihaiItems = CommonUtil.getServiceToCombData(resParam);			
			
			serviceItems = adpDao.selectGetServiceList(0);

		} catch (AnLoginException e) {
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			return NonauthorityPage.class;
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
		}
		
		System.out.println("ListPage initialize End");
		
		return null;
	}

	public Class<?> prerender() {
		
		this.setLoginInfo();
		
		return null;
	}

}
