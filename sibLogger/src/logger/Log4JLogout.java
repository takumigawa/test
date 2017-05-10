package logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4JLogout {

	private static Logger logger; // Loggerインスタンスはクラス変数で保持

	public static void LogOut(String Mes) {

		if (logger == null ){
			BasicConfigurator.configure(); // log4jの初期化
			logger = Logger.getLogger(Log4JLogout.class);
		}
	    logger.info(Mes);

	}

	public static void ErrLogOut(String Mes) {

		if (logger == null ){
			BasicConfigurator.configure(); // log4jの初期化
			logger = Logger.getLogger(Log4JLogout.class);
		}
	    logger.error(Mes);


	}
}
