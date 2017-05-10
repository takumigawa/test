package serviceadapter.web.input;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;


import serviceadapter.bace.PageBaceClass;
import serviceadapter.common.CommonConstants;
import serviceadapter.common.CommonUtil;
import serviceadapter.dao.MAdapterDao;
import serviceadapter.dto.ListDto;
import serviceadapter.entity.MAdapter;
import serviceadapter.exception.AnLoginException;
import serviceadapter.exception.NoMastaMenteAuthException;
import serviceadapter.web.error.NonauthorityPage;
import serviceadapter.web.error.NonloginPage;

/**
 * 
 * @author taku_fujimoto
 *
 */
public class InputPage extends PageBaceClass {

	public MAdapterDao adpDao;
	
	@PageScope
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String serviceNameStr;
	
	@PageScope
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String serviceIDStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String serviceURLStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 6)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_SUJI, messageId = "err.NoMatchEISU")
	private String orderSEQStr;
	
	private String updateMes;

	/** 有効/無効値 */
	@PageScope
	@Required
	public Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	public List<ListDto> kaihaiItems;
	
	public String getServiceNameStr() {
		return serviceNameStr;
	}
	public void setServiceNameStr(String serviceNameStr) {
		this.serviceNameStr = serviceNameStr;
	}

	public String getServiceIDStr() {
		return serviceIDStr;
	}
	public void setServiceIDStr(String serviceIDStr) {
		this.serviceIDStr = serviceIDStr;
	}
	
	public String getServiceURLStr() {
		return serviceURLStr;
	}
	public void setServiceURLStr(String serviceURLStr) {
		this.serviceURLStr = serviceURLStr;
	}
	
	public String getOrderSEQStr() {
		return orderSEQStr;
	}
	public void setOrderSEQStr(String orderSEQStr) {
		this.orderSEQStr = orderSEQStr;
	}
	
	public String getUpdateMes() {
		return updateMes;
	}
	public void setUpdateMes(String updateMes) {
		this.updateMes = updateMes;
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
	
	/** 選択サービスＩＤ */
	@SubapplicationScope
	private String selectID;
	
	public String getSelectID() {
		return this.selectID;
	}
	public void setSelectID(String selectID) {
		this.selectID = selectID;
	}
	
	/**
	 * 
	 * @return
	 */
	public Class<?> initialize() {
		
		try {
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			Map<String, String> reqParam = new HashMap<String, String>();

			reqParam.put("comKey", "0,1");
			reqParam.put("comLabel", "有効,無効");
			
			kaihaiItems = CommonUtil.getServiceToCombData(reqParam);	
			
			if (this.isUpdate()) {
				//更新
				MAdapter ety_adp = adpDao.selectById(selectID);
				
				serviceNameStr = ety_adp.getServicetitle();
				serviceIDStr = ety_adp.getServicetype();
				serviceURLStr = ety_adp.getServiceurl();
				orderSEQStr = ety_adp.getOrderseq().toString();
				kaihai = ety_adp.getKaihai();
				
			} else {
				//新規
				orderSEQStr = String.valueOf(adpDao.getNextOrderSEQ());
			}

		} catch (AnLoginException e) {
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			return NonauthorityPage.class;
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
		}
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> prerender() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Class<?> doFinishUpdate() {
		
		
		if (this.isUpdate()) {
			//更新
			MAdapter ety_adp = adpDao.selectById(selectID);
			
			ety_adp.setServicetitle(serviceNameStr);

			ety_adp.setServiceurl(serviceURLStr);
			ety_adp.setOrderseq(Integer.parseInt(orderSEQStr));
			
			ety_adp.setKaihai(kaihai);

			ety_adp.setUpddate(CommonUtil.getNowUpdDate());
			ety_adp.setUpdid(this.sessionDto.EmployeeID);
			
			adpDao.update(ety_adp);
			
			updateMes = "更新処理が完了しました。";
			
		} else {
			//新規
			MAdapter ety_adp = new MAdapter();
			
			ety_adp.setServicetitle(serviceNameStr);
			ety_adp.setServicetype(serviceIDStr);
			ety_adp.setServiceurl(serviceURLStr);
			ety_adp.setOrderseq(Integer.parseInt(orderSEQStr));
			
			ety_adp.setKaihai(0);
			
			ety_adp.setAdddate(CommonUtil.getNowUpdDate());
			ety_adp.setAddid(this.sessionDto.EmployeeID);
			
			adpDao.insert(ety_adp);
			
			updateMes = "新規追加処理が完了しました。";
			
		}
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> doFinishReturn() {
		return ListPage.class;
	}
	
	/**
	 * <p>画面登録/新規切り替え判定メソッド</p>
	 * @return true 更新/false 新規
	 *
	 */
	public boolean isUpdate(){
		
		if("".equals(selectID)){
			return false;
		}else{
			return true;
		}
	}
	
}
