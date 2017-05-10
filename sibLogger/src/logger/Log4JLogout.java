package logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4JLogout {

	private static Logger logger; // Logger�C���X�^���X�̓N���X�ϐ��ŕێ�

	public static void LogOut(String Mes) {

		if (logger == null ){
			BasicConfigurator.configure(); // log4j�̏�����
			logger = Logger.getLogger(Log4JLogout.class);
		}
	    logger.info(Mes);

	}

	public static void ErrLogOut(String Mes) {

		if (logger == null ){
			BasicConfigurator.configure(); // log4j�̏�����
			logger = Logger.getLogger(Log4JLogout.class);
		}
	    logger.error(Mes);


	}
}
