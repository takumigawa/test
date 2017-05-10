package spd001.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sDBAccess.SibDBAccess;
import sDBAccess.SibPreparedStatement;

public class TSendPdfDao {

	public boolean InsertUpdate(String iempid, String iempname, String targetYear, String targetMonth, String PDFPath, String sendmail) throws SQLException {
		
		SibDBAccess DBA = SibDBAccess.getInstance();
			
		// パラメーターリスト
		List<SibPreparedStatement> paramList = createSelectParam(iempid, targetYear, targetMonth);
		
        // SQL実行		
		ResultSet rs = DBA.ExecuteQuery(createSelectSQL(), paramList);

		//存在チェック
		if (rs.next()) {

			//Update文を実行
			DBA.ExecuteNonQuery(createUpdateSQL(sendmail, PDFPath, iempid, targetYear, targetMonth));
			
		} else {
			//Insert文を実行
			DBA.ExecuteNonQuery(createInsertSQL(sendmail, PDFPath, iempid, iempname, targetYear, targetMonth));
			
		}
		
		rs.close();
		
		return true;	
		
	}
	
	private String createSelectSQL() {
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       empid ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendpdf ");
		sqlBil.append(" WHERE ");
		sqlBil.append("      empid     = ? ");
		sqlBil.append("  AND sendyear  = ? ");
		sqlBil.append("  AND sendmonth = ? ");
		
		return sqlBil.toString();
	}
	
	/**
	 * @param args
	 */
	private List<SibPreparedStatement> createSelectParam(String empid, String iYear, String iMonth) {
		
		// パラメーターリスト
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // パラメータの設定
		SibPreparedStatement param;		

        //
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(Integer.parseInt(empid));
		paramList.add(param);
		
        //
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(Integer.parseInt(iYear));
		paramList.add(param);
		
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(Integer.parseInt(iMonth));
		paramList.add(param);
				
		return paramList;
		
	}
	
	/** updateSQL生成　*/
	private String createUpdateSQL(String sendmail, String sendfile, String empid, String sendyear, String sendmonth) {
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("UPDATE t_sendpdf ");
		sqlBil.append("   SET ");
		sqlBil.append("       sendmail = '" + sendmail + "' ");
		sqlBil.append("      ,sendflg  = 0 ");
		sqlBil.append("      ,senddate = '' ");		
		sqlBil.append("      ,sendfile = '" + sendfile + "' ");
		sqlBil.append(" WHERE ");
		sqlBil.append("      empid     = " + empid + " ");
		sqlBil.append("  AND sendyear  = " + sendyear + " ");
		sqlBil.append("  AND sendmonth = " + sendmonth + " ");

		return sqlBil.toString();
	}
	
	/** InsertSQL生成　*/
	private String createInsertSQL(String sendmail, String sendfile, String empid, String empname, String sendyear, String sendmonth) {
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("INSERT INTO t_sendpdf ");
		sqlBil.append("   (  ");
		sqlBil.append("       sendmail  ");		
		sqlBil.append("      ,sendfile  ");
		sqlBil.append("      ,empid  ");	
		sqlBil.append("      ,empname ");
		sqlBil.append("      ,sendyear  ");
		sqlBil.append("      ,sendmonth ");
		sqlBil.append("      ,sendflg   ");
		sqlBil.append("   ) ");
		sqlBil.append("   VALUES ");
		sqlBil.append("   ( ");
		sqlBil.append("        '" + sendmail + "' ");
		sqlBil.append("      , '" + sendfile + "' ");
		sqlBil.append("      , " + empid + " ");
		sqlBil.append("      , '" + empname + "' ");
		sqlBil.append("      , " + sendyear + " ");
		sqlBil.append("      , " + sendmonth + " ");
		sqlBil.append("      , 0 ");
		sqlBil.append("   ) ");
		
		return sqlBil.toString();
	}
	
}
