function treeFunc(id)//�@�c���[����������\�������肷��֐��ł�
{
	if(!document.getElementsByTagName){return false;}//�@DOM���T�|�[�g���Ă��Ȃ���Ύ��s���Ȃ��Ŕ����܂�
	var obj=document.getElementById(id);//�@�c���[�̃I�u�W�F�N�g���擾
	if(obj.style.display=="inline") {	//�@�\��������\���̏���
		obj.style.display="none";
	} else {
		obj.style.display="inline";
	}
	return false;
}
