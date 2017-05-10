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

        // SQL生成
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

		// パラメーターリスト
		List<SibPreparedStatement> paramList = createParam(bp.getYear(), bp.getMonth(), bp.getDay(), bp.getHour(), bp.getMinute());

        // SQL実行
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

        // SQL生成
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
			//送信タイプ
			listItem.setSendType(rs.getString("sendtype"));
			//送信メールID
			listItem.setSendMailID(rs.getInt("sendmailid"));
			//送信年
			listItem.setYear(rs.getString("year"));
			//送信月
			listItem.setMonth(rs.getString("month"));
			//送信日
			listItem.setDay(rs.getString("day"));
			//送信時間
			listItem.setHour(rs.getString("hour"));
			//送信分
			listItem.setMinute(rs.getString("minute"));
			//更新区分
			listItem.setUpdType(rs.getInt("updtype"));
			//日曜送信区分
			listItem.setWeekSunDay(rs.getString("weeksunday"));
			//月曜送信区分
			listItem.setWeekMonDay(rs.getString("weekmonday"));
			//火曜送信区分
			listItem.setWeekTuesDay(rs.getString("weektuesday"));
			//水曜送信区分
			listItem.setWeekWednesDay(rs.getString("weekwednesday"));
			//木曜送信区分
			listItem.setWeekThursDay(rs.getString("weekthursday"));
			//金曜送信区分
			listItem.setWeekFriDay(rs.getString("weekfriday"));
			//土曜送信区分
			listItem.setWeekSaturDay(rs.getString("weeksaturday"));
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

	/**
	 * @param args
	 */
	private List<SibPreparedStatement> createParam(String iYear, String iMonth, String iDay, String iHour, String iMinute) {

		// パラメーターリスト
		List<SibPreparedStatement> paramList = new ArrayList<SibPreparedStatement>();

        // パラメータの設定
		SibPreparedStatement param;

		Integer intFromMinute =  Integer.parseInt(iMinute) - 5;
		String FromMinute = String.format("%1$02d",intFromMinute);

		Integer intToMinute = Integer.parseInt(iMinute) + 5;
		String ToMinute = String.format("%1$02d",intToMinute);

        //一度だけ送信パラメータ
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

		//日に一度パラメータ
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

		//週に一度パラメータ
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

		//DEL-S 2013/10/18 t.fujimoto パラメータで月を渡してちゃだめなんじゃね？
		////月に一度パラメータ
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

		//曜日判定
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
		sqlBil.append("       (");                            //一度のみ送信
		sqlBil.append("         sendtype = '0' ");
		sqlBil.append("     AND year    <= ?   ");
		sqlBil.append("     AND month   <= ?   ");
		sqlBil.append("     AND day     <= ?   ");
		sqlBil.append("     AND hour    <= ?   ");
		sqlBil.append("     AND minute BETWEEN ? AND ? ");
		sqlBil.append("     AND updtype  = 0 ");
		sqlBil.append("       )");

		sqlBil.append("    OR ");                             //一日に一度
		sqlBil.append("       (");
		sqlBil.append("         sendtype = '1' ");
		sqlBil.append("     AND hour     = ?   ");
		sqlBil.append("     AND minute  BETWEEN ? AND ? ");
		sqlBil.append("     AND updtype  = 0 ");
		sqlBil.append("       )");

		sqlBil.append("    OR ");                             //曜日に一度
		sqlBil.append("       (");
		sqlBil.append("         sendtype = '2' ");
		sqlBil.append("     AND hour     = ?   ");
		sqlBil.append("     AND minute  BETWEEN ? AND ? ");
		sqlBil.append("     AND updtype  = 0 ");
		sqlBil.append(WeekWher);
		sqlBil.append("       )");

		sqlBil.append("    OR ");                             //月に一度
		sqlBil.append("       (");
		sqlBil.append("         sendtype = '3' ");
		//DEL-S 2013/05/15 t.fujimoto 月一度のメール配信で月指定しちゃだめなんじゃない？
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
