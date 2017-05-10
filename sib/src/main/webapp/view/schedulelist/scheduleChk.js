//----------------------------------------------------------------------------------------------------
//--  スケジュール
//--  入力チェックJS
//--  --------------------------------------------------------------------------------------------
//--  修正履歴
//--  2009/12/15 新規作成 SIB J.Hira
//----------------------------------------------------------------------------------------------------
/*
 * 入力チェック
 */
function chkInput() {
	
	// 必須チェック
	// 開始日付
	if (chkClientEssential(document.getElementById("startDate"), "開始日")) {
		return true;
	}
	
	// 終了日付
	if (chkClientEssential(document.getElementById("endDate"), "終了日")) {
		return true;
	}
	
   	// タイトル
	if (chkClientEssential(document.getElementById("titel"), "タイトル")) {
		return true;
	}
	
	// 参加者
	if (chkJoinEmp()) {
		return true;
	}
	
	// 桁数チェック
	// 開始日付
	if (chkClientDecimals(document.getElementById("startDate"), "開始日", 10, null)) {
		return true;
	}
	
	// 終了日付
	if (chkClientDecimals(document.getElementById("endDate"), "終了日", 10, null)) {
		return true;
	}
	
	// タイトル
	if (chkClientDecimals(document.getElementById("titel"), "タイトル", 50, null)) {
		return true;
	}
	
	// 内容
	if (chkClientDecimals(document.getElementById("note"), "内容", 255, null)) {
		return true;
	}
	
	// 有効日付チェック
	// 開始日付
	if (chkDate(document.getElementById("startDate"), "開始日")) {
		return true;
	}
	
	// 終了日付
	if (chkDate(document.getElementById("endDate"), "終了日")) {
		return true;
	}
	
	// 論理チェック
	// 開始日時・終了日時
	if (chkTime()) {
		return true;
	}
	
	return false;
}

/*
 * 参加者チェック
 */
function chkJoinEmp() {

	// 参加者リストの1件目の値の取得
	var listValue = document.schInputForm.joinEmp.options[0].value;
	
	// 取得した値が"ALL"以外の場合、参加者未選択としてアラートを表示させる
	if (listValue != "ALL") {
		alert("参加者を選択してください。");
		return true;
	}

	return false;
}

/*
 * 日時チェック
 */
function chkTime() {
	
	// 開始日の取得
	var sDate = document.getElementById("startDate").value;
	
	// 開始時間の取得
	var sHour = document.getElementById("startHour").value;
	var sMin  = document.getElementById("startMin").value;
	var sTime = sHour + ":" + sMin
	
	// 終了日の取得
	var eDate = document.getElementById("endDate").value;
	
	// 終了時間の取得
	var eHour = document.getElementById("endHour").value;
	var eMin  = document.getElementById("endMin").value;
	var eTime = eHour + ":" + eMin
	
	// 開始日が終了日より後の場合、アラートを表示させる
	if (sDate > eDate) {
		alert("開始日は、終了日以前の日付を入力してください。");
		return true;
		
	// 開始日と終了日が同じ場合、時間を比較する
	} else if (sDate == eDate) {
	
		// 開始時間が終了時間より後の場合、アラートを表示させる
		if (sTime > eTime) {
			alert("開始時間は、終了時間より前の時間を選択してください。");
			return true;
			
		// 開始時間と終了時間が同じ場合、アラートを表示させる
		} else if (sTime == eTime) {
			alert("開始時間と終了時間は、同じ時間には登録できません。");
			return true;
		}
	}
	return false;
}

/*
 * 有効日付チェック
 */
function chkDate(element, label) {
	
	// スラッシュで分割し配列に格納
	var dateArray = element.value.split("/");
		
	// yyyy/mm/dd以外で入力されている場合、アラートを表示させる
	if(dateArray.length != 3) {
	  
      // アラートを表示
      alert(label + "は、\"YYYY/MM/DD\"形式で入力してください。");
      return true;
    
	}　else if(dateArray[1].length != 2) {
	    
      // アラートを表示
      alert(label + "は、\"YYYY/MM/DD\"形式で入力してください。");
      return true;
	
	} else if(dateArray[2].length != 2) {
      
      // アラートを表示
      alert(label + "は、\"YYYY/MM/DD\"形式で入力してください。");
      return true;
	}
	
	// 値の取得
	var year	= dateArray[0];		// 年
	var month	= dateArray[1];		// 月
	var day		= dateArray[2];		// 日

	// 日付の配列の生成
    yy = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

	// 閏年の判定
	// 年が4で割り切れるかつ、月が2の場合、閏年処理を行う
	if (!(year % 4) && month == 2) {
		
		// 閏年フラグ
		var isLeap = true;
	
		// 年が100で割り切れるかつ、年が400で割り切れない場合、閏年フラグにfalseを設定する
		if (!(year % 100) && year % 400) {
			
			// 閏年フラグにfalseを設定	
			isLeap = false;
		}

		// 閏年なら2月を+1日にする
		if (isLeap) {
			yy[1]++;
		}
	}

	// 日範囲検証
	// 月に対応した日以外の場合、アラートを表示させる
	if (!(1 <= day && yy[month-1] >= day)) {
		
		// アラートを表示
		alert(label + "は、有効日付で入力してください。");
		
		return true;
	}
    
    return false;
}