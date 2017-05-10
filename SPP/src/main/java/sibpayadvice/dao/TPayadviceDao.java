package sibpayadvice.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import sibpayadvice.entity.TPayadvice;

@S2Dao(bean=TPayadvice.class)
public interface TPayadviceDao {

	public TPayadvice[] selectAll();

	@Arguments({ "empid", "empyear", "empmonth" })
	public TPayadvice selectById(Integer empid, Integer empyear,
			Integer empmonth);

	public int insert(TPayadvice tPayadvice);

	public int update(TPayadvice tPayadvice);

	public int delete(TPayadvice tPayadvice);

}
