package koroot.web.kol001;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import koroot.common.CommonConstants;
import koroot.common.CommonUtil;
import koroot.dao.MKanjoDao;
import koroot.dao.TKogutiDao;
import koroot.dao.TMessageDao;
import koroot.dto.ListDto;
import koroot.dto.yaritoriMessageDto;
import koroot.entity.MKanjo;
import koroot.entity.TKoguti;
import koroot.entity.TMessage;
import koroot.exception.AnLoginException;
import koroot.exception.NoMastaMenteAuthException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonauthorityPage;
import koroot.web.error.NonloginPage;

public class Kol001g2Page extends PageBaceClass {

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
	@Required
	private List<ListDto> sutatusItems;
	
	@PageScope
	private String kin;
	private String updateMes;
	@PageScope
	private int yaritoriIndex;
	@PageScope
	private List<yaritoriMessageDto> yaritoriItems;
	private String hatudate;
	private String hatumono;
	private String yarimessege;
	
	@Required(target="doMesFinishUpdate", messageId= "err.NoInputData")
	private String yMessage;
	
	private String update2Mes;

	/**　PJコード　セッター・ゲッター　*/
	public String getPjcode() {
		return pjcode;
	}
	public void setPjcode(String pjcode) {
		this.pjcode = pjcode;
	}
	
	/**　PJ名　セッター・ゲッター　*/
	public String getPjname() {
		return pjname;
	}
	public void setPjname(String pjname) {
		this.pjname = pjname;
	}
	
	/**　請求日　セッター・ゲッター　*/
	public String getSeikyudate() {
		return seikyudate;
	}
	public void setSeikyudate(String seikyudate) {
		this.seikyudate = seikyudate;
	}

	/**　申請日　セッター・ゲッター　*/
	public String getSinseidate() {
		return sinseidate;
	}
	public void setSinseidate(String sinseidate) {
		this.sinseidate = sinseidate;
	}

	/**　勘定科目名　セッタ・ゲッター　*/
	public String getKanjo() {
		return kanjo;
	}
	public void setKanjo(String kanjo) {
		this.kanjo = kanjo;
	}

	/**　許可/不許可　セッター・ゲッター　*/
	public Integer getSutatus() {
		return sutatus;
	}
	public void setSutatus(Integer sutatus) {
		this.sutatus = sutatus;
	}

	/**　許可/不許可　セッター・ゲッター	*/
	public List<ListDto> getSutatusItems() {
		return sutatusItems;
	}
	public void setSutatusItems(List<ListDto> sutatusItems) {
		this.sutatusItems = sutatusItems;
	}

	/**　金額　セッター・ゲッター	　*/
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}

	/**　更新メッセージ　セッター・ゲッター　*/
	public String getUpdateMes() {
		return updateMes;
	}
	public void setUpdateMes(String updateMes) {
		this.updateMes = updateMes;
	}

	/**　やりとりメッセージ　index セッター・ゲッター　*/
	public int getYaritoriIndex() {
		return yaritoriIndex;
	}
	public void setYaritoriIndex(int yaritoriIndex) {
		this.yaritoriIndex = yaritoriIndex;
	}

	/**　やりとりメッセージ　セッター・ゲッター　*/
	public List<yaritoriMessageDto> getYaritoriItems() {
		return yaritoriItems;
	}
	public void setYaritoriItems(List<yaritoriMessageDto> yaritoriItems) {
		this.yaritoriItems = yaritoriItems;
	}

	/**　発言日　セッター・ゲッター　*/
	public String getHatudate() {
		return hatudate;
	}
	public void setHatudate(String hatudate) {
		this.hatudate = hatudate;
	}

	/**　発言者　セッター・ゲッター　*/
	public String getHatumono() {
		return hatumono;
	}
	public void setHatumono(String hatumono) {
		this.hatumono = hatumono;
	}

	/**　やりとりメッセージ　セッター・ゲッター　*/
	public String getYarimessege() {
		return yarimessege;
	}
	public void setYarimessege(String yarimessege) {
		this.yarimessege = yarimessege;
	}
	
	/**　メッセージ　セッター・ゲッター　*/
	public String getyMessage() {
		return yMessage;
	}
	public void setyMessage(String yMessage) {
		this.yMessage = yMessage;
	}
	
	/**　更新メッセージ２　セッター・ゲッター　*/
	public String getUpdate2Mes() {
		return update2Mes;
	}
	public void setUpdate2Mes(String update2Mes) {
		this.update2Mes = update2Mes;
	}

	//勘定科目Dao
	public MKanjoDao kanDao;
	
	//経費Dao
	public TKogutiDao kogDao;
	
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
	
	public Class<?> doFinishReturn() {
		
		return Kol001g1Page.class;
		
	}

	//更新
	public Class<?> doFinishUpdate() {

		//経費エンティティ　取得
		TKoguti ety_kogu = kogDao.selectById(selectID);

		//ステータス
		ety_kogu.setState(sutatus);
		
		//更新ステータスチェック
		if(sutatus.equals(5)){

			//経理許可日
			ety_kogu.setKyokaKeiriDate(CommonUtil.getNowUpdDate());
			//経理許可ID
			ety_kogu.setKyokaKeiriEmpid(this.sessionDto.EmployeeID);
			//経理不許可日
			ety_kogu.setFukyokaKeiriDate(null);
			//経理不許可ID
			ety_kogu.setFukyokaKeiriEmpid(null);

		} else {

			//経理許可日
			ety_kogu.setKyokaKeiriDate(null);
			//経理許可ID
			ety_kogu.setKyokaKeiriEmpid(null);
			//経理不許可日
			ety_kogu.setFukyokaKeiriDate(CommonUtil.getNowUpdDate());
			//経理不許可ID
			ety_kogu.setFukyokaKeiriEmpid(this.sessionDto.EmployeeID);
		}

		//更新日
		ety_kogu.setUpddate(CommonUtil.getNowUpdDate());
		//更新者ID
		ety_kogu.setUpdid(this.sessionDto.EmployeeID);
		
		kogDao.update(ety_kogu);
		
		updateMes = "更新しました。";
		
		return null;
	}

	//やりとり更新
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
		
		return Kol001g2Page.class;
		
	}
	
	/**
	 * @return
	 */
	public Class<?> initialize() {
		
		try{
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			sutatusItems = getCombList(CommonConstants.COMB_KOA002);

			//経費エンティティ　取得
			TKoguti ety_kogu = kogDao.selectById(selectID);
			
			//勘定科目エンティティ　取得
			MKanjo ety_kan = kanDao.selectById(ety_kogu.getKanjoId());
			
			//PJコード
			pjcode = ety_kogu.getPjCode();
			//PJ名
			Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_PJ_LIST);
			List<ListDto> pjList =  CommonUtil.getServiceToCombData(reqParam);
			
			if(pjList != null){
				for(ListDto listItem : pjList){
					if(ety_kogu.getPjCode().equals(listItem.value)){
						pjname = listItem.label;
					}
				}
			}
			
			//請求日
			seikyudate = ety_kogu.getSeikyuDate();
			//申請日
			sinseidate = ety_kogu.getKyokaPjlDate();
			//勘定科目
			kanjo = ety_kan.getName();
			//許可/不許可
			if(ety_kogu.getState().equals(3)){
				sutatus = 5;
			} else {
				sutatus = ety_kogu.getState();
			}
			//金額
			NumberFormat nfNum = NumberFormat.getNumberInstance();    //カンマ区切り形式

			kin = nfNum.format(ety_kogu.getKingaku());
			
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

	public Class<?> prerender() {
		return null;
	}
	
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
	
}
