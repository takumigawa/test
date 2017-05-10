package koroot.web.koa002;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;
import koroot.common.CommonConstants;
import koroot.common.CommonUtil;
import koroot.dao.TMessageDao;
import koroot.dao.TProvisionalDetaleDao;
import koroot.dao.TProvisionalHeadDao;
import koroot.dto.KaribaraiDetailListDto;
import koroot.dto.ListDto;
import koroot.dto.yaritoriMessageDto;
import koroot.entity.TMessage;
import koroot.entity.TProvisionalHead;
import koroot.exception.AnLoginException;
import koroot.exception.NoMastaMenteAuthException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonauthorityPage;
import koroot.web.error.NonloginPage;

public class Koa002g3Page extends PageBaceClass {

	@PageScope
	private String pjcode;
	@PageScope
	private String pjname;
	@PageScope
	private String seikyudate;
	@PageScope
	private String sinseidate;
	@PageScope
	private String kanjo;
	
	@Required
	private Integer sutatus;
	@PageScope
	private List<ListDto> sutatusItems;
	
	@PageScope
	private String kin;

	private String updateMes;
	
	@PageScope
	private int keihiIndex;
	@PageScope
	private List<KaribaraiDetailListDto> keihiItems;
	
	private String lsiyoudate;
	private String lkanjoname;
	private String lkin;
	
	@PageScope
	private int yaritoriIndex;
	@PageScope
	private List<yaritoriMessageDto> yaritoriItems;
	
	private String hatudate;
	private String hatumono;
	private String yarimessege;
	private String update2Mes;

	@Required(target="doMesFinishUpdate", messageId= "err.NoInputData")
	private String yMessage;
	
	/**　メッセージ　セッター・ゲッター　*/
	public String getyMessage() {
		return yMessage;
	}
	public void setyMessage(String yMessage) {
		this.yMessage = yMessage;
	}
	
	//仮払いヘッダーDao
	public TProvisionalHeadDao prohDao;

	//仮払い詳細Dao
	public TProvisionalDetaleDao prodDao;
	
	//やりとりメッセ-ジDao
	public TMessageDao mesDao;

	/** 経費ＩＤ */
	@SubapplicationScope
	private Integer selectID;
	
	/**　経費ID セッター・ゲッター　*/
	public Integer getSelectID() {
		return this.selectID;
	}
	public void setSelectID(Integer selectID) {
		this.selectID = selectID;
	}
	
	/**　　*/
	public String getPjcode() {
		return pjcode;
	}
	public void setPjcode(String pjcode) {
		this.pjcode = pjcode;
	}

	/**　　*/
	public String getPjname() {
		return pjname;
	}
	public void setPjname(String pjname) {
		this.pjname = pjname;
	}

	/**　　*/
	public String getSeikyudate() {
		return seikyudate;
	}
	public void setSeikyudate(String seikyudate) {
		this.seikyudate = seikyudate;
	}

	/**　　*/
	public String getSinseidate() {
		return sinseidate;
	}
	public void setSinseidate(String sinseidate) {
		this.sinseidate = sinseidate;
	}

	/**　　*/
	public String getKanjo() {
		return kanjo;
	}
	public void setKanjo(String kanjo) {
		this.kanjo = kanjo;
	}

	/**　　*/
	public Integer getSutatus() {
		return sutatus;
	}
	public void setSutatus(Integer sutatus) {
		this.sutatus = sutatus;
	}

	/**　　*/
	public List<ListDto> getSutatusItems() {
		return sutatusItems;
	}
	public void setSutatusItems(List<ListDto> sutatusItems) {
		this.sutatusItems = sutatusItems;
	}

	/**　　*/
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}

	/**　　*/
	public String getUpdateMes() {
		return updateMes;
	}
	public void setUpdateMes(String updateMes) {
		this.updateMes = updateMes;
	}

	/**　　*/
	public int getKeihiIndex() {
		return keihiIndex;
	}
	public void setKeihiIndex(int keihiIndex) {
		this.keihiIndex = keihiIndex;
	}

	/**　　*/
	public List<KaribaraiDetailListDto> getKeihiItems() {
		return keihiItems;
	}
	public void setKeihiItems(List<KaribaraiDetailListDto> keihiItems) {
		this.keihiItems = keihiItems;
	}

	/**　　*/
	public String getLsiyoudate() {
		return lsiyoudate;
	}
	public void setLsiyoudate(String lsiyoudate) {
		this.lsiyoudate = lsiyoudate;
	}

	/**　　*/
	public String getLkanjoname() {
		return lkanjoname;
	}
	public void setLkanjoname(String lkanjoname) {
		this.lkanjoname = lkanjoname;
	}

	/**　　*/
	public String getLkin() {
		return lkin;
	}
	public void setLkin(String lkin) {
		this.lkin = lkin;
	}

	/**　　*/
	public int getYaritoriIndex() {
		return yaritoriIndex;
	}
	public void setYaritoriIndex(int yaritoriIndex) {
		this.yaritoriIndex = yaritoriIndex;
	}

	/**　　*/
	public List<yaritoriMessageDto> getYaritoriItems() {
		return yaritoriItems;
	}
	public void setYaritoriItems(List<yaritoriMessageDto> yaritoriItems) {
		this.yaritoriItems = yaritoriItems;
	}

	/**　　*/
	public String getHatudate() {
		return hatudate;
	}
	public void setHatudate(String hatudate) {
		this.hatudate = hatudate;
	}

	/**　　*/
	public String getHatumono() {
		return hatumono;
	}
	public void setHatumono(String hatumono) {
		this.hatumono = hatumono;
	}

	/**　　*/
	public String getYarimessege() {
		return yarimessege;
	}
	public void setYarimessege(String yarimessege) {
		this.yarimessege = yarimessege;
	}

	/**　　*/
	public String getUpdate2Mes() {
		return update2Mes;
	}
	public void setUpdate2Mes(String update2Mes) {
		this.update2Mes = update2Mes;
	}

	/**
	 * 
	 * */
	public Class<?> doFinishReturn() {

		return Koa002g1Page.class;
		
	}
	
	/**
	 * 
	 * */
	public Class<?> doMesFinishUpdate() {

		//やりとりメッセージ　エンティティー　
		TMessage ety_mes = new TMessage();
		
		ety_mes.setAdddate(CommonUtil.getNowUpdDate());
		
		ety_mes.setAddid(this.sessionDto.EmployeeID);
		
		ety_mes.setMessage(yMessage);
		
		ety_mes.setInputEmpid(this.sessionDto.EmployeeID);
		
		ety_mes.setKaihai(0);
				
		ety_mes.setSeikyuId(selectID);
		
		mesDao.insert(ety_mes);
		
		update2Mes = "更新しました。";
		yMessage = "";
		
		return Koa002g2Page.class;
		
	}

	/**
	 * 
	 * */
	public Class<?> doFinishUpdate() {
		
		//経費エンティティ　取得
		TProvisionalHead ety_proh = prohDao.selectById(selectID);

		//ステータス
		ety_proh.setPaymentFlag(sutatus);
		
		//支払日
		ety_proh.setPaymentFlagDate(CommonUtil.getNowUpdDate());
		//支払社員ID
		ety_proh.setPaymentFlagEmpid(this.sessionDto.EmployeeID);
		//更新日
		ety_proh.setUpddate(CommonUtil.getNowUpdDate());
		//更新者ID
		ety_proh.setUpdid(this.sessionDto.EmployeeID);
		
		prohDao.update(ety_proh);
		
		updateMes = "更新しました。";
		
		return null;
		
	}

	/**
	 * 
	 * */
	public Class<?> initialize() {

		try{
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			sutatusItems = getCombList(CommonConstants.COMB_KOA004);

			//経費エンティティ　取得
			TProvisionalHead ety_proh = prohDao.selectById(selectID);
			
			//PJコード
			pjcode = ety_proh.getPjCode();
			
			//PJ名
			Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_PJ_LIST);
			List<ListDto> pjList =  CommonUtil.getServiceToCombData(reqParam);
			
			if(pjList != null){
				for(ListDto listItem : pjList){
					if(ety_proh.getPjCode().equals(listItem.value)){
						pjname = listItem.label;
					}
				}
			}
			
			//請求日
			seikyudate = ety_proh.getSeikyuDate();
			//申請日
			sinseidate = ety_proh.getKyokaKeiriDate();
			//勘定科目
			kanjo = "受払い";
			//許可/不許可
			sutatus = ety_proh.getPaymentFlag();
			//金額
			NumberFormat nfNum = NumberFormat.getNumberInstance();    //カンマ区切り形式
			kin = nfNum.format(ety_proh.getKingaku());

			//仮払い詳細
			keihiItems = listFormat(prodDao.getKariDetailList(ety_proh.getProvisionalHeadId()));
			
			//やりとりメッセージ編集
			yaritoriItems = yariFormat(mesDao.selectBySeikyuID(selectID));

		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			//未マスタメンテ権限エラー
			return NonauthorityPage.class;
			
		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
		
	}

	/**
	 * 
	 * */
	public Class<?> prerender() {
		return null;
	}

	/**
	 * やりとりメッセージ取得処理
	 * 
	 * */
	public List<yaritoriMessageDto> yariFormat(List<yaritoriMessageDto> yList) throws Exception{
		
		List<ListDto> empList = this.getEmpList();
		
		for(yaritoriMessageDto yItem : yList) {
			for(ListDto iItem : empList) {
				
				if(yItem.hatumono.equals(iItem.value)){
					yItem.hatumono = iItem.label;
					break;
				}
			}
		}
		
		return yList;
		
	}
	
	/**　
	 * 金額編集メソッド
	 * 　*/
	private List<KaribaraiDetailListDto> listFormat(List<KaribaraiDetailListDto> list) {
		
		NumberFormat nfNum = NumberFormat.getNumberInstance();    //カンマ区切り形式

		//表示情報編集
		if(list != null){
			for(KaribaraiDetailListDto listItem : list){

				//金額編集
				Long tmpLong = Long.parseLong(listItem.lkin);		
				listItem.lkin = nfNum.format(tmpLong);
			}	
			
		}
		
		return list;
	}
	
}
