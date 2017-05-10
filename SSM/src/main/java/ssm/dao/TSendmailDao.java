package ssm.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import ssm.entity.TSendmail;

@S2Dao(bean=TSendmail.class)
public interface TSendmailDao {

	public TSendmail[] selectAll();

	@Arguments("id")
	public TSendmail selectById(Integer id);

	public int insert(TSendmail tSendmail);

	public int update(TSendmail tSendmail);

	public int delete(TSendmail tSendmail);

	public Integer getNextMailID();
	
}
