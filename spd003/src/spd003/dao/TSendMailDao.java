package spd003.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import sDBAccess.SibDBAccess;
import sDBAccess.SibPreparedStatement;
import spd003.entity.TSendMail;

public class TSendMailDao {

	/**
	 * @param args
	 */
	public List<TSendMail> AllSelect() throws SQLException{
		
		List<TSendMail> retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       id ");
		sqlBil.append("      ,mailtitel ");
		sqlBil.append("      ,maildetail ");
		sqlBil.append("      ,frommailaddress ");
		sqlBil.append("      ,atachdfile ");
		sqlBil.append("      ,addid ");
		sqlBil.append("      ,adddate ");
		sqlBil.append("      ,updid ");
		sqlBil.append("      ,upddate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendmail ");
		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString());
		
		retList = mapping(rs);
		
		rs.close();
		
		return retList;
	}
	
	/**
	 * @param args
	 */
	public TSendMail selectById(Integer iID) throws SQLException {
		
		SibDBAccess DBA = SibDBAccess.getInstance();
        
		// パラメーターリスト
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // パラメータの設定
		SibPreparedStatement param = new SibPreparedStatement();
		
        // サンプルデータの主キーの値を設定
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(iID);
		
		paramList.add(param);
        
        // SQL生成
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       id ");
		sqlBil.append("      ,mailtitel ");
		sqlBil.append("      ,maildetail ");
		sqlBil.append("      ,frommailaddress ");
		sqlBil.append("      ,atachdfile ");
		sqlBil.append("      ,addid ");
		sqlBil.append("      ,adddate ");
		sqlBil.append("      ,updid ");
		sqlBil.append("      ,upddate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendmail ");
		sqlBil.append(" WHERE ");
		sqlBil.append("    id = ? ");
		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString(), paramList);
		
		rs.next();
		
		TSendMail listItem = new TSendMail();
		
		//ID
		listItem.setID(rs.getInt("id"));
		//メールタイトル
		listItem.setMailTitel(rs.getString("mailtitel"));
		//メール本文
		listItem.setMailDitail(rs.getString("maildetail"));
		//送信元メールアドレス
		listItem.setFromMailAddress(rs.getString("frommailaddress"));
		//添付ファイル
		
		//追加ID
		listItem.setAddID(rs.getInt("addid"));
		//追加日時
		listItem.setAddDate(rs.getString("adddate"));
		//更新ID
		listItem.setUpdID(rs.getInt("updid"));				
		//更新日時
		listItem.setUpdDate(rs.getString("upddate"));
		
		return listItem;
		
	}
	
	/**
	 * @param args
	 */
	private List<TSendMail> mapping(ResultSet rs) throws SQLException {
		
		List<TSendMail> retList = new ArrayList<TSendMail>();
		
		while (rs.next()){
			
			TSendMail listItem = new TSendMail();
			
			//ID
			listItem.setID(rs.getInt("id"));
			//メールタイトル
			listItem.setMailTitel(rs.getString("mailtitel"));
			//メール本文
			listItem.setMailDitail(rs.getString("mailditail"));
			//送信元メールアドレス
			listItem.setFromMailAddress(rs.getString("FromMailAddress"));
			//添付ファイル
			
			//追加ID
			listItem.setAddID(rs.getInt("addid"));
			//追加日時
			listItem.setAddDate(rs.getString("adddate"));
			//更新ID
			listItem.setUpdID(rs.getInt("updid"));				
			//更新日時
			listItem.setUpdDate(rs.getString("upddate"));
			
			retList.add(listItem);
		}
		
		return retList;
		
	}
} 
