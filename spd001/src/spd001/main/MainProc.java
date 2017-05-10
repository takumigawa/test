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
        
        //�J�n���O�@�o��
        Log4JLogout.LogOut("spd001 main Start");
        
        String targetYear;
        String targetMonth;
        
        BatchParam bp;
        
		SibDBAccessSetting DBASet = new SibDBAccessSetting();
		
		//DB�ڑ��ݒ�
		DBASet.setID("user1");
		DBASet.setPass("sib1962");
		DBASet.setSchema("sibpayadvice");
		DBASet.setURL("133.242.134.145");
		
		SibDBAccess DBA = SibDBAccess.getInstance(DBASet);
		
        try    {
        	
			//�p�����[�^�[�`�F�b�N
			bp = createParam(args);
			
			if (bp == null){
				throw new Exception("�p�����[�^�s��");
			}
			
			TSendymDao ymDao = new TSendymDao();
			
			List<TSendym> ymList = ymDao.SelectALL();
			
	        //�����擾���O
	        Log4JLogout.LogOut(String.format("���t�N�� �擾����%d��", ymList.size()));
	        
			for (TSendym ymItem : ymList) {
				
				bp.setYear(ymItem.getSendyear().toString());
				bp.setMonth(ymItem.getSendmonth().toString());
				
				targetYear = bp.getYear();
				targetMonth = bp.getMonth();
				
	            // jrxml���w�肷��
	            File jrxmlFile = new File(bp.getReportPath() + "sibpaydevice.jasper");
	                      
	            //JDBC�h���C�o�̃��[�h
	            Class.forName(JDBCName);
	            //�f�[�^�x�[�X�ɐڑ�
	            mySqlcon = DriverManager.getConnection(SQLUrl, SQLID, SQLPass);

	            //
	            TEmpDao empDao = new TEmpDao();
	            //
	            TSendPdfDao pdfDao = new TSendPdfDao();
	            
	            List<TEmp> empList = new ArrayList<TEmp>();
	            
	            empList = empDao.SelectYM(bp);
	            
		        //�����擾���O
		        Log4JLogout.LogOut(String.format("���t�Ј� �擾����%d��", empList.size()));
		        
	            //���ʃZ�b�g����f�[�^�����o�� next()�Ŏ��̍s�Ɉړ�
	            for(TEmp listItem : empList) {
	            	
	            	Log4JLogout.LogOut(String.format("�Ј���:%s", listItem.getEmpname()));
	            	
					String empid = listItem.getEmpid().toString();
					String empname = listItem.getEmpname();
					String sendmail = listItem.getSendmail();

	              if(jrxmlFile.exists()){

	                  // �p�����[�^�̐ݒ�
	                  HashMap<String, Object> params = new HashMap<String, Object>();
	                  
	                  // �T���v���f�[�^�̎�L�[�̒l��ݒ�
	                  params.put("paraEmpid", empid);
	                  params.put("paraYear", targetYear);
	                  params.put("paraMonth", targetMonth);
	                  
	                  // �f�[�^�\�[�X�ɂ���f�[�^���A�R���p�C�����ꂽ���[�ɓ����
	                  JasperPrint print = JasperFillManager.fillReport(jrxmlFile.getAbsolutePath(), params, mySqlcon);

	                  //�o�̓p�X�쐬
	                  String PDFPath  = String.format(bp.getOutPath() + "���^����(%s_%s�N%s���x).pdf", empname, targetYear, targetMonth);
	                  // �o�͗p��PDF���w�肷��
	                  File pdf = new File(PDFPath);
	                  // PDF�`���Œ��[�̏o��
	                  JasperExportManager.exportReportToPdfFile(print, pdf.getAbsolutePath());

	                  pdfDao.InsertUpdate(empid, empname, targetYear, targetMonth, PDFPath, sendmail);
	                  
	              }else{
	      	        //PDF�t�H�[�}�b�g�����o
	      	        Log4JLogout.LogOut("sibpaydevice.jasper�t�@�C����������܂���ł����B");
	      	        
	              }
	              
	            }
	            
			}
			
            //�f�[�^�x�[�X��ؒf
            mySqlcon.close();
            
        } catch (Exception e) {
            
  	        //��O�@�o��
  	        Log4JLogout.ErrLogOut(e.getMessage().toString());
  	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
  	      
        } finally {
            if(mySqlcon != null){
                try {
                	mySqlcon.close();
                } catch (SQLException e) {
          	        //��O�@�o��
          	        Log4JLogout.ErrLogOut(e.getMessage());
          	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
                }
            }
            
            try {
				DBA.Close();
			} catch (SQLException e) {
	  	        //��O�@�o��
	  	        Log4JLogout.ErrLogOut(e.getMessage());
	  	        Log4JLogout.ErrLogOut(e.getStackTrace().toString());
			}
        }
        
        //�I�����O�@�o��
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
		
		//���M�N
		bp.setYear(args[0]);
		//���M��
		bp.setMonth(args[1]);
		//���|�[�g�p�X
		bp.setReportPath(args[2]);
		//PDF�o�̓p�X
		bp.setOutPath(args[3]);
		
		return bp;
				
	}


}
