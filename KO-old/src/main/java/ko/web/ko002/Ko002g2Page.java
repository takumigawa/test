package ko.web.ko002;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import ko.dao.TKogutiDao;
import ko.entity.TKoguti;

public class Ko002g2Page {

	public TKoguti koguti;
	public TKogutiDao dao;

	private String employee_id;
	private String employee_name;
	private String employee_mail;
	private Integer seikyuid;
	private String pjcode;
	private Integer state;
	private Integer kanjo;
	private Integer kingaku;
	private String seikyudate;
	private Integer seikyuemp;
	private String torikesidate;
	private Integer torikesienp;
	private String kyokadate;
	private Integer kyokaemp;
	private String fukyokadate;
	private Integer fukyokaemp;
	private String seisandate;
	private Integer seisanemp;
	private String biko;
	private Integer statusVal;

	private String message_id;
	private String messageitems;
	private String Transday_id;
	private String Sender_id;

	@SubapplicationScope
	public Integer seikyu;

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_mail() {
		return employee_mail;
	}

	public void setEmployee_mail(String employee_mail) {
		this.employee_mail = employee_mail;
	}

	public int getSeikyuid() {
		return seikyuid;
	}

	public void setSeikyuid(int seikyuid) {
		this.seikyuid = seikyuid;
	}

	public String getPjcode() {
		return pjcode;
	}

	public void setPjcode(String pjcode) {
		this.pjcode = pjcode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getKanjo() {
		return kanjo;
	}

	public void setKanjo(Integer kanjo) {
		this.kanjo = kanjo;
	}

	public Integer getKingaku() {
		return kingaku;
	}

	public void setKingaku(Integer kingaku) {
		this.kingaku = kingaku;
	}

	public String getSeikyudate() {
		return seikyudate;
	}

	public void setSeikyudate(String seikyudate) {
		this.seikyudate = seikyudate;
	}

	public Integer getSeikyuemp() {
		return seikyuemp;
	}

	public void setSeikyuemp(Integer seikyuemp) {
		this.seikyuemp = seikyuemp;
	}

	public String getTorikesidate() {
		return torikesidate;
	}

	public void setTorikesidate(String torikesidate) {
		this.torikesidate = torikesidate;
	}

	public Integer getTorikesienp() {
		return torikesienp;
	}

	public void setTorikesienp(Integer torikesienp) {
		this.torikesienp = torikesienp;
	}

	public String getKyokadate() {
		return kyokadate;
	}

	public void setKyokadate(String kyokadate) {
		this.kyokadate = kyokadate;
	}

	public Integer getKyokaemp() {
		return kyokaemp;
	}

	public void setKyokaemp(Integer kyokaemp) {
		this.kyokaemp = kyokaemp;
	}

	public String getFukyokadate() {
		return fukyokadate;
	}

	public void setFukyokadate(String fukyokadate) {
		this.fukyokadate = fukyokadate;
	}

	public Integer getFukyokaemp() {
		return fukyokaemp;
	}

	public void setFukyokaemp(Integer fukyokaemp) {
		this.fukyokaemp = fukyokaemp;
	}

	public String getSeisandate() {
		return seisandate;
	}

	public void setSeisandate(String seisandate) {
		this.seisandate = seisandate;
	}

	public Integer getSeisanemp() {
		return seisanemp;
	}

	public void setSeisanemp(Integer seisanemp) {
		this.seisanemp = seisanemp;
	}

	public String getEmployeefaxStr() {
		return biko;
	}

	public void setEmployeefaxStr(String employeefaxStr) {
		biko = employeefaxStr;
	}

	public Integer getStatusVal() {
		return statusVal;
	}

	public void setStatusVal(Integer statusVal) {
		this.statusVal = statusVal;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getMessageitems() {
		return messageitems;
	}

	public void setMessageitems(String messageitems) {
		this.messageitems = messageitems;
	}

	public String getTransday_id() {
		return Transday_id;
	}

	public void setTransday_id(String transday_id) {
		Transday_id = transday_id;
	}

	public String getSender_id() {
		return Sender_id;
	}

	public void setSender_id(String sender_id) {
		Sender_id = sender_id;
	}

	public Class<?> doUpdate() {

		return Ko002g2Page.class;
	}

	public Class<?> doinfo() {
		return null;
	}

	public Class<?> doFinishReturn() {

		return Ko002g1Page.class;
	}

	public Class<?> doinsnew() {
		return null;
	}

	public Class<?> initialize() {
		return null;
	}

	public Class<?> prerender() {
		koguti = dao.selectById(seikyu);

		seikyuid = koguti.getSeikyuId();
		pjcode = koguti.getPjCode();
		state = koguti.getState();
		kanjo = koguti.getKanjoId();
		kingaku = koguti.getKingaku();
		seikyudate = koguti.getSeikyuDate();
		seikyuemp = koguti.getSeikyuEmpid();
		torikesidate = koguti.getTorikesiDate();
		torikesienp = koguti.getTorikesiEmpid();
		kyokadate = koguti.getKyokaDate();
		kyokaemp = koguti.getKyokaEmpid();
		fukyokadate = koguti.getFukyokaDate();
		fukyokaemp = koguti.getFukyokaEmpid();
		seisandate = koguti.getSeisanDate();
		seisanemp = koguti.getSeisanEmpid();
		biko = koguti.getBiko();
		return null;
	}
}
