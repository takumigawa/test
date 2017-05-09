package te002.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRCsvDataSource;

import logger.Log4JLogout;

import sDBAccess.SibDBAccess;
import sDBAccess.SibDBAccessSetting;
import sibXMLParser.SibXMLParser;
import te002.dao.TEmpDao;
import te002.entity.TEmp;
import te002.entity.TSendPDF;


public class MainProc {
	
	private static String JAVA_COMMA = ",";
	private static String JAVA_CR = "\n";
	private static String JAVA_DQ = "\"";
	
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        //開始ログ　出力
        Log4JLogout.LogOut("te002 main Start");
        
        Integer targetID;
        Integer targetYear;
        Integer targetMonth;
        String targetEmpname = "";
        String osSepa = "";
        
        String ReportPath = "";
        String OutPath = "";
        String tmpCsvFile = "";
        
		SibXMLParser XMLpars = SibXMLParser.getInstance("Setting.xml", "/DBA");
		
		SibDBAccessSetting DBASet = new SibDBAccessSetting();

		//DB接続設定
		DBASet.setID(XMLpars.getValue("user"));
		DBASet.setPass(XMLpars.getValue("password"));
		DBASet.setSchema(XMLpars.getValue("schema"));
		DBASet.setURL(XMLpars.getValue("host"));
		
		osSepa = XMLpars.getValue("osSeparator");

		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);
		
		ReportPath = XMLpars.getValue("ReportPath") + osSepa + XMLpars.getValue("ReportFile");
		OutPath = XMLpars.getValue("OutPath");
		tmpCsvFile = XMLpars.getValue("tmpPath") + osSepa + XMLpars.getValue("csvFile");
		
        try    {
        	
        	TEmpDao empDao = new TEmpDao();
			
			List<TEmp> empList = empDao.SelectGroup();
	        
			for (TEmp empItem : empList) {
				
				targetID = empItem.getEmpid();
				targetYear = empItem.getEmpyear();
				targetMonth = empItem.getEmpmonth();
							
	            // jrxmlを指定する
	            File jrxmlFile = new File(ReportPath);
	            
	    		if(jrxmlFile.exists()){
	    			
		            List<TSendPDF> sndList = new ArrayList<TSendPDF>();
		            
		            sndList = empDao.SelectEMPYM(targetID, targetYear, targetMonth);
			        
		    		File file = new File(tmpCsvFile);
		    		
		    	    // 出力ストリームの生成（文字コード指定）
		    		FileOutputStream fos = new FileOutputStream(file);
		    		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
		    		PrintWriter pw = new PrintWriter(osw);
	
		    		//CSV行データバッファ
		    		StringBuilder stb = new StringBuilder();
		    		
		    		//ヘッダーデータ書き込み
		    		stb.append(XMLpars.getValue("csvHead") + JAVA_CR);
		    		
		    		pw.write(stb.toString());
		    		
			        //CSVファイル作成
			    	for(TSendPDF listItem : sndList) {
			    		
			    		stb = new StringBuilder();
			    		
			    		targetEmpname = listItem.getEmpname();	
			    		
			    		stb.append(getCSVText(listItem.getEmpname()));
			    		stb.append(JAVA_COMMA);
			    		stb.append(getCSVText(listItem.getSection()));
			    		stb.append(JAVA_COMMA);
			    		stb.append(listItem.getEmpmonth());
			    		stb.append(JAVA_COMMA);
			    		stb.append(listItem.getAppday());
			    		stb.append(JAVA_COMMA);
			    		stb.append(getCSVText(listItem.getSummary()));
			    		stb.append(JAVA_COMMA);
			    		stb.append(getCSVText(listItem.getSectionFrom()));
			    		stb.append(JAVA_COMMA);
			    		stb.append(getCSVText(listItem.getSectionTo()));
			    		stb.append(JAVA_COMMA);
			    		stb.append(listItem.getMoney().toString());
			    		stb.append(JAVA_COMMA);
			    		stb.append(listItem.getExpDate());
			    		stb.append(JAVA_COMMA);
			    		stb.append(listItem.getTotalMoney().toString());
			    		stb.append(JAVA_CR);
			    		
			    		pw.write(stb.toString());
	
			    	}
			    	
			    	pw.close();

	    			// パラメータの設定
	    			HashMap<String, Object> params = new HashMap<String, Object>();

	    			// サンプルデータの主キーの値を設定
	    			params.put("paraEmpid", "");
	    			
	    			//
	    			JRCsvDataSource ds = new JRCsvDataSource(tmpCsvFile, "UTF-8");
	    			ds.setUseFirstRowAsHeader(true);

	    			// データソースにあるデータを、コンパイルされた帳票に入れる
	    			JasperPrint print = JasperFillManager.fillReport(jrxmlFile.getAbsolutePath(), params, ds);

	    			//出力パス作成
	    			String PDFPath  = String.format(OutPath + osSepa + "%s年%s月度交通費精算書_%s.pdf", targetYear, targetMonth, targetEmpname);
	    			// 出力用のPDFを指定する
	    			File pdf = new File(PDFPath);
	    			
	    			// PDF形式で帳票の出力
	    			JasperExportManager.exportReportToPdfFile(print, pdf.getAbsolutePath());

	    			//送信データに送信済みフラグを立てる
	    			empDao.updateSend(targetID, targetYear, targetMonth);

	    		}else{
	    			//PDFフォーマット未検出
	    			Log4JLogout.LogOut(jrxmlFile.getName() + "ファイルが見つかりませんでした。");

	    		}
	    		
			}
            
        } catch (Exception e) {
            
  	        //例外　出力
  	        Log4JLogout.ErrLogOut(e.getMessage().toString());
  	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
  	      
        } finally {
            
            try {
				DBA.Close();
			} catch (SQLException e) {
	  	        //例外　出力
	  	        Log4JLogout.ErrLogOut(e.getMessage());
	  	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
			}
        }
        
        //終了ログ　出力
        Log4JLogout.LogOut("te002 main End");
        
    }
    
    private static String getCSVText(String iText ) {
    	
    	return JAVA_DQ + iText + JAVA_DQ;
    	
    }

}
