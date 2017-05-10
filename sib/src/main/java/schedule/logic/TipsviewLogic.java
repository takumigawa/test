package schedule.logic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.seasar.teeda.core.util.CancelUtil;

import schedule.dao.MEmployeeDao;
import schedule.dao.TTipsDao;
import schedule.dao.TUploadfileDao;
import schedule.dto.FileListDto;
import schedule.entity.MEmployee;
import schedule.entity.TTips;
import schedule.entity.TUploadfile;

/**
 * <p>トピックス詳細ロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class TipsviewLogic {

	/** トピックステーブルDao */
	public TTipsDao tipsDao;
	/** 社員マスタDao */
	public MEmployeeDao empDao;
	/** アップロードファイルテーブルDao */
	public TUploadfileDao updDao;
	
	/**
	 * <p>社員名取得メソッド</p>
	 * 
	 * @param empID 社員ID
	 * @return 社員名
	 * @throws Exception
	 */
	public String getEmpName(String empID) throws Exception {
		
		// 社員の名前の取得
		int employeeID = Integer.parseInt(empID);
		MEmployee ety_emp = new MEmployee();
		ety_emp = empDao.selectById(employeeID);
		
		return ety_emp.employeeName;
	}
	
	/**
	 * <p>ファイルダウンロード処理</p>
	 * 
	 * @param response HttpServletResponse
	 * @param facesContext FacesContext
	 * @param fileIDList ファイルリスト
	 * @param index インデックス
	 * @throws Exception 例外
	 */
	public void fileDownload(HttpServletResponse response, 
			FacesContext facesContext, FileListDto[] fileArray, int index) throws Exception {
		
		// 選択されたファイル情報の取得
		int fileId = Integer.parseInt(fileArray[index].fileID);
		TUploadfile ety_upd = updDao.selectById(fileId);
		
		// ファイル名をtomcatの文字コード「ISO8859_1」に変換
		String value = ety_upd.fileName; 
		String fileName = null;
		
		try {
			fileName = new String(value.getBytes("Windows-31J"), "8859_1");
		
		} catch (UnsupportedEncodingException e) {
			
			throw e;
		}
		
		// ContentTypeを変更
		response.setContentType("application/octet-stream");
		
		// ヘッダーにファイル名をセット
		response.setHeader("content-disposition", "attachment;filename=\"" + fileName + "\"");
		
		// 出力ストリームにファイルを書き込む
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(ety_upd.fileValue);
			
		} catch (IOException e) {
			
			// 例外がキャンセル押下時以外での発生の場合、例外をスローする
			Throwable cause = e.getCause();
			if (CancelUtil.isCancelled(cause)) {
				
				throw e;
			}
		
		// 出力ストリームを閉じる
		} finally {
			os.close();
		}
		
		// レンダリングさせない
		facesContext.responseComplete();
	}
	
	/**
	 * <p>トピックスに添付されたファイル情報取得メソッド</p>
	 * 
	 * @param fileList ファイルリスト
	 * @param ety_tip　トピックス情報
	 * @throws Exception 例外
	 */
	public void fileInfo(FileListDto[] fileArray, TTips ety_tip) throws Exception {
		
		// 添付ファイルがある場合、配列にファイルIDを格納する
		int[] fileIdArray = new int[5];
		if(ety_tip.file1Id != null) {
			fileIdArray[0] = ety_tip.file1Id;
		}
		if(ety_tip.file2Id != null) {
			fileIdArray[1] = ety_tip.file2Id;
		}
		if(ety_tip.file3Id != null) {
			fileIdArray[2] = ety_tip.file3Id;
		}
		if(ety_tip.file4Id != null) {
			fileIdArray[3] = ety_tip.file4Id;
		}
		if(ety_tip.file5Id != null) {
			fileIdArray[4] = ety_tip.file5Id;
		}
		
		// 添付されているファイル情報の取得
		FileListDto fileListDto;
		for(int i = 0; i < fileIdArray.length; i++) {
			fileListDto = new FileListDto();
			if(fileIdArray[i] != 0) {
				TUploadfile ety_upd = updDao.selectById(fileIdArray[i]);
				fileListDto.fileID = Integer.toString(ety_upd.fileId);
				fileListDto.fileName = ety_upd.fileName;
				fileListDto.fileDeleteFlg = ety_upd.deleteFlg;
			}
			fileArray[i] = fileListDto;
		}
	}
}
