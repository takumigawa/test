package te001.main;

import java.sql.SQLException;
import java.util.List;

import logger.Log4JLogout;
import sDBAccess.SibDBAccess;
import sDBAccess.SibDBAccessSetting;
import sibXMLParser.SibXMLParser;
import te001.dao.TSendDataDao;
import te001.dao.TTrnsExpDao;
import te001.entity.TSendPDF;



public class MainProc {
	
	private static String JAVA_COMMA = ",";
	private static String JAVA_CR = "\n";
	
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        //�J�n���O�@�o��
        Log4JLogout.LogOut("TE001 main Start");
        
		SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/DBA");
		
		SibDBAccessSetting DBASet = new SibDBAccessSetting();

		//DB�ڑ��ݒ�
		DBASet.setID(XMLpars.getValue("user"));
		DBASet.setPass(XMLpars.getValue("password"));
		DBASet.setSchema(XMLpars.getValue("schema"));
		DBASet.setURL(XMLpars.getValue("host"));

		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);
		
        try    {
        	
        	TTrnsExpDao trnDao = new TTrnsExpDao();
        	TSendDataDao senDao = new TSendDataDao();
			
			List<TSendPDF> trnList = trnDao.SelectYM();
			
			senDao.DeleteSend();
	        
			for (TSendPDF trnItem : trnList) {

				senDao.InsertSend(trnItem);
					
			}
			
			trnDao.updateSend();
            
        } catch (Exception e) {
            
  	        //��O�@�o��
  	        Log4JLogout.ErrLogOut(e.getMessage().toString());
  	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
  	      
        } finally {
            
            try {
				DBA.Close();
			} catch (SQLException e) {
	  	        //��O�@�o��
	  	        Log4JLogout.ErrLogOut(e.getMessage());
	  	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
			}
        }
        
        //�I�����O�@�o��
        Log4JLogout.LogOut("TE001 main End");
        
    }

}
