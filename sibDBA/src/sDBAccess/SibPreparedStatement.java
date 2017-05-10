package sDBAccess;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;


public class SibPreparedStatement {
	
	public enum preType{
		String,
		Int,
		Long,
		Byte,
		Float,
		Double,
		Date,
		Time,
		Boolean,
		Timestamp
	}
			
	private Object tValue;
	private preType tType;
	
	public void setValue(Object iValue){
		this.tValue = iValue;
	}
	public Object getValue(){
		return tValue;
	}
	
	public void setType(preType iType){
		this.tType = iType;
	}
	public preType getType(){
		return this.tType;
	}
	
	public void setPreparedStatement(PreparedStatement pstmt,Integer number) throws SQLException{
		
		if (this.tType == preType.String) {
			pstmt.setString(number, (String) this.tValue);
		}
		
		if (this.tType == preType.Int){		
			pstmt.setInt(number, (Integer) this.tValue);
		}
		
		if (this.tType == preType.Long) {
			pstmt.setLong(number, (Long) this.tValue);
		}
		
		if (this.tType == preType.Byte) {
			pstmt.setByte(number, (Byte) this.tValue);
		}
		
		if (this.tType == preType.Float) {
			pstmt.setFloat(number, (Float) this.tValue);
		}
		
		if (this.tType == preType.Double ) {
			pstmt.setDouble(number, (Double) this.tValue);
		}
		
		if (this.tType == preType.Date) {
			pstmt.setDate(number, (Date) this.tValue);
		}
		
		if (this.tType == preType.Time) {
			pstmt.setTime(number, (Time) this.tValue);
		}
		
		if (this.tType == preType.Boolean ){
			pstmt.setBoolean(number, (Boolean) this.tValue);
		}
		
		if (this.tType == preType.Timestamp ) {
			pstmt.setTimestamp(number, (Timestamp) this.tValue);
			
		}
	}
	
}
