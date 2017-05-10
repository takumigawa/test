package koroot.dao;

import koroot.entity.MKoza;

public interface MKozaDao {

	public static final Class BEAN = MKoza.class;
	public static final String selectById_ARGS = "KOZA_ID";

	public MKoza[] selectAll();

	public MKoza selectById(Integer kozaId);

	public int insert(MKoza mKoza);

	public int update(MKoza mKoza);

	public int delete(MKoza mKoza);

}
