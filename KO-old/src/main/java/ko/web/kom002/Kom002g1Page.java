package ko.web.kom002;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;

import ko.common.CommonConstants;
import ko.dto.ListDto;
import ko.dto.StatusItemsDto;
import ko.pagebace.PageBaceClass;



public class Kom002g1Page extends PageBaceClass
{

	/** ログイン社員情報 */
	private String employee_id;
	private String employee_name;
	private String employee_mail;
	
	/** ステータス一覧 */
	  /** ステータス検索 */
	private String kaihai;
	private List<ListDto> kaihaiItems;
	public int statusIndex;
	@SubapplicationScope
	public StatusItemsDto[] statusItems;
	
	  /** ステータス明細 */
	private StatusItemsDto status;
	private String statusID;
	private String statusName;
	private String statusEnabled;
	
	//ステータス格納用
	public  String comId;
	public  String comLabel;
	public  String comKaihai;
	public  String comOrderSEQ;
	public  Map<String,String> strnamelist;
	
	
	/** 社員ID セッタ/ゲッタ */
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	/** 社員名 セッタ/ゲッタ */
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	/** 社員メール セッタ/ゲッタ */
	public String getEmployee_mail() {
		return employee_mail;
	}
	public void setEmployee_mail(String employee_mail) {
		this.employee_mail = employee_mail;
	}

	/** ?? */
	public String getKaihai() {
		return kaihai;
	}
	public void setKaihai(String kaihai) {
		this.kaihai = kaihai;
	}

	/** ?? */
	public List<ListDto> getKaihaiItems() {
		return kaihaiItems;
	}
	public void setKaihaiItems(List<ListDto> kaihaiItems) {
		this.kaihaiItems = kaihaiItems;
	}

	/** ?? */
	public int getStatusIndex() {
		return statusIndex;
	}
	public void setStatusIndex(int statusIndex) {
		this.statusIndex = statusIndex;
	}

	/** ?? */
	public StatusItemsDto[] getStatusItems() {
		return statusItems;
	}
	public void setStatusItems(StatusItemsDto[] statusItems) {
		this.statusItems = statusItems;
	}

	/** ステータス明細 */
	/** ?? */
	public String getStatusID() {
		return statusID;
	}
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}

	/** ?? */
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/** ?? */
	public String getStatusEnabled() {
		return statusEnabled;
	}
	public void setStatusEnabled(String statusEnabled) {
		this.statusEnabled = statusEnabled;
	}


	
	public StatusItemsDto getStatus() {
		return status;
	}
	public void setStatus(StatusItemsDto status) {
		this.status = status;
	}
	public Class<?> doSrech() {
		return null;
	}

	public Class<?> doSrechCancel() {
		return null;
	}

	public Class<?> doReturn() {
		return null;
	}

	public Class<?> doEdit() {
		return null;
	}

	public Class<?> doClassAdd() {
		return null;
	}

	public Class<?> initialize() {
	
	/** ステータスリストの取得 */
		/** マップ宣言 */
		Map<String, String> reqstatus = new HashMap<String, String>();
		Map<String, String> resstatus = new HashMap<String, String>();

		try {
			/** 取得キー設定 */
			reqstatus.put("key1","comblist");       
			reqstatus.put("combName","sibko");      

			/** サービスタイプ指定・値の取得 */
			resstatus = this.getServiceValue(CommonConstants.SERVICE_TYPE_GET_ALL_COMBLIST,reqstatus);

			comId       = resstatus.get("comId");
			comLabel    = resstatus.get("comLabel");
			comKaihai   = resstatus.get("comKaihai");
			comOrderSEQ = resstatus.get("comOrderSEQ");

			
			String[] splitcomId       = comId.split(",");
			String[] splitcomLabel    = comLabel.split(",");
			String[] splitcomKaihai   = comKaihai.split(",");
			String[] splitcomOrderSEQ = comOrderSEQ.split(",");

			int a = splitcomId.length;
			StatusItemsDto[] sttDto = new StatusItemsDto[a];
			
			for(int i=0;i<a;i++){
				StatusItemsDto stt = new StatusItemsDto();
				stt.statusID = splitcomId[i];
				stt.statusName = splitcomLabel[i];
				stt.statusEnabled = splitcomKaihai[i];
				sttDto[i] = stt;
				System.out.println(sttDto.length);
			}
			
			statusItems = sttDto;
			
//			strnamelist = new HashMap<String,String>();
//			for(int i = 0; i < a; i++){
//
//				strnamelist.put(splitcomId[i],splitcomLabel[i]);
//			}
//			
//	
//			
//			
//			for(int i = 0; i > a; i++){
//				
//				 statusItems[i].statusID = strnamelist.get(statusItems[i].statusID.toString());
//			}

			
////			for(int i = 0; i < a; i++){
////				statusItems[i].state = strnamelist.get(statusItems[i].state.toString());
////				}
//
//			    
//			
		}catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
		}
		
		return null;
	}

	
	
	public Class<?> prerender() {
		
		
		HashMap<String, String> test = new HashMap<String, String>();
		
		test.put("1","ぽっかこーひー");
		test.put("2","ぼす");
		employee_id = test.get("1");

//		statusID = "1";
//		statusName = "てすと";
//		statusEnabled = 0;
		
		 
		return null;
	}

}
