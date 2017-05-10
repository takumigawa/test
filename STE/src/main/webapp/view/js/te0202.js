
function copyClick() {

	var obj = document.getElementsByName("option");
	var ret = null;
	
	for (var i = 0 ; i < obj.length ; i++) {
		if ( obj[i].checked ) {
			if (ret == null) {
				ret = i;
			} else {
				ret = ret + "," + i;
			}
		}
	}
	
	if (ret == null) {
		alert("複製請対象を一件以上選択してください。");
		return;
	}

	myRet = confirm("選択した行を複製しますか？");
	if ( myRet == false ){
		return;
	}
	
	document.getElementById("retChk").value = ret;
	document.getElementById("doCopy").click();
	
}

function rowDelClick() {
      
	var obj = document.getElementsByName("option");
	var ret = null;
	
	for (var i = 0 ; i < obj.length ; i++) {
		if ( obj[i].checked ) {
			if (ret == null) {
				ret = i;
			} else {
				ret = ret + "," + i;
			}
		}
	}
	
	if (ret == null) {
		alert("削除対象を一件以上選択してください。");
		return;
	}

	myRet = confirm("選択した行を削除しますか？");
	if ( myRet == false ){
		return;
	}
	
	document.getElementById("retChk").value = ret;
	document.getElementById("doRowDelete").click();
	
}

function deleteClick() {

	myRet = confirm("申請書を削除しますか？");
	if ( myRet == false ){
		return;
	}

	document.getElementById("doDelete").click();
	
}

function cancelClick() {

	myRet = confirm("申請を取り消しますか？");
	if ( myRet == false ){
		return;
	}

	document.getElementById("doCancel").click();
}

function updateClick() {

	myRet = confirm("申請書を更新しますか？");
	if ( myRet == false ){
		return;
	}
	
	document.getElementById("doUpdate").click();

}