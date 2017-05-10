package sDBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SibDBAccess {

	private static SibDBAccess DBA;// = new SibDBAccess();

	private static String JDBCName = "com.mysql.jdbc.Driver";
	private static String BaseUrl = "jdbc:mysql://%rep%?characterEncoding=UTF-8&amp;characterSetResults=UTF-8";
	private static Connection mySqlcon;
	private static Statement stmt;
	private static SibDBAccessSetting DBASetting;
	
	private SibDBAccess(){
        
        try {
        	
        	if(DBASetting == null){
        		throw new Exception();
        	}
        	
        	String SQLUrl = BaseUrl.replaceAll("%rep%?",DBASetting.getURL() + "/" + DBASetting.getSchema());
        	
        	//JDBCドライバのロード
			Class.forName(JDBCName).newInstance();
	        //データベースに接続
	        mySqlcon = DriverManager.getConnection(SQLUrl, DBASetting.getID(), DBASetting.getPass());
	        //ステートメントオブジェクトを作成
        	stmt = mySqlcon.createStatement();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
      		
	}
	
	public ResultSet ExecuteQuery(String iSQL) throws SQLException{

		if(stmt == null) {
			throw new SQLException();
		}
		
        //検索するSQL実行
        ResultSet rs = stmt.executeQuery(iSQL);
        
		return rs;
	}
	
	public ResultSet ExecuteQuery(String iSQL, List<SibPreparedStatement> pst) throws SQLException{

		if(stmt == null) {
			throw new SQLException();
		}
		
		PreparedStatement pstmt = mySqlcon.prepareStatement(iSQL);

		Integer number = 1;
			
		for (SibPreparedStatement item : pst) {
			item.setPreparedStatement(pstmt, number);
			number++;
		}
		
        //検索するSQL実行
        ResultSet rs = pstmt.executeQuery();
        
		return rs;
	}
	
	public int ExecuteNonQuery(String iSQL, List<SibPreparedStatement> pst) throws SQLException{
		
		if (stmt == null) {
			throw new SQLException();
		}
		
		PreparedStatement pstmt = mySqlcon.prepareStatement(iSQL);

		Integer number = 1;
			
		for (SibPreparedStatement item : pst) {
			item.setPreparedStatement(pstmt, number);
			number++;
		}
		
		int rCnt;
		
		//SQLを実行する。
		rCnt = pstmt.executeUpdate(iSQL);
		
		return rCnt;
		
	}

	public int ExecuteNonQuery(String iSQL) throws SQLException{
		
		if (stmt == null) {
			throw new SQLException();
		}
		
		int rCnt;
		
		//SQLを実行する。
		rCnt = stmt.executeUpdate(iSQL);
		
		return rCnt;
		
	}
	
	public boolean BeginTransiction(){
		return true;
	}
	
	public boolean Commit(){
		return true;
	}
	
	public boolean RoleBack(){
		return true;
	}
	
	public boolean isConnect(){
		
		boolean flg = true;
		
		if(mySqlcon == null){
			flg = false;		
		}
		
		return flg;
	}
	
	public static SibDBAccess getInstance(){
		
		if (DBA == null) {
			DBA = new SibDBAccess();
		}
		return DBA;
	}
	
	public static SibDBAccess getInstance(SibDBAccessSetting Setting){
		
		if (DBASetting == null) {
			DBASetting = Setting;
		}
		
		if (DBA == null) {
			DBA = new SibDBAccess();
		}
		return DBA;
	}
	
	public void Close() throws SQLException {
		
		if(mySqlcon != null){
			mySqlcon.close();
		}
	}
}
