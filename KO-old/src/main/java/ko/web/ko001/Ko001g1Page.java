package ko.web.ko001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

//import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.convert.NumberConverter;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import ko.web.error.NonloginPage;

//import schedule.web.error.AbendPage;
//import schedule.web.matterlist.MatterdetailsPage;



import ko.common.CommonConstants;
import ko.dao.TKogutiDao;
import ko.dto.KanjoListDto;
import ko.dto.ListDto;
//import ko.dto.SessionDto;
//import sib.schedule.dto.SessionDto;

//import ko.entity.TKoguti;
import ko.pagebace.PageBaceClass;

//申請者用小口請求画面
public class Ko001g1Page extends PageBaceClass {

	//社員情報項目
	@PageScope
	private String employee_id;
	@PageScope
	private String employee_name;
	@PageScope
	private String employee_mail;
	//private String kogutistr;

//	@Binding
//	public SessionDto sessionDto;

	//当日取得用
	@PageScope
	public  Integer thisyear;
	@PageScope
	public  Integer thismonth;
	public  String thisday;

	//ステータスマスタ取得用項目
	public  String comLabel;
	public  String comKey;

	/** 選択年度 */
	@Required
	@PageScope
	private Integer Yearlist;
	/** 選択月度 */
	@Required
	@PageScope
	private Integer	monthlist;
	/** 選択ステータス */
	@Required
	@PageScope
	private String strlist;

	/** 選択年度格納 */
	public Integer selectYearID;
	/** 選択月度格納 */
	public Integer	selectmonthID;
	/** 選択ステータス格納 */
	public String selectstrID;
	//テスト用項目
	private String[] strcombo;

	/** 検索年度アイテム */
	@PageScope
	public ListDto[] YearlistItems;
	/** 検索月度アイテム */
	@PageScope
	public List<Map<String,Integer>> monthlistItems;
	/** 検索ステータスアイテム */
	@PageScope
	public List<Map<String,String>> strlistItems;
	/** ステータス情報格納 */
	@PageScope
	public  Map<String,String> strnamelist;
	/** 小口請求ＩＤ */
	public Integer selectkogutiID;
	/** 小口請求Index */
	public int kanjoIndex;

	/** 小口リストアイテム */
	@PageScope
	public KanjoListDto[] kanjoItems;
	//小口リストアイテム内容
	/** 請求日 */
	private String seikyu_Date;
	/** プロジェクトコード */
	private String PJ_CODE;
	/** ステータス */
	private String state;
	/** 勘定科目 */
	private String kamoku;
	/** 金額 */
	@NumberConverter(pattern="#,##0")
	private Integer kingaku;
	/** 登録日 */
	private String upd_date;
	/** 備考 */
	private String bikou;


	/** 小口Dao */
	public TKogutiDao KogutiDao;

	/** 社員ID セッタ/ゲッタ */
	public String getEmployee_id() {
		return employee_id;
	}
	public  void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	/** 社員名 セッタ/ゲッタ */
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	/** 社員メール セッタ/ゲッタ */
	public String getEmployee_mail() {
		return employee_mail;
	}
	public void setEmployee_mail(String employee_mail) {
		this.employee_mail = employee_mail;
	}

//	/** 小口名 セッタ/ゲッタ */
//	public String getKogutistr() {
//		return kogutistr;
//	}
//	public void setKogutistr(String kogutistr) {
//		this.kogutistr = kogutistr;
//	}

	/** ステータス セッタ/ゲッタ */
	public String[] getStrcombo() {
		return strcombo;
	}
	public void setStrcombo(String[] strcombo) {
		this.strcombo = strcombo;
	}

	/** 勘定Index セッタ/ゲッタ */
	public int getKanjoIndex() {
		return kanjoIndex;
	}
	public void setKanjoIndex(int kanjoIndex) {
		this.kanjoIndex = kanjoIndex;
	}

	/** 勘定リスト セッタ/ゲッタ */
	public KanjoListDto[] getKanjoItems() {
		return kanjoItems;
	}
	public void setKanjoItems(KanjoListDto[] kanjoItems) {
		this.kanjoItems = kanjoItems;
	}

	/** 年度コンボ セッタ/ゲッタ */
	public Integer getYearlist() {
		return Yearlist;
	}
	public void setYearlist(Integer Yearlist) {
		this.Yearlist = Yearlist;
	}

	/** 月コンボ セッタ/ゲッタ */
	public List<Map<String,Integer>> getmonthlistItems() {
		return monthlistItems;
	}
	public void setmonthlistItems(List<Map<String,Integer>> monthlistItems) {
		this.monthlistItems = monthlistItems;
	}

	/** 月コンボ セッタ/ゲッタ */
	public Integer getmonthlist() {
		return monthlist;
	}
	public void setmonthlist(Integer monthlist) {
		this.monthlist = monthlist;
	}

	/** 請求日 セッタ/ゲッタ */
	public String getSeikyu_Date() {
		return seikyu_Date;
	}
	public void setSeikyu_Date(String seikyu_Date) {
		this.seikyu_Date = seikyu_Date;
	}

	/** プロジェクトコード セッタ/ゲッタ */
	public String getPJ_CODE() {
		return PJ_CODE;
	}
	public void setPJ_CODE(String pJ_CODE) {
		PJ_CODE = pJ_CODE;
	}

	/** ステータス セッタ/ゲッタ */
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	/** 科目 セッタ/ゲッタ */
	public String getkamoku() {
		return kamoku;
	}
	public void setkamoku(String kamoku) {
		this.kamoku = kamoku;
	}

	/** 金額 セッタ/ゲッタ */
	public Integer getKingaku() {
		return kingaku;
	}
	public void setKingaku(Integer kingaku) {
		this.kingaku = kingaku;
	}

	/** 更新日 セッタ/ゲッタ */
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}

	/** 備考 セッタ/ゲッタ */
	public String getBikou() {
		return bikou;
	}
	public void setBikou(String bikou) {
		this.bikou = bikou;
	}

	/** 年度選択コンボ セッタ/ゲッタ */
	public ListDto[] getYearlistItems() {
		return YearlistItems;
	}
	public void setYearlistItems(ListDto[] YearlistItems) {
		this.YearlistItems = YearlistItems;

	}
	public String getStrlist() {
		return strlist;
	}
	public void setStrlist(String strlist) {
		this.strlist = strlist;
	}

	public List<Map<String,String>> getStrlistItems() {
		return strlistItems;
	}
	public void setStrlistItems(List<Map<String,String>> strlistItems) {
		this.strlistItems = strlistItems;
	}

	public Class<?> initialize() {
		//初期表示として当月分の小口請求全件を表示する

		try {

			// 未ログインユーザーをはじく処理
			if(sessionDto.EmployeeID == null){
				return NonloginPage.class;
			}
			employee_id   = this.sessionDto.EmployeeID.toString();
			employee_name = this.sessionDto.EmployeeName;
			employee_mail = this.sessionDto.EmployeeEmail;

//			/** Login社員情報取得 */
//			Map<String, String> reqParam = new HashMap<String, String>();
//			Map<String, String> resParam;

//			//開発用にユーザーに"test"を直指定、SIBアプリより取得予定
//			reqParam.put("userID", "test");       /** Login社員ID */
//			reqParam.put("userPas", "test");      /** Login社員PASS */
//
//			resParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_USERINFO,reqParam);
//
//
//			employee_id   = resParam.get("employeeIDStr");
////			employee_id   = SessionDto.EmployeeID.toString();
//			employee_name = resParam.get("employeeNameStr");
//			employee_mail = resParam.get("employeeEmailStr");

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//当日日付取得
		Calendar now = Calendar.getInstance();
		thisyear  = new Integer(now.get(Calendar.YEAR));                     //年を取得
		thismonth = new Integer(now.get(Calendar.MONTH) + 1);                //月を取得(※1)
		thisday   = new Integer(now.get(Calendar.DATE)).toString();;         //日を取得

		Yearlist  = new Integer(now.get(Calendar.YEAR));
		monthlist = new Integer(now.get(Calendar.MONTH) + 1);

		/** ステータス情報取得 サービスアダプタ連携*/
		Map<String, String> reqstatus = new HashMap<String, String>();
		Map<String, String> resstatus = new HashMap<String, String>();

		try {
			reqstatus.put("key1","comblist");       /** 小口請求用ステータス */
			reqstatus.put("combName","sibko");      /** 小口請求用ステータス 有効 */

			resstatus = this.getServiceValue(CommonConstants.SERVICE_TYPE_COMBLIST,reqstatus);
		}catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
			}
		/** ステータス情報取得 格納*/
		comLabel = resstatus.get("comLabel");
		comKey   = resstatus.get("comKey");
		String[] splitcomLabel = comLabel.split(",");
		String[] splitcomKey   = comKey.split(",");

		int a = splitcomLabel.length;

		strnamelist = new HashMap<String,String>();
		for(int i = 0; i < a; i++){

		strnamelist.put(splitcomKey[i],splitcomLabel[i]);
			}

		strcombo = new String[2];
		strcombo[0] = "1";
		strcombo[1] = "2";
		System.out.println(strcombo);

		//初期表示は当月分の小口請求全件を表示するためステータスを選択項目に加えない
		strlist  = new String();
		strlist = "0";

		//画面遷移後は、選択条件を保持
		Yearlist = thisyear;
		if (selectYearID != null){
			Yearlist = selectYearID;
		}
		monthlist = thismonth;
		if (selectmonthID != null){
			monthlist = selectmonthID;
		}
		if (selectstrID != null){
			strlist = selectstrID;
		}



		/** 初期表示 小口リスト出力 当月分のステータスが を初期表示として出力する*/
		kanjoItems = KogutiDao.selectGetKanjoList(0,Yearlist.toString(),monthlist,strlist,employee_id);

		//勘定コードを勘定項目名に書き換える
		a = kanjoItems.length;
		for(int i = 0; i < a; i++){
			kanjoItems[i].state = strnamelist.get(kanjoItems[i].state.toString());
			}

		return null;
	}

	public Class<?> prerender() {

		/** 検索リスト年出力 */
		setYearlistItems(KogutiDao.selectyearList());



		/** 検索リスト月出力 */
		monthlistItems = new ArrayList<Map<String,Integer>>();
		for(int i = 1; i < 13; i++){
		Map<String,Integer> monthlistmap = new HashMap<String,Integer>();
		monthlistmap.put("label", i);
		monthlistmap.put("value", i);
		monthlistItems.add(monthlistmap);
		}




		/** 検索リストステータス出力 */
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

			strlistItems = new ArrayList<Map<String,String>>();
			for(int i = 0; i < a; i++){
				Map<String,String> strlistmap = new HashMap<String,String>();
				strlistmap.put("label", splitcomLabel[i]);
				strlistmap.put("value", splitcomKey[i]);
				strlistItems.add(strlistmap);
				}

			}catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}
//		/** 小口リスト出力 */
//		kanjoItems = KogutiDao.selectGetKanjoList(0,Yearlist.toString(),monthlist,2);
		return null;
	}

	public Class<?> doSrech(){

		//検索条件によって小口リストを出力
		//改廃（有効）、年度、月度、ステータス、社員ID（ログイン者）
		/** 小口リスト出力 */
		kanjoItems = KogutiDao.selectGetKanjoList(0,Yearlist.toString(),monthlist,strlist,employee_id);
		int a = kanjoItems.length;
		for(int i = 0; i < a; i++){
			kanjoItems[i].state = strnamelist.get(kanjoItems[i].state.toString());
			}
		return null;
	}

	public Class<?> doSrechCancel() {
	      strlist  = new String();
	      strlist = "0";

//		/** 検索条件解除、当月分の小口請求全件を表示*/
		kanjoItems = KogutiDao.selectGetKanjoList(0,thisyear.toString(),thismonth,strlist,employee_id);

		int a = kanjoItems.length;
		for(int i = 0; i < a; i++){
			kanjoItems[i].state = strnamelist.get(kanjoItems[i].state.toString());
			}

		return null;
	}

	public Class<?> doinsnew() {
		//新規登録画面に遷移
		selectkogutiID = -1;

		return Ko001g2Page.class;
	}

	public Class<?> doinfo() {
		try{
		//小口請求詳細を表示
		selectkogutiID = kanjoItems[kanjoIndex].seikyu;
		selectmonthID = monthlist;
		selectYearID =  Yearlist;
		selectstrID  =  strlist;

		}catch(Exception e){
			return null;
		}

		return Ko001g2Page.class;

	}
//	public Integer getSetYearlist() {
//		return seachYearlist;
//	}
//	public void setSetYearlist(Integer seachYearlist) {
//		this.seachYearlist = seachYearlist;
//	}
//	public Integer getSetmonthlist() {
//		return setmonthlist;
//	}
//	public void setSetmonthlist(Integer setmonthlist) {
//		this.setmonthlist = setmonthlist;
//	}
//	public String getSetstrlist() {
//		return setstrlist;
//	}
//	public void setSetstrlist(String setstrlist) {
//		this.setstrlist = setstrlist;
//	}

}
