package schedule.web.mast.classlist;

import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.TSettingDao;
import schedule.dto.ListDto;
import schedule.entity.TSetting;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;

/**
 * <p>役職マスタ新規/更新Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class ClassinputPage extends PageBaceClass {
	
	/** 選択社員ＩＤ */
	@SubapplicationScope
	public Integer selectclassID;
	
	/** 入力項目変数 */
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 10)
	public String ClassNameStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 6)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_SUJI, messageId = "err.NoMatchEISU")
	public String orderSEQStr;
	
	/** 有効/無効値 */
	@PageScope
	@Required
	private Integer kaihai;
	/** 有効/無効一覧 */
	@PageScope
	private List<ListDto> kaihaiItems;
	
	/** 更新メッセージ表示用 */
	public String UpdateMes;
	
	/** HTML処理判別用 */
	public String dOru;
	
	/** エラーメッセージ用 */
	public String retErrMess;

	/** 汎用登録テーブルDao */
	public TSettingDao setDao;
	
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
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doFinishUpdate() {
		
		try{
			
			TSetting ety_set;
			
			if(selectclassID == -1){
				
				//役職情報新規作成
				
				ety_set = new TSetting();
				
				ety_set.key1			= "comblist";
				ety_set.key2			= "class";
				ety_set.key3			= String.valueOf(setDao.getClassNextID());

				ety_set.stringvalue 	= ClassNameStr;
				ety_set.integervalue	= Integer.parseInt(orderSEQStr);
						
				setDao.insert(ety_set);
				
			}else{
				
				//役職情報更新
				
				ety_set = setDao.selectById("comblist","class",selectclassID.toString());
				
				ety_set.stringvalue 	= ClassNameStr;
				
				if (kaihai.equals(0)) {
					if (orderSEQStr.equals("0")) {
						ety_set.integervalue	= setDao.getClassNextOrderSEQ();
					} else {
						ety_set.integervalue	= Integer.parseInt(orderSEQStr);
					}
					
				} else {
					ety_set.integervalue	= 0;
				}
				
				setDao.update(ety_set);
			}
			
		}catch(Exception e){
			return AbendPage.class;
			
		}

		return ClasslistPage.class;
		
	}

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doFinishReturn() {
		return ClasslistPage.class;
	}

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
			kaihaiItems = CommonUtil.getServiceToCombData();
			
			if(selectclassID == -1){
				
				orderSEQStr = String.valueOf(setDao.getClassNextOrderSEQ());
				
			}else{
				
				TSetting ety_set = setDao.selectById("comblist","class",selectclassID.toString());
				
				ClassNameStr 		= ety_set.stringvalue;
				orderSEQStr 		= String.valueOf(ety_set.integervalue);
				
				if (ety_set.integervalue.equals(0)) {
					kaihai = 1;
				} else {
					kaihai = 0;
				}
				
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

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		return null;
	}

	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public boolean isUpdate(){
		
		if(selectclassID == -1){
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
