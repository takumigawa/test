package schedule.web.mast.classlist;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.TSettingDao;
import schedule.dto.ClassListDto;
import schedule.dto.ListDto;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.web.common.*;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;
import schedule.web.masterMaintenance.MastermaintePage;

/**
 * <p>役職マスタ一覧Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class ClasslistPage extends PageBaceClass {
	
	/** 入力項目変数 */
	public String classID;
	public String className;
	public String classEnabled;

	/** 役職一覧Index */
	public int classIndex;
	/** 役職一覧 */
	@PageScope
	public ClassListDto[] classItems;
	
	/** 有効/無効値 */
	@PageScope
	@Required
	public Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	public ListDto[] kaihaiItems;
	
	/** 汎用登録テーブルDao */
	public TSettingDao setDao;
	
	/** 選択役職ＩＤ */
	@SubapplicationScope
	public Integer selectclassID;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {
			
		try {
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			kaihaiItems = setDao.selectByCombList(CommonConstants.COMB_kaihai);
			
			//社員一覧
			classItems = setDao.selectGetClassList( CommonConstants.Kaihai_true);
			
		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			//未マスタメンテ権限エラー
			return NonauthorityPage.class;
			
		} catch (Exception e) {
			
			return AbendPage.class;
			
		}
		
		return null;
	}

	/**
	 * <p>事前描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		
		//ログイン情報
		this.setLoginInfo();
		
		return null;
	}
		
	/**
	 * <p>一覧絞りこみメソッド</p>
	 * @return　削除画面遷移
	 *
	 */
	public Class<?> doSrech(){
		classItems = setDao.selectGetClassList(kaihai);
		return null;
	}
	
	/**
	 * <p>一覧絞りこみ解除</p>
	 * @return　自画面遷移
	 *
	 */
	public Class<?> doSrechCancel(){
		kaihai		= CommonConstants.Kaihai_true;
		classItems 	= setDao.selectGetClassList(CommonConstants.Kaihai_true);
		return null;
	}
	
	/**
	 * <p>編集メソッド</p>
	 * @return　役職編集画面遷移
	 *
	 */
	public Class<?> doEdit(){
		
		selectclassID = Integer.parseInt(classItems[classIndex].classID );
			
		return ClassinputPage.class;
	}

	/**
	 * <p>新規追加メソッド</p>
	 * @return　役職追加画面遷移
	 *
	 */
	public Class<?> doClassAdd(){
		selectclassID = -1;
		return ClassinputPage.class;
	}
	
	/**
	 * <p>戻るメソッド</p>
	 * @return　マスタメンテナンスメニュー画面遷移
	 */
	public Class<?> doReturn(){
		return MastermaintePage.class;
	}
	
	/**
	 * マスタメンテナンス用レイアウト適用
	 * @return
	 */
	public String getLayout(){
		return CommonConstants.MAST_LAOUT_URL;
	}
}
