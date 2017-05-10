package ko.web.ko002;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import ko.common.CommonConstants;
import ko.dao.TKogutiDao;
import ko.dto.KogutiListDto;
import ko.dto.ListDto;
import ko.entity.TKoguti;
import ko.pagebace.PageBaceClass;

public class Ko002g1Page extends PageBaceClass {

	// 当日取得用
	public String thisyear;
	public Integer thismonth;
	public String thisday;
	// ステータス格納用
	public String comLabel;
	public String comKey;
	/** 検索ステータスアイテム */
	public List<Map<String, String>> strlistItems;

	@PageScope
	public Map<String, String> strnamelist;
	/** 小口請求ＩＤ */
	public Integer selectkogutiID;
	/** 小口請求Index */
	public int kogutiIndex;

	private String employee_id;
	private String employee_name;
	private String employee_mail;
	private String yearlist;
	private ListDto[] yearlistItems;
	private String monthlist;
	private List<Map<String, Integer>> monthlistItems;
	private String kogutistr;
	private String[] strcombo;
	private String strlist;
	private String strcom;

	@SubapplicationScope
	private KogutiListDto[] kogutiItems;
	private boolean update;
	private int seikyuid;
	private String seikyudate;
	private String pjcode;
	private String state;
	private String seikyuemp;
	private String kanjoid;
	private String kingaku;
	private String upddate;
	private String biko;
	private String strco;

	public TKogutiDao KogutiDao;

	@SubapplicationScope
	public int seikyu;

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

	public String getYearlist() {
		return yearlist;
	}

	public void setYearlist(String yearlist) {
		this.yearlist = yearlist;
	}

	public ListDto[] getYearlistItems() {
		return yearlistItems;
	}

	public void setYearlistItems(ListDto[] yearlistItems) {
		this.yearlistItems = yearlistItems;
	}

	public String getMonthlist() {
		return monthlist;
	}

	public void setMonthlist(String monthlist) {
		this.monthlist = monthlist;
	}

	public List<Map<String, Integer>> getMonthlistItems() {
		return monthlistItems;
	}

	public void setMonthlistItems(List<Map<String, Integer>> monthlistItems) {
		this.monthlistItems = monthlistItems;
	}

	public String getKogutistr() {
		return kogutistr;
	}

	public void setKogutistr(String kogutistr) {
		this.kogutistr = kogutistr;
	}

	public String[] getStrcombo() {
		return strcombo;
	}

	public void setStrcombo(String[] strcombo) {
		this.strcombo = strcombo;
	}

	public String getStrlist() {
		return strlist;
	}

	public void setStrlist(String strlist) {
		this.strlist = strlist;
	}

	public String getStrcom() {
		return strcom;
	}

	public void setStrcom(String strcom) {
		this.strcom = strcom;
	}

	public int getKogutiIndex() {
		return kogutiIndex;
	}

	public void setKogutiIndex(int kogutiIndex) {
		this.kogutiIndex = kogutiIndex;
	}

	public KogutiListDto[] getKogutiItems() {
		return kogutiItems;
	}

	public void setKogutiItems(KogutiListDto[] kogutiItems) {
		this.kogutiItems = kogutiItems;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public String getSeikyudate() {
		return seikyudate;
	}

	public void setSeikyudate(String seikyudate) {
		this.seikyudate = seikyudate;
	}

	public String getPjcode() {
		return pjcode;
	}

	public void setPjcode(String pjcode) {
		this.pjcode = pjcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSeikyuemp() {
		return seikyuemp;
	}

	public void setSeikyuemp(String seikyuemp) {
		this.seikyuemp = seikyuemp;
	}

	public String getKanjoid() {
		return kanjoid;
	}

	public void setKanjoid(String kanjoid) {
		this.kanjoid = kanjoid;
	}

	public String getKingaku() {
		return kingaku;
	}

	public void setKingaku(String kingaku) {
		this.kingaku = kingaku;
	}

	public String getUpddate() {
		return upddate;
	}

	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

	public String getStrco() {
		return strco;
	}

	public void setStrco(String strco) {
		this.strco = strco;
	}

	public Class<?> doDetails() {
		seikyu = kogutiItems[kogutiIndex].seikyuId;
		return Ko002g2Page.class;
	}

	public Class<?> doSrech() {
		return null;
	}

	public Class<?> doSrechCancel() {
		return null;
	}

	public Class<?> doFinishReturn() {
		return null;
	}

//	public Class<?> doUpdate() {
//		for (KogutiListDto list : kogutiItems) {
//			if (list.update) {
//				TKoguti koguti = new TKoguti();
//				koguti.setSeikyuId(list.seikyuId);
//				koguti.setState(Integer.valueOf(list.state));
//				KogutiDao.updateTKogutiUnlessNull(koguti);
//			}
//
//		}
//		return null;
//	}

	public Class<?> initialize() {
		// 初期表示として当月分の小口請求全件を表示する
		// 当日日付取得
		Calendar now = Calendar.getInstance();

		thisyear = new Integer(now.get(Calendar.YEAR)).toString(); // 年を取得
		thismonth = new Integer(now.get(Calendar.MONTH) + 1); // 月を取得(※1)
		thisday = new Integer(now.get(Calendar.DATE)).toString();
		; // 日を取得

		/** ステータス情報取得 サービスアダプタ連携 */
		Map<String, String> reqstatus = new HashMap<String, String>();
		Map<String, String> resstatus = new HashMap<String, String>();

		try {
			reqstatus.put("key1", "comblist");
			/** 小口請求用ステータス */
			reqstatus.put("combName", "sibko");
			/** 小口請求用ステータス 有効 */

			resstatus = this.getServiceValue(
					CommonConstants.SERVICE_TYPE_COMBLIST, reqstatus);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		/** ステータス情報取得 格納 */
		comLabel = resstatus.get("comLabel");
		comKey = resstatus.get("comKey");
		String[] splitcomLabel = comLabel.split(",");
		String[] splitcomKey = comKey.split(",");

		int a = splitcomLabel.length;

		strnamelist = new HashMap<String, String>();
		for (int i = 0; i < a; i++) {

			strnamelist.put(splitcomKey[i], splitcomLabel[i]);
		}
		strcombo = new String[2];
		strcombo[0] = "1";
		strcombo[1] = "2";

		strlist = new String();
		strlist = "0";
		System.out.println(strcombo);

		// /** 初期表示 小口リスト出力 当月分のステータスが を初期表示として出力する*/
		kogutiItems = KogutiDao.selectGetKogutiList(0);
		a = kogutiItems.length;
		for (int i = 0; i < a; i++) {
			kogutiItems[i].state = strnamelist.get(kogutiItems[i].state
					.toString());
		}
		return null;
	}

	public Class<?> prerender() {

		/** Login社員情報取得 */
		Map<String, String> reqParam = new HashMap<String, String>();
		Map<String, String> resParam;

		try {
			reqParam.put("userID", "test");
			/** Login社員ID */
			reqParam.put("userPas", "test");
			/** Login社員PASS */

			resParam = this.getServiceValue(
					CommonConstants.SERVICE_TYPE_USERINFO, reqParam);

			employee_id = resParam.get("employeeIDStr");
			// employee_id = thismonth.toString(); /** 項目取得チェック用 最後に消します*/
			employee_name = resParam.get("employeeNameStr");
			employee_mail = resParam.get("employeeEmailStr");

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		/** 検索リスト年出力 */
		setYearlistItems(KogutiDao.selectyearList());

		/** 検索リスト月出力 */
		monthlistItems = new ArrayList<Map<String, Integer>>();
		for (int i = 1; i < 13; i++) {
			Map<String, Integer> monthlistmap = new HashMap<String, Integer>();
			monthlistmap.put("label", i);
			monthlistmap.put("value", i);
			monthlistItems.add(monthlistmap);
		}

		/** 検索リストステータス出力 */
		Map<String, String> reqstatus = new HashMap<String, String>();
		Map<String, String> resstatus = new HashMap<String, String>();

		try {
			reqstatus.put("key1", "comdlist");
			/** 小口請求用ステータス */
			reqstatus.put("combName", "sibko");
			/** 小口請求用ステータス 有効 */

			resstatus = this.getServiceValue(
					CommonConstants.SERVICE_TYPE_COMBLIST, reqstatus);

			comLabel = resstatus.get("comLabel");
			comKey = resstatus.get("comKey");

			String[] splitcomLabel = comLabel.split(",");
			String[] splitcomKey = comKey.split(",");

			int a = splitcomLabel.length;

			strlistItems = new ArrayList<Map<String, String>>();
			for (int i = 0; i < a; i++) {
				Map<String, String> strlistmap = new HashMap<String, String>();
				strlistmap.put("label", splitcomLabel[i]);
				strlistmap.put("value", splitcomKey[i]);
				strlistItems.add(strlistmap);
			}

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return null;
	}

}
