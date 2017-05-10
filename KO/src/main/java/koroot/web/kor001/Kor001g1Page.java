package koroot.web.kor001;

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
import koroot.dao.TKeihiDao;
import koroot.dao.TKogutiDao;
import koroot.dto.ListDto;
import koroot.dto.KeihisinnseiListDto;
import koroot.exception.AnLoginException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonloginPage;
import koroot.web.kos001.Kos001g1Page;
import koroot.web.kor001.Kor001g2Page;

public class Kor001g1Page extends PageBaceClass {
	
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
	
	@Required
	@PageScope
	@SubapplicationScope
	public Integer status;
	@Required
	@PageScope
	private List<ListDto> statusItems;
	
	@PageScope
	@SubapplicationScope
	public List<ListDto> statusList;
	
	@PageScope
	@SubapplicationScope
	public Integer selectID;
	
	@PageScope
	@SubapplicationScope
	public List<ListDto> pjList;
	
	@Required
	@PageScope
	private int keihiIndex;
	@Required
	@PageScope
	private List<KeihisinnseiListDto> keihiItems;

	/** 選択年度格納 */
	@PageScope
	@SubapplicationScope
	public Integer selectyearID;
	/** 選択月度格納 */
	@PageScope
	@SubapplicationScope
	public Integer	selectmonthID;
	/** 選択ステータス格納 */
	//@PageScope
	@SubapplicationScope
	public Integer selectstatusID;

	/** 一覧表示項目*/
	private String seikydate;
	private String pjcode;
	private String pjname;
	private String statusname;
	private String kanjoname;
	private String kin;
	private String upddate;
	private String biko;

	/**　経費テーブルDao */	
	public TKeihiDao keiDao;	
		
	/**　請求年ID　ゲッター・セッター　*/
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	/**　請求年コンボ　セッター・ゲッター　*/
	public List<ListDto> getYearItems() {
		return yearItems;
	}
	public void setYearItems(List<ListDto> yearItems) {
		this.yearItems = yearItems;
	}

	/**　請求月ID　セッター・ゲッター　*/
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**　請求月コンボ　セッター・ゲッター　*/
	public List<ListDto> getMonthItems() {
		return monthItems;
	}
	public void setMonthItems(List<ListDto> monthItems) {
		this.monthItems = monthItems;
	}
	

	/**　ステータスID セッター・ゲッター　*/
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** ステータスコンボ　セッター・ゲッター　*/
	public List<ListDto> getStatusItems() {
		return statusItems;
	}
	public void setStatusItems(List<ListDto> statusItems) {
		this.statusItems = statusItems;
	}	
	

	/**　経費Index セッター・ゲッター　*/
	public int getKeihiIndex() {
		return keihiIndex;
	}
	public void setKeihiIndex(int keihiIndex) {
		this.keihiIndex = keihiIndex;
	}

	/**　経費リスト　セッター・ゲッター　*/
	public List<KeihisinnseiListDto> getKeihiItems() {
		return keihiItems;
	}
	public void setKeihiItems(List<KeihisinnseiListDto> keihiItems) {
		this.keihiItems = keihiItems;
	}
	
	
	/**　選択年 セッター・ゲッター　*/
	public Integer getSelectyearID() {
		return this.selectyearID;
	}
	public void setSelectyearID(Integer selectyearID) {
		this.selectyearID = selectyearID;
	}
	
	/**　選択月 セッター・ゲッター　*/
	public Integer getSelectmonthID() {
		return this.selectmonthID;
	}
	public void setSelectmonthID(Integer selectmonthID) {
		this.selectmonthID = selectmonthID;
	}
	/**　選択ステータス セッター・ゲッター　*/
	public Integer getSelectstatusID() {
		return this.selectstatusID;
	}
	public void setSelectstatusID(Integer selectstatusID) {
		this.selectstatusID = selectstatusID;
	}
	
	public String getseikydate() {
		return seikydate;
	}

	public void setseikydate(String seikydate) {
		this.seikydate = seikydate;
	}

	public String getpjcode() {
		return pjcode;
	}

	public void setpjcode(String pjcode) {
		this.pjcode = pjcode;
	}

	public String getPjname() {
		return pjname;
	}
	public void setPjname(String pjname) {
		this.pjname = pjname;
	}
	
	public String getstatusname() {
		return statusname;
	}

	public void setstatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getkanjoname() {
		return kanjoname;
	}

	public void setkanjoname(String kanjoname) {
		this.kanjoname = kanjoname;
	}

	public String getkin() {
		return kin;
	}

	public void setkin(String kin) {
		this.kin = kin;
	}

	public String getupddate() {
		return upddate;
	}

	public void setupddate(String upddate) {
		this.upddate = upddate;
	}

	public String getbiko() {
		return biko;
	}

	public void setbiko(String biko) {
		this.biko = biko;
	}

	public Class<?> doSrech() {
		
		//小口テーブルから情報取得
		String fromDate = year + "/" + String.format(monthFormat, month) + "/01";
		String toDate = year + "/" + String.format(monthFormat, month) + "/31";
		int State = status;
		int kaihai   = 0;
		int seikyu_empID = this.sessionDto.EmployeeID;
		int Type = 0;
		
		keihiItems = listFormat(keiDao.getsinnseiList(fromDate, toDate, State, Type, kaihai, seikyu_empID));
		//keihiItems = listFormat(kogDao.getsinnseiList(fromDate, toDate, State, kaihai, seikyu_empID));
		
		return null;
	}

	public Class<?> doinfo() {
		
		//選択した小口レコードのIDを取得
		selectID = keihiItems.get(keihiIndex).keihiID;
		status   = Integer.parseInt(keihiItems.get(keihiIndex).statusid);
	
		return Kor001g2Page.class;
	}

	public Class<?> doSrechCancel() {
		//初期表示と同一条件で再表示
		Calendar nowCal = Calendar.getInstance();
		
		year = nowCal.get(Calendar.YEAR );	
		month = nowCal.get(Calendar.MONTH) + 1;
		status = 9;
				
		String fromDate = year + "/" + String.format(monthFormat, month) + "/01";
		String toDate = year + "/" + String.format(monthFormat, month) + "/31";
		int State = status;
		int kaihai   = 0;
		int seikyu_empID = this.sessionDto.EmployeeID;
		int Type = 0;
		
		keihiItems = listFormat(keiDao.getsinnseiList(fromDate, toDate, State, Type, kaihai, seikyu_empID));
		return null;
	}

	public Class<?> doinsnew() {
		selectID = -1;
		return Kor001g3Page.class;
	}

	public Class<?> doFinishReturn() {
		
		return Kos001g1Page.class;
		
	}	
	
	public Class<?> initialize()  {
		
		try{
		
	    //セッション情報チェック
		this.chkSession();
		
		//ログイン情報表示
		this.setLoginInfo();
		
		//検索条件-請求年度コンボ作成　経費テーブルに登録実績のある年度+今年度		
		Calendar nowCal = Calendar.getInstance();
		String nowYear = String.valueOf(nowCal.get(Calendar.YEAR));
		nowCal.add(Calendar.YEAR ,-1);
		String zenYear = String.valueOf(nowCal.get(Calendar.YEAR)) + "/12/31";
		
		yearItems = keiDao.getYearList(nowYear, zenYear);
		
		//検索条件-請求月作成
		Integer imonth;		
		ArrayList<ListDto> monthList = new ArrayList<ListDto>();
		ListDto itemDto;		
		for (imonth = 1; imonth <= 12; imonth++)
		{
			itemDto = new ListDto();			
			itemDto.label = imonth.toString() + "月";
			itemDto.value = imonth.toString();			
			monthList.add(itemDto);
		}		
		monthItems = monthList;	
		
		//検索条件-ステータスコンボ作成
		statusItems = this.getCombList(CommonConstants.COMB_KOR001);
		
		//ステータス名取得
		statusList = this.getStatusList(CommonConstants.STATUS_KO001);
		
		//プロジェクトコード名取得		
		Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_PJ_LIST);
		pjList =  CommonUtil.getServiceToCombData(reqParam);
		
		//初期表示　当月
		nowCal = Calendar.getInstance();
		 
		year = nowCal.get(Calendar.YEAR );	
		month = nowCal.get(Calendar.MONTH) + 1;
		status = 9;
		
		if(selectyearID != null && selectmonthID != null && selectstatusID != null){ 
			year   = selectyearID;	
			month  = selectmonthID;
			status = selectstatusID;			
		}
		
		String fromDate = year + "/" + String.format(monthFormat, month) + "/01";
		String toDate = year + "/" + String.format(monthFormat, month) + "/31";
		int State = status;
		int kaihai  = 0;
		int seikyu_empID = this.sessionDto.EmployeeID;
		int Type = 0;

		keihiItems = listFormat(keiDao.getsinnseiList(fromDate, toDate, State, Type, kaihai, seikyu_empID));		
		
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
		
		return null;
	}
	
	/**　
	 * 経費リスト　PJリスト、金額編集メソッド
	 * 　*/
	private List<KeihisinnseiListDto> listFormat(List<KeihisinnseiListDto> list) {
		
		NumberFormat nfNum = NumberFormat.getNumberInstance();    //カンマ区切り形式

		//表示情報編集
		if(list != null){
			for(KeihisinnseiListDto listItem : list){
				//プロジェクトコード名編集
				if(pjList != null){
					for(ListDto pjItem : pjList){
						if(pjItem.value.equals(listItem.pjcode)){
							listItem.pjname = pjItem.label;
							break;
						}
					}
				}
				
				//ステータス名編集
				if(statusList != null){
					for(ListDto statusItem : statusList){
						if(statusItem.value.equals(listItem.statusid)){
							listItem.statusname = statusItem.label;
							break;
						}
					}
				}

				//金額編集
				Long tmpLong = Long.parseLong(listItem.kin);		
				listItem.kin = nfNum.format(tmpLong);

			}	
			
		}
		
		return list;
	}
}