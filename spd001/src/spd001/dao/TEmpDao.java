package spd001.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sDBAccess.SibDBAccess;
import sDBAccess.SibPreparedStatement;
import spd001.dto.BatchParam;
import spd001.entity.TEmp;

public class TEmpDao {

	public List<TEmp> SelectYM(BatchParam bp) throws SQLException {
		
		List<TEmp> retList;
		
		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQL����
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       empid ");
		sqlBil.append("      ,empyear ");
		sqlBil.append("      ,empmonth ");
		sqlBil.append("      ,empname ");
		sqlBil.append("      ,workinghours ");
		sqlBil.append("      ,attendancedays ");
		sqlBil.append("      ,Absentdays ");
		sqlBil.append("      ,useannualdays ");
		sqlBil.append("      ,nonuseannualdays ");
		sqlBil.append("      ,dependent ");
		sqlBil.append("      ,summervacation ");
		sqlBil.append("      ,sendmail ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_emp ");
		sqlBil.append(" WHERE ");
		sqlBil.append("      empyear  = ? ");
		sqlBil.append("  AND empmonth = ? ");
		
		// �p�����[�^�[���X�g
		List<SibPreparedStatement> paramList = createParam(bp.getYear(), bp.getMonth());
		
        // SQL���s		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString(), paramList);
		
		retList = mapping(rs);
		
		rs.close();
				
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
			//
			listItem.setEmpname(rs.getString("empname"));
			//
			listItem.setWorkinghours(rs.getBigDecimal("workinghours"));
			//
			listItem.setAbsentdays(rs.getInt("attendancedays"));
			//
			listItem.setAbsentdays(rs.getInt("Absentdays"));
			//
			listItem.setUseannualdays(rs.getInt("useannualdays"));
			//
			listItem.setNonuseannualdays(rs.getInt("nonuseannualdays"));
			//
			listItem.setDependent(rs.getInt("dependent"));
			//
			listItem.setSummervacation(rs.getInt("summervacation"));
			//
			listItem.setSendmail(rs.getString("sendmail"));
					
			retList.add(listItem);
		}
		
		return retList;	
		
	}
	
	/**
	 * @param args
	 */
	private List<SibPreparedStatement> createParam(String iYear, String iMonth) {
		
		// �p�����[�^�[���X�g
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // �p�����[�^�̐ݒ�
		SibPreparedStatement param;		
		
        //
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iYear);
		paramList.add(param);
		
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iMonth);
		paramList.add(param);
				
		return paramList;
		
	}
	
}
