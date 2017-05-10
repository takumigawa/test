package ssm.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import ssm.entity.TSendlog;

@S2Dao(bean=TSendlog.class)
public interface TSendlogDao {

	public TSendlog[] selectAll();

	@Arguments("id")
	public TSendlog selectById(Integer id);

	public int insert(TSendlog tSendlog);

	public int update(TSendlog tSendlog);

	public int delete(TSendlog tSendlog);

}
