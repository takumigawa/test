package schedule.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.ClassListDto;
import schedule.dto.CombListDto;
import schedule.dto.ListDto;
import schedule.entity.TSetting;

@S2Dao(bean=TSetting.class)
public interface TSettingDao {

	public TSetting[] selectAll();

	/**
	 * <p>key項目セレクト</p>
	 * @param　大項目
	 * @param 中項目
	 * @param 小項目
	 * @return　汎用登録テーブルエンティティー
	 *
	 */
	@Arguments( { "key1", "key2", "key3" })
	public TSetting selectById(String key1, String key2, String key3);

	/**
	 * <p>key項目セレクト</p>
	 * @param　大項目
	 * @param 中項目
	 * @return　汎用登録テーブルエンティティー
	 *
	 */
	@Arguments( { "key1", "key2"})
	public TSetting[] selectByTwoId(String key1, String key2);

	/**
	 * <p>key項目セレクト</p>
	 * @param　大項目
	 * @param 中項目
	 * @return　汎用登録テーブルエンティティー
	 *
	 */
	@Arguments( "key2" )
	public ListDto[] selectByCombList(String key2);

	/**
	 * <p>key項目セレクト</p>
	 * @param　大項目
	 * @param 中項目
	 * @return　汎用登録テーブルエンティティー
	 *
	 */
	@Arguments( "key2" )
	public ListDto[] selectByStatusList(String key2);
	
	/**
	 * <p>key項目セレクト</p>
	 * @param　大項目
	 * @param 中項目
	 * @return　汎用登録テーブルエンティティー
	 *
	 */
	@Arguments( "key2" )
	public CombListDto[] selectByAllCombList(String key2);
	
	/**
	 * <p>key項目セレクト</p>
	 * @param　大項目
	 * @param 中項目
	 * @return　汎用登録テーブルエンティティー
	 *
	 */
	@Arguments( "kaihai" )
	public ClassListDto[] selectGetClassList(Integer kaihai);

	/**
	 * <p>権限リスト取得</p>
	 * @return　汎用登録テーブルエンティティー
	 *
	 */
	public List<ListDto> selectGetAuthList();
	
	/**
	 * <p>役職コンボ次の表示順取得　(表示順MAX値 + 10)</p>
	 * @return　int 表示順
	 *
	 */
	public int getClassNextOrderSEQ();

	/**
	 * <p>権限値の最大値取得　最大値</p>
	 * @return　int 表示順
	 *
	 */
	public Integer getMAXKengen();
	
	/**
	 * <p>役職コンボ次のID取得　(IDMAX値 + 1)</p>
	 * @return　int ID
	 *
	 */
	public int getClassNextID();
	
	public int insert(TSetting setting);

	public int update(TSetting setting);

	public int delete(TSetting setting);

}
