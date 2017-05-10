package spd002.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sDBAccess.SibDBAccess;
import sDBAccess.SibPreparedStatement;
import spd002.common.CommonUtil;
import spd002.dto.BatchParam;
import spd002.entity.TSendPdf;

public class TSendPdfDao {

	public List<TSendPdf> SelectYM(BatchParam bp) throws SQLException {
		
		List<TSendPdf> retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       empid ");
		sqlBil.append("      ,empname ");
		sqlBil.append("      ,sendyear ");
		sqlBil.append("      ,sendmonth ");
		sqlBil.append("      ,sendfile ");
		sqlBil.append("      ,sendmail ");
		sqlBil.append("      ,sendflg ");
		sqlBil.append("      ,senddate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendpdf ");
		sqlBil.append(" WHERE ");
		sqlBil.append("      sendyear  = ? ");
		sqlBil.append("  AND sendmonth = ? ");
		sqlBil.append("  AND sendflg   = 0 ");
		
		// パラメーターリスト
		List<SibPreparedStatement> paramList = createParam(bp.getYear(), bp.getMonth());
		
        // SQL実行		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString(), paramList);
		
		retList = mapping(rs);
		
		rs.close();
				
		return retList;	
		
	}
	
	public void Update(String iempid, String targetYear, String targetMonth) throws SQLException {
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
		DBA.ExecuteNonQuery(createUpdateSQL(iempid, targetYear, targetMonth));
		
	}
	
	/** updateSQL生成　*/
	private String createUpdateSQL(String empid, String sendyear, String sendmonth) {
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("UPDATE t_sendpdf ");
		sqlBil.append("   SET ");
		sqlBil.append("       sendflg  = 1 ");
		sqlBil.append("      ,senddate  = '" + CommonUtil.getNowUpdDate() + "'");
		sqlBil.append(" WHERE ");
		sqlBil.append("      empid     = " + empid + " ");
		sqlBil.append("  AND sendyear  = " + sendyear + " ");
		sqlBil.append("  AND sendmonth = " + sendmonth + " ");
		
		return sqlBil.toString();
	}
	
	/**
	 * @param args
	 */
	private List<TSendPdf> mapping(ResultSet rs) throws SQLException {
		
		List<TSendPdf> retList = new ArrayList<TSendPdf>();
		
		while (rs.next()){
			
			TSendPdf listItem = new TSendPdf();

			//
			listItem.setEmpid(rs.getInt("empid"));
			//
			listItem.setEmpname(rs.getString("empname"));
			//
			listItem.setSendyear(rs.getInt("sendyear"));
			//
			listItem.setSendmonth(rs.getInt("sendmonth"));
			//
			listItem.setSendfile(rs.getString("sendfile"));
			//
			listItem.setSendmail(rs.getString("sendmail"));
			//
			listItem.setSendflg(rs.getInt("sendflg"));
			//
			listItem.setSenddate(rs.getString("senddate"));
					
			retList.add(listItem);
		}
		
		return retList;	
		
	}
	
	/**
	 * @param args
	 */
	private List<SibPreparedStatement> createParam(String iYear, String iMonth) {
		
		// パラメーターリスト
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // パラメータの設定
		SibPreparedStatement param;		
		
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
	
}
