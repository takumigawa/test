package schedule.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.entity.TRelationProjectEmployee;

@S2Dao(bean=TRelationProjectEmployee.class)
public interface TRelationProjectEmployeeDao {

	public TRelationProjectEmployee[] selectAll();

	@Arguments({ "PJ_CODE", "Employee_ID" })
	public TRelationProjectEmployee selectById(String pjCode, Integer employeeId);

	public int insert(TRelationProjectEmployee tRelationProjectEmployee);

	public int update(TRelationProjectEmployee tRelationProjectEmployee);

	public int delete(TRelationProjectEmployee tRelationProjectEmployee);

	@Arguments({ "Employee_ID" })
	public String[] getuserProject(Integer Employee_ID);
}
