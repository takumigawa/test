package schedule.logic;

import schedule.dao.MEmployeeDao;
import schedule.dao.TTipsDao;
import schedule.dao.TUploadfileDao;
import schedule.dto.FileListDto;
import schedule.entity.MEmployee;
import schedule.entity.TTips;
import schedule.entity.TUploadfile;

/**
 * <p>トピックス削除確認ロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class TipsconfLogic {

	/** 社員マスタDao */
	public MEmployeeDao empDao;
	/** トピックステーブルDao */
	public TTipsDao tipsDao;
	/** アップロードファイルテーブルDao */
	public TUploadfileDao updDao;
	
	/**
	 * <p>社員名取得メソッド</p>
	 * 
	 * @param empID 社員ID
	 * @return 社員名
	 * @throws Exception 例外
	 */
	public String getEmpName(String empID) throws Exception {
		
		// 社員の名前の取得
		int employeeID = Integer.parseInt(empID);
		MEmployee ety_emp = new MEmployee();
		ety_emp = empDao.selectById(employeeID);
		
		return ety_emp.employeeName;
	}
	
	/**
	 * <p>トピックス削除メソッド</p>
	 * 
	 * @param fileArray ファイルリスト
	 * @param tipsID トピックスID
	 * @throws Exception 例外
	 */
	public void tipsDelete(FileListDto[] fileArray, String tipsID) throws Exception {
		
		// トピックスの削除
		TTips ety_tip = new TTips();
		ety_tip.tipsId = Integer.parseInt(tipsID);
		tipsDao.delete(ety_tip);
		
		// 添付ファイルリストのサイズ分、添付ファイル削除処理を繰り返す
		for(int i = 0; i < fileArray.length; i++) {
			
			// 添付ファイルがある場合、アップロードファイルの削除
			if(fileArray[i].fileID != null) {
				TUploadfile ety_upd = new TUploadfile();
				ety_upd.fileId = Integer.parseInt(fileArray[i].fileID);
				updDao.delete(ety_upd);
			}
		}
	}
	
	/**
	 * <p>トピックスに添付されたファイル情報取得メソッド</p>
	 * 
	 * @param fileArray ファイルリスト
	 * @param ety_tip トピックス情報
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
