package schedule.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.PJCodeDto;
import schedule.dto.PJMstDto;
import schedule.entity.TProject;

@S2Dao(bean=TProject.class)
public interface TProjectDao {

	/**
	 * <p>プロジェクトコード一覧取得</p>
	 * @param 改廃(0:有効　1:無効　2:有効/無効)
	 * @return　プロジェクトコード一覧
	 *
	 */
	@Arguments( "kaihai" )
	public PJCodeDto[] selectGetPjcodeList(Integer kaihai);

	@Arguments( "kaihai" )
	public PJMstDto[] selectGetPjmstList(Integer kaihai);
	
	@Arguments("PJ_CODE")
	public TProject selectById(String pjCode);

	public int insert(TProject tProject);

	public int update(TProject tProject);

	public int delete(TProject tProject);
	
	@Arguments({"Employee_ID","kaihai"})
	public PJCodeDto[] selectGetuserPjcodeList(Integer Employee_ID, Integer kaihai);

}
