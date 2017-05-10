package ssm.web.sendmail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import ssm.common.CommonConstants;
import ssm.common.CommonUtil;
import ssm.dao.TSendtimeDao;
import ssm.dto.ListDto;
import ssm.dto.SendMailDataDto;
import ssm.pagebace.PageBaceClass;

public class SendlistPage extends PageBaceClass {

	/** 改廃コンボ　 */
	@PageScope
	@Required
	private Integer kaihai;
	@PageScope
	private List<ListDto> kaihaiItems;

	/** かなコンボ　*/
	private String kanaGyo;
	private List<ListDto> kanaGyoItems;

	/** 送信設定一覧　*/
	@PageScope
	private int sendIndex;
	@PageScope
	private List<SendMailDataDto> sendItems;

	private String sendType;
	private String sendTime;
	private String sendTitel;
	private String sendMailaddress;
	private String sendFlg;

	public TSendtimeDao timDao;

	@SubapplicationScope
	public Integer selectID;

	/** 改廃index　セッター・ゲッター*/
	public Integer getKaihai() {
		return kaihai;
	}
	public void setKaihai(Integer kaihai) {
		this.kaihai = kaihai;
	}

	/**　改廃リスト　セッター・ゲッター*/
	public List<ListDto> getKaihaiItems() {
		return kaihaiItems;
	}
	public void setKaihaiItems(List<ListDto> kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}

	public String getKanaGyo() {
		return kanaGyo;
	}
	public void setKanaGyo(String kanaGyo) {
		this.kanaGyo = kanaGyo;
	}

	public List<ListDto> getKanaGyoItems() {
		return kanaGyoItems;
	}
	public void setKanaGyoItems(List<ListDto> kanaGyoItems) {
		this.kanaGyoItems = kanaGyoItems;
	}

	/** 送信IDIndex セッター・ゲッター　*/
	public int getSendIndex() {
		return sendIndex;
	}
	public void setSendIndex(int sendIndex) {
		this.sendIndex = sendIndex;
	}

	/**　送信アイテム　セッター・ゲッター　*/
	public List<SendMailDataDto> getSendItems() {
		return sendItems;
	}
	public void setSendItems(List<SendMailDataDto> sendItems) {
		this.sendItems = sendItems;
	}

	/**　送信区分　セッター・ゲッター　*/
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	/**　送信時間　セッター・ゲッター　*/
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	/**　送信タイトル　セッター・ゲッター　*/
	public void setSendTitel(String sendTitel){
		this.sendTitel = sendTitel;
	}
	public String getSendTitel() {
		return this.sendTitel;
	}

	/**　送信メールアドレス　セッター・ゲッター　*/
	public String getSendMailaddress() {
		return sendMailaddress;
	}
	public void setSendMailaddress(String sendMailaddress) {
		this.sendMailaddress = sendMailaddress;
	}

	/**　送信済み区分　セッター・ゲッター　*/
	public String getSendFlg() {
		return sendFlg;
	}
	public void setSendFlg(String sendFlg) {
		this.sendFlg = sendFlg;
	}

	public Class<?> doSrech() {

		//送信区分　再設定
		sendItems = timDao.selectSendTime(kaihai);

		return null;
	}

	public Class<?> doSrechCancel() {

		kaihai = 0;
		kanaGyo = "0";

		//送信メールリスト取得
		sendItems = timDao.selectSendTime(0);

		return null;
	}

	public Class<?> doSendSchAdd() {
		selectID = -1;

		return SendaddPage.class;
	}

	public Class<?> doReturn() {
		return null;
	}

	public Class<?> doEdit() {

		selectID = sendItems.get(sendIndex).sendID;

		return SendaddPage.class;
	}

	public Class<?> initialize() {

		try {

			Map<String, String> reqParam = new HashMap<String,String>();

			//改廃コンボ取得
			reqParam.put("combName", CommonConstants.COMB_updsendtype);

			kaihaiItems = CommonUtil.getServiceToCombData(this.getServiceValue(CommonConstants.SERVICE_TYPE_COMBLIST, reqParam));

			//かなコンボ取得
			reqParam = new HashMap<String,String>();

			reqParam.put("combName", CommonConstants.COMB_kana);

			kanaGyoItems = CommonUtil.getServiceToCombData(this.getServiceValue(CommonConstants.SERVICE_TYPE_COMBLIST, reqParam));

			//送信メールリスト取得
			sendItems = timDao.selectSendTime(0);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return null;

	}

	public Class<?> prerender() {

		//ログインID設定
		this.setLoginInfo();

		return null;
	}

}
