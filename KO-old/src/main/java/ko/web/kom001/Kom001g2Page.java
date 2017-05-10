
package ko.web.kom001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Length;
import org.seasar.teeda.extension.annotation.validator.RegularExpression;
import org.seasar.teeda.extension.annotation.validator.Required;


//import schedule.entity.MCustomer;


import ko.common.CommonConstants;
import ko.common.CommonUtil;
import ko.dao.MKanjoDao;
import ko.dto.ListDto;
import ko.entity.MKanjo;
import ko.pagebace.PageBaceClass;
import ko.validator.ChkKanjonameValidator;
import ko.validator.ChkjuhukuValidator;

public class Kom001g2Page extends PageBaceClass {

	/** 選択顧客ＩＤ */
	@SubapplicationScope
	public Integer selectkanID;

	private Integer kanjoID;
//	private String kanjoName;
//	private Integer kanjoOrderSEQ;
	private Integer kaihaiselect;
	private List<ListDto> kaihaiselectItems;

	/** 入力チェック */
	private String errorName;
	private String errorOrder;
	boolean flg = true;

	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 30)
	//@ChkKanjonameValidator(targetId = "kanjoOrderSEQ")
	public String kanjoName;

	@Required(target="doFinishUpdate", messageId= "err.NoInputData")
	@Length(target="doFinishUpdate", maximum = 3)
	@RegularExpression(target="doFinishUpdate", pattern = CommonConstants.REG_HANKAKU_SUJI, messageId = "err.NoMatchEISU")
	@ChkjuhukuValidator(targetId = "kanjoName")
	public String kanjoOrderSEQ;

	/** 有効/無効値 */
	@PageScope
	@Required
	private Integer kaihai;

	/** 更新メッセージ表示用 */
	public String UpdateMes;

	/** HTML処理判別用 */
	public String dOru;


	public Integer getKanjoID() {
		return kanjoID;
	}
	public void setKanjoID(Integer kanjoID) {
		this.kanjoID = kanjoID;
	}


	public String getKanjoName() {
		return kanjoName;
	}
	public void setKanjoName(String kanjoName) {
		this.kanjoName = kanjoName;
	}

	public String getKanjoOrderSEQ() {
		return kanjoOrderSEQ;
	}
	public void setKanjoOrderSEQ(String kanjoOrderSEQ) {
		this.kanjoOrderSEQ = kanjoOrderSEQ;
	}

	public Integer getKaihai(){
		return kaihai;
	}
	public void setKaihai(Integer kaihai){
		this.kaihai = kaihai;
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

	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorOrder() {
		return errorOrder;
	}
	public void setErrorOrder(String errorOrder) {
		this.errorOrder = errorOrder;
	}



	public MKanjoDao Mkao;

	public Class<?> doFinishUpdate(){

		MKanjo ety_kan;
//		if(kanjoName == null || kanjoName.equals("")){
//			flg = false;
//			errorName = "勘定科目名が未入力です";
//		}
//		if(kanjoOrderSEQ == null || kanjoOrderSEQ.equals("")){
//			flg = false;
//			errorOrder = "表示順が未入力です";
//		}else{
//			try{
//				kanjoOrderSEQ.intValue();
//			}catch(Exception e){
//				flg = false;
//				errorOrder = "表示順に数値を入力してください";
//			}
//		}
//
		if(flg){
			if(selectkanID == -1){

				//顧客情報新規作成
				System.out.println("------------------------------------------------------------------");
				System.out.println(kanjoOrderSEQ);
				System.out.println("------------------------------------------------------------------");
				String test = Mkao.getOrderSEQ(new Integer(kanjoOrderSEQ));
				if(test == null){
					ety_kan = new MKanjo();

					ety_kan.setKanjo_Id(kanjoID = Mkao.getMaxID());
					ety_kan.setName(kanjoName);
					ety_kan.setOderseq(kanjoOrderSEQ);
					ety_kan.setKaisai(kaihaiselect);
					ety_kan.setAdddate(null);
					ety_kan.setAddid(null);
					ety_kan.setUpddate(null);
					ety_kan.setUpdid(null);

					Mkao.insert(ety_kan);

					UpdateMes = "新規追加しました。";
				}else{
					UpdateMes = "表示順が重複しています。";
				}


			}else{

				//顧客情報更新処理
				ety_kan = Mkao.selectById(selectkanID);

				ety_kan.setName(kanjoName);
				ety_kan.setOderseq(kanjoOrderSEQ);
				ety_kan.setKaisai(kaihaiselect);

				Mkao.update(ety_kan);

				UpdateMes = "更新しました。";

			}
			return null;
		}
		return null;
	}

	public Class<?> initialize() {

		if(selectkanID == -1){

			kanjoID = Mkao.getMaxID();

			//kanjoOrderSEQ = Mkao.getMaxOrderSEQ();
			String calculate = Mkao.getMaxOrderSEQ();
			int int_calculate = Integer.parseInt(calculate);
			int orderSEQ = 0;
			if(int_calculate < 11){
				orderSEQ = int_calculate;
			} else if(int_calculate > 10 && int_calculate < 101){
				orderSEQ = int_calculate % 10;
			} else{
				orderSEQ = (int_calculate % 100) % 10;
			}
			switch(orderSEQ){
			case 0:kanjoOrderSEQ = calculate; break;
			case 1:kanjoOrderSEQ = new Integer(int_calculate + 9).toString(); break;
			case 2:kanjoOrderSEQ = new Integer(int_calculate + 8).toString(); break;
			case 3:kanjoOrderSEQ = new Integer(int_calculate + 7).toString(); break;
			case 4:kanjoOrderSEQ = new Integer(int_calculate + 6).toString(); break;
			case 5:kanjoOrderSEQ = new Integer(int_calculate + 5).toString(); break;
			case 6:kanjoOrderSEQ = new Integer(int_calculate + 4).toString(); break;
			case 7:kanjoOrderSEQ = new Integer(int_calculate + 3).toString(); break;
			case 8:kanjoOrderSEQ = new Integer(int_calculate + 2).toString(); break;
			case 9:kanjoOrderSEQ = new Integer(int_calculate + 1).toString(); break;
			}

		} else{
			MKanjo ety_kan = Mkao.selectById(selectkanID);
			kanjoID = ety_kan.getKanjo_Id();
			kanjoName = ety_kan.getName();
			kanjoOrderSEQ = ety_kan.getOderseq();
			kaihaiselect = ety_kan.getKaisai();
		}

		return null;
	}

	public Class<?> prerender() throws Exception {

//		kaihaiselectItems = new ArrayList();
//
//		Map map1 = new HashMap();
//		map1.put("label", "有効");
//		map1.put("value", new Integer(0));
//
//		kaihaiselectItems.add(map1);
//
//		Map map2 = new HashMap();
//		map2.put("label", "無効");
//		map2.put("value", new Integer(1));
//
//		kaihaiselectItems.add(map2);
		
		Map<String, String> map1 = new HashMap<String,String>();
		map1.put("combName", "kaihai");
		
		kaihaiselectItems = CommonUtil.getServiceToCombData(this.getServiceValue(CommonConstants.SERVICE_TYPE_COMBLIST, map1));

		if(selectkanID == -1){

			kanjoID = Mkao.getMaxID();

		} else{
			MKanjo ety_kan = Mkao.selectById(selectkanID);
			kanjoID = ety_kan.getKanjo_Id();
			kanjoName = ety_kan.getName();
			kanjoOrderSEQ = ety_kan.getOderseq();
			kaihaiselect = ety_kan.getKaisai();
		}

		return null;
	}

	public Class<?> doFinishReturn(){
		return Kom001g1Page.class;
	}

	/**
	 * <p>画面登録/新規切り替え判定メソッド</p>
	 * @return true 更新/false 新規
	 *
	 */
	public boolean isUpdate(){

		if(selectkanID == -1){
			return false;
		}else{
			return true;
		}
	}
	public Integer getSelectkanID() {
		return selectkanID;
	}
	public void setSelectkanID(Integer selectkanID) {
		this.selectkanID = selectkanID;
	}

}

