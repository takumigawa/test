package schedule.web.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import schedule.dto.ListDto;

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
	 * 
	 * @param iCombData
	 * @return
	 */
	public static List<ListDto> getServiceToCombData(){
		
		List<ListDto> combList = new ArrayList<ListDto>(); 
		
		ListDto listItem; 
		
		listItem = new ListDto();
		
		listItem.label = "有効";
		listItem.value = "0";
		
		combList.add(listItem);

		listItem = new ListDto();
		
		listItem.label = "無効";
		listItem.value = "1";
		
		combList.add(listItem);
		
		return combList;
		
	}
	
}
