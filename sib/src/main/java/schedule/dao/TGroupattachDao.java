package schedule.dao;
import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.EmpListDto;
import schedule.entity.TGroupattach;


@S2Dao(bean=TGroupattach.class)
public interface TGroupattachDao {

	public TGroupattach[] selectAll();

	@Arguments( { "group_ID", "Employee_ID" })
	public TGroupattach selectById(Integer groupId, Integer employeeId);

	public int insert(TGroupattach groupattach);

	public int update(TGroupattach groupattach);

	public int delete(TGroupattach groupattach);

	/**
	 * <p>グループ所属社員削除</p>
	 * @param　グループID
	 * @return　コンボリストDto
	 *
	 */
	@Arguments("GroupID")
	public int deletebyId(Integer GroupID);
	
	/**
	 * <p>グループ所属社員情報リストの取得</p>
	 * @param グループId
	 * @return グループ所属社員情報リスト
	 */
	@Arguments("GroupID")
	public List<EmpListDto> selectGetEmpList(Integer GroupID);
	
}
