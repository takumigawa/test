package sibpayadvice.dao;

import sibpayadvice.entity.TCalender;

public interface TCalenderDao {

	public static final Class<TCalender> BEAN = TCalender.class;
	public static final String selectById_ARGS = "year, month";

	public TCalender[] selectAll();

	public TCalender selectById(Integer year, Integer month);

	public int insert(TCalender tCalender);

	public int update(TCalender tCalender);

	public int delete(TCalender tCalender);

}
