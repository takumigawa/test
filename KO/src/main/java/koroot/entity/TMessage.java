package koroot.entity;

import org.seasar.dao.annotation.tiger.Bean;
import org.seasar.dao.annotation.tiger.Column;

@Bean(table="t_message")
public class TMessage {

	private Integer messageId;
	private String message;
	private Integer inputEmpid;
//	private Integer seikyuType;
	private Integer seikyuId;
	private Integer kaihai;
	private String adddate;
	private Integer addid;
	private String upddate;
	private Integer updid;

	@Column("message_ID")
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column("input_empID")
	public Integer getInputEmpid() {
		return inputEmpid;
	}

	public void setInputEmpid(Integer inputEmpid) {
		this.inputEmpid = inputEmpid;
	}

//	@Column("seikyu_TYPE")
//	public Integer getSeikyuType() {
//		return seikyuType;
//	}
//
//	public void setSeikyuType(Integer seikyuType) {
//		this.seikyuType = seikyuType;
//	}

	@Column("seikyu_ID")
	public Integer getSeikyuId() {
		return seikyuId;
	}

	public void setSeikyuId(Integer seikyuId) {
		this.seikyuId = seikyuId;
	}

	public Integer getKaihai() {
		return kaihai;
	}

	public void setKaihai(Integer kaihai) {
		this.kaihai = kaihai;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	public Integer getAddid() {
		return addid;
	}

	public void setAddid(Integer addid) {
		this.addid = addid;
	}

	public String getUpddate() {
		return upddate;
	}

	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}

	public Integer getUpdid() {
		return updid;
	}

	public void setUpdid(Integer updid) {
		this.updid = updid;
	}

}
