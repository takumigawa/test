package ste.pagebace;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;

import ste.dto.SessionDto;
import ste.exception.AnLoginException;
import ste.exception.NoMastaMenteAuthException;
import ste.service.SibServiceAdapter;

/**
 * 各システムのページクラスの基底となるクラス
 * @author　t.fujimoto
 */
public class PageBaceClass {

	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;

	/** メッセージ出力用 */
	@PageScope
	protected String errMes;
	
	/** ログイン社員情報 */
	@PageScope
	protected String employee_id;
	@PageScope
	protected String employee_name;
	@PageScope
	protected String employee_mail;

	/**
	 * ベースクラスのインストラクタ
	 * @author t.fujimoto
	 */
	public PageBaceClass(){

	}
	/** 社員IDのセッタ */
	public void setEmployee_id(String iId){
		employee_id = iId;
	}
	/** 社員IDのゲッタ */
	public String getEmployee_id() {
		return employee_id;
	}
	/** 社員名のセッタ */
	public void setEmployee_name(String iId){
		employee_name = iId;
	}
	/** 社員名のゲッタ */
	public String getEmployee_name() {
		return employee_name;
	}
	/** メールアドレスのセッタ */
	public void setEmployee_mail(String iId){
		employee_mail = iId;
	}
	/** メールアドレスｎゲッタ */
	public String getEmployee_mail() {
		return employee_mail;
	}

	/** メッセージセッタ・ゲッタ */
	public String getErrMes() {
		return errMes;
	}
	public void setErrMes(String errMes) {
		this.errMes = errMes;
	}
	
	/**
	 * セッション情報のログインチェックを行う。
	 * @author t.fujimoto
	 * @throws AnLoginException セッション情報にログイン情報がない場合、スローされる。
	 */
	protected void chkSession() throws AnLoginException {

		// 未ログインユーザーをはじく処理
		if (sessionDto.EmployeeID == null) {
			throw new AnLoginException();
		}
	}

	/**
	 * マスタメンテナンス権限チェックを行う。
	 * @author t.fujimoto
	 * @throws NoMastaMenteAuthException セッション情報にマスタメンテ変更権限が無い場合、スローされる。
	 */
	protected void chkMastermainte() throws NoMastaMenteAuthException {
		//マスターメンテ管理未許可ユーザーをはじく処理
		if(sessionDto.MastermainteFlg == false){
			throw new NoMastaMenteAuthException();
		}
	}

	/**
	 * 他サービスからデータを取得するメソッド
	 * @param serviceType　サービスのタイプを指定する
	 * @param requestParams　サービスへのポストデータを指定する
	 * @return　サービスからの指定データが返却される。
	 * @throws Exception　例外がスローされる。
	 */
	protected Map<String, String> getServiceValue(String serviceType, Map<String, String> requestParams) throws Exception {

		//サービスアダプタクラスをインスタンスする
		SibServiceAdapter SAdp = new SibServiceAdapter();

		//サービスプロバイダのURLを設定する。
		SAdp.setServiceProvider("http://133.242.134.145:8080/sibserviceadapter/view/adapter/adapter.html");
		//サービスプロバイダのタイプを設定する。
		SAdp.setServiceProviderType(serviceType);

		//データをポストし、戻りのマップデータを返却する。
		return SAdp.postData(requestParams);

	}

	/**
	 * 他サービスからデータを取得するメソッド
	 * @param serviceType サービスのタイプを指定する
	 * @return サービスからの指定データが返却される。
	 * @throws Exception 例外がスローされる。
	 */
	protected Map<String, String> getServiceValue(String serviceType) throws Exception {

		Map<String, String> reqParam = new HashMap<String, String>();

		return this.getServiceValue(serviceType, reqParam);
	}

	/**
	 * ログイン情報を設定する
	 */
	protected void setLoginInfo() {

		//ログイン情報
//		employee_id 	= sessionDto.EmployeeID.toString();
//		employee_name	= sessionDto.EmployeeName;
//		employee_mail	= sessionDto.EmployeeEmail;

		employee_id 	= "0";
		employee_name	= "馬場・サンフランシスコ・友康jr";
		employee_mail	= "baba@sib.co.jp";

	}

}
