package ssm.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import ssm.dto.SendMailDataDto;
import ssm.entity.TSendtime;

@S2Dao(bean=TSendtime.class)
public interface TSendtimeDao {

	public TSendtime[] selectAll();

	@Arguments("updType")
	public List<SendMailDataDto> selectSendTime(Integer updsendtype);

	@Arguments("id")
	public TSendtime selectById(Integer id);

	@Arguments("sendID")
	public TSendtime selectByMailID(Integer id);

	public int insert(TSendtime tSendtime);

	public int update(TSendtime tSendtime);

	public int delete(TSendtime tSendtime);

}
