package koroot.web.koa001;

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
import koroot.dto.ListDto;
import koroot.dto.keihikyokaListDto;
import koroot.exception.AnLoginException;
import koroot.exception.NoMastaMenteAuthException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonauthorityPage;
import koroot.web.error.NonloginPage;
import koroot.web.kos001.Kos001g1Page;

public class Koa001g1Page extends PageBaceClass {
	
	private static String monthFormat = "%02d";

	@Required
	@PageScope
	private Integer year;
	@Required
	@PageScope
	private List<ListDto> yearItems;
	
	@Required
	@PageScope
	private Integer month;
	@Required
	@PageScope
	private List<ListDto> monthItems;
	
	@Required
	@PageScope
	private Integer status;
	@Required
	@PageScope
	private List<ListDto> statusItems;
	
	@Required
	@PageScope
	private String pjcodeList;
	@Required
	@PageScope
	private List<ListDto> pjcodeListItems;
	
	private String seikydate;
	private String pjcode;
	private String pjname;
	private String statusname;
	private String kanjoname;
	private String kin;
	private String sindate;
	
	@Required
	@PageScope
	private int keihiIndex;
	@Required
	@PageScope
	@SubapplicationScope
	private List<keihikyokaListDto> keihiItems;
	
	@SubapplicationScope
	private Integer selectID;
	
	@PageScope
	@SubapplicationScope
	public List<ListDto> pjList;

	@PageScope
	@SubapplicationScope
	public List<ListDto> statusList;
	
	/**　小口テーブルDao */
	public TKogutiDao kogDao;
	
	/**　選択経費ID　セッター・ゲッター　*/
	public Integer getSelectID() {
		return selectID;
	}
	public void setSelectID(Integer selectID) {
		this.selectID = selectID;
	}
	
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

	/**　プロジェクトコードID　セッター・ゲッター　*/
	public String getPjcodeList() {
		return pjcodeList;
	}
	public void setPjcodeList(String pjcodeList) {
		this.pjcodeList = pjcodeList;
	}

	/**　プロジェクトコードコンボ　セッター・ゲッター　*/
	public List<ListDto> getPjcodeListItems() {
		return pjcodeListItems;
	}
	public void setPjcodeListItems(List<ListDto> pjcodeListItems) {
		this.pjcodeListItems = pjcodeListItems;
	}

	/**　請求日　セッター・ゲッター　*/
	public String getSeikydate() {
		return seikydate;
	}
	public void setSeikydate(String seikydate) {
		this.seikydate = seikydate;
	}

	/**　プロジェクトコード　セッター・ゲッター　*/
	public String getPjcode() {
		return pjcode;
	}
	public void setPjcode(String pjcode) {
		this.pjcode = pjcode;
	}

	/**　プロジェクト名　セッター・ゲッター　*/
	public String getPjname() {
		return pjname;
	}
	public void setPjname(String pjname) {
		this.pjname = pjname;
	}

	/**　ステータス　セッター・ゲッター　*/
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	/**　勘定科目名　セッター・ゲッター　*/
	public String getKanjoname() {
		return kanjoname;
	}
	public void setKanjoname(String kanjoname) {
		this.kanjoname = kanjoname;
	}

	/**　金額　セッター・ゲッターセッター・ゲッター */
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}

	/**　申請日　セッター・ゲッター　*/
	public String getSindate() {
		return sindate;
	}
	public void setSindate(String sindate) {
		this.sindate = sindate;
	}

	/**　経費Index セッター・ゲッター　*/
	public int getKeihiIndex() {
		return keihiIndex;
	}
	public void setKeihiIndex(int keihiIndex) {
		this.keihiIndex = keihiIndex;
	}

	/**　経費リスト　セッター・ゲッター　*/
	public List<keihikyokaListDto> getKeihiItems() {
		return keihiItems;
	}
	public void setKeihiItems(List<keihikyokaListDto> keihiItems) {
		this.keihiItems = keihiItems;
	}

	public Class<?> doFinishReturn() {
		
		return Kos001g1Page.class;
		
	}
	
	public Class<?> doDetails() {
		
		selectID = keihiItems.get(keihiIndex).keihiID;
		
		//仮払いか通常申請によって詳細画面を振り分ける
		String netxID = keihiItems.get(keihiIndex).rowtype;
		
		if(netxID.equals("0")) {
			//通常申請
			return Koa001g2Page.class;
		} else {
			//仮払
			return Koa001g3Page.class;
		}

	}

	/**　　*/
	public Class<?> doSrech() {
		
		//小口テーブルから情報取得
		String fromDate = year + "/" + String.format(monthFormat, month) + "/01";
		String toDate = year + "/" + String.format(monthFormat, month) + "/31";
		String inStatus = status.toString();
		String inPJCode = pjcodeList;
		
		keihiItems = listFormat(kogDao.getKogList(fromDate, toDate, inStatus, inPJCode));
		
		return null;
	}

	/**　　*/
	public Class<?> doSrechCancel() {
		
		//初期表示
		Calendar nowCal = Calendar.getInstance();
		
		year = nowCal.get(Calendar.YEAR );
		month = nowCal.get(Calendar.MONTH) + 1;
		status = 3;
		pjcodeList = "!";
		
		//小口テーブルから情報取得
		String fromDate = year + "/" + String.format(monthFormat, month) + "/01";
		String toDate = year + "/" + String.format(monthFormat, month) + "/31";
		String inStatus = status.toString();
		String inPJCode = pjcodeList;
		
		keihiItems = listFormat(kogDao.getKogList(fromDate, toDate, inStatus, inPJCode));
		
		return null;
	}
	
	/**　　*/
	public Class<?> initialize() {
		
		try{
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-ステータスコンボ
			statusItems = this.getCombList(CommonConstants.COMB_KOA001);
			
			//検索条件-請求年
			Calendar nowCal = Calendar.getInstance();
			
			String nowYear = String.valueOf(nowCal.get(Calendar.YEAR));
			
			nowCal.add(Calendar.YEAR ,-1);
			String zenYear = String.valueOf(nowCal.get(Calendar.YEAR)) + "/12/31";
			
			yearItems = kogDao.getYearList(nowYear, zenYear);
			
			//検索条件-請求月
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
			
			//検索条件-プロジェクトコード		
			Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_PJ_LIST);
			
			pjList =  CommonUtil.getServiceToCombData(reqParam);
					
			ListDto syoItem = new ListDto();
			
			syoItem.label = "全て";
			syoItem.value = "!";
			
			pjcodeListItems = new ArrayList<ListDto>();
			
			pjcodeListItems.add(syoItem);
			
			//PJリストが未取得以外の場合、コンボリストに値を追加する。
			if(pjList != null) {
				for(ListDto listItem : pjList) {
					
					ListDto tmpItem = new ListDto();
					
					tmpItem.value = listItem.value;
					tmpItem.label = listItem.value;
					pjcodeListItems.add(tmpItem);
				}				
			}
			
			//ステータス名取得
			statusList = this.getStatusList(CommonConstants.STATUS_KO001);
						
			//初期表示
			nowCal = Calendar.getInstance();
			
			year = nowCal.get(Calendar.YEAR );
			month = nowCal.get(Calendar.MONTH) + 1;
			status = 3;
			
			String fromDate = year + "/" + String.format(monthFormat, month) + "/01";
			String toDate = year + "/" + String.format(monthFormat, month) + "/31";
			String inStatus = status.toString();
			String inPJCode = "!";
			
			keihiItems = listFormat(kogDao.getKogList(fromDate, toDate, inStatus, inPJCode));
			
			
		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			//未マスタメンテ権限エラー
			return NonauthorityPage.class;
			
		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}

	public Class<?> prerender() {
		
		this.setLoginInfo();
		
		return null;
	}
	
	/**　
	 * 経費リスト　PJリスト、金額編集メソッド
	 * 　*/
	private List<keihikyokaListDto> listFormat(List<keihikyokaListDto> list) {
		
		NumberFormat nfNum = NumberFormat.getNumberInstance();    //カンマ区切り形式

		//表示情報編集
		if(list != null){
			for(keihikyokaListDto listItem : list){
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
