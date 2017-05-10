package schedule.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.MatListDto;
import schedule.entity.TMatter;

@S2Dao(bean=TMatter.class)
public interface TMatterDao {

	public TMatter[] selectAll();

	@Arguments("Matter_ID")
	public TMatter selectById(Integer matterId);
	
	/**
	 * <p>案件一覧取得</p>
	 * @param　名前絞り込み（あ～わ行指定)
	 * @param 社員ID検索
	 * @param 改廃(0:有効　1:無効 2:有効/無効)
	 * @return　案件一覧
	 *
	 */
	@Arguments( { "aGyo", "empID", "kaihai" })
	public MatListDto[] selectGetMatList(String aGyo, String empID, Integer kaihai);
	
	/**
	 * <p>社員の案件概要取得</p>
	 * @param 社員ID
	 * @return　社員の案件概要
	 */
	@Arguments("employeeID")
	public List<String> selectGetMatTitel(String employeeID);

	public int insert(TMatter matter);

	public int update(TMatter matter);

	public int delete(TMatter matter);

}
