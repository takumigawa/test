package schedule.web.mast.grouplist;

import java.util.ArrayList;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import schedule.dao.MEmployeeDao;
import schedule.dao.MGroupDao;
import schedule.dao.TGroupattachDao;
import schedule.dto.ListDto;
import schedule.dto.SessionDto;
import schedule.entity.MGroup;
import schedule.entity.TGroupattach;
import schedule.web.common.CommonConstants;
import schedule.web.error.AbendPage;
import schedule.web.error.NonauthorityPage;
import schedule.web.error.NonloginPage;

/**
 * <p>グループマスタ一覧Pageクラス</p>
 * @author T.fujimoto
 *
 */
public class GroupattachPage {

	/** ログイン社員情報 */
	public String employee_id;
	public String employee_name;
	public String employee_mail;
	
	/** 入力項目変数 */
	public String groupName;
	public String groupauthority;
	public String groupSEQ;
	public String groupEnabled;
	
	/** グループ値 */
	@PageScope
	@Required
	public Integer group;
	/** グループ一覧 */
	@PageScope
	public ListDto[] groupItems;
	
	
	/** 所属社員一覧値 */
	@PageScope
	public String[] atEmp;
	/** 所属社員一覧 */
	@PageScope
	public ArrayList<ListDto> atEmpItems;
	
	/** 非所属社員一覧値 */
	@PageScope
	public String[] reEmp;
	/** 非所属社員一覧 */
	@PageScope
	public ArrayList<ListDto> reEmpItems;
	
	/** セッションDTO */
	@Binding
	public SessionDto sessionDto;
	
	/** グループマスタDao */
	public MGroupDao gruDao;

	/** グループ所属テーブルDao */
	public TGroupattachDao gruatDao;
	
	/** 社員マスタDao */
	public MEmployeeDao empDao;
	
	/** 選択案件ＩＤ */
	@SubapplicationScope
	public Integer selectgruID;
	
	/** エラーメッセージ表示用 */
	public String retErrMess;
	
	/**
	 * <p>画面初期化メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> initialize() {
		
		//エラーメッセージ初期化
		retErrMess = "";
		
		try{
			
			// 未ログインユーザーをはじく処理
			if(sessionDto.EmployeeID == null){
				return NonloginPage.class;
			}
			
			//マスターメンテ管理未許可ユーザーをはじく処理
			if(sessionDto.MastermainteFlg == false){
				return NonauthorityPage.class;
			}
			
			//検索条件-グループ指定コンボ
			groupItems = gruDao.selectGetGroupCombList();
			
			atEmpItems = gruDao.selectGetGroupAttachList(Integer.parseInt(groupItems[0].value));
			
			reEmpItems = gruDao.selectGetGroupnonAttachList(Integer.parseInt(groupItems[0].value));
			
		}catch(Exception e){
			return AbendPage.class;
		}
		
		return null;
	}

	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> prerender() {
		
		//ログイン情報
		employee_id 	= sessionDto.EmployeeID.toString();
		employee_name	= sessionDto.EmployeeName;
		employee_mail	= sessionDto.EmployeeEmail;
		
		return null;
	}
	
	/**
	 * <p>事前画面描写メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doCombChange(){

		//エラーメッセージ初期化
		retErrMess = "";
		
		atEmpItems = gruDao.selectGetGroupAttachList(group);
		
		reEmpItems = gruDao.selectGetGroupnonAttachList(group);
		
		return null;
	}
	
	/**
	 * <p>社員追加メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doAttach(){

		//エラーメッセージ初期化
		retErrMess = "";
		
		try{
			
			ArrayList<ListDto> tmp;
			
			//追加
			
			for(int i = 0;i < reEmpItems.size() ;i++){
				
				boolean flg = false;
				
				for(int y = 0;y < reEmp.length;y++){
					
					if(reEmpItems.get(i).value.equals(reEmp[y])){
						flg = true;
					}
					
				}
				
				if(flg){
					atEmpItems.add(reEmpItems.get(i));
				}

			}
			
			//削除
			tmp= new ArrayList<ListDto>();
			
			for(int i = 0;i < reEmpItems.size() ;i++){
				
				boolean flg = true;
				
				for(int y = 0;y < reEmp.length;y++){
					
					if(reEmpItems.get(i).value.equals(reEmp[y])){
						flg = false;
					}
					
				}
				
				if(flg){
					tmp.add(reEmpItems.get(i));
				}

			}
			
			reEmpItems = tmp;
			
		}catch(Exception e){
			return AbendPage.class;
		}

		return null;
		
	}
	
	/**
	 * <p>社員削除メソッド</p>
	 * @return 自画面遷移
	 * @throws Exception 
	 *
	 */
	public Class<?> doRetach(){
		
		try{
			
			//エラーメッセージ初期化
			retErrMess = "";
			
			ArrayList<ListDto> tmp;
			
			//デフォルトグループチェック
			tmp = new ArrayList<ListDto>();
			
			for(int i = 0; i < atEmp.length; i++){
				
				ListDto adlist = empDao.selectByEmpIDGruID(Integer.parseInt(atEmp[i]), group);
				
				if(adlist != null){
					tmp.add(adlist);
				}
			}		
			
			if(tmp.size() != 0){
				
				StringBuilder sb = new StringBuilder();
				
				MGroup ety_gru = gruDao.selectById(group);
				
				sb.append(CommonConstants.cmt_groupretach1.replace("&%rep%&", ety_gru.name));
				sb.append(CommonConstants.cmt_crlf);
				
				for(int i = 0;i < tmp.size(); i++){
					
					sb.append(tmp.get(i).value );
					sb.append("　");
					sb.append(tmp.get(i).label );
					sb.append(CommonConstants.cmt_crlf);
					
				}				
				
				sb.append(CommonConstants.cmt_crlf);
				sb.append(CommonConstants.cmt_groupretach2);
				
				retErrMess = sb.toString();
				
				return null;
				
			}
					
			//追加
			for(int i = 0;i < atEmpItems.size() ;i++){
				
				boolean flg = false;
				
				for(int y = 0;y < atEmp.length;y++){
					
					if(atEmpItems.get(i).value.equals(atEmp[y])){
						flg = true;
					}
					
				}
				
				if(flg){
					reEmpItems.add(atEmpItems.get(i));
				}

			}
			
			//削除
			tmp = new ArrayList<ListDto>();
			
			for(int i = 0;i < atEmpItems.size() ;i++){
				
				boolean flg = true;
				
				for(int y = 0;y < atEmp.length;y++){
					
					if(atEmpItems.get(i).value.equals(atEmp[y])){
						flg = false;
					}
					
				}
				
				if(flg){
					tmp.add(atEmpItems.get(i));
				}

			}
			
			atEmpItems = tmp;
			
		}catch(Exception e){
			return AbendPage.class;
		}

		return null;
		
	}
	
	/**
	 * <p>更新メソッド</p>
	 * @return 自画面遷移
	 *
	 */
	public Class<?> doUpdate(){
		
		//エラーメッセージ初期化
		retErrMess = "";
		
		try{
			
			TGroupattach ety_gruat;
			
			gruatDao.deletebyId(group);
			
			for(int i = 0;i < atEmpItems.size();i++){
				
				ety_gruat = new TGroupattach();
				
				ety_gruat.groupId		= group;
				ety_gruat.employeeId 	= Integer.parseInt(atEmpItems.get(i).value);
				
				gruatDao.insert(ety_gruat);
				
			}
			
		}catch(Exception e){
			return AbendPage.class;
		}

		return null;
		
	}
	
	/**
	 * マスタメンテナンス用レイアウト適用
	 * @return
	 */
	public String getLayout(){
		return CommonConstants.MAST_LAOUT_URL;
	}
	
}
