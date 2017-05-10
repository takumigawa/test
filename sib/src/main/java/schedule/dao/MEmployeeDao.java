package schedule.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.EmpListDto;
import schedule.dto.ListDto;
import schedule.entity.MEmployee;

@S2Dao(bean=MEmployee.class)
public interface MEmployeeDao {

	public MEmployee[] selectAll();

	@Arguments("Employee_ID")
	public MEmployee selectById(Integer employeeId);
	
	/**
	 * <p>社員一覧取得</p>
	 * @param　名前絞り込み（あ～わ行指定)
	 * @param 改廃(0:有効　1:無効 2:有効/無効)
	 * @return　社員一覧
	 *
	 */
	@Arguments( { "aGyo", "kaihai" })
	public EmpListDto[] selectGetEmpList(String aGyo, Integer kaihai);
	
	/**
	 * <p>ログイン情報取得</p>
	 * @param　ログインID
	 * @param ログインパスワード
	 * @return　社員情報
	 *
	 */
	@Arguments( { "loginID", "loginPassword" })
	public MEmployee selectByIDPas(String loginID, String loginPassword);
	
	/**
	 * <p>デフォルト所属グループチェック取得</p>
	 * @param　社員ID
	 * @param グループID
	 * @return　社員情報
	 *
	 */
	@Arguments( { "EmployeeID", "GroupID" })
	public ListDto selectByEmpIDGruID(Integer EmployeeID, Integer GroupID);

	/**
	 * <p>ログインID取得</p>
	 * @param　社員ID
	 * @return　社員情報
	 *
	 */
	@Arguments( {"LoginID","EmployeeID"} )
	public Integer selectByEmpID(String LoginID, String EmployeeID);
	
	/**
	 * <p>役職ID使用チェック</p>
	 * @param　役職ID
	 * @return　社員マスタでの役職ID使用数
	 *
	 */
	@Arguments( "ClassID" )
	public Integer selectByGetClassIDCount(Integer ClassID);

	/**
	 * <p>グループID使用チェック</p>
	 * @param　グループID
	 * @return　社員マスタでのグループID使用数
	 *
	 */
	@Arguments( "GroupID" )
	public Integer selectByGetGroupIDCount(Integer GroupID);
	
	/**
	 * <p>社員表示順取得　(表示順MAX値 + 10)</p>
	 * @return　int 表示順
	 *
	 */
	public int getNextOrderSEQ();
	
	/**
	 * <p>社員一覧コンボ用取得</p>
	 * @return　コンボリストDto
	 *
	 */
	public ListDto[] selectGetEmpCombList();
	
	public int insert(MEmployee employee);

	public int update(MEmployee employee);

	public int delete(MEmployee employee);

}
