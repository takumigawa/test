package koroot.web.kor001;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;


import koroot.common.CommonConstants;
import koroot.common.CommonUtil;
import koroot.dao.MKanjoDao;
import koroot.dao.TKeihiDao;
import koroot.dao.TMessageDao;
import koroot.dto.ListDto;
import koroot.dto.yaritoriMessageDto;
import koroot.entity.MKanjo;
import koroot.entity.TKeihi;
import koroot.entity.TMessage;
import koroot.exception.AnLoginException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonloginPage;
import koroot.web.kor001.Kor001g1Page;
import koroot.web.kor001.Kor001g2Page;

public class Kor001g3Page extends PageBaceClass{

@PageScope
@SubapplicationScope
private Integer selectID;
@SubapplicationScope
private  Integer year;
@SubapplicationScope
private  Integer month;
@SubapplicationScope
@Required(target="doInsnew,doUpdate", messageId= "err.Nostatus")
private  Integer status;

@Required(target="doInsnew,doUpdate", messageId= "err.Noseikyudate")
@RegularExpression(target="doInsnew,doUpdate", pattern = CommonConstants.REG_DAYFORMAT_CHECK, messageId = "err.NoMatchDAY")
//	@ChkDateValidator(targetId = "seikyudate")
private String seikyudate;
private String statusname;
@Required(target="doInsnew,doUpdate", messageId= "err.Nosiyouday")
@RegularExpression(target="doInsnew,doUpdate", pattern = CommonConstants.REG_DAYFORMAT_CHECK, messageId = "err.NoMatchDAY")
private String siyouday;
@Required(target="doInsnew,doUpdate", messageId= "err.Nopjcode")
private String pjcode;
private String pjname;
@Required(target="doInsnew,doUpdate", messageId= "err.Nokanjo")
private Integer kanjo;
@Required(target="doInsnew,doUpdate", messageId= "err.Nokin")
private Integer kin;
private String biko;
@PageScope
@Required(target="doUpdatemessage", messageId= "err.NoInputData")
private String yMessage;
@PageScope
@SubapplicationScope
private String kogutiMes;
private String updateMes;
private int yaritoriIndex;
private List<yaritoriMessageDto> yaritoriItems;
private String hatudate;
private String hatumono;
private String yarimessege;
private String isnotUpdate;

	/** ステータスアイテム */
	@Required
	@PageScope
	private List<ListDto> statusItems;

	/** 勘定アイテム */
	@Required
	@PageScope
	private List<ListDto> kanjoItems;

	/** PJアイテム*/
	@Required
	@PageScope
	private List<ListDto> pjcodeItems;

	//当日取得用
	@PageScope
	public String  today;
	public String  thisyear;
	public String  thismonth;
	public String  thisday;


	//勘定科目Dao
	public MKanjoDao kanDao;
	
	//経費Dao
	public TKeihiDao keiDao;	

	//やりとりメッセ-ジDao
	public TMessageDao mesDao;


	/**　経費ID セッター・ゲッター　*/
	public Integer getSelectID() {
		return this.selectID;
	}
	public void setSelectID(Integer selectID) {
		this.selectID = selectID;
	}

	/** ステータスコンボ　セッター・ゲッター　*/
	public List<ListDto> getStatusItems() {
		return statusItems;
	}
	public void setStatusItems(List<ListDto> statusItems) {
		this.statusItems = statusItems;
	}

	/**　ステータスID セッター・ゲッター　*/
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**　勘定ID セッター・ゲッター　*/
	public Integer getkanjo() {
		return kanjo;
	}
	public void setkanjo(Integer kanjo) {
		this.kanjo = kanjo;
	}


	/** 勘定コンボ　セッター・ゲッター　*/
	public List<ListDto> getkanjoItems() {
		return kanjoItems;
	}
	public void setkanjoItems(List<ListDto> kanjoItems) {
		this.kanjoItems = kanjoItems;
	}


	/**　プロジェクトコードコンボ　セッター・ゲッター　*/
	public List<ListDto> getpjcodeItems() {
		return pjcodeItems;
	}
	public void setpjcodeItems(List<ListDto> pjcodeItems) {
		this.pjcodeItems = pjcodeItems;
	}

	public String getseikyudate() {
		return seikyudate;
	}

	public void setseikyudate(String seikyudate) {
		this.seikyudate = seikyudate;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getSiyouday() {
		return siyouday;
	}

	public void setSiyouday(String siyouday) {
		this.siyouday = siyouday;
	}

	public String getPjcode() {
		return pjcode;
	}

	public void setPjcode(String pjcode) {
		this.pjcode = pjcode;
	}

	public String getPjname() {
		return pjname;
	}

	public void setPjname(String pjname) {
		this.pjname = pjname;
	}

	public Integer getKanjo() {
		return kanjo;
	}

	public void setKanjyo(Integer kanjo) {
		this.kanjo = kanjo;
	}

	public Integer getKin() {
		return kin;
	}

	public void setKin(Integer kin) {
		this.kin = kin;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}
	public String getIsnotUpdate() {
		return isnotUpdate;
	}

	public void setIsnotUpdate(String isnotUpdate) {
		this.isnotUpdate = isnotUpdate;
	}

	public String getkogutiMes() {
		return kogutiMes;
	}
	public void setkogutiMes(String kogutiMes) {
		this.kogutiMes = kogutiMes;
	}

	public String getUpdateMes() {
		return updateMes;
	}

	public void setUpdateMes(String updateMes) {
		this.updateMes = updateMes;
	}

	public String getyMessage() {
		return yMessage;
	}

	public void setyMessage(String yMessage) {
		this.yMessage = yMessage;
	}
	public int getYaritoriIndex() {
		return yaritoriIndex;
	}

	public void setYaritoriIndex(int yaritoriIndex) {
		this.yaritoriIndex = yaritoriIndex;
	}

	public List<yaritoriMessageDto> getYaritoriItems() {
		return yaritoriItems;
	}

	public void setYaritoriItems(List<yaritoriMessageDto> yaritoriItems) {
		this.yaritoriItems = yaritoriItems;
	}

	public String getHatudate() {
		return hatudate;
	}

	public void setHatudate(String hatudate) {
		this.hatudate = hatudate;
	}

	public String getHatumono() {
		return hatumono;
	}

	public void setHatumono(String hatumono) {
		this.hatumono = hatumono;
	}

	public String getYarimessege() {
		return yarimessege;
	}

	public void setYarimessege(String yarimessege) {
		this.yarimessege = yarimessege;
	}



	public Class<?> doInsnew() {
		//新規登録
		TKeihi ety_keih = new TKeihi();

		ety_keih.setPjCode(pjcode);
		ety_keih.setState(status);
		ety_keih.setKanjoId(kanjo);
		ety_keih.setKingaku(kin);
		ety_keih.setSiyouDate(siyouday);
		ety_keih.setSeikyuDate(seikyudate);
		ety_keih.setSeikyuEmpid(sessionDto.EmployeeID);
		ety_keih.setBiko(biko);
		ety_keih.setKaihai(0);
		ety_keih.setAdddate(today);
		ety_keih.setAddid(sessionDto.EmployeeID);
		ety_keih.setShiharaiFlag(0);


		keiDao.insert(ety_keih);
		selectID = keiDao.getMAXseikyuID();

		kogutiMes = "申請しました。";
		
		updateMes = "";

		return Kor001g2Page.class;
	}

	public Class<?> doUpdate() {
		//申請内容更新
		TKeihi ety_keih = keiDao.selectById(selectID);

		ety_keih.setPjCode(pjcode);
		ety_keih.setState(status);
		ety_keih.setKanjoId(kanjo);
		ety_keih.setKingaku(kin);
		ety_keih.setSiyouDate(siyouday);
		ety_keih.setSeikyuDate(seikyudate);
		ety_keih.setBiko(biko);
		ety_keih.setUpddate(today);
		ety_keih.setUpdid(sessionDto.EmployeeID);

		keiDao.update(ety_keih);

		kogutiMes = "申請内容を更新しました。";
		
		updateMes = "";

		return Kor001g2Page.class;
	}


	public Class<?> doUpdatemessage() {

		//やりとりメッセージ　エンティティー　
		TMessage ety_mes = new TMessage();

		ety_mes.setAdddate(CommonUtil.getNowUpdDate());

		ety_mes.setAddid(this.sessionDto.EmployeeID);

		ety_mes.setMessage(yMessage);

		ety_mes.setInputEmpid(this.sessionDto.EmployeeID);

		ety_mes.setKaihai(0);

		ety_mes.setSeikyuId(selectID);


		mesDao.insert(ety_mes);

		updateMes = "メッセージ追加しました。";
		yMessage = "";

		return Kor001g3Page.class;
	}

	public Class<?> doReturn() {

		if(selectID == -1){
		return Kor001g1Page.class;

		}else{

		return Kor001g2Page.class;
		}
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

			//入力条件-ステータスコンボ
			statusItems = this.getCombList(CommonConstants.COMB_KOR003);

			//入力条件-プロジェクトコード
			Map<String, String> reqstatus = new HashMap<String, String>();
			reqstatus.put("comKey","employee_id");       /** 小口請求用ステータス */
		    reqstatus.put("comLabel",sessionDto.EmployeeID.toString());      /** 小口請求用ステータス 有効 */

			Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_PJ_LIST,reqstatus);

			pjcodeItems =  CommonUtil.getServiceToCombData(reqParam);

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

			if(selectID == -1){
				//新規登録時

				seikyudate = today;
				siyouday   = today;
				status  = 1;
				pjcode  = null;
				kanjo   = null;
				kin     = 0;
				biko    = null;

			} else{

			//経費エンティティ　取得
			TKeihi ety_keih = keiDao.selectById(selectID);
			//請求日
			seikyudate       = ety_keih.getSeikyuDate();
			//使用日
			siyouday         = ety_keih.getSiyouDate();
			//ステータス
			status           = ety_keih.getState();
			//プロジェクトコード
			pjcode           = ety_keih.getPjCode();
			//勘定科目
			kanjo            = ety_keih.getKanjoId();
			//金額
			kin              =  ety_keih.getKingaku();
            //備考
            biko             = ety_keih.getBiko();

  			//やりとりメッセージ編集
			yaritoriItems = yariFormat(mesDao.selectBySeikyuID(selectID));

			}

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

		//ログイン情報表示
		this.setLoginInfo();

		//やりとりメッセージ編集
		try {
			yaritoriItems = yariFormat(mesDao.selectBySeikyuID(selectID));
		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;

		}catch(Exception e){
			//アベンド
			return AbendPage.class;
		}

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

	/**
	 * <p>画面登録/新規切り替え判定メソッド</p>
	 * @return true 更新/false 新規
	 *
	 */
	public boolean isUpdate(){

		if(selectID == -1){
			return false;
		}else{
			return true;
		}
	}

}

