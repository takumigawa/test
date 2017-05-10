package ko.dto;
import java.io.Serializable;

public class MessageDto implements Serializable{
	private static final long serialVersionUID = 1L;

	public String transday_id;
	public Integer  sender_id;
	public String message_id;

	public String getmessage_id() {
		return message_id;
	}

	public void setmessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getadddate() {
		return transday_id;
	}

	public void setadddate(String transday_id) {
		this.transday_id = transday_id;
	}

	public Integer getsender_id() {
		return sender_id;
	}

	public void setsender_id(Integer sender_id) {
		this.sender_id = sender_id;
	}
}

