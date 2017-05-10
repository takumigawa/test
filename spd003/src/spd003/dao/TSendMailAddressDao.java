package spd003.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sDBAccess.SibDBAccess;
import sDBAccess.SibPreparedStatement;

import spd003.entity.TSendMailAddress;

public class TSendMailAddressDao {

	/**
	 * @param args
	 */
	public List<TSendMailAddress> ALLSelect() throws SQLException{
		
		List<TSendMailAddress> retList;

		SibDBAccess DBA = SibDBAccess.getInstance();
		
        // SQL����
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       id ");
		sqlBil.append("      ,sendmailid ");
		sqlBil.append("      ,tomailaddress ");
		sqlBil.append("      ,addid ");
		sqlBil.append("      ,adddate ");
		sqlBil.append("      ,updid ");
		sqlBil.append("      ,upddate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendmailaddress ");
		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString());
		
		retList = mapping(rs);
		
		rs.close();
		
		return retList;
	}
	
	/**
	 * @param args
	 */
	public List<TSendMailAddress> SelectToMailID(Integer iMailID) throws SQLException{
		
		List<TSendMailAddress> retList;

		SibDBAccess DBA = SibDBAccess.getInstance();
		
		// �p�����[�^�[���X�g
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // �p�����[�^�̐ݒ�
		SibPreparedStatement param = new SibPreparedStatement();
		
        // �T���v���f�[�^�̎�L�[�̒l��ݒ�
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(iMailID);
		
		paramList.add(param);
        
        // SQL����
		StringBuilder sqlBil = new StringBuilder();
		
		sqlBil.append("SELECT ");
		sqlBil.append("       id ");
		sqlBil.append("      ,sendmailid ");
		sqlBil.append("      ,tomailaddress ");
		sqlBil.append("      ,addid ");
		sqlBil.append("      ,adddate ");
		sqlBil.append("      ,updid ");
		sqlBil.append("      ,upddate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendmailaddress ");
		sqlBil.append(" WHERE ");
		sqlBil.append("    sendmailid = ? ");
		
		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString(), paramList);
		
		retList = mapping(rs);
		
		return retList;
	}
	
	/**
	 * @param args
	 */
	private List<TSendMailAddress> mapping(ResultSet rs) throws SQLException {
		
		List<TSendMailAddress> retList = new ArrayList<TSendMailAddress>();
				
		while (rs.next()){
			
			TSendMailAddress listItem = new TSendMailAddress();
			
			//ID
			listItem.setID(rs.getInt("id"));
			//���M���[��ID
			listItem.setSendMailID(rs.getInt("sendmailid"));
			//���M���[���A�h���X
			listItem.setToMailAddress(rs.getString("tomailaddress"));
			//�ǉ����[�U�[ID
			listItem.setAddID(rs.getInt("addid"));
			//�ǉ�����
			listItem.setAddDate(rs.getString("adddate"));
			//�X�VID
			listItem.setUpdID(rs.getInt("updid"));				
			//�X�V����
			listItem.setUpdDate(rs.getString("upddate"));
			
			retList.add(listItem);
		}
		
		return retList;
		
	}
}
