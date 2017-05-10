package spd002.common;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * <p>���ʊ֐��N���X</p>
 * @author T.fujimoto
 *
 */
public class CommonUtil {

	/**
	 * <p>yyyy/MM/dd HH:mm:ss�`�������擾</p>
	 * <p>�������̎������t�H�[�}�b�g���ĕ�����Ƃ��Ď擾</p>
	 * 
	 * @return String �ҏW�㕶����
	 */
	public static String getNowUpdDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date nowDate = new Date();
		
		return sdf.format(nowDate.getTime());
	}
	
}
