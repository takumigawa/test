package schedule.web.mast.grouplist;

import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MGroupDao;
import schedule.dao.TSettingDao;
import schedule.dto.GroupListDto;
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
 * <p>グループマスタ一覧Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class GrouplistPage extends PageBaceClass {
	
	/** 入力項目変数 */
	public String groupName;
	public String groupauthority;
	public String groupSEQ;
	public String groupEnabled;
	
	/** グループ一覧Index */
	public int groupIndex;
	/** グループ一覧 */
	@PageScope
	public GroupListDto[] groupItems;
	
	/** 有効/無効値 */
	@PageScope
	@Required
	public Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	public ListDto[] kaihaiItems;

	/** 社員ID */
	@PageScope
	public String employeeID;
	/** 社員一覧 */
	@PageScope
	public ListDto[] employeeIDItems;
	
	/** 行絞り込み値 */
	@PageScope
	@Required
	public String kanaGyo;
	/** 行絞り込み一覧 */
	@PageScope
	public ListDto[] kanaGyoItems;
	
	/** グループマスタDao */
	public MGroupDao gruDao;
	
	/** 汎用登録テーブルDao */
	public TSettingDao setDao;
	
	/** 選択案件ＩＤ */
	@SubapplicationScope
	public Integer selectgruID;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {
				
		try{
			
			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			//検索条件-有効/無効コンボ
			kaihaiItems = setDao.selectByCombList(CommonConstants.COMB_kaihai);

			//検索条件-ひらがな検索コンボ
			kanaGyoItems = setDao.selectByCombList(CommonConstants.COMB_kana);
						
			//社員一覧
			groupItems = getAuthValue(gruDao.selectGetGroupList(null,CommonConstants.Kaihai_true));
			
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

	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		
		//ログイン情報
		this.setLoginInfo();
		
		return null;
	}
	
	/**
	 * <p>一覧絞り込みメソッド/p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doSrech(){
		
		//社員一覧
		groupItems = gruDao.selectGetGroupList(kanaGyo, kaihai);
		return null;
	}
	
	/**
	 * <p>一覧絞り込み解除メソッド/p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doSrechCancel(){
		kaihai		= CommonConstants.Kaihai_true;
		kanaGyo 	= CommonConstants.COMB_kaihai_init;
		employeeID	= null;
		
		//社員一覧
		groupItems = gruDao.selectGetGroupList(kanaGyo, kaihai);
		return null;
	}
			
	/**
	 * <p>社員編集メソッド/p>
	 * @return 社員編集/登録画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> doDetails(){
	
		try{
			selectgruID = Integer.parseInt(groupItems[groupIndex].groupID );

		}catch(Exception e){
			return AbendPage.class;
		}
			
		return GroupinputPage.class;
	}

	/**
	 * <p>社員追加メソッド</p>
	 * @return 社員編集/登録画面遷移
	 *
	 */
	public Class<?> doEmployeeAdd(){
		selectgruID = -1;
		return GroupinputPage.class;
	}
	
	/**
	 * <p>前画面以降メソッド</p>
	 * @return マスターメンテナンス一覧画面遷移
	 *
	 */
	public Class<?> doReturn(){
		
		return MastermaintePage.class;
	}
	
	private GroupListDto[] getAuthValue(GroupListDto[] groupItems) {

		Integer iAuth;
		Integer intChkAuthV;
		List<ListDto> authItems = setDao.selectGetAuthList();

		for (GroupListDto grItem : groupItems) {
			
			iAuth = Integer.valueOf(grItem.groupauthority);
			grItem.groupauthority = "";

			for (ListDto item : authItems) {
				
				intChkAuthV = Integer.valueOf(item.value);
				
				if ((iAuth & intChkAuthV) == intChkAuthV) {
					if (!"".equals(grItem.groupauthority)) {
						grItem.groupauthority += "<br/>";
					}
					grItem.groupauthority += item.label;
				}
			}
			
		}
			
		return groupItems;
	}
	
	/**
	 * マスタメンテナンス用レイアウト適用
	 * @return
	 */
	public String getLayout(){
		return CommonConstants.MAST_LAOUT_URL;
	}
}
