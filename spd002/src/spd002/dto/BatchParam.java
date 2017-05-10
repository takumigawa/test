package spd002.dto;

public class BatchParam {

	private String iYear;
	private String iMonth;
	
	/** パラメータ　年　セッター・ゲッター　*/
	public void setYear(String iYear) {
		this.iYear = iYear;
	}
	public String getYear() {
		return this.iYear;
	}
	
	/** パラメーター　月　セッター・ゲッター　*/
	public void setMonth(String iMonth) {
		this.iMonth = iMonth;
	}
	public String getMonth(){
		return this.iMonth;
	}

}
