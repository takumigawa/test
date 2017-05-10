package koroot.web.sample;

import koroot.pagebace.PageBaceClass;

public class SamplePage extends PageBaceClass {

	private String contents;
	private String c_pad;
	private String employee_id;
	private String employee_name;
	private String employee_mail;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getC_pad() {
		return c_pad;
	}

	public void setC_pad(String c_pad) {
		this.c_pad = c_pad;
	}

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

	public Class initialize() {
		return null;
	}

	public Class prerender() {
		return null;
	}

}
