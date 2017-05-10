package ssm.dto;

import java.io.Serializable;

public class SendMailDataDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Integer sendID;
	public String sendType;
	public String sendTime;
	public String sendTitel;
	public String sendMailaddress;
	public String sendFlg;

}
