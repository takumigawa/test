package schedule.logic;

import java.util.List;

import org.seasar.teeda.extension.util.UploadedFile;

import schedule.dao.TTipsDao;
import schedule.dao.TUploadfileDao;
import schedule.dto.ListDto;
import schedule.dto.SessionDto;
import schedule.dto.UploadfileDto;
import schedule.entity.TTips;
import schedule.entity.TUploadfile;
import schedule.web.common.CommonUtil;

/**
 * <p>トピックス登録ロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class TipsinputLogic {

	/** トピックステーブルDao */
	public TTipsDao tipsDao;
	/** アップロードファイルテーブルDao */
	public TUploadfileDao updDao;
	
	/**
	 * <p>優先順位リスト取得メソッド</p>
	 * 
	 * @param priorityArray 優先順位リスト
	 * @throws Exception 例外
	 */
	public void getPriorityList(ListDto[] priorityArray) throws Exception {
		
		// 優先順位リストの取得
		ListDto listDto = new ListDto();
		listDto.label = "";
		listDto.value = "No";
		priorityArray[0] = listDto;
		for(int i = 0; i < 6; i++) {
			listDto = new ListDto();
			listDto.label = Integer.toString(i);
			listDto.value = Integer.toString(i);
			priorityArray[i + 1] = listDto;
		}
	}
	
	/**
	 * <p>ファイルリスト取得メソッド</p>
	 * 
	 * @param fileList ファイルリスト
	 * @throws Exception 例外
	 */
	public void getFileList(List<UploadfileDto> fileList) throws Exception {
		
		for(int i = 0; i < 5; i++) {
			UploadfileDto upfileDto = new UploadfileDto();
			upfileDto.autoDeleteFlg = true;
			fileList.add(upfileDto);
		}
	}
	
	/**
	 * <p>トピックス情報取得メソッド</p>
	 * 
	 * @param fileList ファイルリスト
	 * @param tipsIDStr トピックスID
	 * @return トピックス情報
	 * @throws Exception 例外
	 */
	public TTips tipsInfo(List<UploadfileDto> fileList, String tipsIDStr) throws Exception {
		
		TTips ety_tip = new TTips();
		
		// 編集の場合、トピックス情報を取得し、表示項目に設定
		if(!tipsIDStr.equals("-1")) {
			
			// トピックス情報の取得
			int tipsID = Integer.parseInt(tipsIDStr);
			ety_tip = tipsDao.selectById(tipsID);
			
			// 添付ファイルがある場合、添付ファイルリストにファイルIDとファイル名を設定
			int[] fileIdArray = attachedFileIdList(ety_tip);
			
			for(int i = 0; i < fileIdArray.length; i++) {
				if(fileIdArray[i] != 0) {
					TUploadfile ety_upd = new TUploadfile();
					ety_upd = updDao.selectById(fileIdArray[i]);
					fileList.get(i).fileID = Integer.toString(ety_upd.fileId);
					fileList.get(i).fileDeleteFlg = ety_upd.deleteFlg;
					fileList.get(i).fileName = ety_upd.fileName;
					
					if(ety_upd.deleteFlg == 1) {
						fileList.get(i).autoDeleteFlg = true;
					} else {
						fileList.get(i).autoDeleteFlg = false;
					}
				}
			}
		}
		
		return ety_tip;
	}
	
	/**
	 * <p>添付ファイル擬似削除処理</p>
	 * 
	 * @param fileList ファイルリスト
	 * @param index インデックス
	 * @throws Exception 例外
	 */
	public void fileEdit(List<UploadfileDto> fileList, int index) throws Exception {
		
		// 選択されたファイル情報の初期化
		fileList.get(index).fileID = null;
		fileList.get(index).fileName = null;
		fileList.get(index).autoDeleteFlg = true;
		fileList.get(index).uploadedFile = null;
		fileList.get(index).fileDeleteFlg = 1;
		for(int i = 0; i < fileList.size(); i++) {
			if(fileList.get(i).uploadedFile != null) {
				UploadedFile uploadedFile = fileList.get(i).uploadedFile;
				fileList.get(i).fileID = "0";
				fileList.get(i).fileName = uploadedFile.getName();
				uploadedFile.delete();
			}
		}
	}
	
	/**
	 * <p>トピックス登録メソッド</p>
	 * 
	 * @param fileList ファイルリスト
	 * @param ety_tip トピックス情報
	 * @param sessionDto セッションDto
	 * @return エラーメッセージ
	 * @throws Exception 例外
	 */
	public String tipsConfirm(List<UploadfileDto> fileList,
			TTips ety_tip, SessionDto sessionDto) throws Exception {
		
		// ファイルアップロード処理
		TUploadfile ety_upd;
		int[] fileIdArray = new int[5];
		for(int i = 0; i < 5; i++) {
			
			// エンティティの初期化
			ety_upd = new TUploadfile();
			
			// ファイルが選択されている場合、アップロードファイルテーブルにファイル情報を挿入
			if (fileList.get(i).uploadedFile != null) {
				UploadedFile uploadedFile = fileList.get(i).uploadedFile;
				
				// ファイルの存在チェック
				if(uploadedFile.getSize() == 0) {
					return "ファイルが存在しないか、ファイルサイズが0バイトの為、\nアップロードできません。";
				}
				
				// エンティティに値を設定
				ety_upd.fileName			= uploadedFile.getName();	// ファイル名
				
				// ファイル名の桁数チェック
				if(ety_upd.fileName.length() > 256) {
					return "ファイル名が256桁を超えています。\nファイル名を変更してください。";
				}
				
				// エンティティに値を設定
				ety_upd.fileValue			= uploadedFile.get();			// ファイルデータ
				ety_upd.adddate				= CommonUtil.getNowUpdDate();	// 作成日
				ety_upd.addid				= sessionDto.EmployeeID;		// 作成者ID
				
				// 自動削除可のチェックボックスがチェックされていない場合、自動削除フラグに0を設定
				if(!fileList.get(i).autoDeleteFlg) {
					ety_upd.deleteFlg	= 0;
				}
				
				// アップロードファイルテーブルにファイル情報を挿入
				updDao.insert(ety_upd);
				
				// 挿入されたファイル情報のファイルIDの取得
				fileIdArray[i] = ety_upd.fileId;
				
				// アップロードファイルの削除
				uploadedFile.delete();
				
			// アップロードファイルテーブルのファイル情報更新
			} else if(fileList.get(i).fileID != null) {
				fileIdArray[i] = Integer.parseInt(fileList.get(i).fileID);
				if(fileList.get(i).fileDeleteFlg != 2) {
					ety_upd = updDao.selectById(fileIdArray[i]);
					if(fileList.get(i).autoDeleteFlg) {
						ety_upd.deleteFlg = 1;
					} else {
						ety_upd.deleteFlg = 0;
					}
					updDao.update(ety_upd);
				}
			}
		}
		
		// アップロードファイルテーブル削除処理
		fileDelete(ety_tip, fileIdArray);
		
		// ファイルIDリストの1件目が0以外の場合、ファイルID1にファイルIDを設定
		if(fileIdArray[0] != 0) {
			ety_tip.file1Id		= fileIdArray[0];
		} else {
			ety_tip.file1Id		= null;
		}
		
		// ファイルIDリストの2件目が0以外の場合、ファイルID2にファイルIDを設定
		if(fileIdArray[1] != 0) {
			ety_tip.file2Id		= fileIdArray[1];
		} else {
			ety_tip.file2Id		= null;
		}
		
		// ファイルIDリストの3件目が0以外の場合、ファイルID3にファイルIDを設定
		if(fileIdArray[2] != 0) {
			ety_tip.file3Id		= fileIdArray[2];
		} else {
			ety_tip.file3Id		= null;
		}
		
		// ファイルIDリストの4件目が0以外の場合、ファイルID4にファイルIDを設定
		if(fileIdArray[3] != 0) {
			ety_tip.file4Id		= fileIdArray[3];
		} else {
			ety_tip.file4Id		= null;
		}
		
		// ファイルIDリストの5件目が0以外の場合、ファイルID5にファイルIDを設定
		if(fileIdArray[4] != 0) {
			ety_tip.file5Id		= fileIdArray[4];
		} else {
			ety_tip.file5Id		= null;
		}
		
		// 新規登録の場合、トピックス情報を挿入
		if(ety_tip.tipsId == -1) {
			ety_tip.tipsId = null;
			tipsDao.insert(ety_tip);
			
		// 編集の場合、トピックス情報を更新
		} else {
			tipsDao.update(ety_tip);
		}
		
		return "";
	}
	
	/**
	 * <p>トピックス情報のファイルIDをint配列に変換するメソッド</p>
	 * 
	 * @param ety_tip トピックス情報
	 * @return　ファイルIDリスト（int配列）
	 * @throws Exception 例外
	 */
	private int[] attachedFileIdList(TTips ety_tip) throws Exception {
		
		// ファイルIDリストの生成
		int[] fileIdArray = new int[5];
		
		// ファイルID1がnull以外の場合、ファイルIDリストの1件目に設定
		if(ety_tip.file1Id != null) {
			fileIdArray[0] = ety_tip.file1Id;
		}
		
		// ファイルID2がnull以外の場合、ファイルIDリストの2件目に設定
		if(ety_tip.file2Id != null) {
			fileIdArray[1] = ety_tip.file2Id;
		}
		
		// ファイルID3がnull以外の場合、ファイルIDリストの3件目に設定
		if(ety_tip.file3Id != null) {
			fileIdArray[2] = ety_tip.file3Id;
		}
		
		// ファイルID4がnull以外の場合、ファイルIDリストの4件目に設定
		if(ety_tip.file4Id != null) {
			fileIdArray[3] = ety_tip.file4Id;
		}
		
		// ファイルID5がnull以外の場合、ファイルIDリストの5件目に設定
		if(ety_tip.file5Id != null) {
			fileIdArray[4] = ety_tip.file5Id;
		}
		return fileIdArray;
	}
	
	/**
	 * <p>アップロードファイルテーブル削除処理</p>
	 * 
	 * @param ety_tip トピックス情報
	 * @param fileArray ファイルIDリスト
	 * @throws Exception 例外
	 */
	private void fileDelete(TTips ety_tip, int[] fileArray) throws Exception {
		
		// トピックス情報のファイルIDをint配列に変換
		int[] fileIdArray = attachedFileIdList(ety_tip);
		
		// ファイルIDリストにないトピックス情報のファイルIDを元にアップロードファイルテーブルの削除を行う
		for(int i = 0; i < fileIdArray.length; i++) {
			if(fileIdArray[i] != 0 && fileIdArray[i] != fileArray[i]) {
				TUploadfile ety_upd = new TUploadfile();
				ety_upd.fileId = fileIdArray[i];
				updDao.delete(ety_upd);
			}
		}
	}
}
