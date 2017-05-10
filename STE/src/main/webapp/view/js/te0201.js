function chengeList(){

	document.getElementById("doReflash").click();

} 

function sendClick() {

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
		alert("申請対象を一件以上選択してください。");
		return;
	}

	document.getElementById("retChk").value = ret;
	document.getElementById("doSend").click();
	
}