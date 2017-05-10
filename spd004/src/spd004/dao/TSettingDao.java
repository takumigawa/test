package spd004.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import sDBAccess.SibDBAccess;
import spd004.entity.TSetting;


public class TSettingDao {

	public TSetting Select() throws SQLException {
		
		TSetting retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQLê∂ê¨
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       empname ");
		sqlBil.append("      ,empmail ");
		sqlBil.append("  FROM ");
		sqlBil.append("    m_setting ");
			
        // SQLé¿çs		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString());
		
		retList = mapping(rs);
		
		rs.close();
				
		return retList;	
		
	}
	
	/**
	 * @param args
	 */
	private TSetting mapping(ResultSet rs) throws SQLException {
		
		TSetting retList = new TSetting();
		
		while (rs.next()){
			

			//
			retList.setEmpname(rs.getString("empname"));
			//
			retList.setEmpmail(rs.getString("empmail"));

		}
		
		return retList;	
		
	}
	
}
