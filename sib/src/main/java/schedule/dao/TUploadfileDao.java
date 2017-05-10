package schedule.dao;
import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.entity.TUploadfile;


@S2Dao(bean=TUploadfile.class)
public interface TUploadfileDao {

	public TUploadfile[] selectAll();

	@Arguments("File_ID")
	public TUploadfile selectById(Integer fileId);

	public int insert(TUploadfile uploadfile);
	
	public int update(TUploadfile uploadfile);
	
	public int delete(TUploadfile uploadfile);

}
