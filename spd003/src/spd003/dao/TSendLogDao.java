package spd003.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sDBAccess.SibDBAccess;
import spd003.entity.TSendLog;

public class TSendLogDao {

	/**
	 * @param args
	 */
	public List<TSendLog> AllSelect() throws SQLException{
		
		List<TSendLog> retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQL����
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       id ");
		sqlBil.append("      ,senddate ");
		sqlBil.append("      ,sendtitel ");
		sqlBil.append("      ,senddetail ");
		sqlBil.append("      ,toaddress ");
		sqlBil.append("      ,fromaddress ");
		sqlBil.append("      ,adddate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendlog ");
		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString());
		
		retList = mapping(rs);
		
		rs.close();
		
		return retList;
	}
	
	/**
	 * @param args
	 */
	public boolean Insert(TSendLog iValue) throws SQLException {
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
	    Date date1 = new Date();  //(1)Date�I�u�W�F�N�g�𐶐�

	    //(2)SimpleDateFormat�I�u�W�F�N�g�𐶐�
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
        // SQL����
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("INSERT INTO t_sendlog ");
		sqlBil.append("      ( ");		
		sqlBil.append("       senddate ");
		sqlBil.append("      ,sendtitel ");
		sqlBil.append("      ,senddetail ");
		sqlBil.append("      ,toaddress ");
		sqlBil.append("      ,fromaddress ");
		sqlBil.append("      ) ");
		sqlBil.append("  value ");
		sqlBil.append("      ( ");		
		sqlBil.append("       '" + sdf1.format(date1) + "'");
		sqlBil.append("      ,'" + iValue.getSendTitel() + "'");
		sqlBil.append("      ,'" + iValue.getSendDetail() + "'");
		sqlBil.append("      ,'" + iValue.getToAddress() + "'");
		sqlBil.append("      ,'" + iValue.getFromAddress() + "'");
		sqlBil.append("      ) ");
		
		DBA.ExecuteNonQuery(sqlBil.toString());
		
		return true;
		
	}
	
	/**
	 * @param args
	 */
	private List<TSendLog> mapping(ResultSet rs) throws SQLException {
		
		List<TSendLog> retList = new ArrayList<TSendLog>();
		
		while (rs.next()){
			
			TSendLog listItem = new TSendLog();
			
			//ID
			listItem.setId(rs.getInt("ID"));
			//���M����
			listItem.setSendDate(rs.getString("senddate"));
			//���M���[���^�C�g��
			listItem.setSendTitel(rs.getString("sendtitel"));
			//���M���[���{��
			listItem.setSendDetail(rs.getString("senddetail"));
			//���M���A�h���X
			listItem.setToAddress(rs.getString("toaddress"));
			//���M��A�h���X
			listItem.setFromAddress(rs.getString("fromaddress"));
			
			retList.add(listItem);
		}
		
		return retList;
		
	}
	
}
