package schedule.dto;

import java.io.Serializable;

import org.seasar.teeda.extension.util.UploadedFile;

public class UploadfileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String fileID;
	public String fileName;
	public UploadedFile uploadedFile;
	public Integer fileDeleteFlg;
	public boolean autoDeleteFlg;
}
