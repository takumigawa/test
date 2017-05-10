//----------------------------------------------------------------------------------------------------
//--  社内休暇管理システム
//--  共通JS
//--  --------------------------------------------------------------------------------------------
//--  修正履歴
//--  2009/09/14 新規作成 Xsi T.Kitagawa
//----------------------------------------------------------------------------------------------------
/*
 * 必須入力チェック
 */
function chkClientEssential(inputitem, inputlabel) {
	if (inputitem.value == "") {
		alert(inputlabel + "を入力してください。");
		return true;
	}
	return false;
}

/*
 * 必須入力(時間プルダウン)チェック
 */
function chkClientPulldownEssential(inputitem1, inputitem2, inputlabel) {
	if (inputitem1.value == "" || inputitem2.value == "") {
		alert(inputlabel + "を入力してください。");
		return true;
	}
	return false;
}

/*
 * 半角英数字チェック
 */
function chkClientHalfSizeString(inputitem, inputlabel) {
	if(!inputitem.value.match(/[0-9a-zA-Z\s]+/)) {
		alert(inputlabel + "は半角英数字で入力してください。");
		return true;
	}
	return false;
}

/*
 * 数字チェック
 */
function chkClientHarfSizeNumber(inputitem, inputlabel) {
	if(inputitem.value.match(/[^0-9\s]+/)){
		alert(inputlabel + "は半角数字を入力してください。");
		return true;
	}
	return false;
}

/*
 * 桁数チェック
 */
function chkClientDecimals(inputitem, inputlabel, maxlength, minlength) {
		if(inputitem.value.length==0) {
			return false;
		}else if(inputitem.value.length > maxlength || inputitem.value.length < minlength) {
			if(minlength==null) {
				alert(inputlabel + "は" + maxlength + "桁以下で入力してください。");
				return true;
			} else if(minlength==maxlength) {
				alert(inputlabel + "は" + maxlength + "桁で入力してください。");
				return true;
			} else {
				alert(inputlabel + "は" + minlength + "-"
				+ maxlength + "桁以下で入力してください。");
				return true;
			}
		}
	return false;
}

/*
 * 全角かなチェック
 */
function chkClientFullSizeKana(inputitem, inputlabel) {
	if(inputitem.value.match(/[^あ-ん|^ー]/)) {
		alert(inputlabel + "はひらがなで入力してください。");
		return true;
	}
	return false;
}

/*
 * 全角カナチェック
 */
function chkClientFullSizeKatakana(inputitem, inputlabel) {
	if(!inputitem.value.match(/[^ア-ン|^ー]/)) {
		alert(inputlabel + "はカタカナで入力してください。");
		return true;
	}
	return false;
}

/*
 * 半角カナチェック
 */
function chkClientHarfSizeKatakana(inputitem, inputlabel) {
	if(!inputitem.value.match(/[ｦ-ﾟ]/)) {
		alert(inputlabel + "は半角カタカナで入力してください。");
		return true;
	}
	return false;
}

/*
 * 半角スペースチェック
 */
function chkClientHalfSizeSpece(inputitem, inputlabel) {
	if(inputitem.value.match(/[' ']/)) {
		alert(inputlabel + "に半角スペースが混在しています。");
		return true;
	}
	return false;
}

/*
 * 全角スペースチェック
 */
function chkClientFullSizeSpece(inputitem, inputlabel) {
	if(inputitem.value.match(/['　']/)) {
		alert(inputlabel + "に全角スペースが混在しています。");
		return true;
	}
	return false;
}

/*
 * 記号チェック
 */
function chkClientCall(inputitem, inputlabel) {
	if(inputitem.value.match(/[！”＃＄％＆’（）＝～｜‘｛＋＊｝＜＞？＿－＾￥＠「；：」、。・]+/)){
		alert(inputlabel + "に記号が入っています。");
		return true;
	}
	return false;
}

/*
 * 記号チェック
 */
function chkClientCallOwn(inputitem, inputlabel, inputseiki) {
	if(inputitem.value.match(inputseiki)){
		alert(inputlabel + "が正しくありません。");
		return true;
	}
	return false;
}


// 有効日付チェック
function checkDate(inputlabel, inputyear, inputmonth, inputday) {
	var year = inputyear.value;
	var month = inputmonth.value;
	var day = inputday.value;

    yy = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

    // 年月日相関チェック

	// 月相関チェック
    if(year!="") {
    	if(month=="") {
			alert(inputmonth, inputlabel + "(月)を入力してください。");
			return true;
		} else if (day=="") {
			// 月の範囲検証
			if (!(month >= 1 && month <= 12)) {
				alert(inputmonth, inputlabel + "(月)の指定が正しくありません。");
				return true;
			// 日相関チェック
			} else {
				alert(inputday, inputlabel + "(日)を入力してください。");
				return true;
			}
		}

	// 年相関チェック
	} else {
    	if(month!="" || day!="") {
    	alert(ono)
			alert(inputyear, inputlabel + "(年)を入力してください。");
			return true;
		} else {
			return true;
		}
		return true;
	}

	// 月範囲検証
	if (!(month >= 1 && month <= 12)) {
		alert(inputmonth, inputlabel + "(月)の指定が正しくありません。");
		return true;
	}

	// 閏年の判定
	if (!(year % 4) && month == 2) {
		var isLeap = true;
	
		if (!(year % 100)) {
			if (year % 400) {
				isLeap = false;
			}
		}

		// 閏年なら2月に+1日
		if (isLeap) {
			yy[1]++;
		}
	}

	// 日範囲検証
	if (!(1 <= day && yy[month-1] >= day)) {
		alert(inputday, inputlabel + "(日)の指定が間違ってます。");
		return true;
	}
    return false;
}