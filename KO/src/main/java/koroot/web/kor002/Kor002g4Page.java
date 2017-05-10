package koroot.web.kor002;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import koroot.common.CommonConstants;
import koroot.dao.MKanjoDao;
import koroot.dao.TProvisionalDetaleDao;
import koroot.dao.TProvisionalHeadDao;
import koroot.dto.ListDto;
import koroot.dto.ProvdetailListDto;
import koroot.dto.ProvsinnseiListDto;
import koroot.entity.MKanjo;
import koroot.entity.TProvisionalDetale;
import koroot.entity.TProvisionalHead;
import koroot.exception.AnLoginException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonloginPage;
import koroot.web.kor002.Kor002g1Page;
import koroot.web.kor002.Kor002g2Page;

public class Kor002g4Page extends PageBaceClass{
	
	/** 選択仮払いＩＤ */
@PageScope
@SubapplicationScope
private Integer selectID;

@RegularExpression(target="doInsnew,doUpdate", pattern = CommonConstants.REG_DAYFORMAT_CHECK, messageId = "err.NoMatchDAY")
private String siyouday;
@Required(target="doUpdate", messageId= "err.Nokanjo")
private Integer kanjo;
@Required(target="doInsnew,doUpdate", messageId= "err.Nokin")
private Integer meisaikin;
private Integer meikin;
private String biko;
	
	@Required
	@PageScope
	private int meisaiIndex;
	@Required
	@PageScope
	private List<ProvdetailListDto> meisaiItems;
	
	
	//仮払いヘッダDao
	public TProvisionalHeadDao provDao;
	//仮払い明細Dao
	public TProvisionalDetaleDao detailDao;
	//勘定科目Dao
	public MKanjoDao kanDao;
	
	/** 勘定アイテム */
	@Required
	@PageScope
	private List<ListDto> kanjoItems;
	//当日取得用
	@PageScope
	public String  today;
	public String  thisyear;
	public String  thismonth;
	public String  thisday;
	
	private String UpdateMes;
	
	/**　仮払いID セッター・ゲッター　*/
	public Integer getSelectID() {
		return this.selectID;
	}
	public void setSelectID(Integer selectID) {
		this.selectID = selectID;
	}	
	
	public Integer getKanjo() {
		return kanjo;
	}
	public void setKanjo(Integer kanjo) {
		this.kanjo = kanjo;
	}
	
	/** 勘定コンボ　セッター・ゲッター　*/
	public List<ListDto> getkanjoItems() {
		return kanjoItems;
	}
	public void setkanjoItems(List<ListDto> kanjoItems) {
		this.kanjoItems = kanjoItems;
	}
	/**　明細Index セッター・ゲッター　*/
	public int getmeisaiIndex() {
		return meisaiIndex;
	}
	public void setmeisaiIndex(int meisaiIndex) {
		this.meisaiIndex = meisaiIndex;
	}
	/** 明細リスト　セッター・ゲッター　*/
	public List<ProvdetailListDto> getmeisaiItems() {
		return meisaiItems;
	}
	public void setmeisaiItems(List<ProvdetailListDto> meisaiItems) {
		this.meisaiItems = meisaiItems;
	}

	public String getUpdateMes() {
		return UpdateMes;
	}

	public void setUpdateMes(String updateMes) {
		UpdateMes = updateMes;
	}

	public Class<?> doReturn() {
		return Kor002g2Page.class;
	}

	public Class<?> doUpdate() {
		return null;
	}

	public Class<?> doInsnew() {
		
		//新規登録
		TProvisionalDetale ety_detail = new TProvisionalDetale();
//
		ety_detail.setProvisionalHeadId(selectID);
		ety_detail.setKanjoId(kanjo);
		ety_detail.setKingaku(meisaikin);
//		ety_detail.setresitoData();
		ety_detail.setBiko(biko);
		ety_detail.setSiyoudate(siyouday);
		ety_detail.setKaihai(0);
		ety_detail.setAdddate(today);
		ety_detail.setAddid(sessionDto.EmployeeID);

		detailDao.insert(ety_detail);
		
		return null;
	}
	
	public Class<?> initialize() {
		
		   
		try{			
		    //セッション情報チェック
			this.chkSession();
			//ログイン情報表示
			this.setLoginInfo();
			
			//当日日付を取得とする
			Calendar now = Calendar.getInstance();
			thisyear  = new Integer(now.get(Calendar.YEAR)).toString();          //年を取得だ
			thismonth = new Integer(now.get(Calendar.MONTH) + 1).toString();     //月を取得(※1)
			if(Integer.parseInt(thismonth) < 10){
				thismonth = "0" + thismonth;
			}
			thisday   = new Integer(now.get(Calendar.DATE)).toString();;         //日を取得
			if(Integer.parseInt(thisday) < 10){
				thisday = "0" + thisday;
			}
			today = thisyear + "/" + thismonth + "/" + thisday;
			
			//入力条件-勘定科目
			MKanjo[]  dao_Kanjo = kanDao.selectAll();
			int length = dao_Kanjo.length;
			kanjoItems = new ArrayList<ListDto>();

			for(int i = 0; i < length; i++){
				ListDto mapKanjo = new ListDto();
				mapKanjo.label = dao_Kanjo[i].getName();
				mapKanjo.value = dao_Kanjo[i].getKanjoId().toString();
				kanjoItems.add(mapKanjo);
			}
			
			biko = "";
			//明細一覧取得
			int kaihai   = 0;
			
			meisaiItems = listFormat(detailDao.getProvdetailList(selectID, kaihai));
			
			

				
			} catch (AnLoginException e) {
				//未ログインエラー
				return NonloginPage.class;

			}catch(Exception e){
				//アベンド
				return AbendPage.class;
			}
		
		return null;
	}

	public Class<?> prerender() {
		return null;
	}

	private List<ProvdetailListDto> listFormat(List<ProvdetailListDto> list) {
		
		NumberFormat nfNum = NumberFormat.getNumberInstance();    //カンマ区切り形式

		//表示情報編集
		if(list != null){
			for(ProvdetailListDto listItem : list){

				//金額編集
				Long tmpLong = Long.parseLong(listItem.kin);		
				listItem.kin = nfNum.format(tmpLong);
				
				}		
			}			
		return list;
	}
	public String getSiyouday() {
		return siyouday;
	}
	public void setSiyouday(String siyouday) {
		this.siyouday = siyouday;
	}
	public Integer getmeikin() {
		return meikin;
	}
	public void setmeikin(Integer meikin) {
		this.meikin = meikin;
	}
	public String getBiko() {
		return biko;
	}
	public void setBiko(String biko) {
		this.biko = biko;
	}
	public Integer getMeisaikin() {
		return meisaikin;
	}
	public void setMeisaikin(Integer meisaikin) {
		this.meisaikin = meisaikin;
	}
		
}
