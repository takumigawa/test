//----------------------------------------------------------------------------------------------------
//--  社員個人情報
//--  入力チェックJS
//--  --------------------------------------------------------------------------------------------
//--  修正履歴
//--  2009/12/09 新規作成 SIB T.Fujimoto
//----------------------------------------------------------------------------------------------------
/*
 * 必須入力チェック
 */
function chkInput() {

   	//社員名-入力チェック
	if (chkClientEssential(document.getElementById("EmployeenameStr"), "社員名")) {
		return true;
	}
   	//社員名(カナ)-入力チェック
	if (chkClientEssential(document.getElementById("EmployeenamekanaStr"), "社員名（カナ）")) {
		return true;
	}
   	//ログインＩＤ-入力チェック
	if (chkClientEssential(document.getElementById("EmployeeloginidStr"), "ログインＩＤ")) {
		return true;
	}

	//社員名-桁数チェック
	if(document.getElementById("EmployeenameStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("EmployeenameStr"), "社員名" ,10, 1)) {
			return true;
		}
	}
	//社員名（カナ）-桁数チェック
	if(document.getElementById("EmployeenamekanaStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("EmployeenamekanaStr"), "社員名（カナ）", 20, 1)) {
			return true;
		}
	}
	//ログインＩＤ-桁数チェック
	if(document.getElementById("EmployeeloginidStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("EmployeeloginidStr"), "ログインＩＤ", 20, 1)) {
			return true;
		}
	}
	//郵便番号-桁数チェック
	if(document.getElementById("EmployeepostcodeStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("EmployeepostcodeStr"), "郵便番号", 8, 1)) {
			return true;
		}
	}
	//メールアドレス-桁数チェック
	if(document.getElementById("EmployeeemailStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("EmployeeemailStr"), "メールアドレス", 32, 1)) {
			return true;
		}
	}
	//住所-桁数チェック
	if(document.getElementById("Employeeaddress1Str").value.length > 0){
		if (chkClientDecimals(document.getElementById("Employeeaddress1Str"), "住所", 60, 1)) {
			return true;
		}
	}
	//建物-桁数チェック
	if(document.getElementById("Employeeaddress2Str").value.length > 0){
		if (chkClientDecimals(document.getElementById("Employeeaddress2Str"), "建物", 30, 1)) {
			return true;
		}
	}
	//電話番号（自宅）-桁数チェック
	if(document.getElementById("EmployeetelStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("EmployeetelStr"), "電話番号（自宅）", 20, 1)) {
			return true;
		}
	}
	//電話番号（携帯）-桁数チェック
	if(document.getElementById("EmployeemphoneStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("EmployeemphoneStr"), "電話番号（携帯）", 20, 1)) {
			return true;
		}
	}
	//電話番号（ＦＡＸ）-桁数チェック
	if(document.getElementById("EmployeefaxStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("EmployeefaxStr"), "電話番号（ＦＡＸ）", 20, 1)) {
			return true;
		}
	}

	//社員名（カナ）-半角カナチェック
	if (chkClientHarfSizeKatakana(document.getElementById("EmployeenamekanaStr"), "社員名（カナ）")) {
		return true;
	}
	//ログインＩＤ-半角英数チェック
	if (chkClientHalfSizeString(document.getElementById("EmployeeloginidStr"), "ログインＩＤ")) {
		return true;
	}
	//郵便番号-半角英数チェック
	if(document.getElementById("EmployeepostcodeStr").value.length > 0){
		if (chkClientHalfSizeString(document.getElementById("EmployeepostcodeStr"), "郵便番号")) {
			return true;
		}
	}
	//電話番号（自宅）-半角英数チェック
	if(document.getElementById("EmployeetelStr").value.length > 0){
		if (chkClientHalfSizeString(document.getElementById("EmployeetelStr"), "電話番号（自宅）")) {
			return true;
		}
	}
	//電話番号（携帯）-半角英数チェック
	if(document.getElementById("EmployeemphoneStr").value.length > 0){
		if (chkClientHalfSizeString(document.getElementById("EmployeemphoneStr"), "電話番号（携帯）")) {
			return true;
		}
	}
	//電話番号（ＦＡＸ）-半角英数チェック
	if(document.getElementById("EmployeefaxStr").value.length > 0){
		if (chkClientHalfSizeString(document.getElementById("EmployeefaxStr"), "電話番号（ＦＡＸ）")) {
			return true;
		}
	}
	

	return false;

}
