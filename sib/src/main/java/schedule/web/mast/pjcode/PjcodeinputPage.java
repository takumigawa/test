package schedule.web.mast.pjcode;

import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.TProjectDao;
import schedule.dto.ListDto;
import schedule.entity.TProject;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.validator.UseProjectCodeValidator;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;

/**
 * 
 * @author taku_fujimoto
 *
 */
public class PjcodeinputPage extends PageBaceClass {
	
	public TProjectDao proDao;
	
	@PageScope
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_ESUJI, messageId = "err.NoMatchEISU")
	@UseProjectCodeValidator(target="doFinishUpdate", messageId= "err.UseProjectCode")
	private String projectCodeStr;
	
	@PageScope
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	private String projectNameStr;
		
	//更新メッセージ
	private String updateMes;

	/** 有効/無効値 */
	@PageScope
	@Required
	public Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	public List<ListDto> kaihaiItems;
	
	/** プロジェクトコード セッタ/ゲッタ */
	public String getProjectCodeStr() {
		return projectCodeStr;
	}
	public void setProjectCodeStr(String projectCodeStr) {
		this.projectCodeStr = projectCodeStr;
	}

	/** プロジェクト名 セッタ/ゲッタ */
	public String getProjectNameStr() {
		return projectNameStr;
	}
	public void setProjectNameStr(String projectNameStr) {
		this.projectNameStr = projectNameStr;
	}
	
	/** 更新メッセージ セッタ/ゲッタ */
	public String getUpdateMes() {
		return updateMes;
	}
	public void setUpdateMes(String updateMes) {
		this.updateMes = updateMes;
	}
	
	/** 改廃index セッタ/ゲッタ */
	public Integer getKaihai() {
		return kaihai;
	}
	public void setKaihai(Integer kaihai) {
		this.kaihai = kaihai;
	}

	/** 改廃 セッタ/ゲッタ */
	public List<ListDto> getKaihaiItems() {
		return kaihaiItems;
	}
	public void setKaihaiItems(List<ListDto> kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}
	
	/** 選択プロジェクトＩＤ */
	@SubapplicationScope
	private String selectID;
	
	/** 選択プロジェクトＩＤ セッタ/ゲッタ */
	public String getSelectID() {
		return this.selectID;
	}
	public void setSelectID(String selectID) {
		this.selectID = selectID;
	}
	
	/**
	 * 
	 * @return
	 */
	public Class<?> initialize() {
		
		try {
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			kaihaiItems = CommonUtil.getServiceToCombData();	
			
			if (this.isUpdate()) {
				//更新
				TProject ety_pro = proDao.selectById(selectID);
				
				this.projectCodeStr = ety_pro.getPjCode();
				this.projectNameStr = ety_pro.getPjName();
				this.kaihai = ety_pro.getKaihai();
				
			}

		} catch (AnLoginException e) {
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			return NonauthorityPage.class;
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
		}
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> prerender() {
		
		//ユーザー情報設定。
		this.setLoginInfo();
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Class<?> doFinishUpdate() {
		
		
		if (this.isUpdate()) {
			//更新
			TProject ety_pro = proDao.selectById(selectID);
			
			//プロジェクト名
			ety_pro.setPjName(projectNameStr);
			
			//改廃
			ety_pro.setKaihai(kaihai);
			
			//更新日付、ユーザーＩＤ
			ety_pro.setUpddate(CommonUtil.getNowUpdDate());
			ety_pro.setUpdid(this.sessionDto.EmployeeID);
			
			//更新処理
			proDao.update(ety_pro);
			
			updateMes = "更新処理が完了しました。";
			
		} else {
			//新規
			TProject ety_pro = new TProject();

			//プロジェクトコード、プロジェクト名
			ety_pro.setPjCode(projectCodeStr);
			ety_pro.setPjName(projectNameStr);
			
			//改廃
			ety_pro.setKaihai(0);
			
			//新規追加日付、ユーザーＩＤ
			ety_pro.setAdddate(CommonUtil.getNowUpdDate());
			ety_pro.setAddid(this.sessionDto.EmployeeID);
			
			//追加処理
			proDao.insert(ety_pro);
			
			updateMes = "新規追加処理が完了しました。";
			
		}
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> doFinishReturn() {
		return PjcodelistPage.class;
	}
	
	/**
	 * <p>画面登録/新規切り替え判定メソッド</p>
	 * @return true 更新/false 新規
	 *
	 */
	public boolean isUpdate(){
		
		if("".equals(selectID)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * マスタメンテナンス用レイアウト適用
	 * @return
	 */
	public String getLayout(){
		return CommonConstants.MAST_LAOUT_URL;
	}
	
}
