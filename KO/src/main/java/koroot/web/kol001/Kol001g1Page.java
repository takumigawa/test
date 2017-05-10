package koroot.web.kol001;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;
import koroot.common.CommonConstants;
import koroot.common.CommonUtil;
import koroot.dao.TKogutiDao;
import koroot.dto.KanrishasinseiListDto;
import koroot.dto.ListDto;
import koroot.dto.KeihisinnseiListDto;
import koroot.exception.AnLoginException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonloginPage;
import koroot.web.kos001.Kos001g1Page;
import koroot.web.kol001.Kol001g2Page;

public class Kol001g1Page extends PageBaceClass {

	private static String monthFormat = "%02d";

	@Required
	@PageScope
	@SubapplicationScope
	public Integer year;
	@Required
	@PageScope
	private List<ListDto> yearItems;

	@Required
	@PageScope
	@SubapplicationScope
	public Integer month;
	@Required
	@PageScope
	private List<ListDto> monthItems;

	@PageScope
	@SubapplicationScope
	public List<ListDto> pjcodeList;
	@Required
	@PageScope
	private List<ListDto> pjcodeListItems;

	@Required
	@PageScope
	@SubapplicationScope
	public Integer seikyuEmpList;
	@Required
	@PageScope
	private List<ListDto> seikyuEmpListItems;

	@Required
	@PageScope
	@SubapplicationScope
	public Integer statusList;
	@Required
	@PageScope
	private List<ListDto> statusListItems;

	@PageScope
	@SubapplicationScope
	public List<ListDto> statusNameList;
	
	@PageScope
	@SubapplicationScope
	public Integer selectID;

	@Required
	@PageScope
	private int keihiIndex;
	@Required
	@PageScope
	private List<KanrishasinseiListDto> keihiItems;

	/** 選択年度格納 */
	@PageScope
	@SubapplicationScope
	public Integer selectyearID;
	/** 選択月度格納 */
	@PageScope
	@SubapplicationScope
	public Integer selectmonthID;
	/** 選択ステータス格納 */
	// @PageScope
	@SubapplicationScope
	public Integer selectstatusID;
	/** 選択申請者格納 */
	// @PageScope
	@SubapplicationScope
	public Integer selectempID;

	private String seikyudate;
	private String pjcode;
	private String pjname;
	private String seikyuemp;
	private String statusName;
	private String kanjoname;
	private String kin;
	private String upddate;

	/** 　小口テーブルDao */
	public TKogutiDao kogDao;

	/**　請求年ID　ゲッター・セッター　*/
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	/**　請求年コンボ　ゲッター・セッター　*/
	public List<ListDto> getYearItems() {
		return yearItems;
	}
	public void setYearItems(List<ListDto> yearItems) {
		this.yearItems = yearItems;
	}

	/**　請求月ID　ゲッター・セッター　*/
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**　請求月コンボ　ゲッター・セッター　*/
	public List<ListDto> getMonthItems() {
		return monthItems;
	}
	public void setMonthItems(List<ListDto> monthItems) {
		this.monthItems = monthItems;
	}

	/**　PJコードID　ゲッター・セッター　*/
	public List<ListDto> getPjcodeList() {
		return pjcodeList;
	}
	public void setPjcodeList(List<ListDto> pjcodeList) {
		this.pjcodeList = pjcodeList;
	}

	/**　PJコードコンボ　ゲッター・セッター　*/
	public List<ListDto> getPjcodeListItems() {
		return pjcodeListItems;
	}
	public void setPjcodeListItems(List<ListDto> pjcodeListItems) {
		this.pjcodeListItems = pjcodeListItems;
	}

	/**　請求社員ID　ゲッター・セッター　*/
	public Integer getSeikyuEmpList() {
		return seikyuEmpList;
	}
	public void setSeikyuEmpList(Integer seikyuEmpList) {
		this.seikyuEmpList = seikyuEmpList;
	}

	/**　請求社員コンボ　ゲッター・セッター　*/
	public List<ListDto> getSeikyuEmpListItems() {
		return seikyuEmpListItems;
	}
	public void setSeikyuEmpListItems(List<ListDto> seikyuEmpListItems) {
		this.seikyuEmpListItems = seikyuEmpListItems;
	}

	/**　ステータスID　ゲッター・セッター　*/
	public Integer getStatusList() {
		return statusList;
	}
	public void setStatusList(Integer statusList) {
		this.statusList = statusList;
	}

	/**　ステータスコンボ　ゲッター・セッター　*/
	public List<ListDto> getStatusListItems() {
		return statusListItems;
	}
	public void setStatusListItems(List<ListDto> statusListItems) {
		this.statusListItems = statusListItems;
	}

	/**　経費リスト　ゲッター・セッター　*/
	public List<KanrishasinseiListDto> getKeihiItems() {
		return keihiItems;
	}
	public void setKeihiItems(List<KanrishasinseiListDto> keihiItems) {
		this.keihiItems = keihiItems;
	}

	/**　請求日　セッター・ゲッター　*/
	public String getSeikyudate() {
		return seikyudate;
	}
	public void setSeikyudate(String seikyudate) {
		this.seikyudate = seikyudate;
	}

	/**　PJコード　セッター・ゲッター　*/
	public String getPjcode() {
		return pjcode;
	}
	public void setPjcode(String pjcode) {
		this.pjcode = pjcode;
	}

	/**　PJ名　セッター・ゲッター　*/
	public String getPjname() {
		return pjname;
	}
	public void setPjname(String pjname) {
		this.pjname = pjname;
	}
	
	/** 請求社員　セッター・ゲッター　*/
	public String getSeikyuemp() {
		return seikyuemp;
	}
	public void setSeikyuemp(String seikyuemp) {
		this.seikyuemp = seikyuemp;
	}

	/**　ステータス名　セッター・ゲッター　*/
	public String getStatus() {
		return statusName;
	}
	public void setStatus(String status) {
		this.statusName = status;
	}

	/**　勘定科目名　セッター・ゲッター　*/
	public String getKanjoname() {
		return kanjoname;
	}
	public void setKanjoname(String kanjoname) {
		this.kanjoname = kanjoname;
	}

	/**　金額　セッター・ゲッター　*/
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}

	/**　最終更新日　セッター・ゲッター　*/
	public String getUpddate() {
		return upddate;
	}
	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}

	public Class<?> doSrech() {

		// 小口テーブルから情報取得
		String fromDate = year + "/" + String.format(monthFormat, month)
				+ "/01";
		String toDate = year + "/" + String.format(monthFormat, month) + "/31";
		int State = statusList;
		int kaihai = 0;
		int seikyu_empID = this.sessionDto.EmployeeID;

		keihiItems = listFormat(kogDao.getkanrishaList(fromDate, toDate, State,
				kaihai, seikyu_empID));

		return null;
	}

	public Class<?> doinfo() {

		// 選択した小口レコードのIDを取得
		selectID = keihiItems.get(keihiIndex).keihiID;
		statusList = Integer.parseInt(keihiItems.get(keihiIndex).statusid);

		return Kol001g2Page.class;
	}

	public Class<?> doSrechCancel() {
		// 初期表示と同一条件で再表示
		Calendar nowCal = Calendar.getInstance();

		year = nowCal.get(Calendar.YEAR);
		month = nowCal.get(Calendar.MONTH) + 1;
		statusList = 9;
		seikyuEmpList = 9;

		String fromDate = year + "/" + String.format(monthFormat, month)
				+ "/01";
		String toDate = year + "/" + String.format(monthFormat, month) + "/31";
		int State = statusList;

		int kaihai = 0;
		int seikyu_empID = this.sessionDto.EmployeeID;

		keihiItems = listFormat(kogDao.getkanrishaList(fromDate, toDate, State,
				kaihai, seikyu_empID));
		return null;
	}

	public Class<?> doFinishReturn() {

		return Kos001g1Page.class;

	}

	public Class<?> initialize() {

		try {

			// セッション情報チェック
			this.chkSession();

			// ログイン情報表示
			this.setLoginInfo();
			
			this.chkPrjLeader();

			// 検索条件-請求年
			Calendar nowCal = Calendar.getInstance();

			String nowYear = String.valueOf(nowCal.get(Calendar.YEAR));

			nowCal.add(Calendar.YEAR, -1);
			String zenYear = String.valueOf(nowCal.get(Calendar.YEAR))
					+ "/12/31";

			yearItems = kogDao.getYearList(nowYear, zenYear);

			// 検索条件-請求月
			Integer imonth;

			ArrayList<ListDto> monthList = new ArrayList<ListDto>();
			ListDto itemDto;

			for (imonth = 1; imonth <= 12; imonth++) {
				itemDto = new ListDto();

				itemDto.label = imonth.toString() + "月";
				itemDto.value = imonth.toString();

				monthList.add(itemDto);
			}

			monthItems = monthList;

			// 検索条件-ステータスコンボ
			statusListItems = this.getCombList(CommonConstants.COMB_KOL001);

			// ステータス名取得
			statusNameList = this.getStatusList(CommonConstants.STATUS_KO001);

			// 検索条件-プロジェクトコード
			Map<String, String> reqParam = this
					.getServiceValue(CommonConstants.SERVICE_TYPE_PJ_LIST);

			pjcodeList = CommonUtil.getServiceToCombData(reqParam);

			ListDto syoItem = new ListDto();

			syoItem.label = "全て";
			syoItem.value = "!";

			pjcodeListItems = new ArrayList<ListDto>();

			pjcodeListItems.add(syoItem);

			// PJリストが未取得以外の場合、コンボリストに値を追加する。
			if (pjcodeList != null) {
				for (ListDto listItem : pjcodeList) {

					ListDto tmpItem = new ListDto();

					tmpItem.value = listItem.value;
					tmpItem.label = listItem.value;
					pjcodeListItems.add(tmpItem);
				}
			}
			// 初期表示
			nowCal = Calendar.getInstance();

			year = nowCal.get(Calendar.YEAR);
			month = nowCal.get(Calendar.MONTH) + 1;
			statusList = 9;

			if (selectyearID != null && selectmonthID != null
					&& selectstatusID != null) {
				year = selectyearID;
				month = selectmonthID;
				statusList = selectstatusID;
			}

			String fromDate = year + "/" + String.format(monthFormat, month)
					+ "/01";
			String toDate = year + "/" + String.format(monthFormat, month)
					+ "/31";
			int State = statusList;
			int kaihai = 0;
			int seikyu_empID = this.sessionDto.EmployeeID;

			keihiItems = listFormat(kogDao.getkanrishaList(fromDate, toDate,
					State, kaihai, seikyu_empID));

			kaihai = 0;

		} catch (AnLoginException e) {
			// 未ログインエラー
			return NonloginPage.class;

		} catch (Exception e) {
			// アベンド
			return AbendPage.class;
		}

		return null;
	}

	public Class<?> prerender() {

		// ログイン情報表示
		this.setLoginInfo();

		return null;
	}

	/**
	 * 　 経費リスト　PJリスト、金額編集メソッド 　
	 */
	private List<KanrishasinseiListDto> listFormat(
			List<KanrishasinseiListDto> list) {

		NumberFormat nfNum = NumberFormat.getNumberInstance(); // カンマ区切り形式

		// 表示情報編集
		if (list != null) {
			for (KanrishasinseiListDto listItem : list) {
				// プロジェクトコード名編集
				if (pjcodeList != null) {
					for (ListDto pjItem : pjcodeList) {
						if (pjItem.value.equals(listItem.pjcode)) {
							listItem.pjname = pjItem.label;
							break;
						}
					}
				}

				// ステータス名編集
				if (statusNameList != null) {
					for (ListDto statusItem : statusNameList) {
						if (statusItem.value.equals(listItem.statusid)) {
							listItem.statusname = statusItem.label;
							break;
						}
					}
				}

				// 金額編集
				Long tmpLong = Long.parseLong(listItem.kin);
				listItem.kin = nfNum.format(tmpLong);

			}

		}

		return list;
	}
}