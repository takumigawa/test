package ko.web.kom001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

//import schedule.web.masterMaintenance.MastermaintePage;

import ko.common.CommonConstants;
import ko.common.CommonUtil;
import ko.dao.MKanjoDao;
import ko.dto.KanjoMasterDto;
import ko.dto.ListDto;
import ko.dto.SessionDto;
import ko.entity.MKanjo;
import ko.pagebace.PageBaceClass;

public class Kom001g1Page extends PageBaceClass{

	@Binding
	private String employee_id;
	@Binding 
	private String employee_name;
	@Binding 
	private String employee_mail;
	private Integer kaihaiselect;
	private List<ListDto> kaihaiselectItems;
//	private int kanjoIndex;
	
	/** 顧客一覧 */
	@PageScope
	private KanjoMasterDto[] kanjoItems;
	private Integer kanjoID;
	private Integer kanjo;
	private String name;
	private Integer orderSEQ;
	
	private String kaihai;
	private String chengeUpd;
	
	/** 顧客一覧Index */
	public int kanjoIndex;
	
	/** 選択顧客ＩＤ */
	@SubapplicationScope
	public Integer selectkanID;
	
	@SubapplicationScope
	public SessionDto sessiondto;
	
	public MKanjoDao Mkao;

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
 
	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_mail() {
		return employee_mail;
	}

	public void setEmployee_mail(String employee_mail) {
		this.employee_mail = employee_mail;
	}

	public Integer getKaihaiselect() {
		return kaihaiselect;
	}

	public void setKaihaiselect(Integer kaihaiselect) {
		this.kaihaiselect = kaihaiselect;
	}

	public List<ListDto> getKaihaiselectItems() {
		return kaihaiselectItems;
	}

	public void setKaihaiselectItems(List<ListDto> kaihaiselectItems) {
		this.kaihaiselectItems = kaihaiselectItems;
	}


	public KanjoMasterDto[] getKanjoItems() {
		return kanjoItems;
	}

	public void setKanjoItems(KanjoMasterDto[] kanjoItems) {
		this.kanjoItems = kanjoItems;
	}

	public Integer getKanjo_ID() {
		return kanjoID;
	}

	public void setKanjo_ID(Integer kanjo_ID) {
		kanjoID = kanjo_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderSEQ() {
		return orderSEQ;
	}

	public void setOrderSEQ(Integer orderSEQ) {
		this.orderSEQ = orderSEQ;
	}
	
	public Integer getKanjo() {
		return kanjo;
	}

	public void setKanjo(Integer kanjo) {
		this.kanjo = kanjo;
	}

	public String getKaihai() {
		return kaihai;
	}

	public void setKaihai(String kaihai) {
		this.kaihai = kaihai;
	}


	public Class<?> prerender() throws Exception {
		
//		kanjoItems = Mkao.selectGetKanjoMaster(0);
		
		
//		Map map1 = new HashMap();
//		map1.put("label", "有効のみ");
//		map1.put("value", new Integer(0));
//		
//		kaihaiselectItems.add(map1);
//		
//		Map map2 = new HashMap();
//		map2.put("label", "無効のみ");
//		map2.put("value", new Integer(1));
//		
//		kaihaiselectItems.add(map2);
//		
//		Map map3 = new HashMap();
//		map3.put("label", "有効/無効");
//		map3.put("value", new Integer(2));
//		
//		kaihaiselectItems.add(map3);
//	
		
		Map<String, String> map1 = new HashMap<String,String>();
		map1.put("combName", "kaihai");
		
		kaihaiselectItems = CommonUtil.getServiceToCombData(this.getServiceValue(CommonConstants.SERVICE_TYPE_COMBLIST, map1));
		
		//ログイン情報
//		employee_id 	= sessionDto.EmployeeID.toString();
//		employee_name	= sessionDto.EmployeeName;
//		employee_mail	= sessionDto.EmployeeEmail;
		
//		this.setLoginInfo();
		
		employee_id = "1";
		employee_name = "テスト";
		employee_mail = "test@sib.co.jp";
		
		return null;
	}
	
	public Class<?> dochengeUpd(){
		return null;
	}
	
	public Class<?> doSrech() {
		
		kanjoItems = Mkao.selectGetKanjoMaster(kaihaiselect);
		return null;
	}

	public Class<?> doFinishReturn() {
		return null;
//		return MastermaintePage.class;
	}

	public Class<?> doDetails() {
		
		
		selectkanID = kanjoItems[kanjoIndex].kanjo;
		
		
		return Kom001g2Page.class;
	}
	
	public Class<?> doinsnew(){
		
		selectkanID = -1;
		return Kom001g2Page.class;
	}

	public Class<?> initialize() {
		
		kanjoItems = Mkao.selectGetKanjoMaster2();
				
		return null;
	}
	
}
