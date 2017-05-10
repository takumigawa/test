package koroot.web.kom001;

import java.util.List;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;
import koroot.common.CommonConstants;
import koroot.common.CommonUtil;
import koroot.dao.MKanjoDao;
import koroot.dto.ListDto;
import koroot.entity.MKanjo;
import koroot.exception.AnLoginException;
import koroot.exception.NoMastaMenteAuthException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonauthorityPage;
import koroot.web.error.NonloginPage;

public class Kom001g2Page extends PageBaceClass {

	@SubapplicationScope
	private Integer kanjoID;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String kanjoName;
	
	@Required
	private Integer kaihai;
	@Required
	@PageScope
	private List<ListDto> kaihaiItems;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_SUJI, messageId = "err.NoMatchEISU")
	private String orderSEQ;
	
	private String updateMes;
	
	private String dOru;
	
	public MKanjoDao kanDao;

	/** 選択勘定ＩＤ */
	@SubapplicationScope
	private Integer selectID;
	
	public Integer getSelectID() {
		return this.selectID;
	}
	public void setSelectID(Integer selectID) {
		this.selectID = selectID;
	}
	
	/**　勘定ID　セッター・ゲッター　*/
	public Integer getKanjoID() {
		return kanjoID;
	}
	public void setKanjoID(Integer kanjoID) {
		this.kanjoID = kanjoID;
	}

	/**　勘定名　セッター・ゲッター　*/
	public String getKanjoName() {
		return kanjoName;
	}
	public void setKanjoName(String kanjoName) {
		this.kanjoName = kanjoName;
	}

	/**　改廃　セッター・ゲッター　*/
	public Integer getKaihai() {
		return kaihai;
	}
	public void setKaihai(Integer kaihai) {
		this.kaihai = kaihai;
	}

	/**　改廃リスト　セッター・ゲッター　*/
	public List<ListDto> getKaihaiItems() {
		return kaihaiItems;
	}
	public void setKaihaiItems(List<ListDto> kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}

	/**　表示順　セッター・ゲッター　*/
	public String getOrderSEQ() {
		return orderSEQ;
	}
	public void setOrderSEQ(String orderSEQ) {
		this.orderSEQ = orderSEQ;
	}

	/**　メッセージ　セッター・ゲッター　*/
	public String getUpdateMes() {
		return updateMes;
	}
	public void setUpdateMes(String updateMes) {
		this.updateMes = updateMes;
	}

	/**　画面ステータス　セッター・ゲッター　*/
	public String getdOru() {
		return dOru;
	}
	public void setdOru(String dOru) {
		this.dOru = dOru;
	}


	/**　　*/
	public Class<?> doFinishUpdate() {
		
		try{
			
			MKanjo ety_kan;
			
			if(dOru.equals("Ins")){
				
				//勘定情報新規作成
				ety_kan = new MKanjo();
				
				ety_kan.setKanjoId(kanjoID);
				ety_kan.setName(kanjoName);
				
				ety_kan.setOrderseq(Integer.parseInt(orderSEQ));  
				ety_kan.setKaihai(0);
				
				ety_kan.setAdddate(CommonUtil.getNowUpdDate());
				ety_kan.setAddid(sessionDto.EmployeeID);
				
				kanDao.insert(ety_kan);
				
				updateMes = "新規追加しました。";
				
			}else{
				
				//勘定情報新規作成
				ety_kan = kanDao.selectById(selectID);
				
				ety_kan.setName(kanjoName);
				
				ety_kan.setOrderseq(Integer.parseInt(orderSEQ));
				
				ety_kan.setKaihai(kaihai);
				
				ety_kan.setUpddate(CommonUtil.getNowUpdDate());
				ety_kan.setUpdid(sessionDto.EmployeeID);
				
				kanDao.update(ety_kan);
				
				updateMes = "更新しました。";
			}
			
			return null;
	
		}catch(Exception e){
			return AbendPage.class;
			
		}
		
	}
	
	/**　　*/
	public Class<?> doFinishReturn() {
		return Kom001g1Page.class;
	}

	/**　　*/
	public Class<?> initialize() {
		
		try{
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			kaihaiItems = getCombList(CommonConstants.COMB_kaihaiYN);
			
			if(selectID.equals(-1)){
				
				kanjoID = kanDao.getNextKanjoID();
				
				orderSEQ = String.valueOf(kanDao.getNextOrderSEQ());
				dOru = "Ins";
				
			}else{
				
				MKanjo ety_kan = kanDao.selectById(selectID);
				
				kanjoID				= ety_kan.getKanjoId();
				kanjoName			= ety_kan.getName();
				orderSEQ 			= String.valueOf(ety_kan.getOrderseq());
				kaihai              = ety_kan.getKaihai();
				
				dOru = "Upd";
				
			}

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

	/**　　*/
	public Class<?> prerender() {
		return null;
	}

	/**
	 * <p>画面登録/新規切り替え判定メソッド</p>
	 * @return true 更新/false 新規
	 *
	 */
	public boolean isUpdate(){
		
		if(dOru.equals("Ins")){
			return false;
		}else{
			return true;
		}
	}
	
}
