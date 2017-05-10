package schedule.logic;

import schedule.dao.MEmployeeDao;
import schedule.dao.TTipsDao;
import schedule.dao.TUploadfileDao;
import schedule.dto.ListDto;
import schedule.dto.TipsListDto;

/**
 * <p>トピックス一覧ロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class TipslistLogic {

	/** トピックステーブルDao */
	public TTipsDao tipsDao;
	/** 社員マスタDao */
	public MEmployeeDao empDao;
	/** アップロードファイルテーブルDao  */
	public TUploadfileDao updDao;
	
	/**
	 * <p>社員一覧取得メソッド</p>
	 * 
	 * @return 社員一覧
	 * @throws Exception 例外
	 */
	public ListDto[] getEmpList() throws Exception {
		
		// 社員一覧の取得
		ListDto[] empArray = empDao.selectGetEmpCombList();
		ListDto listDto = new ListDto();
		listDto.label = "全社員";
		listDto.value = "ALL";
		ListDto[] empWorkArray = new ListDto[empArray.length + 1];
		for(int i = empArray.length; i >= 0; i--) {
			if(i == 0) {
				empWorkArray[i] = listDto;
			} else {
				empWorkArray[i] = empArray[i - 1];
			}
		}
		
		return empWorkArray;
	}
	
	/**
	 * <p>トピックス一覧取得メソッド</p>
	 * 
	 * @param sDate 開始日
	 * @param eDate 終了日
	 * @param empID 社員ID
	 * @return トピックス一覧
	 * @throws Exception 例外
	 */
	public TipsListDto[] getTipsList(String sDate, String eDate, String empID) throws Exception {
		
		// 社員IDがnull以外かつ、社員IDが"ALL"の場合、社員IDにnullを設定
		if(empID != null && empID.equals("ALL")) {
			empID = null;
		}
		
		// トピックス一覧の取得
		TipsListDto[] topicsArray = tipsDao.selectGetTipsListSearch(sDate, eDate, empID);
		
		// トピックス情報にファイル有無フラグを設定
		for(int i = 0; i < topicsArray.length; i++) {
			String[] fileIdArray = new String[5];
			if(topicsArray[i].file1ID != null) {
				fileIdArray[0] = topicsArray[i].file1ID;
			}
			if(topicsArray[i].file2ID != null) {
				fileIdArray[1] = topicsArray[i].file2ID;
			}
			if(topicsArray[i].file3ID != null) {
				fileIdArray[2] = topicsArray[i].file3ID;
			}
			if(topicsArray[i].file4ID != null) {
				fileIdArray[3] = topicsArray[i].file4ID;
			}
			if(topicsArray[i].file5ID != null) {
				fileIdArray[4] = topicsArray[i].file5ID;
			}
			boolean workFlg = false;
			for(int j = 0; j < fileIdArray.length; j++) {
				if(fileIdArray[j] != null) {
					workFlg = true;
				}
			}
			topicsArray[i].fileFlg = workFlg;
		}
		
		return topicsArray;
	}
}
