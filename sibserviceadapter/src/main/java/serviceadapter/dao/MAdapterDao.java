package serviceadapter.dao;

import org.seasar.dao.annotation.tiger.Arguments;

import serviceadapter.dto.ServiceListDto;
import serviceadapter.entity.MAdapter;

public interface MAdapterDao {

	public static final Class<?> BEAN = MAdapter.class;
	public static final String selectById_ARGS = "serviceType";

	public MAdapter[] selectAll();

	public MAdapter selectById(String servicetype);

	public int insert(MAdapter mAdapter);

	public int update(MAdapter mAdapter);

	public int delete(MAdapter mAdapter);

	//追加メソッド//
	
	/**
	 * <p>サービス一覧取得</p>
	 * @param 改廃(0:有効　1:無効　2:有効/無効)
	 * @return　サービス一覧
	 *
	 */
	@Arguments( "kaihai" )
	public ServiceListDto[] selectGetServiceList(Integer kaihai);
	
	/**
	 * <p>サービス表示順取得　(表示順MAX値 + 10)</p>
	 * @return　int 表示順
	 *
	 */
	public int getNextOrderSEQ();
}
