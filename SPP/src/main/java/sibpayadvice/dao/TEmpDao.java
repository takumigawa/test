package sibpayadvice.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import sibpayadvice.entity.TEmp;

@S2Dao(bean=TEmp.class)
public interface TEmpDao {

	public TEmp[] selectAll();

	@Arguments({ "empid", "empyear", "empmonth" })
	public TEmp selectById(Integer empid, Integer empyear, Integer empmonth);

	public int insert(TEmp tEmp);

	public int update(TEmp tEmp);

	public int delete(TEmp tEmp);

}
