//----------------------------------------------------------------------------------------------------
//--  社員情報マスタ
//--  入力チェックJS
//--  --------------------------------------------------------------------------------------------
//--  修正履歴
//--  2009/12/15 新規作成 SIB T.Fujimoto
//----------------------------------------------------------------------------------------------------
/*
 * 必須入力チェック
 */
function chkInput() {

   	//社員名-入力チェック
	if (chkClientEssential(document.getElementById("employeeID"), "社員名")) {
		return true;
	}
   	//案件タイトル-入力チェック
	if (chkClientEssential(document.getElementById("MatterTitleStr"), "案件タイトル")) {
		return true;
	}

	//案件タイトル-桁数チェック
	if(document.getElementById("MatterTitleStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("MatterTitleStr"), "案件タイトル", 50, 1)) {
			return true;
		}
	}
	//案件住所-桁数チェック
	if(document.getElementById("MatteraddressStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("MatteraddressStr"), "案件住所", 120, 1)) {
			return true;
		}
	}
	//期間開始-桁数チェック
	if(document.getElementById("MatterStartStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("MatterStartStr"), "期間開始", 50, 1)) {
			return true;
		}
	}
	//期間終了-桁数チェック
	if(document.getElementById("MatterEndStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("MatterEndStr"), "期間終了", 50, 1)) {
			return true;
		}
	}
	
	//案件内容-桁数チェック
	if(document.getElementById("MatternoteStr").value.length > 0){
		if (chkClientDecimals(document.getElementById("MatternoteStr"), "案件内容", 255, 1)) {
			return true;
		}
	}

	return false;

}
