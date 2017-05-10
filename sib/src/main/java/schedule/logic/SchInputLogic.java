package schedule.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import schedule.dao.MEmployeeDao;
import schedule.dao.MGroupDao;
import schedule.dao.TScheduleDao;
import schedule.dao.TSchedulegroupDao;
import schedule.dto.ListDto;
import schedule.dto.SessionDto;
import schedule.entity.TSchedule;
import schedule.entity.TSchedulegroup;
import schedule.web.common.CommonUtil;

/**
 * <p>スケジュール登録ロジッククラス</p>
 * 
 * @author J.Hira
 * @version 1.0
 *
 */
public class SchInputLogic {

	/** 社員マスタDao */
	public MEmployeeDao empDao;
	/** グループマスタDao */
	public MGroupDao gruDao;
	/** スケジュールテーブルDao */
	public TScheduleDao schDao;
	/** スケジュールグループテーブルDao */
	public TSchedulegroupDao schgruDao;
	
	/**
	 * <p>時間リスト作成処理</p>
	 * 
	 * @param timeArray 対象配列
	 * @param flg 処理フラグ
	 * @throws Exception 例外
	 */
	public void getTimeList(ListDto[] timeArray, String flg) throws Exception {
		
		// 1件目に未選択項目を追加
		ListDto listDto = new ListDto();
		listDto.label = "";
		listDto.value = "99";
		timeArray[0] = listDto;
		
		// 対象配列のサイズ分、選択項目を追加
		for(int i = 1; i < timeArray.length; i++) {
			listDto = new ListDto();
			
			// 処理フラグが"hour"の場合、1時間単位で選択項目を追加
			if(flg.equals("hour")) {
				String numStr = zeroPading(Integer.toString(i - 1), 2);
				listDto.label = numStr;
				listDto.value = numStr;
				timeArray[i] = listDto;
				
			// 処理フラグが"min"の場合、15分単位で選択項目を追加
			} else if(flg.equals("min")) {
				int min = (i - 1) * 15;
				String minStr = zeroPading(Integer.toString(min), 2);
				listDto.label = minStr;
				listDto.value = minStr;
				timeArray[i] = listDto;
			}
		}
	}
	
	/**
	 * <p>優先順位リスト取得メソッド</p>
	 * 
	 * @return 優先順位リスト
	 * @throws Exception 例外
	 */
	public ListDto[] getPriorityList() throws Exception {
		
		// 優先順位リストの取得
		ListDto[] priorityArray = new ListDto[7];
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
		
		return priorityArray;
	}
	
	/**
	 * <p>グループリスト取得メソッド</p>
	 * 
	 * @return グループリスト
	 * @throws Exception 例外
	 */
	public ListDto[] getGroupList() throws Exception {
		
		// グループリストの取得
		ListDto[] groupArray = gruDao.selectGetGroupCombList();
		ListDto listDto = new ListDto();
		listDto.label = "全社員表示";
		listDto.value = "0";
		ListDto[] groupIdArray = new ListDto[groupArray.length + 1];
		for(int i = groupArray.length; i >= 0; i--) {
			if(i == 0) {
				groupIdArray[i] = listDto;
			} else {
				groupIdArray[i] = groupArray[i - 1];
			}
		}
		
		return groupIdArray;
	}
	
	/**
	 * <p>参加者・不参加者リスト取得メソッド</p>
	 * 
	 * @param joinEmpList 参加者リスト
	 * @param absenceEmpList 不参加者リスト
	 * @param scheduleID スケジュールID
	 * @param flg 処理フラグ
	 * @throws Exception 例外 
	 */
	public void getEmpList(ArrayList<ListDto> joinEmpList, 
			ArrayList<ListDto> absenceEmpList, String scheduleID, String flg) throws Exception {
		
		// スケジュール不参加者の取得
		ListDto[] empList = empDao.selectGetEmpCombList();
		List<ListDto> empWorkList = Arrays.asList(empList);
		absenceEmpList.addAll(empWorkList);
		empListAdd(absenceEmpList);
		
		// 編集の場合、スケジュール参加者を取得し、不参加者を編集する
		if(flg.equals("EDIT")) {
			
			int workID = Integer.parseInt(scheduleID);
			
			// スケジュール参加者の取得
			ArrayList<ListDto> workList = schgruDao.selectGetEmpList(workID);
			joinEmpList.addAll(workList);
			empListAdd(joinEmpList);
			
			// スケジュール不参加者の取得
			workList = schgruDao.selectGetAbsenceEmpList(workID);
			absenceEmpList.clear();
			absenceEmpList.addAll(workList);
			empListAdd(absenceEmpList);
		}
	}
	
	/**
	 * <p>スケジュール登録メソッド</p>
	 * 
	 * @param ety_sch スケジュール情報
	 * @param joinEmpList 参加者リスト
	 * @param sessionDto セッションDto
	 * @param flg 処理フラグ
	 * @throws Exception 例外
	 */
	public void schConfirm(TSchedule ety_sch, 
			ArrayList<ListDto> joinEmpList, SessionDto sessionDto, String flg) throws Exception {
		
		// 全員の項目を削除
		empListDelete(joinEmpList);
		
		// 追加の場合、スケジュールテーブルにスケジュールを挿入
		if(flg.equals("NEW")) {
			ety_sch.employeeId = sessionDto.EmployeeID;
			ety_sch.addid = sessionDto.EmployeeID;
			ety_sch.adddate = CommonUtil.getNowUpdDate();
			schDao.insert(ety_sch);
			
		// 編集の場合、スケジュールテーブルのスケジュールを更新
		} else {
			ety_sch.updid = sessionDto.EmployeeID;
			ety_sch.upddate = CommonUtil.getNowUpdDate();
			schDao.update(ety_sch);
			
			// 編集したスケジュールをスケジュールグループテーブルから削除
			schgruDao.deleteBySchgroup(ety_sch.workId);
		}
		
		// スケジュールグループテーブルのエンティティにスケジュールIDを設定
		TSchedulegroup ety_schgru = new TSchedulegroup();
		ety_schgru.scheduleId = ety_sch.workId;
		
		// 参加者の社員IDをスケジュールテーブルに登録
		for(int i = 0; i < joinEmpList.size(); i++) {
			int empID = Integer.parseInt(joinEmpList.get(i).value);
			ety_schgru.employeeId = empID;
			schgruDao.insert(ety_schgru);
		}
	}
	
	/**
	 * <p>不参加者リストの変更処理</p>
	 * 
	 * @param joinEmpList 参加者リスト
	 * @param absenceEmpList 不参加者リスト
	 * @param gruID グループID
	 * @throws Exception 例外
	 */
	public void combChange(ArrayList<ListDto> joinEmpList, 
			ArrayList<ListDto> absenceEmpList, String gruID) throws Exception {
		
		// 全社員表示が選択された場合、全社員一覧を取得
		if(gruID.equals("0")) {
			ListDto[] empList = empDao.selectGetEmpCombList();
			List<ListDto> empWorkList = Arrays.asList(empList);
			absenceEmpList.clear();
			absenceEmpList.addAll(empWorkList);
		
		// グループが選択された場合、グループに所属している社員一覧を取得
		} else {
			int groupID = Integer.parseInt(gruID);
			ArrayList<ListDto> workList = gruDao.selectGetGroupAttachList(groupID);
			absenceEmpList.clear();
			absenceEmpList.addAll(workList);
		}
		
		// 参加者が選択されている場合、取得した社員一覧から参加者を削除
		if(joinEmpList != null) {
			for(int i = 0; i < absenceEmpList.size(); i++) {
				for(int j = 0; j < joinEmpList.size(); j++) {
					if(absenceEmpList.get(i).value.equals(joinEmpList.get(j).value)) {
						absenceEmpList.remove(i);
					}
				}
			}
		}
		
		// 全員の項目を追加
		empListAdd(absenceEmpList);
	}
	
	/**
	 * <p>参加者追加メソッド</p>
	 * 
	 * @param joinEmpList 参加者リスト
	 * @param absenceEmpList 不参加者リスト
	 * @param absenceArray 追加する社員ID配列
	 * @throws Exception 例外
	 */
	public void join(ArrayList<ListDto> joinEmpList,
			ArrayList<ListDto> absenceEmpList, String[] absenceArray) throws Exception {
		
		// 全員の項目を削除
		empListDelete(joinEmpList);
		empListDelete(absenceEmpList);
		
		// 参加者リスト追加処理
		for(int i = 0; i < absenceEmpList.size(); i++) {
			for(int j = 0; j < absenceArray.length; j++) {
				
				// 全員の項目が選択された場合、不参加者リスト全員を参加者リストに追加
				if(absenceArray[j].equals("ALL")) {
					empListDelete(absenceEmpList);
					joinEmpList.addAll(absenceEmpList);
					absenceEmpList.clear();
					
				// 選択された社員を参加者リストに追加し、不参加者リストから削除
				} else {
					if(absenceEmpList.get(i).value.equals(absenceArray[j])){
						joinEmpList.add(absenceEmpList.get(i));
						absenceEmpList.remove(i);
					}
				}
			}
		}
		
		// 全員の項目を追加
		empListAdd(joinEmpList);
		empListAdd(absenceEmpList);
	}
	
	/**
	 * <p>参加者削除メソッド</p>
	 * 
	 * @param joinEmpList 参加者リスト
	 * @param absenceEmpList 不参加者リスト
	 * @param gruID グループID
	 * @param joinArray 削除する社員ID配列
	 * @throws Exception
	 */
	public void absence(ArrayList<ListDto> joinEmpList,
			ArrayList<ListDto> absenceEmpList, String gruID, String[] joinArray) throws Exception {
		
		// 全員の項目を削除
		empListDelete(joinEmpList);
		empListDelete(absenceEmpList);
		
		// 参加者リスト削除処理
		for(int i = 0; i < joinEmpList.size(); i++) {
			for(int j = 0; j < joinArray.length; j++) {
				
				// 全員の項目が選択された場合、参加者リスト全員を参加者リストから削除
				if(joinArray[j].equals("ALL")) {
					joinEmpList.clear();
				
				// 選択された社員を参加者リストから削除
				} else {
					if(joinEmpList.get(i).value.equals(joinArray[j])){
						joinEmpList.remove(i);
					}
				}
			}
		}
		
		// 不参加者リストを更新
		combChange(joinEmpList, absenceEmpList, gruID);
		
		// 全員の項目を追加
		empListAdd(joinEmpList);
	}
	
	/**
	 * <p>全員の項目の削除処理</p>
	 * 
	 * @param empList 対象リスト
	 * @throws Exception 例外
	 */
	private void empListDelete(ArrayList<ListDto> empList) throws Exception {
		
		// 対象リストのサイズが0より大きい、かつ対象リストの1件目が全員の項目の場合、全員の項目を削除
		if(empList.size() > 0 && empList.get(0).value.equals("ALL")) {
			empList.remove(0);
		}
	}
	
	/**
	 * <p>全員の項目の追加処理</p>
	 * 
	 * @param empList 対象リスト
	 * @throws Exception 例外
	 */
	private void empListAdd(ArrayList<ListDto> empList) throws Exception {
		
		// 対象リストのサイズが0より大きい、かつ対象リストの1件目が全員の項目以外の場合、全員の項目を追加
		if(empList.size() > 0 && !empList.get(0).value.equals("ALL")) {
			ListDto listDto = new ListDto();
			listDto.label = "全員";
			listDto.value = "ALL";
			empList.add(0, listDto);
		}
	}
	
	/**
	 * <p>先頭0埋め処理メソッド</p>
	 * <p>対象文字列を指定桁数まで先頭0埋めする</p>
	 * 
	 * @param str　編集対象文字列
	 * @param num 指定桁数
	 * @return　編集後文字列
	 * @throws Exception 例外
	 */
	private static String zeroPading(String str, int num) throws Exception {
		
		// 文字列の長さを取得
		int strLength = str.length();
		
		// 0用文字列の生成
		StringBuffer sbZero = new StringBuffer();
		
		// 指定の桁数まで0の文字列を作成する
		for (int i = 0; i < num - strLength; i++) {
			sbZero.append("0");
		}

		// 0用文字列と編集対象文字列を結合
		StringBuffer sb = null;
		sb = new StringBuffer(sbZero);
		sb.append(str);

		return sb.toString();
	}
}
