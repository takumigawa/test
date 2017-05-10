/**
 *
 */
package schedule.web.updltest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.util.UploadedFile;

import schedule.dao.TUploadfileDao;
import schedule.dto.SessionDto;
import schedule.dto.UploadfileDto;
import schedule.entity.TUploadfile;
import schedule.web.common.CommonUtil;


/**
 * @author shot
 */
public class FilePage implements Serializable {

	@Binding	
	public SessionDto session;
	
	public HttpServletResponse response;
	
	public FacesContext facesContext;
	
	private static final long serialVersionUID = 1L;

	public UploadedFile uploadedFile;
	
	public TUploadfileDao updDao;
	
	public List<UploadfileDto> fileListItems;
	public int fileListIndex;
	
	/*public UploadedFile getUploadedFile() {
		
		if(uploadedFile.isInMemory()) {
			return uploadedFile;
		}
		
		return (UploadedFile) uploadedFile.getStoreLocation();
	}

	
	public void setUploadedFile(UploadedFile uploadedFile) {
		
		this.uploadedFile = uploadedFile;
		System.out.println("#####uploadFileSize[" + uploadedFile.getSize()
				+ "]");
	}*/

	public Class<?> initialize() {
		
		fileListItems = new ArrayList<UploadfileDto>();
		for(int i = 0; i < 5; i++) {
			fileListItems.add(new UploadfileDto());
		}
		return null;
	}
	
	public Class<?> doUpload() {
		
		/* アップデート処理 */
		
		TUploadfile ety_upd = new TUploadfile();
		int[] fileIdList = new int[5];
		
		for(int i = 0; i < 5; i++) {
			if (fileListItems.get(i).uploadedFile != null) {
				uploadedFile = fileListItems.get(i).uploadedFile;
				System.out.println("#####uploadFileSize[" + uploadedFile.getSize() + "]");
				
				if(uploadedFile.isInMemory() == true){
					System.out.println("メモリー");
				}else{
					System.out.println("非メモリー");
				}
				
				System.out.println("#####uploadStoreLocation[" + uploadedFile.getStoreLocation() + "]");
				
				ety_upd.fileName	= uploadedFile.getName();
				ety_upd.fileValue	= uploadedFile.get();
				
				if(ety_upd.fileValue.length == 0) {
					// 中身がないとはじく？
					System.out.println("ファイルの中身がない・・・");
				}
				
				//作成日付、ID
				ety_upd.adddate		= CommonUtil.getNowUpdDate();
				ety_upd.addid		= 2;//session.EmployeeID;
				
				updDao.insert(ety_upd);
				fileIdList[i] = ety_upd.fileId;
			}
			uploadedFile.delete();
		}
		
		/*　ダウンロード処理　 */
		for(int i = 0; i < fileIdList.length; i++) {
			if(fileIdList[i] != 0) {
				TUploadfile updfile = updDao.selectById(fileIdList[i]);
				String value = updfile.fileName;
				String fileName = null;
				
				// ファイル名をtomcatの文字コード「ISO8859_1」に変換
				try {
					fileName = new String(value.getBytes("Windows-31J"), "8859_1");
				} catch (UnsupportedEncodingException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}    
				
				byte[] downloadData = updfile.fileValue; 
				
				response.setContentType("application/octet-stream");
				response.setHeader("content-disposition", "attachment;filename=\"" + fileName + "\"");
				
				// osにファイルを書き込む．
				try {
					OutputStream os = response.getOutputStream();
					
					os.write(downloadData);
					
					os.close();
					
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				
				//とかで，ContentTypeを変更．ヘッダーにファイル名をセット．
				//最後に
				
				if(facesContext == null){
					System.out.println("くそばっかかぁぁぁぁぁぁぁだよおおおおおおんｎ");
				}
				facesContext.responseComplete();
			}
		}
		
		return null;
	}
}
