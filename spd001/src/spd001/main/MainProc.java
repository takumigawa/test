package spd001.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logger.Log4JLogout;
import sDBAccess.SibDBAccess;
import sDBAccess.SibDBAccessSetting;
import spd001.dao.TEmpDao;
import spd001.dao.TSendPdfDao;
import spd001.dao.TSendymDao;
import spd001.dto.BatchParam;
import spd001.entity.TEmp;
import spd001.entity.TSendym;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class MainProc {

	private static String JDBCName = "com.mysql.jdbc.Driver";
	private static String SQLUrl = "jdbc:mysql://133.242.134.145/sibpayadvice?characterEncoding=UTF-8&amp;characterSetResults=UTF-8";
	private static String SQLID = "user1";
	private static String SQLPass = "sib1962";

    /**
     * @param args
     */
    public static void main(String[] args) {
        Connection mySqlcon = null;
        
        //開始ログ　出力
        Log4JLogout.LogOut("spd001 main Start");
        
        String targetYear;
        String targetMonth;
        
        BatchParam bp;
        
		SibDBAccessSetting DBASet = new SibDBAccessSetting();
		
		//DB接続設定
		DBASet.setID("user1");
		DBASet.setPass("sib1962");
		DBASet.setSchema("sibpayadvice");
		DBASet.setURL("133.242.134.145");
		
		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);
		
        try    {
        	
			//パラメーターチェック
			bp = createParam(args);
			
			if (bp == null){
				throw new Exception("パラメータ不正");
			}
			
			TSendymDao ymDao = new TSendymDao();
			
			List<TSendym> ymList = ymDao.SelectALL();
			
	        //件数取得ログ
	        Log4JLogout.LogOut(String.format("送付年月 取得件数%d件", ymList.size()));
	        
			for (TSendym ymItem : ymList) {
				
				bp.setYear(ymItem.getSendyear().toString());
				bp.setMonth(ymItem.getSendmonth().toString());
				
				targetYear = bp.getYear();
				targetMonth = bp.getMonth();
				
	            // jrxmlを指定する
	            File jrxmlFile = new File(bp.getReportPath() + "sibpaydevice.jasper");
	                      
	            //JDBCドライバのロード
	            Class.forName(JDBCName);
	            //データベースに接続
	            mySqlcon = DriverManager.getConnection(SQLUrl, SQLID, SQLPass);

	            //
	            TEmpDao empDao = new TEmpDao();
	            //
	            TSendPdfDao pdfDao = new TSendPdfDao();
	            
	            List<TEmp> empList = new ArrayList<TEmp>();
	            
	            empList = empDao.SelectYM(bp);
	            
		        //件数取得ログ
		        Log4JLogout.LogOut(String.format("送付社員 取得件数%d件", empList.size()));
		        
	            //結果セットからデータを取り出す next()で次の行に移動
	            for(TEmp listItem : empList) {
	            	
	            	Log4JLogout.LogOut(String.format("社員名:%s", listItem.getEmpname()));
	            	
					String empid = listItem.getEmpid().toString();
					String empname = listItem.getEmpname();
					String sendmail = listItem.getSendmail();

	              if(jrxmlFile.exists()){

	                  // パラメータの設定
	                  HashMap<String, Object> params = new HashMap<String, Object>();
	                  
	                  // サンプルデータの主キーの値を設定
	                  params.put("paraEmpid", empid);
	                  params.put("paraYear", targetYear);
	                  params.put("paraMonth", targetMonth);
	                  
	                  // データソースにあるデータを、コンパイルされた帳票に入れる
	                  JasperPrint print = JasperFillManager.fillReport(jrxmlFile.getAbsolutePath(), params, mySqlcon);

	                  //出力パス作成
	                  String PDFPath  = String.format(bp.getOutPath() + "給与明細(%s_%s年%s月度).pdf", empname, targetYear, targetMonth);
	                  // 出力用のPDFを指定する
	                  File pdf = new File(PDFPath);
	                  // PDF形式で帳票の出力
	                  JasperExportManager.exportReportToPdfFile(print, pdf.getAbsolutePath());

	                  pdfDao.InsertUpdate(empid, empname, targetYear, targetMonth, PDFPath, sendmail);
	                  
	              }else{
	      	        //PDFフォーマット未検出
	      	        Log4JLogout.LogOut("sibpaydevice.jasperファイルが見つかりませんでした。");
	      	        
	              }
	              
	            }
	            
			}
			
            //データベースを切断
            mySqlcon.close();
            
        } catch (Exception e) {
            
  	        //例外　出力
  	        Log4JLogout.ErrLogOut(e.getMessage().toString());
  	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
  	      
        } finally {
            if(mySqlcon != null){
                try {
                	mySqlcon.close();
                } catch (SQLException e) {
          	        //例外　出力
          	        Log4JLogout.ErrLogOut(e.getMessage());
          	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
                }
            }
            
            try {
				DBA.Close();
			} catch (SQLException e) {
	  	        //例外　出力
	  	        Log4JLogout.ErrLogOut(e.getMessage());
	  	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
			}
        }
        
        //終了ログ　出力
        Log4JLogout.LogOut("spd001 main End");
        
    }

	/**
	 * @param args
	 */
	private static BatchParam createParam(String[] args) {
		
		if (args.length != 4) {
			return null;
		}
		
		BatchParam bp = new BatchParam();
		
		//送信年
		bp.setYear(args[0]);
		//送信月
		bp.setMonth(args[1]);
		//レポートパス
		bp.setReportPath(args[2]);
		//PDF出力パス
		bp.setOutPath(args[3]);
		
		return bp;
				
	}


}
