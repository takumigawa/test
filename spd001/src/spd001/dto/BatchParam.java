package spd001.dto;

public class BatchParam {

	private String iYear;
	private String iMonth;
	private String reportPath;
	private String outPath;
	
	/** �p�����[�^�@�N�@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setYear(String iYear) {
		this.iYear = iYear;
	}
	public String getYear() {
		return this.iYear;
	}
	
	/** �p�����[�^�[�@���@�Z�b�^�[�E�Q�b�^�[�@*/
	public void setMonth(String iMonth) {
		this.iMonth = iMonth;
	}
	public String getMonth(){
		return this.iMonth;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	public String getOutPath() {
		return outPath;
	}
	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

}
