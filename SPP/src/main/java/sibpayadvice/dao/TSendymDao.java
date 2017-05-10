package sibpayadvice.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import sibpayadvice.entity.TSendym;

@S2Dao(bean=TSendym.class)
public interface TSendymDao {

	public TSendym[] selectAll();

	@Arguments({ "sendyear", "sendmonth" })
	public TSendym selectById(Integer sendyear, Integer sendmonth);

	public int insert(TSendym tSendym);

	public int update(TSendym tSendym);

	public int delete(TSendym tSendym);

}
