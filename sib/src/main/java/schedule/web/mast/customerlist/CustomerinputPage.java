package schedule.web.mast.customerlist;

import java.util.List;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Email;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MCustomerDao;
import schedule.dao.TSettingDao;
import schedule.dto.ListDto;
import schedule.entity.MCustomer;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;

/**
 * <p>顧客マスタ新規/更新Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class CustomerinputPage extends PageBaceClass {
	
	/** 選択顧客ＩＤ */
	@SubapplicationScope
	public Integer selectcusID;
	
	/** 入力項目変数 */
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 30)
	public String CustomerNameStr;
	
	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 60)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_KANA, messageId = "err.NoMatchHANKANA")
	public String CustomerNamekanaStr;
	
	@Length(target="doFinishUpdate", maximum = 8)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_POSTCODE_CHECK, messageId = "err.NoMatchPostCodeMes")
	public String CustomerpostcodeStr;
	
	@Length(target="doFinishUpdate", maximum = 60)
	public String Customeraddress1Str;
	
	@Length(target="doFinishUpdate", maximum = 30)
	public String Customeraddress2Str;
	
	@Length(target="doFinishUpdate", maximum = 20)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_TELNO_CHECK, messageId = "err.NoMatchTelNoMes")
	public String CustomertelStr;
	
	@Length(target="doFinishUpdate", maximum = 20)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_TELNO_CHECK, messageId = "err.NoMatchTelNoMes")
	public String CustomerfaxStr;
	
	@Length(target="doFinishUpdate", maximum = 32)
	@Email(target="doFinishUpdate", messageId = "err.NoMatchEMailMes")
	public String CustomeremailStr;
	
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

	/** 顧客マスタDao */
	public MCustomerDao cusDao;
	
	/** 改廃index セッタ/ゲッタ */
	public void setKaihai(Integer kaihai){
		this.kaihai = kaihai;
	}
	public Integer getKaihai(){
		return this.kaihai;
	}
	
	/** 改廃 セッタ/ゲッタ */
	public void setKaihaiItems(List<ListDto> kaihaiItems){
		this.kaihaiItems = kaihaiItems;
	}
	public List<ListDto> getKaihaiItems(){
		return this.kaihaiItems;
	}
	
	/**
	 * <p>更新メソッド</p>
	 * @return 顧客一覧画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> doFinishUpdate(){
		
		try{
			
			MCustomer ety_cus;
			
			if(selectcusID == -1){
				
				//顧客情報新規作成
				ety_cus = new MCustomer();
				
				ety_cus.name 		= CustomerNameStr;
				ety_cus.nameKana 	= CustomerNamekanaStr;
				ety_cus.postcode	= CustomerpostcodeStr;
				ety_cus.address1	= Customeraddress1Str;
				ety_cus.address2	= Customeraddress2Str;
				ety_cus.tel			= CustomertelStr;
				ety_cus.fax			= CustomerfaxStr;
				ety_cus.email 		= CustomeremailStr;
				
				ety_cus.orderseq 	= Integer.parseInt(orderSEQStr);
				ety_cus.kaihai		= CommonConstants.Kaihai_true;
				
				ety_cus.adddate		= CommonUtil.getNowUpdDate();
				ety_cus.addid		= sessionDto.EmployeeID;
				
				cusDao.insert(ety_cus);
				
				UpdateMes = "新規追加しました。";
				
			}else{
				
				//顧客情報新規作成
				ety_cus = cusDao.selectById(selectcusID);
				
				ety_cus.name 		= CustomerNameStr;
				ety_cus.nameKana 	= CustomerNamekanaStr;
				ety_cus.postcode	= CustomerpostcodeStr;
				ety_cus.address1	= Customeraddress1Str;
				ety_cus.address2	= Customeraddress2Str;
				ety_cus.tel			= CustomertelStr;
				ety_cus.fax			= CustomerfaxStr;
				ety_cus.email 		= CustomeremailStr;
				
				ety_cus.orderseq 	= Integer.parseInt(orderSEQStr);
				
				ety_cus.kaihai      = kaihai;
				
				ety_cus.upddate		= CommonUtil.getNowUpdDate();
				ety_cus.updid		= sessionDto.EmployeeID;
				
				cusDao.update(ety_cus);
				
				UpdateMes = "更新しました。";
			}
			
			return null;
	
		}catch(Exception e){
			return AbendPage.class;
			
		}

	}

	/**
	 * <p>戻るメソッド</p>
	 * @return 顧客一覧画面遷移
	 *
	 */
	public Class<?> doFinishReturn() {
		return CustomerlistPage.class;
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
			
			if(selectcusID == -1){
				
				orderSEQStr = String.valueOf(cusDao.getNextOrderSEQ());
				dOru = "Ins";
				
			}else{
				
				MCustomer ety_cus = cusDao.selectById(selectcusID);
				
				CustomerNameStr 	= ety_cus.name;
				CustomerNamekanaStr	= ety_cus.nameKana;
				CustomerpostcodeStr = ety_cus.postcode;
				Customeraddress1Str = ety_cus.address1;
				Customeraddress2Str = ety_cus.address2;
				CustomertelStr		= ety_cus.tel;
				CustomerfaxStr		= ety_cus.fax;
				CustomeremailStr	= ety_cus.email;
				orderSEQStr 		= String.valueOf(ety_cus.orderseq);
				kaihai              = ety_cus.kaihai;
				
				dOru = "Upd";
				
			}

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
		
		if(selectcusID == -1){
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
