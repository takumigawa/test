package dbcommon;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String getNowUpdDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		return sdf.format(new Date());

	}
}
