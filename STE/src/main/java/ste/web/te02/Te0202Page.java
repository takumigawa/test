package ste.web.te02;

import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import ste.common.CommonConstants;
import ste.common.CommonUtil;
import ste.dao.TTightenDao;
import ste.dao.TTransexpDao;
import ste.dto.expDto;
import ste.entity.TTighten;
import ste.entity.TTransexp;
import ste.pagebace.PageBaceClass;

public class Te0202Page extends PageBaceClass {

	@SubapplicationScope
	public String empyear;
	@SubapplicationScope
	public String empmonth;
	
	@PageScope
	private int expIndex;
	@PageScope
	private List<expDto> expItems;
	
	@Required(target="doUpdate", messageId= "err.NoInputData")
	@Length(target="doUpdate", maximum = 2)
	@RegularExpression(target="doUpdate", pattern = CommonConstants.REG_HANKAKU_SUJI, messageId = "err.NoMatchTelNoMes")
	private String applicationdate;
	
	@Required(target="doUpdate", messageId= "err.NoInputData")
	@Length(target="doUpdate", maximum = 30)
	private String summary;

	private String sectionfrom;
	
	private String sectionto;
	
	@Required(target="doUpdate", messageId= "err.NoInputData")
	@Length(target="doUpdate", maximum = 5)
	@RegularExpression(target="doUpdate", pattern = CommonConstants.REG_HANKAKU_SUJI, messageId = "err.NoMatchTelNoMes")
	private Integer money;
	
	private String chk;
	
	private String retChk;
	
	//申請状況Dao
	public TTightenDao tenDao;
	
	public TTransexpDao trsDao;

	public String getEmpyear() {
		return empyear;
	}

	public void setEmpyear(String empyear) {
		this.empyear = empyear;
	}

	public String getEmpmonth() {
		return empmonth;
	}

	public void setEmpmonth(String empmonth) {
		this.empmonth = empmonth;
	}

	public int getExpIndex() {
		return expIndex;
	}

	public void setExpIndex(int expIndex) {
		this.expIndex = expIndex;
	}

	public List<expDto> getExpItems() {
		return expItems;
	}

	public void setExpItems(List<expDto> expItems) {
		this.expItems = expItems;
	}

	public String getApplicationdate() {
		return applicationdate;
	}

	public void setApplicationdate(String applicationdate) {
		this.applicationdate = applicationdate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSectionfrom() {
		return sectionfrom;
	}

	public void setSectionfrom(String sectionfrom) {
		this.sectionfrom = sectionfrom;
	}

	public String getSectionto() {
		return sectionto;
	}

	public void setSectionto(String sectionto) {
		this.sectionto = sectionto;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
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
	public Class<?> doUpdate() {
		
		for(expDto item : expItems) {
			
			//trsDao.update(item);
		
		}
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doDelete() {
		
		TTighten etyTen = tenDao.selectById(Integer.valueOf(employee_id), Integer.valueOf(empyear), Integer.valueOf(empmonth));

		//申請書削除
		tenDao.delete(etyTen);
		
		//明細削除
		trsDao.deleteById(Integer.valueOf(employee_id), Integer.valueOf(empyear), Integer.valueOf(empmonth));
		
		return Te0201Page.class;
	}

	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doRowDelete() {

		errMes = "";
		
		//選択インデックス
		String retIndex[] = retChk.split(",");
		
		for (int i = 0 ; i < retIndex.length ; i++) {
			
			//TTransexp etyExp = expItems.get(Integer.valueOf(retIndex[i]));
			
			//trsDao.delete(etyExp);
			
		}
		
		this.expItems = null;
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doCopy() {

		errMes = "";
		
		String retIndex[] = retChk.split(",");
		
		for (int i = 0 ; i < retIndex.length ; i++) {
			
			//TTransexp etyExp = expItems.get(Integer.valueOf(retIndex[i]));
			
			//etyExp.setBranchno(trsDao.selectByIdNextBrounch(Integer.valueOf(employee_id), Integer.valueOf(empyear), Integer.valueOf(empmonth)));
			//etyExp.setAdddate(CommonUtil.getNowUpdDate());
			//etyExp.setAddid(Integer.valueOf(employee_id));
			
			//trsDao.insert(etyExp);
			
		}
		
		this.expItems = null;
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doAdd() {
		
		TTransexp etyExp = new TTransexp();
		
		etyExp.setEmpid(Integer.valueOf(employee_id));
		etyExp.setEmpyear(Integer.valueOf(empyear));
		etyExp.setEmpmonth(Integer.valueOf(empmonth));
		etyExp.setBranchno(trsDao.selectByIdNextBrounch(Integer.valueOf(employee_id), Integer.valueOf(empyear), Integer.valueOf(empmonth)));
		etyExp.setApplicationdate("1");
		etyExp.setMoney(0);
		etyExp.setSectionfrom("");
		etyExp.setSectionto("");
		etyExp.setAdddate(CommonUtil.getNowUpdDate());
		etyExp.setAddid(Integer.valueOf(employee_id));
		
		trsDao.insert(etyExp);
		
		
		return null;
	}

	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doReturn() {
		
		return Te0201Page.class;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doReflash() {
		return null;
	} 
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doCancel() {

		errMes = "";
		
		TTighten etyTen = tenDao.selectById(Integer.valueOf(employee_id), Integer.valueOf(empyear), Integer.valueOf(empmonth));
		
		
		if (etyTen.getApprovalflg().equals(0)) {
			
			etyTen.setTightenflg(0);
			etyTen.setUpddate(CommonUtil.getNowUpdDate());
			etyTen.setUpdid(Integer.valueOf(employee_id));
			
			tenDao.update(etyTen);
			
			return Te0201Page.class;
			
		} else {
			errMes = "受付状態が『確認待』以外の為、取り消しできません。";
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
		
		return null;
	}

	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
						
		this.expItems = trsDao.selectByIdSin(Integer.valueOf(employee_id), Integer.valueOf(empyear), Integer.valueOf(empmonth));
		
		return null;
	}
	
	/**
	 * <p>チェックボックス描写メソッド</p>
	 * @return チェックボックス描写フラグ
	 */
	public boolean isViewChk() {
		
		boolean ret = true;
		
		TTighten etyTen = tenDao.selectById(Integer.valueOf(employee_id), Integer.valueOf(empyear), Integer.valueOf(empmonth));
		
		if (!etyTen.getTightenflg().equals(0)) {
			ret = false;
		}
		
		return ret;
		
	}
	
}
