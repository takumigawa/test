package schedule.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.entity.MCompany;

@S2Dao(bean=MCompany.class)
public interface MCompanyDao {

	public MCompany[] selectAll();

	@Arguments("company_id")
	public MCompany selectById(Integer companyId);

	public int insert(MCompany company);

	public int update(MCompany company);

	public int delete(MCompany company);

}
