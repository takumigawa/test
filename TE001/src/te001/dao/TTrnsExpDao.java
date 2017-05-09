package te001.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sDBAccess.SibDBAccess;
import te001.entity.TSendPDF;
import dbcommon.CommonUtil;


public class TTrnsExpDao {

	public List<TSendPDF> SelectYM() throws SQLException {
		
		List<TSendPDF> retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQLê∂ê¨
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       T1.empid as empid ");
		sqlBil.append("      ,T1.empyear as empyear ");
		sqlBil.append("      ,T1.empmonth as empmonth ");
		sqlBil.append("      ,T1.empname as empname ");
		sqlBil.append("      ,T1.Section as Section ");
		sqlBil.append("      ,T2.branchno as branchno ");
		sqlBil.append("      ,T2.ApplicationDate AS AppDate ");
		sqlBil.append("      ,T2.SectionFrom as SectionFrom ");
		sqlBil.append("      ,T2.SectionTo as SectionTo ");
		sqlBil.append("      ,T2.Summary as Summary ");
		sqlBil.append("      ,T2.Money as Money ");
		sqlBil.append("      ,T1.expDate as expDate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_tighten T1 ");
		sqlBil.append("   ,t_transexp T2 ");
		sqlBil.append(" WHERE ");
		sqlBil.append("       T1.tightenflg   = 1 ");
		sqlBil.append("   AND T1.approvalflg  = 0 ");
		sqlBil.append("   AND T1.empid     = T2.empid ");
		sqlBil.append("   AND T1.empyear   = T2.empyear ");
		sqlBil.append("   AND T1.empmonth  = T2.empmonth ");

		
        // SQLé¿çs		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString());
		
		retList = mapping(rs);
		
		rs.close();
				
		return retList;	
		
	}
		
	public Integer updateSend() throws SQLException {
		
		SibDBAccess DBA = SibDBAccess.getInstance();

        // SQLê∂ê¨
		StringBuilder sqlBil = new StringBuilder();

		sqlBil.append("UPDATE t_tighten ");
		sqlBil.append("   SET approvalflg = 1 ");
		sqlBil.append("      ,updid   = 999999 ");
		sqlBil.append("      ,upddate = '" + CommonUtil.getNowUpdDate() + "'");
		sqlBil.append(" WHERE ");
		sqlBil.append("       approvalflg = 0 ");
		sqlBil.append("   AND tightenflg  = 1 ");

		Integer rCnt = DBA.ExecuteNonQuery(sqlBil.toString());
		
		return rCnt;
		
	}
	
	/**
	 * @param args
	 */
	private List<TSendPDF> mapping(ResultSet rs) throws SQLException {
		
		List<TSendPDF> retList = new ArrayList<TSendPDF>();
		
		while (rs.next()){
			
			TSendPDF listItem = new TSendPDF();

			//
			listItem.setEmpid(rs.getInt("empid"));
			//
			listItem.setEmpyear(rs.getInt("empyear"));
			//
			listItem.setEmpmonth(rs.getInt("empmonth"));
			//
			listItem.setEmpname(rs.getString("empname"));
			//
			listItem.setBranchno(rs.getInt("branchno"));
			//
			listItem.setSection(rs.getString("Section"));
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
			listItem.setExpDate(rs.getString("expDate"));
			
			retList.add(listItem);
		}
		
		return retList;	
		
	}
	
}
