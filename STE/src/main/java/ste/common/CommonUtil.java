package ste.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import ste.dto.ListDto;


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

	/**
	 * <p>\#,##0形式取得</p>
	 * <p>数値をフォーマットして文字列として取得</p>
	 * @param iData 編集したい数値
	 * @return String 編集後文字列
	 */
	public static String getCurrencyFormat(Integer iData) {
		
		return String.format("\\%1$,3d",iData);
	}
	
	/**
	 * <p>#,##0形式取得</p>
	 * <p>数値をフォーマットして文字列として取得</p>
	 * @param iData 編集したい数値
	 * @return String 編集後文字列
	 */
	public static String getNumberFormat(Integer iData) {
		
		return String.format("%1$,3d",iData);
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
