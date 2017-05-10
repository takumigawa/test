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
		
        // SQL����
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
        
		// �p�����[�^�[���X�g
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // �p�����[�^�̐ݒ�
		SibPreparedStatement param = new SibPreparedStatement();
		
        // �T���v���f�[�^�̎�L�[�̒l��ݒ�
		param.setType(SibPreparedStatement.preType.Int);
		param.setValue(iID);
		
		paramList.add(param);
        
        // SQL����
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
		//���[���^�C�g��
		listItem.setMailTitel(rs.getString("mailtitel"));
		//���[���{��
		listItem.setMailDitail(rs.getString("maildetail"));
		//���M�����[���A�h���X
		listItem.setFromMailAddress(rs.getString("frommailaddress"));
		//�Y�t�t�@�C��
		
		//�ǉ�ID
		listItem.setAddID(rs.getInt("addid"));
		//�ǉ�����
		listItem.setAddDate(rs.getString("adddate"));
		//�X�VID
		listItem.setUpdID(rs.getInt("updid"));				
		//�X�V����
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
			//���[���^�C�g��
			listItem.setMailTitel(rs.getString("mailtitel"));
			//���[���{��
			listItem.setMailDitail(rs.getString("mailditail"));
			//���M�����[���A�h���X
			listItem.setFromMailAddress(rs.getString("FromMailAddress"));
			//�Y�t�t�@�C��
			
			//�ǉ�ID
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
