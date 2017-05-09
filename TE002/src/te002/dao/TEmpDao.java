package te002.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sDBAccess.SibDBAccess;
import sDBAccess.SibPreparedStatement;
import dbcommon.CommonUtil;
import te002.entity.TEmp;
import te002.entity.TSendPDF;

public class TEmpDao {

	public List<TEmp> SelectGroup() throws SQLException {
		
		List<TEmp> retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       empid ");
		sqlBil.append("      ,empyear ");
		sqlBil.append("      ,empmonth ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_senddata ");
		sqlBil.append(" WHERE ");
		sqlBil.append("      sendflg  = 0 ");
		sqlBil.append("  group by empid,empyear,empmonth");
		sqlBil.append("  order by empid,empyear,empmonth");
		
        // SQL実行		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString());
		
		retList = mapping(rs);
		
		rs.close();
				
		return retList;	
		
	}
	
	public List<TSendPDF> SelectEMPYM(Integer empid, Integer empYear, Integer empMonth) throws SQLException {
		
		List<TSendPDF> retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       * ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_senddata t1 ");
		sqlBil.append("   ,(SELECT  ");
		sqlBil.append("            empid ");
		sqlBil.append("           ,empyear  ");
		sqlBil.append("           ,empmonth  ");
		sqlBil.append("           ,SUM(Money) as totalMoney  ");
		sqlBil.append("       FROM  t_senddata ");
		sqlBil.append("      WHERE ");
		sqlBil.append("           sendflg   = 0 ");
		sqlBil.append("      GROUP BY empid,empyear,empmonth ");
		sqlBil.append("             ) t2 ");
		sqlBil.append(" WHERE ");
		sqlBil.append("       t1.empid     = ? ");
		sqlBil.append("   AND t1.empyear   = ? ");
		sqlBil.append("   AND t1.empmonth  = ? ");
		sqlBil.append("   AND t1.sendflg   = 0 ");
		sqlBil.append("   AND t1.empid     = t2.empid ");
		sqlBil.append("   AND t1.empyear   = t2.empyear ");
		sqlBil.append("   AND t1.empmonth  = t2.empmonth ");
		sqlBil.append("  order by t1.AppDate,t1.branchno");
		
		// パラメーターリスト
		List<SibPreparedStatement> paramList = createParam(empid ,empYear ,empMonth);
		
        // SQL実行		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString(),paramList);
		
		retList = mappingPDF(rs);
		
		rs.close();
				
		return retList;	
		
	}
	
	public Integer updateSend(Integer empid, Integer empYear, Integer empMonth) throws SQLException {
		
		SibDBAccess DBA = SibDBAccess.getInstance();

        // SQL生成
		StringBuilder sqlBil = new StringBuilder();

		sqlBil.append("UPDATE t_senddata ");
		sqlBil.append("   SET sendflg = 1 ");
		sqlBil.append("      ,updid   = 999999 ");
		sqlBil.append("      ,upddate = '" + CommonUtil.getNowUpdDate() + "'");
		sqlBil.append(" WHERE ");
		sqlBil.append("       sendflg = 0 ");
		sqlBil.append("   AND empid     = " + empid.toString() );
		sqlBil.append("   AND empyear   = " + empYear.toString() );
		sqlBil.append("   AND empmonth = " + empMonth.toString() );

		Integer rCnt = DBA.ExecuteNonQuery(sqlBil.toString());
		
		return rCnt;
		
	}
	
	/**
	 * @param args
	 */
	private List<TSendPDF> mappingPDF(ResultSet rs) throws SQLException {
		
		List<TSendPDF> retList = new ArrayList<TSendPDF>();
		
		while (rs.next()){
			
			TSendPDF listItem = new TSendPDF();
		
			//
			listItem.setEmpname(rs.getString("empname"));
			//
			listItem.setSection(rs.getString("Section"));
			//
			listItem.setEmpmonth(rs.getString("empmonth"));
			//
			listItem.setAppday(rs.getString("AppDate"));
			//
			listItem.setSectionFrom(rs.getString("SectionFrom"));
			//
			listItem.setSectionTo(rs.getString("SectionTo"));
			//
			listItem.setSummary(rs.getString("Summary"));
			//
			listItem.setMoney(rs.getInt("Money"));
			//
			listItem.setTotalMoney(rs.getInt("totalMoney"));
			//
			listItem.setExpDate(rs.getString("expDate"));
			
			retList.add(listItem);
		}
		
		return retList;	
		
	}
	
	/**
	 * @param args
	 */
	private List<TEmp> mapping(ResultSet rs) throws SQLException {
		
		List<TEmp> retList = new ArrayList<TEmp>();
		
		while (rs.next()){
			
			TEmp listItem = new TEmp();
			
			//
			listItem.setEmpid(rs.getInt("empid"));
			//
			listItem.setEmpyear(rs.getInt("empyear"));
			//
			listItem.setEmpmonth(rs.getInt("empmonth"));
					
			retList.add(listItem);
		}
		
		return retList;	
		
	}
	
	/**
	 * @param args
	 */
	private List<SibPreparedStatement> createParam(Integer iID, Integer iYear, Integer iMonth) {
		
		// パラメーターリスト
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // パラメータの設定
		SibPreparedStatement param;		

        //
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(iID);
		paramList.add(param);
		
        //
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(iYear);
		paramList.add(param);
		
		//
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(iMonth);
		paramList.add(param);
				
		return paramList;
		
	}
	
}
