package te001.dao;

import java.sql.SQLException;

import sDBAccess.SibDBAccess;
import te001.entity.TSendPDF;
import dbcommon.CommonUtil;

public class TSendDataDao {

	public Integer DeleteSend() throws SQLException {
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQLê∂ê¨
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("DELETE ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_senddata ");
		sqlBil.append(" WHERE ");
		sqlBil.append("       EXISTS ( ");
		sqlBil.append("                SELECT 'X'");
		sqlBil.append("                  FROM t_tighten ");
		sqlBil.append("                 WHERE t_senddata.empid      = t_tighten.empid");
		sqlBil.append("                   AND t_senddata.empyear    = t_tighten.empyear");
		sqlBil.append("                   AND t_senddata.empmonth   = t_tighten.empmonth");
		sqlBil.append("                   AND t_tighten.tightenflg  = 1");
		sqlBil.append("                   AND t_tighten.approvalflg = 0");
		sqlBil.append("             ) ");
		
        // SQLé¿çs		
		Integer rCnt = DBA.ExecuteNonQuery(sqlBil.toString());
		
		return rCnt;
		
	}
	
	public Integer InsertSend(TSendPDF iItem) throws SQLException {
		
		SibDBAccess DBA = SibDBAccess.getInstance();

        // SQLê∂ê¨
		StringBuilder sqlBil = new StringBuilder();

		sqlBil.append("INSERT INTO t_senddata ");
		sqlBil.append("   ( ");
		sqlBil.append("    empid ");
		sqlBil.append("   ,empyear ");
		sqlBil.append("   ,empmonth ");
		sqlBil.append("   ,branchno ");
		sqlBil.append("   ,sendflg ");
		sqlBil.append("   ,empname ");
		sqlBil.append("   ,expDate ");
		sqlBil.append("   ,AppDate ");
		sqlBil.append("   ,Section ");
		sqlBil.append("   ,Summary ");
		sqlBil.append("   ,SectionFrom ");
		sqlBil.append("   ,SectionTo ");
		sqlBil.append("   ,Money ");
		sqlBil.append("   ,adddate ");
		sqlBil.append("   ,addid ");
		sqlBil.append("   ) ");
		sqlBil.append("   Value ");
		sqlBil.append("   ( ");
		sqlBil.append("    " + iItem.getEmpid().toString());
		sqlBil.append("   ," + iItem.getEmpyear().toString());
		sqlBil.append("   ," + iItem.getEmpmonth().toString());
		sqlBil.append("   ," + iItem.getBranchno().toString());
		sqlBil.append("   , 0 ");
		sqlBil.append("   ,'" + iItem.getEmpname() + "'");
		sqlBil.append("   ,'" + iItem.getExpDate() + "'");
		sqlBil.append("   ,'" + iItem.getAppday() + "'");
		sqlBil.append("   ,'" + iItem.getSection() + "'");
		sqlBil.append("   ,'" + iItem.getSummary() + "'");
		sqlBil.append("   ,'" + iItem.getSectionFrom() + "'");
		sqlBil.append("   ,'" + iItem.getSectionTo() + "'");
		sqlBil.append("   ," + iItem.getMoney().toString());
		sqlBil.append("   ,'" + CommonUtil.getNowUpdDate() + "'");
		sqlBil.append("   ,999999 ");
		sqlBil.append("   ) ");
		
		Integer rCnt = DBA.ExecuteNonQuery(sqlBil.toString());
		
		return rCnt;
		
	}
	
}
