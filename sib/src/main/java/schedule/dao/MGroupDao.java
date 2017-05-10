package schedule.dao;
import java.util.ArrayList;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.GroupListDto;
import schedule.dto.ListDto;
import schedule.entity.MGroup;


@S2Dao(bean=MGroup.class)
public interface MGroupDao {

	public MGroup[] selectAll();

	@Arguments("Group_ID")
	public MGroup selectById(Integer groupId);
	
	/**
	 * <p>グループ一覧取得</p>
	 * @param　名前絞り込み（あ～わ行指定)
	 * @param 改廃(0:有効　1:無効 2:有効/無効)
	 * @return　グループ一覧
	 *
	 */
	@Arguments( { "aGyo", "kaihai" })
	public GroupListDto[] selectGetGroupList(String aGyo, Integer kaihai);

	/**
	 * <p>社員に対するグループの登録取得</p>
	 * @param　社員ID
	 * @return　グループ一覧
	 *
	 */
	@Arguments("empID" )
	public GroupListDto[] selectByEmployeeId(Integer empID);
	
	/**
	 * <p>グループ表示順取得　(表示順MAX値 + 10)</p>
	 * @return　int 表示順
	 *
	 */
	public int getNextOrderSEQ();
	
	/**
	 * <p>グループ一覧コンボ用取得</p>
	 * @return　コンボリストDto
	 *
	 */
	public ListDto[] selectGetGroupCombList();

	/**
	 * <p>グループ所属社員取得</p>
	 * @param　グループID
	 * @return　コンボリストDto
	 *
	 */
	@Arguments("GroupID")
	public ArrayList<ListDto> selectGetGroupAttachList(Integer GroupID);

	/**
	 * <p>グループ非所属社員取得</p>
	 * @param　グループID
	 * @return　コンボリストDto
	 *
	 */
	@Arguments("GroupID")
	public ArrayList<ListDto> selectGetGroupnonAttachList(Integer GroupID);
	
	public int insert(MGroup group);

	public int update(MGroup group);

	public int delete(MGroup group);

}
