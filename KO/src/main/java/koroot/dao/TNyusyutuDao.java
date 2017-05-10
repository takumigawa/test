package koroot.dao;

import koroot.entity.TNyusyutu;

public interface TNyusyutuDao {

	public static final Class BEAN = TNyusyutu.class;
	public static final String selectById_ARGS = "nyusyutu_ID";

	public TNyusyutu[] selectAll();

	public TNyusyutu selectById(Integer nyusyutuId);

	public int insert(TNyusyutu tNyusyutu);

	public int update(TNyusyutu tNyusyutu);

	public int delete(TNyusyutu tNyusyutu);

}
