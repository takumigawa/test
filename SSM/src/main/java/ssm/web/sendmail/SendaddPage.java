package ssm.web.sendmail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Email;
import org.seasar.teeda.extension.annotation.validator.Required;


import ssm.common.CommonConstants;
import ssm.common.CommonUtil;
import ssm.dao.TSendmailDao;
import ssm.dao.TSendmailaddressDao;
import ssm.dao.TSendtimeDao;
import ssm.dto.ListDto;
import ssm.entity.TSendmail;
import ssm.entity.TSendmailaddress;
import ssm.entity.TSendtime;
import ssm.exception.AnLoginException;
import ssm.exception.NoMastaMenteAuthException;
import ssm.pagebace.PageBaceClass;
import ssm.web.error.AbendPage;
import ssm.web.error.NonauthorityPage;
import ssm.web.error.NonloginPage;

public class SendaddPage extends PageBaceClass {

	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String radsendType;

	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String mailTitelStr;

	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Email(target="doFinishUpdate", messageId = "err.NoMatchEMailMes")
	private String mailAddressStr;
	private String yearStr;
	private String monthStr;
	private String dayStr;
	private String onedayStr;

	@Required
	@PageScope
	private String hour;
	@Required
	@PageScope
	private List<ListDto> hourItems;

	@Required
	@PageScope
	private String minute;
	@Required
	@PageScope
	private List<ListDto> minuteItems;

	private String sunday;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;

	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String mailToAddressStr;

	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String mailDetailStr;

	@SubapplicationScope
	public Integer selectID;

	private String initType;

	/** 有効/無効値 */
	@PageScope
	@Required
	private Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	private List<ListDto> kaihaiItems;

	/**　送信区分　セッター・ゲッター　*/
	public String getRadsendType() {
		return radsendType;
	}
	public void setRadsendType(String radsendType) {
		this.radsendType = radsendType;
	}

	/**　メールタイトル　セッター・ゲッター　*/
	public String getMailTitelStr() {
		return mailTitelStr;
	}
	public void setMailTitelStr(String mailTitelStr) {
		this.mailTitelStr = mailTitelStr;
	}

	/**　送信元メールアドレス　セッター・ゲッター　*/
	public String getMailAddressStr() {
		return mailAddressStr;
	}
	public void setMailAddressStr(String mailAddressStr) {
		this.mailAddressStr = mailAddressStr;
	}

	/**　送信年　セッター・ゲッター　*/
	public String getYearStr() {
		return yearStr;
	}
	public void setYearStr(String yearStr) {
		this.yearStr = yearStr;
	}

	/**　送信月　セッター・ゲッター　*/
	public String getMonthStr() {
		return monthStr;
	}
	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}

	/**　送信日　セッター・ゲッター　*/
	public String getDayStr() {
		return dayStr;
	}
	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}

	/**　送信日　セッター・ゲッター　*/
	public String getOnedayStr() {
		return onedayStr;
	}
	public void setOnedayStr(String onedayStr) {
		this.onedayStr = onedayStr;
	}

	/**　送信時間Index　セッター・ゲッター　*/
	public String getHour() {
		return this.hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**　送信時間　セッター・ゲッター　*/
	public List<ListDto> getHourItems() {
		return hourItems;
	}
	public void setHourItems(List<ListDto> hourItems) {
		this.hourItems = hourItems;
	}

	/**　送信分Index　セッター・ゲッター　*/
	public String getMinute() {
		return this.minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}

	/**　送信分　セッター・ゲッター　*/
	public List<ListDto> getMinuteItems() {
		return this.minuteItems;
	}
	public void setMinuteItems(List<ListDto> minuteItems) {
		this.minuteItems = minuteItems;
	}

	/**　日曜　セッター・ゲッター　*/
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	/**　月曜　セッター・ゲッター　*/
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}

	/**　火曜　セッター・ゲッター　*/
	public String getTuesday() {
		return tuesday;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	/**　水曜　セッター・ゲッター　*/
	public String getWednesday() {
		return wednesday;
	}
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	/**　木曜　セッター・ゲッター　*/
	public String getThursday() {
		return thursday;
	}
	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	/** 金曜　セッター・ゲッター　*/
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}

	/**　土曜　セッター・ゲッター　*/
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	/**　送信先メールアドレス	セッター・ゲッター　*/
	public String getMailToAddressStr() {
		return mailToAddressStr;
	}
	public void setMailToAddressStr(String mailToAddressStr) {
		this.mailToAddressStr = mailToAddressStr;
	}

	/**　送信メール内容　セッター・ゲッター　*/
	public String getMailDetailStr() {
		return mailDetailStr;
	}
	public void setMailDetailStr(String mailDetailStr) {
		this.mailDetailStr = mailDetailStr;
	}

	/**　初期表示タイプ　セッター・ゲッター　*/
	public String getInitType() {
		return this.initType;
	}
	public void setInitType(String initType) {
		this.initType = initType;
	}

	/** 改廃index　セッター・ゲッター */
	public void setKaihai(Integer kaihai){
		this.kaihai = kaihai;
	}
	public Integer getKaihai(){
		return this.kaihai;
	}

	/** 改廃　セッター・ゲッター */
	public void setKaihaiItems(List<ListDto> kaihaiItems){
		this.kaihaiItems = kaihaiItems;
	}
	public List<ListDto> getKaihaiItems(){
		return this.kaihaiItems;
	}

	public TSendmailaddressDao smaDao;

	public TSendmailDao mailDao;

	public TSendtimeDao timeDao;

	/**
	 * @Update
	 *
	 */
	public Class<?> doFinishUpdate() {

		//
		TSendmail ety_mail;
		//
		TSendmailaddress ety_sma;
		//
		TSendtime ety_time;

		Integer targetID;

		if(selectID.equals(-1)){

			//メール情報設定
			ety_mail = new TSendmail();

			targetID = mailDao.getNextMailID();

			//メールID
			ety_mail.setId(targetID);
			//メール送信元アドエス
			ety_mail.setFrommailaddress(mailAddressStr);
			//メールタイトル
			ety_mail.setMailtitel(mailTitelStr);
			//メール本文
			ety_mail.setMaildetail(mailDetailStr);
			//メール作成者ID
			ety_mail.setAddid(this.sessionDto.EmployeeID);
			//メール作成日時
			ety_mail.setAdddate(CommonUtil.getNowUpdDate());

			mailDao.insert(ety_mail);

			//メール送信時間設定
			ety_time = new TSendtime();

			//メールID
			ety_time.setSendmailid(targetID);
			//送信タイプ
			ety_time.setSendtype(radsendType);

			//一回のみ
			if(radsendType.equals("0")){

				//送信年
				ety_time.setYear(yearStr);
				//送信月
				ety_time.setMonth(monthStr);
				//送信日
				ety_time.setDay(dayStr);

			}

			//週
			if(radsendType.equals("2")){

				//月曜日
				if ("true".equals(monday)) {
					ety_time.setWeekmonday("1");
				}

				//火曜日
				if ("true".equals(tuesday)) {
					ety_time.setWeektuesday("1");
				}

				//水曜日
				if ("true".equals(wednesday)) {
					ety_time.setWeekwednesday("1");
				}

				//木曜日
				if ("true".equals(thursday)) {
					ety_time.setWeekthursday("1");
				}

				//金曜日
				if ("true".equals(friday)) {
					ety_time.setWeekfriday("1");
				}

				//土曜日
				if ("true".equals(saturday)) {
					ety_time.setWeeksaturday("1");
				}

				//日曜日
				if ("true".equals(sunday)) {
					ety_time.setWeeksunday("1");
				}

			}

			//月一回
			if(radsendType.equals("3")){
				//送信日
				ety_time.setDay(onedayStr);
			}

			//送信時間
			ety_time.setHour(hour);
			//送信分
			ety_time.setMinute(minute);

			//追加ID
			ety_time.setAddid(this.sessionDto.EmployeeID);
			//追加日時
			ety_time.setAdddate(CommonUtil.getNowUpdDate());
			//更新フラグ
			ety_time.setUpdtype(0);

			timeDao.insert(ety_time);

		} else {

			ety_mail = mailDao.selectById(selectID);

			targetID = ety_mail.getId();

			//メール送信元アドエス
			ety_mail.setFrommailaddress(mailAddressStr);
			//メールタイトル
			ety_mail.setMailtitel(mailTitelStr);
			//メール本文
			ety_mail.setMaildetail(mailDetailStr);
			//メール作成者ID
			ety_mail.setUpdid(this.sessionDto.EmployeeID);
			//メール作成日時
			ety_mail.setUpddate(CommonUtil.getNowUpdDate());
			//送信区分

			mailDao.update(ety_mail);

			//メール送信時間設定
			ety_time = timeDao.selectByMailID(targetID);

			//初期化
			//送信年
			ety_time.setYear(null);
			//送信月
			ety_time.setMonth(null);
			//送信日
			ety_time.setDay(null);
			//曜日
			ety_time.setWeekmonday(null);
			ety_time.setWeektuesday(null);
			ety_time.setWeekwednesday(null);
			ety_time.setWeekthursday(null);
			ety_time.setWeekfriday(null);
			ety_time.setWeeksaturday(null);
			ety_time.setWeeksunday(null);

			//送信タイプ
			ety_time.setSendtype(radsendType);

			//一回のみ
			if(radsendType.equals("0")){

				//送信年
				ety_time.setYear(yearStr);
				//送信月
				ety_time.setMonth(monthStr);
				//送信日
				ety_time.setDay(dayStr);

			}

			//週
			if(radsendType.equals("2")){

				//月曜日
				if ("true".equals(monday)) {
					ety_time.setWeekmonday("1");
				}
				//火曜日
				if ("true".equals(tuesday)) {
					ety_time.setWeektuesday("1");
				}
				//水曜日
				if ("true".equals(wednesday)) {
					ety_time.setWeekwednesday("1");
				}
				//木曜日
				if ("true".equals(thursday)) {
					ety_time.setWeekthursday("1");
				}
				//金曜日
				if ("true".equals(friday)) {
					ety_time.setWeekfriday("1");
				}
				//土曜日
				if ("true".equals(saturday)) {
					ety_time.setWeeksaturday("1");
				}
				//日曜日
				if ("true".equals(sunday)) {
					ety_time.setWeeksunday("1");
				}

			}

			//月一回
			if(radsendType.equals("3")){
				//送信日
				ety_time.setDay(onedayStr);
			}

			//送信時間
			ety_time.setHour(hour);
			//送信分
			ety_time.setMinute(minute);

			//追加ID
			ety_time.setUpdid(this.sessionDto.EmployeeID);
			//追加日時
			ety_time.setUpddate(CommonUtil.getNowUpdDate());
			//更新フラグ
			ety_time.setUpdtype(this.kaihai);

			timeDao.update(ety_time);

			smaDao.deleteBySendMailId(targetID);

		}

		//送信先アドレス
		String[] spList = mailToAddressStr.split("\r");

		for(int i = 0;i < spList.length;i++){

			ety_sma = new TSendmailaddress();

			//メールID
			ety_sma.setSendmailid(targetID);
			//メール送信先アドレス
			ety_sma.setTomailaddress(spList[i]);
			//追加者ID
			ety_sma.setAddid(this.sessionDto.EmployeeID);
			//追加時間
			ety_sma.setAdddate(CommonUtil.getNowUpdDate());

			smaDao.insert(ety_sma);

		}

		return SendlistPage.class;

	}

	/**
	 * @return
	 */
	public Class<?> doFinishReturn() {

		return SendlistPage.class;

	}

	public Class<?> initialize() {


		try{

			//セッション情報チェック
			//this.chkSession();

			//マスタメンテナンス権限チェック
			//this.chkMastermainte();

			//時間リスト設定
			List<ListDto> HourList = new ArrayList<ListDto>();

			ListDto item;

			Integer i;

			for (i = 0;i < 24;i++) {

				item = new ListDto();

				item.label = String.format("%1$02d",i) + "時";
				item.value  = String.format("%1$02d",i);

				HourList.add(item);
			}

			hourItems = HourList;

			//分リスト設定
			List<ListDto> MinuteList = new ArrayList<ListDto>();

			for (i = 0;i < 6;i++) {

				item = new ListDto();

				item.label = String.format("%1$02d",i * 10) + "分";
				item.value  = String.format("%1$02d",i * 10);

				MinuteList.add(item);
			}

			minuteItems = MinuteList;

//		} catch (AnLoginException e) {
//			//未ログインエラー
//			return NonloginPage.class;
//
//		} catch (NoMastaMenteAuthException e) {
//			//未マスタメンテ権限エラー
//			return NonauthorityPage.class;
//
		}catch(Exception e){
			return AbendPage.class;
		}

		return null;
	}

	public Class<?> prerender() {

		//
		this.setLoginInfo();

		Map<String, String> reqParam = new HashMap<String,String>();

		//改廃コンボ取得
		reqParam.put("combName", CommonConstants.COMB_updsendtype);

		try {
			kaihaiItems = CommonUtil.getServiceToCombData(this.getServiceValue(CommonConstants.SERVICE_TYPE_COMBLIST, reqParam));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		radsendType = "0";

		if (!selectID.equals(-1)) {

			//
			TSendmail ety_mail = mailDao.selectById(selectID);
			//
			TSendtime ety_time = timeDao.selectByMailID(ety_mail.getId());

			this.mailTitelStr = ety_mail.getMailtitel();
			this.mailDetailStr = ety_mail.getMaildetail();
			this.mailAddressStr = ety_mail.getFrommailaddress();

			//送信区分
			this.radsendType = ety_time.getSendtype();
			//送信フラグ
			this.kaihai = ety_time.getUpdtype();

			//年
			this.yearStr = ety_time.getYear();
			//月
			this.monthStr = ety_time.getMonth();
			//日付
			this.dayStr = ety_time.getDay();
			//時
			this.hour = ety_time.getHour();
			//分
			this.minute = ety_time.getMinute();
			//日付（一度のみ）
			this.onedayStr = ety_time.getDay();

			//日曜日
			if ( ety_time.getWeeksunday() != null && ety_time.getWeeksunday().equals("1")) {
				this.sunday = "true";
			} else {
				this.sunday = "false";
			}
			//月曜日
			if ( ety_time.getWeekmonday() != null && ety_time.getWeekmonday().equals("1")) {
				this.monday = "true";
			} else {
				this.monday = "false";
			}
			//火曜日
			if ( ety_time.getWeekthursday() != null && ety_time.getWeekthursday().equals("1")) {
				this.thursday = "true";
			} else {
				this.thursday = "false";
			}
			//水曜日]
			if ( ety_time.getWeekwednesday() != null && ety_time.getWeekwednesday().equals("1")) {
				this.wednesday = "true";
			} else {
				this.wednesday = "false";
			}
			//木曜日
			if ( ety_time.getWeektuesday() != null && ety_time.getWeektuesday().equals("1")) {
				this.tuesday = "true";
			} else {
				this.tuesday = "false";
			}
			//金曜日
			if ( ety_time.getWeekfriday() != null && ety_time.getWeekfriday().equals("1")) {
				this.friday = "true";
			} else {
				this.friday = "false";
			}
			//土曜日
			if ( ety_time.getWeeksaturday() != null && ety_time.getWeeksaturday().equals("1")) {
				this.saturday = "true";
			} else {
				this.saturday = "false";
			}

			List<TSendmailaddress> addList = smaDao.selectBySendMailId(ety_mail.getId());

			this.mailToAddressStr = "";

			for (TSendmailaddress Item : addList) {
				if (!this.mailToAddressStr.equals("")) {
					this.mailToAddressStr += "\r";
				}
				this.mailToAddressStr += Item.getTomailaddress();
			}

			initType = ety_time.getSendtype();

		} else {
			initType = "0";
		}

		this.sessionDto.EmployeeID = 2;

		return null;
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
