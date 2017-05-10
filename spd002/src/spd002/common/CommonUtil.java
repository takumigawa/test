package spd002.common;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * <p>共通関数クラス</p>
 * @author T.fujimoto
 *
 */
public class CommonUtil {

	/**
	 * <p>yyyy/MM/dd HH:mm:ss形式時刻取得</p>
	 * <p>現時刻の時刻をフォーマットして文字列として取得</p>
	 * 
	 * @return String 編集後文字列
	 */
	public static String getNowUpdDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date nowDate = new Date();
		
		return sdf.format(nowDate.getTime());
	}
	
}
