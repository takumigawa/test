package sibpayadvice.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_sendym")
public class TSendym {

	private Integer sendyear;
	private Integer sendmonth;
	private Integer sendflg;

	public Integer getSendyear() {
		return sendyear;
	}

	public void setSendyear(Integer sendyear) {
		this.sendyear = sendyear;
	}

	public Integer getSendmonth() {
		return sendmonth;
	}

	public void setSendmonth(Integer sendmonth) {
		this.sendmonth = sendmonth;
	}

	public Integer getSendflg() {
		return sendflg;
	}

	public void setSendflg(Integer sendflg) {
		this.sendflg = sendflg;
	}

}
