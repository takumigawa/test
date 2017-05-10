package koroot.web.kom001;

import java.util.List;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;
import koroot.common.CommonConstants;
import koroot.dao.MKanjoDao;
import koroot.dto.KanjoListDto;
import koroot.dto.ListDto;
import koroot.exception.AnLoginException;
import koroot.exception.NoMastaMenteAuthException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonauthorityPage;
import koroot.web.error.NonloginPage;
import koroot.web.kos001.Kos001g1Page;

public class Kom001g1Page extends PageBaceClass {

	//勘定マスタDao
	public MKanjoDao kanDao;
	
	@Required
	@PageScope
	private String kaihai;
	@Required
	@PageScope
	private List<ListDto> kaihaiItems;
	
	@Required
	@PageScope
	private int kanjoIndex;
	@Required
	@PageScope
	private List<KanjoListDto> kanjoItems;
	
	private String kanjoID;
	private String kanjoName;
	private String orderSEQ;
	private String kanjoEnabled;
	
	@SubapplicationScope
	private Integer selectID;

	/**　選択勘定科目ID　セッター・ゲッター　*/
	public Integer getSelectID() {
		return selectID;
	}
	public void setSelectID(Integer selectID) {
		this.selectID = selectID;
	}
	
	/** 改廃　セッター・ゲッター */
	public String getKaihai() {
		return kaihai;
	}
	public void setKaihai(String kaihai) {
		this.kaihai = kaihai;
	}

	/**　改廃リスト　セッター・ゲッター　*/
	public List<ListDto> getKaihaiItems() {
		return kaihaiItems;
	}
	public void setKaihaiItems(List<ListDto> kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}

	/**　勘定Index セッター・ゲッター　*/
	public int getKanjoIndex() {
		return kanjoIndex;
	}
	public void setKanjoIndex(int kanjoIndex) {
		this.kanjoIndex = kanjoIndex;
	}

	/**　勘定List セッター・ゲッター　*/
	public List<KanjoListDto> getKanjoItems() {
		return kanjoItems;
	}
	public void setKanjoItems(List<KanjoListDto> kanjoItems) {
		this.kanjoItems = kanjoItems;
	}

	/**　勘定選択ID セッター・ゲッター　*/
	public String getKanjoID() {
		return kanjoID;
	}
	public void setKanjoID(String kanjoID) {
		this.kanjoID = kanjoID;
	}

	/**　勘定名　セッター・ゲッター　*/
	public String getKanjoName() {
		return kanjoName;
	}
	public void setKanjoName(String kanjoName) {
		this.kanjoName = kanjoName;
	}

	/**　表示順　セッター・ゲッター　*/
	public String getOrderSEQ() {
		return orderSEQ;
	}
	public void setOrderSEQ(String orderSEQ) {
		this.orderSEQ = orderSEQ;
	}

	/**　有効/無効　セッター・ゲッター　*/
	public String getKanjoEnabled() {
		return kanjoEnabled;
	}
	public void setKanjoEnabled(String kanjoEnabled) {
		this.kanjoEnabled = kanjoEnabled;
	}

	/**　*/
	public Class<?> doFinishReturn() {
		
		return Kos001g1Page.class;
		
	}
	
	/**　　*/
	public Class<?> doSrech() {
		
		//勘定マスタリスト　取得
		Integer kanI = Integer.parseInt(kaihai);
		
		//勘定マスタリスト　取得
		kanjoItems = kanDao.getSelectByList(kanI);
		
		return null;
	}

	/**　　*/
	public Class<?> doSrechCancel() {
		
		kaihai		= CommonConstants.Kaihai_true.toString();
		
		//勘定マスタリスト　取得
		kanjoItems = kanDao.getSelectByList(CommonConstants.Kaihai_true);
		
		return null;
	}

	/**　　*/
	public Class<?> doEmployeeAdd() {
		
		selectID = -1;
		
		return Kom001g2Page.class;
	}
	
	/**　　*/
	public Class<?> doDetails() {
		
		selectID = Integer.parseInt(kanjoItems.get(kanjoIndex).kanjoID);
		
		return Kom001g2Page.class;
	}

	/**　　*/
	public Class<?> initialize() {
		
		try{
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ		
			kaihaiItems = getCombList(CommonConstants.COMB_kaihai);	
			
			//勘定マスタリスト　取得
			kanjoItems = kanDao.getSelectByList(CommonConstants.Kaihai_true);
			
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
		
		this.setLoginInfo();
		
		return null;
	}

}
