package schedule.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.ComListDto;
import schedule.entity.TCommerical;


@S2Dao(bean=TCommerical.class)
public interface TCommericalDao {

	public TCommerical[] selectAll();

	@Arguments( { "cd_ID", "userID" })
	public TCommerical selectById(Integer cdId, Integer userid);

	/**
	 * <p>商流一覧取得</p>
	 * @param　商流ID
	 * @return　エンティティーリスト
	 *
	 */
	@Arguments( "cd_ID" )
	public TCommerical[] selectByIdList(Integer cdId);
	
	/**
	 * <p>商流顧客名商一覧取得</p>
	 * @param　商流ID
	 * @return　顧客名一覧
	 *
	 */
	@Arguments( "cd_ID")
	public ComListDto[] selectNameById(Integer cdId);
	
	public int insert(TCommerical commerical);

	public int update(TCommerical commerical);

	public int delete(TCommerical commerical);

}
