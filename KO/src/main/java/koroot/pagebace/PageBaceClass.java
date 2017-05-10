package koroot.pagebace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import koroot.common.CommonConstants;
import koroot.common.CommonUtil;
import koroot.dto.ListDto;
import koroot.dto.SessionDto;
import koroot.exception.AnLoginException;
import koroot.exception.NoKogutiKnariAuthException;
import koroot.exception.NoMastaMenteAuthException;
import koroot.exception.NoProjectAuthException;
import koroot.service.SibServiceAdapter;


import org.seasar.framework.container.annotation.tiger.Binding;


/**
 * 各システムのページクラスの基底となるクラス
 * @author　t.fujimoto
 */
public class PageBaceClass {

	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;

	/** ログイン社員情報 */
	private String employee_id;
	private String employee_name;
	private String employee_mail;

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
	 * プロジェクト管理権限チェックを行う。
	 * @author t.fujimoto
	 * @throws NoProjectAuthException セッション情報にプロジェクト管理権限が無い場合、スローされる。
	 */
	protected void chkPrjLeader() throws NoProjectAuthException {
		//プロジェクト管理未許可ユーザーをはじく処理
		if(sessionDto.PjLeaderFlg == false){
			throw new NoProjectAuthException();
		}
	}
	
	/**
	 * 経費管理権限チェックを行う。
	 * @author t.fujimoto
	 * @throws NoKogutiKnariAuthException セッション情報に経費管理権限が無い場合、スローされる。
	 */
	protected void chkKogutiKanri() throws NoKogutiKnariAuthException {
		//マスターメンテ管理未許可ユーザーをはじく処理
		if(sessionDto.KogutiKanriFlg == false){
			throw new NoKogutiKnariAuthException();
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
		SAdp.setServiceProvider("http://localhost:8080/sibserviceadapter/view/adapter/adapter.html");
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
		employee_id 	= sessionDto.EmployeeID.toString();
		employee_name	= sessionDto.EmployeeName;
		employee_mail	= sessionDto.EmployeeEmail;

	}
	
	/***
	 * コンボリストを取得する。
	 * @throws Exception 
	 */
	protected List<ListDto> getCombList(String combType) throws Exception {
		
		Map<String, String> requestParams = new HashMap<String, String>();
		
		requestParams.put("combName", combType);
		
		Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_COMBLIST, requestParams);
		
		return CommonUtil.getServiceToCombData(reqParam);	
		
	}

	/***
	 *　ステータスリストを取得する。
	 * @throws Exception 
	 */
	protected List<ListDto> getStatusList(String statusType) throws Exception {
		
		Map<String, String> requestParams = new HashMap<String, String>();
		
		requestParams.put("statusName", statusType);
		
		Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_STATUSLIST, requestParams);
		
		return CommonUtil.getServiceToCombData(reqParam);	
		
	}
	
	/***
	 * 社員リストを取得する。
	 * @throws Exception 
	 */
	protected List<ListDto> getEmpList() throws Exception {
		
		Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_EMPELIST);
		
		return CommonUtil.getServiceToCombData(reqParam);		
		
	}
	
}
