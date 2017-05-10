package koroot.dao;

import java.util.List;

import koroot.dto.yaritoriMessageDto;
import koroot.entity.TMessage;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

@S2Dao(bean=TMessage.class)
public interface TMessageDao {

	public TMessage[] selectAll();

	@Arguments("message_ID")
	public TMessage selectById(Integer messageId);

	public int insert(TMessage tMessage);

	public int update(TMessage tMessage);

	public int delete(TMessage tMessage);

	@Arguments("SeikyuID")
	public List<yaritoriMessageDto> selectBySeikyuID(Integer SeikyuID);
	
}
