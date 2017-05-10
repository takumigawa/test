package ko.dao;

import ko.dto.KanjoListDto;
import ko.dto.MessageDto;
import ko.entity.TMessage;

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

@Arguments({ "kaihai","seikyu_id" })
public MessageDto[] GetMessage(Integer kaihai,Integer seikyu_id);

}