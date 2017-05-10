package sibpayadvice.common;

/**
 * <p>共通定数クラス</p>
 * @author T.fujimoto
 *
 */
public class CommonConstants {

	/**************************************************
	 * 正規表現
	 **************************************************/
	
	/**　半角記号	 */
	public static final String REG_HANKAKU_KIGOU 	= "\\p{Punct}";

	/** 全角記号 */
	public static final String REG_ZENKAKU_KIGOU 	= "！”＃＄％＆’（）＝～｜‘｛＋＊｝＜＞？＿－＾￥＠「；：」、。・";
	
	/**　半角英数字 */
	public static final String REG_HANKAKU_ESUJI 	= "^[a-zA-Z0-9+#-]*$";
	
	/** 半角数字 */
	public static final String REG_HANKAKU_SUJI 	= "^[0-9]*$";
	
	/** 全角かな */
	public static final String REG_ZENKAKU_KANA 	= "^[\\u3040-\\u309F]*$";
	
	/** 半角カナ */
	public static final String REG_HANKAKU_KANA 	= "^[ｦ-ﾟ ]*$";
	
	/** 半角スペース */
	public static final String REG_HANKAKU_SPACE 	= "[\\s]+";
	
	/** 全角スペース */
	public static final String REG_ZENKAKU_SPACE 	= "[　]+";
	
	/** 日付（YYYY/MM/DD） */
	public static final String REG_DAYFORMAT_CHECK 	= "[0-9]{4}/[0-9]{2}/[0-9]{2}";

	/** 郵便番号 */
	public static final String REG_POSTCODE_CHECK 	= "\\d{3}-\\d{4}";
	
	/** 電話番号 */
	public static final String REG_TELNO_CHECK 	= "\\d{2,4}-\\d{2,4}-\\d{4}";
	
	/**************************************************
	 * コンボボックス用コード値
	 **************************************************/

	/** 有効/無効 */
	public static final String COMB_kaihai 		= "kaihai";
	
	/** 名前絞り込み */
	public static final String COMB_kana 		= "kana";
	
	/** 役職 */
	public static final String COMB_class 		= "class";
	
	/**************************************************
	 * コンボボックス初期化用
	 **************************************************/

	/** 有効/無効 */
	public static final String COMB_kaihai_init = "between 'ｦ' and 'ﾟ'";
	
	/**************************************************
	 * 定数
	 **************************************************/

	/** 改廃-有効 */
	public static final Integer Kaihai_true 	= 0;

	/** 改廃-無効 */
	public static final Integer Kaihai_false 	= 1;
	
	/** 文字列定数-空白 */
	public static final String Moji_Empty 	= "";	
	
	/** 文字列定数-郵便番号 */
	public static final String Moji_PostMark 	= "〒";	

	/** 文字列定数-電話 */
	public static final String Moji_TEL 	= "TEL";	
	
	/** 文字列定数-FAX */
	public static final String Moji_FAX 	= "FAX";	

	/** 文字列定数-true */
	public static final String STR_TRUE 	= "true";	
	
	/** 文字列定数-false */
	public static final String STR_FALSE 	= "false";	
	
	/**************************************************
	 * 汎用テーブルKey
	 **************************************************/
	
	/** 大項目-『system』 */
	public static final String k1_system 		= "system";

	/** 大項目-『comblist』 */
	public static final String k1_comblist 		= "comblist";
	
	/** 中項目-『役職コンボ用』 */
	public static final String k2_class 		= "class";

	/** 中項目-『改廃コンボ用』 */
	public static final String k2_kaihai 		= "kaihai";
	
	/** 中項目-『権限グループ』 */
	public static final String k2_authority 	= "authority";

	/** 小項目-『マスターメンテナンス権限』 */
	public static final String k3_mastermainte 	= "mastermainte";
	
	/** 小項目-『グループ外実行権限』 */
	public static final String k3_groupexecute	= "groupexecute";

	/** 小項目-『小口管理権限』 */
	public static final String k3_kogutikanri	= "kogutikanri";
	
	/**************************************************
	 * サービスタイプ
	 **************************************************/
	/** ユーザ認証 */
	public static final String SERVICE_TYPE_USERAUTH	= "userAuth";
	
	/** ユーザ情報 */
	public static final String SERVICE_TYPE_USERINFO	= "userInfo";
	
	/** 会社情報 */
	public static final String SERVICE_TYPE_COMPANYINFO	= "companyInfo";

	/** コンボリスト情報 */
	public static final String SERVICE_TYPE_COMBLIST	= "combList";
	
	/**************************************************
	 * エラーメッセージ
	 **************************************************/

	/** エラーメッセージ-置換ワード */
	public static final String cmt_repWord			= "&%rep%&";
	
	/** エラーメッセージ-改行 */
	public static final String cmt_crlf			= "\n";
	
	/** エラーメッセージ-グループ編集 */
	public static final String cmt_groupretach1	= "以下の社員は『&%rep%&』グループがデフォルトグループに設定されています。";

	/** エラーメッセージ-グループ編集 */
	public static final String cmt_groupretach2	= "デフォルトグループからは削除できません。確認して再度実行してください。";

	/** エラーメッセージ-社員編集 */
	public static final String cmt_employeeenb1	= "有効に設定しようとしてる社員『&%rep%&』の";
	
	/** エラーメッセージ-社員編集 */
	public static final String cmt_employeeenb2	= "役職『&%rep%&』が無効に設定されています。";
	
	/** エラーメッセージ-社員編集 */
	public static final String cmt_employeeenb3	= "デフォルトグループ『&%rep%&』が無効に設定されています。";

	/** エラーメッセージ-社員編集 */
	public static final String cmt_employeeenb4	= "社員を有効にする場合、&%rep%&を先に有効に設定してください。";
	
	/** 小項目-『権限マージ方法』 
	 * <p>return 0:優先グループ権限</p>
	 * <p>       1:グループ結合</p>
	 * <p>       2:グループ重複権限のみ</p>
	 * */
	public static final String k3_groupmarge 	= "groupmarge";
	
	/**
	 * コンストラクタ(インスタンス制御)
	 */
	private CommonConstants() {
	}
}