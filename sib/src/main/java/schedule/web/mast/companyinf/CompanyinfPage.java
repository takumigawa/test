package schedule.web.mast.companyinf;

import org.seasar.teeda.extension.annotation.validator.Email;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MCompanyDao;
import schedule.dao.TSettingDao;
import schedule.dto.ListDto;
import schedule.entity.MCompany;
import schedule.exception.AnLoginException;
import schedule.exception.NoMastaMenteAuthException;
import schedule.pagebace.PageBaceClass;
import schedule.web.common.CommonConstants;
import schedule.web.common.CommonUtil;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;
import schedule.web.masterMaintenance.MastermaintePage;

/**
 * <p>
 * 会社情報更新PageClass
 * </p>
 * 
 * @author T.fujimoto
 * 
 */
public class CompanyinfPage extends PageBaceClass {

	/** 入力項目変数 */
	@Required(target="doEntrydd", messageId= "err.NoInputData")
	@Length(target="doEntrydd", maximum = 16)
	public String companyNameStr;
	
	@Length(target="doEntrydd", maximum = 16)
	public String companyPresidentNameStr;
	public String PresidentCd;
	public ListDto[] PresidentCdItems;
	
	@Length(target="doEntrydd", maximum = 8)
	@RegularExpression(target="doEntrydd", pattern = CommonConstants.REG_POSTCODE_CHECK, messageId = "err.NoMatchPostCodeMes")
	public String companyPostCodeStr;
	
	@Length(target="doEntrydd", maximum = 32)
	public String companyAddress1Str;
	
	@Length(target="doEntrydd", maximum = 16)
	public String companyAddress2Str;
	
	@Length(target="doEntrydd", maximum = 20)
	@RegularExpression(target="doEntrydd", pattern = CommonConstants.REG_TELNO_CHECK, messageId = "err.NoMatchTelNoMes")
	public String companyTelStr;
	
	@Length(target="doEntrydd", maximum = 20)
	@RegularExpression(target="doEntrydd", pattern = CommonConstants.REG_TELNO_CHECK, messageId = "err.NoMatchTelNoMes")
	public String companyFaxStr;
	
	@Length(target="doEntrydd", maximum = 20)
	@Email(target="doEntrydd", messageId = "err.NoMatchEMailMes")
	public String companyemailStr;

	/** 更新メッセージ表示用 */
	public String UpdateMes;

	/** 会社情報マスタDao */
	public MCompanyDao comDao;

	/** 汎用登録テーブルDao */
	public TSettingDao setDao;

	/**
	 * <p>
	 * 画面初期化メソッド
	 * </p>
	 * 
	 * @return 自画面遷移
	 * 
	 */
	public Class<?> initialize() {

		try {

			//セッション情報チェック
			this.chkSession();
			
			//マスタメンテナンス権限チェック
			this.chkMastermainte();
			
			// 代表者の役職名リスト作成
			PresidentCdItems = setDao.selectByCombList("class");

			// 会社情報初期表示
			MCompany ety_com[] = comDao.selectAll();

			companyNameStr = ety_com[0].companyName; // 会社名
			companyPresidentNameStr = ety_com[0].presidentName; // 代表者名
			companyPostCodeStr = ety_com[0].postCode; // 郵便番号
			companyAddress1Str = ety_com[0].address1; // 住所
			companyAddress2Str = ety_com[0].address2; // 建物名
			companyTelStr = ety_com[0].tel; // 電話番号
			companyFaxStr = ety_com[0].fax; // FAX
			companyemailStr = ety_com[0].email; // メールアドレス

			if (ety_com[0].classId != null) {
				PresidentCd = ety_com[0].classId.toString();
			} else {
				PresidentCd = null;
			}
			
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
	 * <p>
	 * 社員マスタ一覧Pageクラス-事前描写
	 * </p>
	 * 
	 * @param なし
	 * @return 自ページ遷移
	 */
	public Class<?> prerender() {

		return null;
	}

	/**
	 * <p>
	 * 会社情報更新Pageクラス
	 * </p>
	 * 
	 * @param なし
	 * @return 自ページ遷移
	 */
	public Class<?> doEntrydd() {

		try {

			// 会社情報はID『1』固定とする
			MCompany ety_com = comDao.selectById(1);

			ety_com.companyName = companyNameStr;
			ety_com.presidentName = companyPresidentNameStr;

			// 代表者の役職名IDの型変更
			try {
				ety_com.classId = Integer.parseInt(PresidentCd);
			} catch (Exception e) {
				ety_com.classId = null;
			}

			ety_com.postCode = companyPostCodeStr;
			ety_com.address1 = companyAddress1Str;
			ety_com.address2 = companyAddress2Str;
			ety_com.tel = companyTelStr;
			ety_com.fax = companyFaxStr;
			ety_com.email = companyemailStr;

			ety_com.upddate = CommonUtil.getNowUpdDate();
			ety_com.updid = sessionDto.EmployeeID;

			// 更新処理
			comDao.update(ety_com);

			// 更新後、フッダの会社情報更新
			sessionDto.CompanyName = ety_com.companyName;
			sessionDto.CompanyPostCode = ety_com.postCode;
			sessionDto.CompanyAddress1 = ety_com.address1;
			sessionDto.CompanyAddress2 = ety_com.address2;
			sessionDto.CompanyTel = ety_com.tel;
			sessionDto.CompanyFax = ety_com.fax;

			UpdateMes = "会社情報マスタの更新が終了しました。";

		} catch (Exception e) {
			return AbendPage.class;
		}

		return null;
	}

	/**
	 * <p>
	 * 戻るボタン押下時
	 * </p>
	 * 
	 * @param なし
	 * @return マスターメンテナンス一覧画面ページ遷移
	 */
	public Class<?> doReturn() {
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
