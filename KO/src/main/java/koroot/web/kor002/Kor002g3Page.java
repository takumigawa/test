package koroot.web.kor002;


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
import koroot.dao.TMessageDao;
import koroot.dao.TProvisionalHeadDao;
import koroot.dto.ListDto;
import koroot.dto.yaritoriMessageDto;
import koroot.entity.TMessage;
import koroot.entity.TProvisionalHead;
import koroot.exception.AnLoginException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonloginPage;
import koroot.web.kor002.Kor002g1Page;
import koroot.web.kor002.Kor002g2Page;

public class Kor002g3Page extends PageBaceClass{

	/** 選択経費ＩＤ */
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
	private String karibaraiday;
	@Required(target="doInsnew,doUpdate", messageId= "err.Nopjcode")
	private String pjcode;
	private String pjname;
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
	private List yaritoriItems;
	private String hatudate;
	private String hatumono;
	private String yarimessege;

	private String isnotUpdate;

	/** ステータスアイテム */
	@Required
	@PageScope
	private List<ListDto> statusItems;

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
	//仮払いDao
	public TProvisionalHeadDao provDao;
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

	public String getKaribaraiday() {
		return karibaraiday;
	}

	public void setKaribaraiday(String karibaraiday) {
		this.karibaraiday = karibaraiday;
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

	public List getYaritoriItems() {
		return yaritoriItems;
	}

	public void setYaritoriItems(List yaritoriItems) {
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
		TProvisionalHead ety_prov = new TProvisionalHead();
//
		ety_prov.setSeikyuDate(seikyudate);
		ety_prov.setState(status);
		ety_prov.setKaribaraiDate(karibaraiday);
		ety_prov.setPjCode(pjcode);
		ety_prov.setKingaku(kin);
		ety_prov.setSeikyuEmpid(sessionDto.EmployeeID);
		ety_prov.setBiko(biko);
		ety_prov.setKaihai(0);
		ety_prov.setAdddate(today);
		ety_prov.setAddid(sessionDto.EmployeeID);
		ety_prov.setDetailEntryFlag(0);
		ety_prov.setPaymentFlag(0);
		ety_prov.setTighteningFlag(0);

		provDao.insert(ety_prov);
		selectID = provDao.getMAXkaribaraiID();

		kogutiMes = "申請しました。";
		
		updateMes = "";

		return Kor002g2Page.class;
	}

	public Class<?> doUpdate() {
		//申請内容更新
		TProvisionalHead ety_prov = provDao.selectById(selectID);

		ety_prov.setSeikyuDate(seikyudate);
		ety_prov.setState(status);
		ety_prov.setKaribaraiDate(karibaraiday);
		ety_prov.setPjCode(pjcode);
		ety_prov.setKingaku(kin);
		ety_prov.setBiko(biko);
		ety_prov.setUpddate(today);
		ety_prov.setUpdid(sessionDto.EmployeeID);

		provDao.update(ety_prov);

		kogutiMes = "申請内容を更新しました。";
		
		updateMes = "";

		return Kor002g2Page.class;
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

		return Kor002g3Page.class;
	}

	public Class<?> doReturn() {

		if(selectID == -1){
		return Kor002g1Page.class;

		}else{

		return Kor002g2Page.class;
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

			if(selectID == -1){
				//新規登録時

				seikyudate = today;
				status  = 1;
				pjcode  = null;
				kin     = 0;
				biko    = null;

			} else{

			//仮払いエンティティ　取得
			TProvisionalHead ety_prov = provDao.selectById(selectID);
			//請求日
			seikyudate    = ety_prov.getSeikyuDate();
			//ステータス
			status        = ety_prov.getState();
			//仮払い日
			karibaraiday  = ety_prov.getKaribaraiDate();
			//プロジェクトコード
			pjcode        = ety_prov.getPjCode();
			//金額
			kin           = ety_prov.getKingaku();
            //備考
            biko          = ety_prov.getBiko();

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

