package spd001.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sDBAccess.SibDBAccess;
import spd001.entity.TSendym;

public class TSendymDao {

	public List<TSendym> SelectALL() throws SQLException {
		
		List<TSendym> retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       sendyear ");
		sqlBil.append("      ,sendmonth ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendym ");
		sqlBil.append(" WHERE ");
		sqlBil.append("      sendflg  = 0 ");
			
        // SQL実行		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString());
		
		retList = mapping(rs);
		
		rs.close();
				
		return retList;	
		
	}
	
	/**
	 * @param args
	 */
	private List<TSendym> mapping(ResultSet rs) throws SQLException {
		
		List<TSendym> retList = new ArrayList<TSendym>();
		
		while (rs.next()){
			
			TSendym listItem = new TSendym();
			
			//
			listItem.setSendyear(rs.getInt("sendyear"));
			//
			listItem.setSendmonth(rs.getInt("sendmonth"));
				
			retList.add(listItem);
		}
		
		return retList;	
		
	}
	
	public boolean Update(Integer SendYear, Integer SendMonth) throws SQLException {
		
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
		//Update文を実行
		DBA.ExecuteNonQuery(createUpdateSQL(SendYear, SendMonth));
		
		return true;	
		
	}
	
	/** updateSQL生成　*/
	private String createUpdateSQL(Integer SendYear, Integer SendMonth) {
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("UPDATE t_sendym ");
		sqlBil.append("   SET ");
		sqlBil.append("       sendflg = 1 ");
		sqlBil.append(" WHERE ");
		sqlBil.append("      sendyear  = " + SendYear + " ");
		sqlBil.append("  AND sendmonth = " + SendMonth + " ");

		return sqlBil.toString();
	}

	
}
