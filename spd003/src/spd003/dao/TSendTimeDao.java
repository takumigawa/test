package spd003.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sDBAccess.SibDBAccess;
import sDBAccess.SibPreparedStatement;
import spd003.dto.BatchParam;
import spd003.entity.TSendTime;

public class TSendTimeDao {

	/**
	 * @param args
	 */
	public List<TSendTime> AllSelect() throws SQLException{

		List<TSendTime> retList;

		SibDBAccess DBA = SibDBAccess.getInstance();

        // SQL����
		StringBuilder sqlBil = new StringBuilder();

		sqlBil.append("SELECT ");
		sqlBil.append("       id ");
		sqlBil.append("      ,sendtype ");
		sqlBil.append("      ,sendmailid ");
		sqlBil.append("      ,year ");
		sqlBil.append("      ,month ");
		sqlBil.append("      ,day ");
		sqlBil.append("      ,hour ");
		sqlBil.append("      ,minute ");
		sqlBil.append("      ,updtype ");
		sqlBil.append("      ,weeksunday ");
		sqlBil.append("      ,weekmonday ");
		sqlBil.append("      ,weektuesday ");
		sqlBil.append("      ,weekwednesday ");
		sqlBil.append("      ,weekthursday ");
		sqlBil.append("      ,weekfriday ");
		sqlBil.append("      ,weeksaturday ");
		sqlBil.append("      ,addid ");
		sqlBil.append("      ,adddate ");
		sqlBil.append("      ,updid ");
		sqlBil.append("      ,upddate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendtime ");

		ResultSet rs = DBA.ExecuteQuery(sqlBil.toString());

		retList = mapping(rs);

		rs.close();

		return retList;
	}

	/**
	 * @param args
	 */
	public List<TSendTime> SelectToTime(BatchParam bp) throws SQLException{

		List<TSendTime> retList;

		SibDBAccess DBA = SibDBAccess.getInstance();

		// �p�����[�^�[���X�g
		List<SibPreparedStatement> paramList = createParam(bp.getYear(), bp.getMonth(), bp.getDay(), bp.getHour(), bp.getMinute());

        // SQL���s
		ResultSet rs = DBA.ExecuteQuery(createSQL(bp.getYear(), bp.getMonth(), bp.getDay()), paramList);

		retList = mapping(rs);

		rs.close();

		return retList;
	}

	/**
	 * @param args
	 */
	public Integer Update(BatchParam bp) throws SQLException{

		SibDBAccess DBA = SibDBAccess.getInstance();

		Integer intFromMinute =  Integer.parseInt(bp.getMinute()) - 5;
		String FromMinute = String.format("%1$02d",intFromMinute);

		Integer intToMinute = Integer.parseInt(bp.getMinute()) + 5;
		String ToMinute = String.format("%1$02d",intToMinute);

        // SQL����
		StringBuilder sqlBil = new StringBuilder();

		sqlBil.append("UPDATE t_sendtime ");
		sqlBil.append("   SET updtype = 1 ");
		sqlBil.append(" WHERE ");
		sqlBil.append("       sendtype = 0 ");
		sqlBil.append("   AND year    <= '" + bp.getYear() + "'");
		sqlBil.append("   AND month   <= '" + bp.getMonth() + "'");
		sqlBil.append("   AND day     <= '" + bp.getDay() + "'");
		sqlBil.append("   AND hour    <= '" + bp.getHour() + "'");
		sqlBil.append("   AND minute BETWEEN '" + FromMinute + "' AND '" + ToMinute + "' ");
		sqlBil.append("   AND updtype  = 0 ");

		Integer rCnt = DBA.ExecuteNonQuery(sqlBil.toString());

		return rCnt;
	}

	/**
	 * @param args
	 */
	private List<TSendTime> mapping(ResultSet rs) throws SQLException {

		List<TSendTime> retList = new ArrayList<TSendTime>();

		while (rs.next()){

			TSendTime listItem = new TSendTime();

			//ID
			listItem.setID(rs.getInt("id"));
			//���M�^�C�v
			listItem.setSendType(rs.getString("sendtype"));
			//���M���[��ID
			listItem.setSendMailID(rs.getInt("sendmailid"));
			//���M�N
			listItem.setYear(rs.getString("year"));
			//���M��
			listItem.setMonth(rs.getString("month"));
			//���M��
			listItem.setDay(rs.getString("day"));
			//���M����
			listItem.setHour(rs.getString("hour"));
			//���M��
			listItem.setMinute(rs.getString("minute"));
			//�X�V�敪
			listItem.setUpdType(rs.getInt("updtype"));
			//���j���M�敪
			listItem.setWeekSunDay(rs.getString("weeksunday"));
			//���j���M�敪
			listItem.setWeekMonDay(rs.getString("weekmonday"));
			//�Ηj���M�敪
			listItem.setWeekTuesDay(rs.getString("weektuesday"));
			//���j���M�敪
			listItem.setWeekWednesDay(rs.getString("weekwednesday"));
			//�ؗj���M�敪
			listItem.setWeekThursDay(rs.getString("weekthursday"));
			//���j���M�敪
			listItem.setWeekFriDay(rs.getString("weekfriday"));
			//�y�j���M�敪
			listItem.setWeekSaturDay(rs.getString("weeksaturday"));
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

	/**
	 * @param args
	 */
	private List<SibPreparedStatement> createParam(String iYear, String iMonth, String iDay, String iHour, String iMinute) {

		// �p�����[�^�[���X�g
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // �p�����[�^�̐ݒ�
		SibPreparedStatement param;

		Integer intFromMinute =  Integer.parseInt(iMinute) - 5;
		String FromMinute = String.format("%1$02d",intFromMinute);

		Integer intToMinute = Integer.parseInt(iMinute) + 5;
		String ToMinute = String.format("%1$02d",intToMinute);

        //��x�������M�p�����[�^
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iYear);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iMonth);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iDay);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iHour);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(FromMinute);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(ToMinute);
		paramList.add(param);

		//���Ɉ�x�p�����[�^
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iHour);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(FromMinute);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(ToMinute);
		paramList.add(param);

		//�T�Ɉ�x�p�����[�^
		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iHour);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(FromMinute);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(ToMinute);
		paramList.add(param);

		//DEL-S 2013/10/18 t.fujimoto �p�����[�^�Ō���n���Ă��Ⴞ�߂Ȃ񂶂�ˁH
		////���Ɉ�x�p�����[�^
		//param = new SibPreparedStatement();
		//param.setType(SibPreparedStatement.preType.String);
		//param.setValue(iMonth);
		//paramList.add(param);
		//DEL-E 2013/10/18 t.fujimoto

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iDay);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(iHour);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(FromMinute);
		paramList.add(param);

		param = new SibPreparedStatement();
		param.setType(SibPreparedStatement.preType.String);
		param.setValue(ToMinute);
		paramList.add(param);

		return paramList;

	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	private String createSQL(String iYear, String iMonth, String iDay) {

		String WeekWher = "";

		Date  date;
		Calendar cal = Calendar.getInstance();

		try {
			date = DateFormat.getDateInstance().parse(iYear + "/" + iMonth + "/" + iDay);
			cal.setTime(date);

		} catch (ParseException e) {

		}

		//�j������
	    switch (cal.get(Calendar.DAY_OF_WEEK)) {
	        case Calendar.SUNDAY:
	        	WeekWher = "     AND weeksunday  = '1' ";
	        	break;

	        case Calendar.MONDAY:
	        	WeekWher = "     AND weekmonday  = '1' ";
	        	break;

	        case Calendar.TUESDAY:
	        	WeekWher = "     AND weektuesday  = '1' ";
	        	break;

	        case Calendar.WEDNESDAY:
	        	WeekWher = "     AND weekwednesday  = '1' ";
	        	break;

	        case Calendar.THURSDAY:
	        	WeekWher = "     AND weekthursday  = '1' ";
	        	break;

	        case Calendar.FRIDAY:
	        	WeekWher = "     AND weekfriday  = '1' ";
	        	break;

	        case Calendar.SATURDAY:
	        	WeekWher = "     AND weeksaturday  = '1' ";
	        	break;
	    }

		StringBuilder sqlBil = new StringBuilder();

		sqlBil.append("SELECT ");
		sqlBil.append("       id ");
		sqlBil.append("      ,sendtype ");
		sqlBil.append("      ,sendmailid ");
		sqlBil.append("      ,year ");
		sqlBil.append("      ,month ");
		sqlBil.append("      ,day ");
		sqlBil.append("      ,hour ");
		sqlBil.append("      ,minute ");
		sqlBil.append("      ,updtype ");
		sqlBil.append("      ,weeksunday ");
		sqlBil.append("      ,weekmonday ");
		sqlBil.append("      ,weektuesday ");
		sqlBil.append("      ,weekwednesday ");
		sqlBil.append("      ,weekthursday ");
		sqlBil.append("      ,weekfriday ");
		sqlBil.append("      ,weeksaturday ");
		sqlBil.append("      ,addid ");
		sqlBil.append("      ,adddate ");
		sqlBil.append("      ,updid ");
		sqlBil.append("      ,upddate ");
		sqlBil.append("  FROM ");
		sqlBil.append("    t_sendtime ");
		sqlBil.append(" WHERE ");
		sqlBil.append("       (");                            //��x�̂ݑ��M
		sqlBil.append("         sendtype = '0' ");
		sqlBil.append("     AND year    <= ?   ");
		sqlBil.append("     AND month   <= ?   ");
		sqlBil.append("     AND day     <= ?   ");
		sqlBil.append("     AND hour    <= ?   ");
		sqlBil.append("     AND minute BETWEEN ? AND ? ");
		sqlBil.append("     AND updtype  = 0 ");
		sqlBil.append("       )");

		sqlBil.append("    OR ");                             //����Ɉ�x
		sqlBil.append("       (");
		sqlBil.append("         sendtype = '1' ");
		sqlBil.append("     AND hour     = ?   ");
		sqlBil.append("     AND minute  BETWEEN ? AND ? ");
		sqlBil.append("     AND updtype  = 0 ");
		sqlBil.append("       )");

		sqlBil.append("    OR ");                             //�j���Ɉ�x
		sqlBil.append("       (");
		sqlBil.append("         sendtype = '2' ");
		sqlBil.append("     AND hour     = ?   ");
		sqlBil.append("     AND minute  BETWEEN ? AND ? ");
		sqlBil.append("     AND updtype  = 0 ");
		sqlBil.append(WeekWher);
		sqlBil.append("       )");

		sqlBil.append("    OR ");                             //���Ɉ�x
		sqlBil.append("       (");
		sqlBil.append("         sendtype = '3' ");
		//DEL-S 2013/05/15 t.fujimoto ����x�̃��[���z�M�Ō��w�肵���Ⴞ�߂Ȃ񂶂�Ȃ��H
		//sqlBil.append("     AND month    = ?   ");
		//DEL-E 2013/05/15 t.fujimoto
		sqlBil.append("     AND day      = ?   ");
		sqlBil.append("     AND hour     = ?   ");
		sqlBil.append("     AND minute  BETWEEN ? AND ? ");
		sqlBil.append("     AND updtype  = 0 ");
		sqlBil.append("       )");

		return sqlBil.toString();

	}
}
