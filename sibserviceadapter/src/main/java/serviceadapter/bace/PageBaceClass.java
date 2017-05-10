package serviceadapter.bace;

import org.seasar.framework.container.annotation.tiger.Binding;
import serviceadapter.dto.SessionDto;
import serviceadapter.exception.AnLoginException;
import serviceadapter.exception.NoMastaMenteAuthException;

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
	 * 小口管理権限チェックを行う。
	 * @author t.fujimoto
	 * @throws NoMastaMenteAuthException セッション情報にマ小口管理権限が無い場合、スローされる。
	 */
	protected void chkKogutikanri() throws NoMastaMenteAuthException {
		//マスターメンテ管理未許可ユーザーをはじく処理
		if(sessionDto.KogutiKanriFlg == false){
			throw new NoMastaMenteAuthException();
		}
	}
	
	/**
	 * ログイン情報を設定する
	 */
	protected void setLoginInfo() {
		
		//ログイン情報
		employee_id 	= this.sessionDto.EmployeeID.toString();
		employee_name	= this.sessionDto.EmployeeName;
		employee_mail	= this.sessionDto.EmployeeEmail;
		
	}
	
}
