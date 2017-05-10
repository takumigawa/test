package schedule.web.mast.grouplist;

import java.util.ArrayList;
import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MGroupDao;
import schedule.dao.TSettingDao;
import schedule.dto.ListDto;
import schedule.entity.MGroup;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;

/**
 * <p>社員マスタ新規/更新Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class GroupinputPage extends PageBaceClass {
	
	/** 選択グループＩＤ */
	@SubapplicationScope
	public Integer selectgruID;
	
	/** 入力項目変数 */
	public String GroupIDStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 30)
	public String GroupNameStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 60)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_KANA, messageId = "err.NoMatchHANKANA")
	public String GroupNamekanaStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 6)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_SUJI, messageId = "err.NoMatchEISU")
	public String GroupSEQStr;
	
	/** 権限オプション */
	private String[] option;
	private List<ListDto> optionItems;
	
	/** 有効/無効値 */
	@PageScope
	@Required
	private Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	private List<ListDto> kaihaiItems;
	
	/** 更新メッセージ表示用 */
	public String UpdateMes;
		
	/** 改廃index　セッタ/ゲッタ */
	public void setKaihai(Integer kaihai){
		this.kaihai = kaihai;
	}
	public Integer getKaihai(){
		return this.kaihai;
	}
	
	/** 改廃　セッタ/ゲッタ */
	public void setKaihaiItems(List<ListDto> kaihaiItems){
		this.kaihaiItems = kaihaiItems;
	}
	public List<ListDto> getKaihaiItems(){
		return this.kaihaiItems;
	}
	
	/** 権限index　セッタ/ゲッタ */
	public String[] getOption() {
		return option;
	}
	public void setOption(String[] option) {
		this.option = option;
	}
	
	/** 権限　セッタ/ゲッタ */
	public List<ListDto> getOptionItems() {
		return optionItems;
	}
	public void setOptionItems(List<ListDto> optionItems){
		this.optionItems = optionItems;
	}

	/** グループマスタDao */
	public MGroupDao gruDao;
	
	/** セッティングDao */
	public TSettingDao setDao;
	
	/**
	 * <p>更新メソッド</p>
	 * @return 社員一覧画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> doFinishUpdate(){
		
		try{
			
			MGroup ety_gru;
			
			if(selectgruID == -1){
				
				//社員情報新規作成
				
				ety_gru = new MGroup();
				
				ety_gru.name		= GroupNameStr;
				ety_gru.nameKana	= GroupNamekanaStr;
				ety_gru.authority = 0;
				
				for (String autiItem : option) {
					ety_gru.authority	+= Integer.parseInt(autiItem);
				}
								
				if(GroupSEQStr != null){
					ety_gru.orderseq		= Integer.parseInt(GroupSEQStr);
				}else{
					ety_gru.orderseq		= Integer.parseInt(GroupSEQStr);
				}
				
				ety_gru.kaihai			= CommonConstants.Kaihai_true;
				
				ety_gru.adddate 		= CommonUtil.getNowUpdDate();
				ety_gru.addid			= sessionDto.EmployeeID;
				
				gruDao.insert(ety_gru);
				
				UpdateMes               = "新規追加しました。";
				
			}else{
				
				//グループ情報更新
				
				ety_gru = gruDao.selectById(selectgruID);
				
				ety_gru.name		= GroupNameStr;
				ety_gru.nameKana	= GroupNamekanaStr;
				
				ety_gru.authority = 0;
				
				for (String autiItem : option) {
					ety_gru.authority	+= Integer.parseInt(autiItem);
				}
				
				ety_gru.orderseq	= Integer.parseInt(GroupSEQStr);
				
				ety_gru.kaihai      = kaihai;
			
				ety_gru.upddate 	= CommonUtil.getNowUpdDate();
				ety_gru.updid		= sessionDto.EmployeeID;
				
				gruDao.update(ety_gru);
				
				UpdateMes               = "更新しました。";

			}
			
		}catch(Exception e){
			return AbendPage.class;
			
		}

		return null;
		
	}

	/**
	 * <p>戻るメソッド</p>
	 * @return 社員一覧画面遷移
	 *
	 */
	public Class<?> doFinishReturn() {
		return GrouplistPage.class;
	}

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
			kaihaiItems = CommonUtil.getServiceToCombData();
			
			//権限オプション設定
			optionItems = setDao.selectGetAuthList();
			
			if(selectgruID == -1){

				GroupSEQStr = String.valueOf(gruDao.getNextOrderSEQ());
				
			}else{
				
				MGroup ety_gru = gruDao.selectById(selectgruID);
				
				GroupIDStr			= String.valueOf(ety_gru.groupId);
				GroupNameStr		= ety_gru.name ;
				GroupNamekanaStr	= ety_gru.nameKana;
				option				= getAuthValue(ety_gru.authority);
				GroupSEQStr			= String.valueOf(ety_gru.orderseq);	
				kaihai              = ety_gru.kaihai;
				
			}

		} catch (AnLoginException e) {
			// 未ログインエラー
			return NonloginPage.class;
			
		} catch (NoMastaMenteAuthException e) {
			//マスタメンテ権限エラー
			return NonauthorityPage.class;
			
		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}
	
	private String[] getAuthValue(Integer iAuth) {
		
		String[] retAry;
		Integer intChkAuthV;
		List<ListDto> authItems = setDao.selectGetAuthList();
		List<String> retList = new ArrayList<String>();
		
		for (ListDto item : authItems) {
			
			intChkAuthV = Integer.valueOf(item.value);
			
			if ((iAuth & intChkAuthV) == intChkAuthV) {
				retList.add(item.value);
			}
		}
		
		retAry = new String[retList.size()];
		
		for(int i = 0;i < retList.size();i++) {
			retAry[i] = retList.get(i);
		}
		
		return retAry;
	}

	/**
	 * <p>事前描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		return null;
	}

	/**
	 * <p>画面登録/新規切り替え判定メソッド</p>
	 * @return true 更新/false 新規
	 *
	 */
	public boolean isUpdate(){
		
		if(selectgruID == -1){
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
