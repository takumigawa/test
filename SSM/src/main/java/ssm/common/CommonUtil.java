package ssm.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import ssm.dto.ListDto;


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

	public static List<ListDto> getServiceToCombData(Map<String, String> iCombData){
		
		String keyStr = iCombData.get("comKey");
		String labelStr = iCombData.get("comLabel");
		
		String[] keyAr = keyStr.split(",");
		String[] lblAr = labelStr.split(",");
		
		List<ListDto> combList = new ArrayList<ListDto>();
		
		for (int i = 0; i < keyAr.length;i++) {
			ListDto listItem = new ListDto();
			
			listItem.label = lblAr[i];
			listItem.value = keyAr[i];
	
			combList.add(listItem);
			
		}
		
		return combList;
		
	}
	
}
