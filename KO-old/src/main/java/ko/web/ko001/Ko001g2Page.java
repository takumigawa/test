package ko.web.ko001;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.convert.NumberConverter;
import org.seasar.teeda.extension.annotation.scope.PageScope;


import ko.common.CommonConstants;
import ko.dao.MKanjoDao;
import ko.dao.TKogutiDao;
import ko.dao.TMessageDao;
//import ko.dto.KanjoListDto;
import ko.dto.ListDto;
import ko.dto.MessageDto;
import ko.entity.MKanjo;
import ko.entity.TKoguti;
import ko.entity.TMessage;
import ko.pagebace.PageBaceClass;
import ko.web.error.NonloginPage;

public class Ko001g2Page extends PageBaceClass{

	@PageScope
	private String employee_id;
	@PageScope
	private String employee_name;
	@PageScope
	private String employee_mail;
	@PageScope
	private Integer seikyuID;
	@PageScope
	private String sinsei_Date;
	@PageScope
	private String state_id;
	@PageScope
	private String usedday_id;
	@PageScope
	private String pjcode_id;
	@PageScope
	public  String kanjoname;
	@PageScope
	private String kanjostr;

	private String strcombo;
	private List<ListDto>   strcomboItems;

	@PageScope
	@NumberConverter(pattern="#,##0")
	private Integer kingaku_id;
	@PageScope
	private String bikou_id;

	@PageScope
	private MessageDto[] messageItems;
//	private String message;
	private String transday_id;
	private String sender_id;
	private String message_id;

	@PageScope
	public Integer selectkogutiID;

	/** 小口Dao */
	public TKogutiDao KogutiDao;
	/** 勘定Dao */
	public MKanjoDao  KanjoDao;
	/** メッセージDao */
	public TMessageDao  MessageDao;
	/** 勘定ID格納 */
	@PageScope
	public Integer kanjo_code;
	/** ステータスID格納 */
	private String  local_state;
	/**ステータス格納用 */
	public  String comLabel;
	public  String comKey;
	@PageScope
	public  Map<String,String> strnamelist;

	/** 検索ステータスアイテム */
	@PageScope
	public List<Map<String,String>> strlistItems;

	/** 勘定アイテム */
	@PageScope
	public List<Map<String,String>> kanjonameItems;

	//当日取得用
	@PageScope
	public String today;
	@PageScope
	public  String  thisyear;
	@PageScope
	public  Integer thismonth;
	public  String  thisday;

	/** 選択年度格納 */
//	@PageScope
	public Integer selectYear;
	/** 選択月度格納 */
	@PageScope
	public Integer	selectmonthID;
	/** 選択ステータス格納 */
	@PageScope
	public String selectstrID;

	/** 更新メッセージ表示用 */
	public String UpdateMes;

	boolean flg = true;


	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_mail() {
		return employee_mail;
	}

	public void setEmployee_mail(String employee_mail) {
		this.employee_mail = employee_mail;
	}

	public String getSinsei_Date() {
		return sinsei_Date;
	}

	public void setSinsei_Date(String sinsei_Date) {
		this.sinsei_Date = sinsei_Date;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public String getUsedday_id() {
		return usedday_id;
	}

	public void setUsedday_id(String usedday_id) {
		this.usedday_id = usedday_id;
	}

	public String getPjcode_id() {
		return pjcode_id;
	}

	public void setPjcode_id(String pjcode_id) {
		this.pjcode_id = pjcode_id;
	}

	public String getkanjoname() {
		return kanjoname;
	}

	public void setkanjoname(String kanjoname) {
		this.kanjoname = kanjoname;
	}

	public String getKanjostr() {
		return kanjostr;
	}

	public void setKanjostr(String kanjostr) {
		this.kanjostr = kanjostr;
	}

	public String getStrcombo() {
		return strcombo;
	}

	public void setStrcombo(String strcombo) {
		this.strcombo = strcombo;
	}

	public List<ListDto> getStrcomboItems() {
		return strcomboItems;
	}

	public void setStrcomboItems(List<ListDto> strcomboItems) {
		this.strcomboItems = strcomboItems;
	}

	public Integer getKingaku_id() {
		return kingaku_id;
	}

	public void setKingaku_id(Integer kingaku_id) {
		this.kingaku_id = kingaku_id;
	}

	public String getBikou_id() {
		return bikou_id;
	}

	public void setBikou_id(String bikou_id) {
		this.bikou_id = bikou_id;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public MessageDto[] getmessageItems() {
		return messageItems;
	}

	public void setmessageItems(MessageDto[] messageItems) {
		this.messageItems = messageItems;
	}

	public String gettransday_id() {
		return transday_id;
	}

	public void settransday_id(String transday_id) {
		this.transday_id = transday_id;
	}

	public String getsender_id() {
		return sender_id;
	}

	public void setsender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	/** 勘定科目名リスト セッタ/ゲッタ */
	public  List<Map<String, String>> getkanjonameItems() {
		return kanjonameItems;
	}
	public void setkanjonameItems(List<Map<String,String>> kanjonameItems) {
		this.kanjonameItems = kanjonameItems;
	}


	public Class<?> initialize() {

		// 未ログインユーザーをはじく処理
		if(sessionDto.EmployeeID == null){
			return NonloginPage.class;
		}
		employee_id   = this.sessionDto.EmployeeID.toString();
		employee_name = this.sessionDto.EmployeeName;
		employee_mail = this.sessionDto.EmployeeEmail;

		//当日日付を取得とする
		Calendar now = Calendar.getInstance();
		thisyear  = new Integer(now.get(Calendar.YEAR)).toString();          //年を取得だ
		thismonth = new Integer(now.get(Calendar.MONTH) + 1);                //月を取得(※1)
		thisday   = new Integer(now.get(Calendar.DATE)).toString();;         //日を取得
		today = thisyear + "/" + thismonth + "/" + thisday;                  //当日日付を保持

//		/** Login社員情報取得 */
//		Map<String, String> reqParam = new HashMap<String, String>();
//		Map<String, String> resParam;
//
//		try {
//			reqParam.put("userID", "test");       /** Login社員ID */
//			reqParam.put("userPas", "test");      /** Login社員PASS */
//
//			resParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_USERINFO,reqParam);
//
//			employee_id   = resParam.get("employeeIDStr");
////			employee_id   = thismonth.toString();  /** 項目取得チェック用 最後に消します*/
//			employee_name = resParam.get("employeeNameStr");
//			employee_mail = resParam.get("employeeEmailStr");
//
//
//		} catch (Exception e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}

		//ステータスリスト取得
		Map<String, String> reqstatus = new HashMap<String, String>();
		Map<String, String> resstatus = new HashMap<String, String>();

		try {
			reqstatus.put("key1","comdlist");       /** 小口請求用ステータス */
			reqstatus.put("combName","sibko");      /** 小口請求用ステータス 有効 */

			resstatus = this.getServiceValue(CommonConstants.SERVICE_TYPE_COMBLIST,reqstatus);

			comLabel = resstatus.get("comLabel");
			comKey   = resstatus.get("comKey");

			String[] splitcomLabel = comLabel.split(",");
			String[] splitcomKey   = comKey.split(",");

			int a = splitcomLabel.length;

			strnamelist = new HashMap<String,String>();
			for(int i = 0; i < a; i++){

				strnamelist.put(splitcomKey[i],splitcomLabel[i]);
			}
		}catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
		}


		//新規登録、詳細照会をハンドリングする
		if(selectkogutiID == -1){
			//新規登録時
			//申請日は当日日付を固定値とする
			sinsei_Date = today;

			//ステータス名取得 新規登録の場合は請求ステータス固定
			state_id = strnamelist.get("1");

		} else{
			//詳細照会時
			//selectkogutiID = 1;
			TKoguti ety_Koguti = KogutiDao.selectById(selectkogutiID);

			sinsei_Date = ety_Koguti.getSeikyuDate();
			local_state = ety_Koguti.getState().toString();
			usedday_id  = ety_Koguti.getSiyouDate();
			pjcode_id   = ety_Koguti.getPjCode();
			kingaku_id  = ety_Koguti.getKingaku();
			bikou_id    = ety_Koguti.getBiko();

			//勘定項目名取得
			kanjo_code = new Integer(0);
			kanjo_code = ety_Koguti.getKanjoId();
			MKanjo       ety_Kogutijyo = KanjoDao.selectById(kanjo_code);
			kanjoname   =  ety_Kogutijyo.getName();
			kanjoname   =  kanjo_code.toString();
			//ステータス名取得
			state_id = strnamelist.get(local_state);

			//メッセージ取得
//			MessageDto[] ety_Message = MessageDao.GetMessage(0,selectkogutiID);
			messageItems = MessageDao.GetMessage(0,selectkogutiID);

//			//社員名取得
//			Map<String, String> reqParam = new HashMap<String, String>();
//			Map<String, String> resParam = new HashMap<String, String>();




//		length  = messageItems.length;
//		message = messageItems[0].getmessage();
//		message = ety_Message[0].getmessage();






//		messageItems[0].addid   = ety_Message[0].getaddid();
//		messageItems[0].message = ety_Message[0].getmessage();

	}


		return null;
	}

	public Class<?> prerender() {
		//勘定科目選択プルダウン取得
		MKanjo[]  dao_Kanjo = KanjoDao.selectAll();

		int length = dao_Kanjo.length;
		kanjonameItems = new ArrayList<Map<String,String>>();

		for(int i = 0; i < length; i++){
			Map<String,String> mapKanjo = new HashMap<String,String>();
			mapKanjo.put("label",dao_Kanjo[i].getName());
			mapKanjo.put("value",dao_Kanjo[i].getKanjo_Id().toString());
			kanjonameItems.add(mapKanjo);

		//メッセージ取得
		messageItems = MessageDao.GetMessage(0,selectkogutiID);
			}
		return null;
	}


	public Class<?> doUpdate() {
		//個口情報更新処理
		TKoguti ety_Koguti;

		if(flg){
			if(selectkogutiID == -1){

//				//新規小口請求作成
				ety_Koguti = new TKoguti();
//
//				ety_Koguti.setSeikyuId(null);
				ety_Koguti.setPjCode(pjcode_id);
				ety_Koguti.setState(1);
				ety_Koguti.setKanjoId(Integer.parseInt(kanjoname));
				ety_Koguti.setKingaku(kingaku_id);
				ety_Koguti.setSiyouDate(usedday_id);
				ety_Koguti.setSeikyuDate(today);
				ety_Koguti.setSeikyuEmpid(Integer.parseInt(employee_id));
//				ety_Koguti.setTorikesiDate(null);
//				ety_Koguti.setTorikesiEmpid(null);
//				ety_Koguti.setKyokaDate(null);
//				ety_Koguti.setKyokaEmpid(null);
//				ety_Koguti.setFukyokaDate(kanjoName);
//				ety_Koguti.setFukyokaEmpid(kanjoOrderSEQ);
//				ety_Koguti.setSeisanDate(kaihaiselect);
//				ety_Koguti.setSeisanEmpid(null);
//				ety_Koguti.setSimeDate(null);
//				ety_Koguti.setSimeEmpid(null);
//				ety_Koguti.setResitodata(null);
				ety_Koguti.setBiko(bikou_id);
				ety_Koguti.setKaihai(0);
				ety_Koguti.setAdddate(today);
				ety_Koguti.setAddid(Integer.parseInt(employee_id));
//				ety_Koguti.setUpddate(null);
//				ety_Koguti.setUpdid(null);
//
				KogutiDao.insert(ety_Koguti);

				UpdateMes = "新規追加しました。";

			}else{

				//既存個口情報更新処理
				ety_Koguti = KogutiDao.selectById(selectkogutiID);
//				int i = Integer.parseInt(strnamelist);

//				ety_Koguti.setSeikyuId(null);
				ety_Koguti.setPjCode(pjcode_id);
//				ety_Koguti.setState(null);
				ety_Koguti.setKanjoId(Integer.parseInt(kanjoname));
				ety_Koguti.setKingaku(kingaku_id);
				ety_Koguti.setSiyouDate(usedday_id);
//				ety_Koguti.setSeikyuDate(null);
//				ety_Koguti.setSeikyuEmpid(null);
//				ety_Koguti.setTorikesiDate(null);
//				ety_Koguti.setTorikesiEmpid(null);
//				ety_Koguti.setKyokaDate(null);
//				ety_Koguti.setKyokaEmpid(null);
//				ety_Koguti.setFukyokaDate(kanjoName);
//				ety_Koguti.setFukyokaEmpid(kanjoOrderSEQ);
//				ety_Koguti.setSeisanDate(kaihaiselect);
//				ety_Koguti.setSeisanEmpid(null);
//				ety_Koguti.setSimeDate(null);
//				ety_Koguti.setSimeEmpid(null);
//				ety_Koguti.setResitodata(null);
				ety_Koguti.setBiko(bikou_id);
//				ety_Koguti.setKaihai(null);
//				ety_Koguti.setAdddate(null);
//				ety_Koguti.setAddid(null);
				ety_Koguti.setUpddate(today);
				ety_Koguti.setUpdid(Integer.parseInt(employee_id));

				KogutiDao.update(ety_Koguti);

				UpdateMes = "更新しました。";
			}
			return null;
		}


		return null;
	}





	public Class<?> doUpdateCancel() {

		if(selectmonthID != null ){

		}

		return  Ko001g1Page.class;
	}

	public Class<?> doUpdatemessage() {

		//新規やり取りメッセージ追加処理
		TMessage ety_Message =  new TMessage();

//		ety_Message.setMessageId(null);
		ety_Message.setMessage(message_id);
		ety_Message.setInputEmpid(Integer.parseInt(employee_id));
		ety_Message.setSeikyuId(selectkogutiID);
		ety_Message.setKaihai(0);
		ety_Message.setAdddate(today);
		ety_Message.setAddid(Integer.parseInt(employee_id));
//		ety_Message.setUpddate(null);
//		ety_Message.setUpdid(null);

//
		MessageDao.insert(ety_Message);

		UpdateMes = "新規追加しました。";


		return null;
	}

	public Class<?> messageinfo() {
		return null;
	}

	public Integer getSeikyuID() {
		return seikyuID;
	}

	public void setSeikyuID(Integer seikyuID) {
		this.seikyuID = seikyuID;
	}

	public String getUpdateMes() {
		return UpdateMes;
	}

	public void setUpdateMes(String updateMes) {
		UpdateMes = updateMes;
	}



}