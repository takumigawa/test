package ste.web.te02;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import ste.common.CommonUtil;
import ste.dto.ListDto;
import ste.dto.payDto;
import ste.dto.transDto;
import ste.pagebace.PageBaceClass;
import ste.dao.TTightenDao;
import ste.entity.TTighten;

public class Te0201Page extends PageBaceClass {

	@SubapplicationScope
	public String empyear;
	@SubapplicationScope
	public String empmonth;
	
	//申請状況Dao
	public TTightenDao tenDao;
		
	@PageScope
	@Required
	private String kaihai;
	@PageScope
	private List<ListDto> kaihaiItems;

	@PageScope
	@Required
	private String year;
	@PageScope
	private List<ListDto> yearItems;
	
	@PageScope
	@Required
	private String month;
	@PageScope
	private List<ListDto> monthItems;
	
	@PageScope
	private int payIndex;
	@PageScope
	private List<payDto> payItems;

	private String empYM;
	private String summary;
	private String money;
	private String uke;
	
	private String retChk;
	
	//検索コンボ
	public String getKaihai() {
		return kaihai;
	}
	public void setKaihai(String kaihai) {
		this.kaihai = kaihai;
	}
	//検索コンボ
	public List<ListDto> getKaihaiItems() {
		return kaihaiItems;
	}
	public void setKaihaiItems(List<ListDto> kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}
	
	//年コンボ
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	//年コンボ
	public List<ListDto> getYearItems() {
		return yearItems;
	}
	public void setYearItems(List<ListDto> yearItems) {
		this.yearItems = yearItems;
	}
	
	//月コンボ
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	//年コンボ
	public List<ListDto> getMonthItems() {
		return monthItems;
	}
	public void setMonthItems(List<ListDto> monthItems) {
		this.monthItems = monthItems;
	}

	//
	public int getPayIndex() {
		return payIndex;
	}
	public void setPayIndex(int payIndex) {
		this.payIndex = payIndex;
	}

	//リストアイテム
	public List<payDto> getPayItems() {
		return payItems;
	}
	public void setPayItems(List<payDto> payItems) {
		this.payItems = payItems;
	}
	
	//年月日
	public String getEmpYM() {
		return empYM;
	}
	public void setEmpYM(String empYM) {
		this.empYM = empYM;
	}
	
	//申請状況
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	//受取状況
	public String getUke() {
		return uke;
	}
	public void setUke(String uke) {
		this.uke = uke;
	}
	
	//金額
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	//検索コンボ
	public String getRetChk() {
		return retChk;
	}
	public void setRetChk(String retChk) {
		this.retChk = retChk;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doSend() {
		
		errMes = "";
		
		String retIndex[] = retChk.split(",");
		
		for (int i = 0 ; i < retIndex.length ; i++) {
			
			payDto item = payItems.get(Integer.valueOf(retIndex[i]));
			
			TTighten etyTen =  tenDao.selectById(Integer.valueOf(employee_id), item.empyear, item.empmonth);
			
			etyTen.setUpddate(CommonUtil.getNowUpdDate());
			etyTen.setUpdid(Integer.valueOf(employee_id));
			
			//申請状況を申請中にする
			etyTen.setTightenflg(1);
			etyTen.setApprovalflg(0);
			
			//更新処理
			tenDao.update(etyTen);
			
		}
		
		errMes = "更新しました。";
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doReflash() {
		
		errMes = "";	
		
		//payItems = getTranList(Integer.valueOf(employee_id),Integer.valueOf(kaihai));
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doinputDetail() {
		
		payDto item = payItems.get(payIndex);
		
		this.empyear = item.empyear.toString();
		this.empmonth = item.empmonth.toString();
		
		return Te0202Page.class;
		
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doNewData() {
		
		errMes = "";
		
		TTighten etyTen =  tenDao.selectById(Integer.valueOf(employee_id), Integer.valueOf(year), Integer.valueOf(month));
		
		//申請書が存在する場合は、エラーとする
		if (etyTen == null ) {
			
			etyTen = new TTighten();
			
			//社員ID
			etyTen.setEmpid(Integer.valueOf(employee_id));
			//年
			etyTen.setEmpyear(Integer.valueOf(year));
			//月
			etyTen.setEmpmonth(Integer.valueOf(month));
			//名前
			etyTen.setEmpname("");
			//申請状況
			etyTen.setTightenflg(0);
			//承認状況
			etyTen.setApprovalflg(0);
			//新規作成ID
			etyTen.setAddid(Integer.valueOf(employee_id));
			//新規作成年月日
			etyTen.setAdddate(CommonUtil.getNowUpdDate());
			
			tenDao.insert(etyTen);
			
			errMes = "申請書を作成しました。";
			
		} else {
			errMes = "既に申請書が作成されています。";
		}
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {
		
		setLoginInfo();
		
		//検索リスト
		this.kaihaiItems = getKensakuComb();
		//年リスト
		this.yearItems = getYearComb();
		//月リスト
		this.monthItems = getMonthComb();
		
		return null;
	}

	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 */
	public Class<?> prerender() {
		
		if (kaihai == null ) {
			payItems = getTranList(Integer.valueOf(employee_id) ,0);
		} else {
			payItems = getTranList(Integer.valueOf(employee_id), Integer.valueOf(kaihai));
		}
		
		Calendar cal = Calendar.getInstance();
		
		month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 */
	private List<ListDto> getYearComb() {
		
		ListDto item = new ListDto();
		List<ListDto> combList = new ArrayList<ListDto>();
		
		Calendar cal = Calendar.getInstance();
		
		int iYear = cal.get(Calendar.YEAR);
		
		item.label = String.valueOf(iYear);
		item.value = String.valueOf(iYear);
		combList.add(item); 
		
		iYear--;

		item = new ListDto();
		item.label = String.valueOf(iYear);
		item.value = String.valueOf(iYear);
		combList.add(item); 
		
		return combList;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	private List<ListDto> getMonthComb() {
		
		List<ListDto> combList = new ArrayList<ListDto>();
		
		for (int i = 1 ; i <= 12 ;i++) {
			ListDto item = new ListDto();
			item.label = String.valueOf(i);
			item.value = String.valueOf(i);
			combList.add(item); 
		}
		
		return combList;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 */
	private List<ListDto> getKensakuComb() {
		
		ListDto item = new ListDto();
		List<ListDto> combList = new ArrayList<ListDto>();
		
		item.label = "未申請";
		item.value = "0";
		combList.add(item); 

		item = new ListDto();
		item.label = "申請済み";
		item.value = "1";
		combList.add(item); 
		
		item = new ListDto();
		item.label = "全て";
		item.value = "2";
		combList.add(item); 
		
		return combList;
	}

	/**
	 * <p>データ行背景色決定メソッド</p>
	 * @return <TR>の背景色 CSSクラス
	 */
	private List<payDto> getTranList(int empID,int kai) {
		
		List<transDto> retList;
		
		retList = tenDao.selectByIdList(empID, kai);
		
		List<payDto> list = new ArrayList<payDto>();
		
		for (transDto item : retList ) {
			
			payDto addItem = new payDto();
			
			addItem.empyear = Integer.valueOf(item.empyear);
			addItem.empmonth = Integer.valueOf(item.empmonth);
			addItem.empYM = item.empyear + "/" + item.empmonth;
			
			if (item.tightenflg.equals(0)) {
				addItem.summary = "未申請";
				addItem.uke = "";
			} else {
				addItem.summary = "申請済";
				if (item.approvalflg.equals(0)) {
					addItem.uke = "確認待";
				} else if (item.approvalflg.equals(1)) {
					addItem.uke = "承認中";
				} else if (item.approvalflg.equals(2)) {
					addItem.uke = "受領済";
				}
			}
			
			//金額フォーマット
			addItem.money = CommonUtil.getCurrencyFormat(item.totalMo);

			list.add(addItem);
		}	
		
		return list;
		
	}
	
	/**
	 * <p>チェックボックス描写メソッド</p>
	 * @return チェックボックス描写フラグ
	 */
	public boolean isViewChk() {
		
		boolean ret = true;
		if (!payItems.get(payIndex).summary.equals("未申請")) {
			ret = false;
		}
		
		return ret;
		
	}
	
	/**
	 * <p>チェックボックス描写メソッド</p>
	 * @return チェックボックス描写フラグ
	 */
	public boolean isView2Chk() {
		
		boolean ret = true;
		if (!(payItems.get(payIndex).uke.equals("確認待") 
				|| payItems.get(payIndex).uke.equals(""))) {
			ret = false;
		}
		
		return ret;
		
	}
	
	/**
	 * <p>データ行背景色決定メソッド</p>
	 * @return <TR>の背景色 CSSクラス
	 */
	public String getDatarowStyleClass() {
	    return (payIndex + 1) % 2 == 0 ? "FtrTbl01gray" : "FtrTbl01white";
	}

}
