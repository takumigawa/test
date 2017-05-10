package schedule.entity;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_uploadfile")
public class TUploadfile {

	public static final String fileId_ID = "identity";
	public Integer fileId;
	public String fileName;
	public byte[] fileValue;
	public Integer deleteFlg;
	public String adddate;
	public Integer addid;
}
