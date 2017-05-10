package ssm.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import ssm.entity.TSendmailaddress;

@S2Dao(bean=TSendmailaddress.class)
public interface TSendmailaddressDao {

	public TSendmailaddress[] selectAll();

	@Arguments("id")
	public TSendmailaddress selectById(Integer id);

	@Arguments("id")
	public List<TSendmailaddress> selectBySendMailId(Integer id);

	@Arguments("id")
	public int deleteBySendMailId(Integer id);

	public int insert(TSendmailaddress tSendmailaddress);

	public int update(TSendmailaddress tSendmailaddress);

	public int delete(TSendmailaddress tSendmailaddress);

}
