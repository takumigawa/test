function treeFunc(id)//　ツリーを消したり表示したりする関数です
{
	if(!document.getElementsByTagName){return false;}//　DOMをサポートしていなければ実行しないで抜けます
	var obj=document.getElementById(id);//　ツリーのオブジェクトを取得
	if(obj.style.display=="inline") {	//　表示←→非表示の処理
		obj.style.display="none";
	} else {
		obj.style.display="inline";
	}
	return false;
}
