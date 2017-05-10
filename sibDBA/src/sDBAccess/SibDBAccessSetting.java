package sDBAccess;

public class SibDBAccessSetting {

	private String tURL = "";
	private String Schema = "";
	private String SQLID = "";
	private String SQLPass = "";
	
	public void setURL(String URL){
		this.tURL = URL;
	}
	public String getURL(){
		return this.tURL;
	}
	
	public void setSchema(String Schema){
		this.Schema = Schema;
	}
	public String getSchema(){
		return this.Schema;
	}
	
	public void setID(String ID){
		this.SQLID = ID;
	}
	public String getID(){
		return this.SQLID;
	}
	
	public void setPass(String Pass){
		this.SQLPass = Pass;
	}
	public String getPass(){
		return this.SQLPass;
	}
}
