//----------------------------------------------------------------------------------------------------
//--  トピックス
//--  入力チェックJS
//--  --------------------------------------------------------------------------------------------
//--  修正履歴
//--  2010/02/24 新規作成 SIB J.Hira
//----------------------------------------------------------------------------------------------------
/*
 * 入力チェック
 */
function chkInput() {
	
	// 必須チェック
	// 掲載開始日
	if (chkClientEssential(document.getElementById("startDateStr"), "掲載開始日")) {
		return true;
	}
	
	// 掲載終了日
	if (chkClientEssential(document.getElementById("endDateStr"), "掲載終了日")) {
		return true;
	}
	
   	// 内容
	if (chkClientEssential(document.getElementById("noteStr"), "内容")) {
		return true;
	}
	
	
	// 桁数チェック
	// 掲載開始日
	if (chkClientDecimals(document.getElementById("startDateStr"), "掲載開始日", 10, null)) {
		return true;
	}
	
	// 掲載終了日
	if (chkClientDecimals(document.getElementById("endDateStr"), "掲載終了日", 10, null)) {
		return true;
	}
	
	// 内容
	if (chkClientDecimals(document.getElementById("noteStr"), "内容", 255, null)) {
		return true;
	}
	
	// 有効日付チェック
	// 掲載開始日
	if (chkDate(document.getElementById("startDateStr"), "掲載開始日")) {
		return true;
	}
	
	// 掲載終了日
	if (chkDate(document.getElementById("endDateStr"), "掲載終了日")) {
		return true;
	}
	
	// 論理チェック
	// 開始日・終了日
	if (document.getElementById("startDateStr").value > document.getElementById("endDateStr").value) {
		alert("掲載開始日は、掲載終了日以前の日付を入力してください。");
		return true;
	}
	
	return false;
}
/*
 * 検索チェック
 */
function chkSearch(sDate,eDate) {
	
	// 開始日が終了日より後の場合、アラートを表示させる
	if (sDate.value > eDate.value) {
		alert("開始日は、終了日以前の日付を入力してください。");
		return true;
	}
	
	if(chkDate(document.getElementById("searchStartDate"), "開始日")) {
		return true;
	}
	
	if(chkDate(document.getElementById("searchEndDate"), "終了日")) {
		return true;
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