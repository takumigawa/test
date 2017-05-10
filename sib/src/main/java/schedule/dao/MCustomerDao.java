package schedule.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.CusListDto;
import schedule.dto.ListDto;
import schedule.entity.MCustomer;

@S2Dao(bean=MCustomer.class)
public interface MCustomerDao {

	public MCustomer[] selectAll();

	@Arguments("customer_ID")
	public MCustomer selectById(Integer customerId);
	
	/**
	 * <p>顧客一覧取得</p>
	 * @param　名前絞り込み（あ～わ行指定)
	 * @param 改廃(0:有効　1:無効 2:有効/無効)
	 * @return　顧客一覧
	 *
	 */
	@Arguments( { "aGyo", "kaihai" })
	public CusListDto[] selectGetCusList(String aGyo, Integer kaihai);
	
	/**
	 * <p>顧客表示順取得　(表示順MAX値 + 10)</p>
	 * @return　int 表示順
	 *
	 */
	public int getNextOrderSEQ();
	
	/**
	 * <p>顧客コンボリスト取得</p>
	 * @return　ListDto[] 顧客コンボ
	 *
	 */
	public ListDto[] selectGetCusCombList();	
	
	public int insert(MCustomer customer);

	public int update(MCustomer customer);

	public int delete(MCustomer customer);

}
