package koroot.web.kor001;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.seasar.teeda.extension.annotation.scope.PageScope;
import org.seasar.teeda.extension.annotation.scope.SubapplicationScope;
import org.seasar.teeda.extension.annotation.validator.Required;

import koroot.common.CommonConstants;
import koroot.common.CommonUtil;
import koroot.dao.MKanjoDao;
import koroot.dao.TKeihiDao;
import koroot.dao.TMessageDao;
import koroot.dto.ListDto;
import koroot.dto.yaritoriMessageDto;
import koroot.entity.MKanjo;
import koroot.entity.TKeihi;
import koroot.entity.TMessage;
import koroot.exception.AnLoginException;
import koroot.pagebace.PageBaceClass;
import koroot.web.error.AbendPage;
import koroot.web.error.NonloginPage;
import koroot.web.kor001.Kor001g1Page;
import koroot.web.kor001.Kor001g3Page;

public class Kor001g2Page extends PageBaceClass{

	/** 選択経費ＩＤ */
@PageScope
@SubapplicationScope
private Integer selectID;
@SubapplicationScope
private  Integer year;
@SubapplicationScope
private  Integer month;
@SubapplicationScope
private  Integer status;

/** 選択年度格納 */
@PageScope
@SubapplicationScope
private Integer selectyearID;
/** 選択月度格納 */
@PageScope
@SubapplicationScope
private Integer	selectmonthID;
/** 選択ステータス格納 */
@PageScope
@SubapplicationScope
private Integer selectstatusID;


	/**　経費ID セッター・ゲッター　*/
	public Integer getSelectID() {
		return this.selectID;
	}
	public void setSelectID(Integer selectID) {
		this.selectID = selectID;
	}

	/**　選択年 セッター・ゲッター　*/
	public Integer getSelectyearID() {
		return this.selectyearID;
	}
	public void setSelectyearID(Integer selectyearID) {
		this.selectyearID = selectyearID;
	}
	/**　選択月 セッター・ゲッター　*/
	public Integer getSelectmonthID() {
		return this.selectmonthID;
	}
	public void setSelectmonthID(Integer selectmonthID) {
		this.selectmonthID = selectmonthID;
	}
	/**　選択ステータス セッター・ゲッター　*/
	public Integer getSelectstatusID() {
		return this.selectstatusID;
	}
	public void setSelectstatusID(Integer selectstatusID) {
		this.selectstatusID = selectstatusID;
	}

	@PageScope
	private String seikyudate;
	@PageScope
	private String statusname;
	@PageScope
	private String siyouday;
	@PageScope
	private String pjcode;
	@PageScope
	private String pjname;
	@PageScope
	private String kanjoname;
	@PageScope
	private String kin;
	@PageScope
	private String biko;
	@PageScope
	@SubapplicationScope
	private String kogutiMes;
	private String updateMes;
	@Required(target="doUpdatemessage", messageId= "err.NoInputData")
	private String yMessage;
	private int yaritoriIndex;
	private List<yaritoriMessageDto> yaritoriItems;
	private String hatudate;
	private String hatumono;
	private String yarimessege;

	//勘定科目Dao
	public MKanjoDao kanDao;

	//経費Dao
	public TKeihiDao keiDao;

	//やりとりメッセ-ジDao
	public TMessageDao mesDao;


	/**　請求年ID　ゲッター・セッター　*/
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	/**　請求月ID　セッター・ゲッター　*/
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**　ステータスID セッター・ゲッター　*/
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSeikyudate() {
		return seikyudate;
	}

	public void setSeikyudate(String seikyudate) {
		this.seikyudate = seikyudate;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getSiyouday() {
		return siyouday;
	}

	public void setSiyouday(String siyouday) {
		this.siyouday = siyouday;
	}

	public String getPjcode() {
		return pjcode;
	}

	public void setPjcode(String pjcode) {
		this.pjcode = pjcode;
	}

	public String getPjname() {
		return pjname;
	}

	public void setPjname(String pjname) {
		this.pjname = pjname;
	}

	public String getKanjoname() {
		return kanjoname;
	}

	public void setKanjoname(String kanjoname) {
		this.kanjoname = kanjoname;
	}

	public String getKin() {
		return kin;
	}

	public void setKin(String kin) {
		this.kin = kin;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

	public String getupdateMes() {
		return updateMes;
	}

	public void setupdateMes(String updateMes) {
		this.updateMes = updateMes;
	}

	public String getyMessage() {
		return yMessage;
	}

	public void setyMessage(String yMessage) {
		this.yMessage = yMessage;
	}

	public int getYaritoriIndex() {
		return yaritoriIndex;
	}

	public void setYaritoriIndex(int yaritoriIndex) {
		this.yaritoriIndex = yaritoriIndex;
	}

	public List<yaritoriMessageDto> getYaritoriItems() {
		return yaritoriItems;
	}

	public void setYaritoriItems(List<yaritoriMessageDto> yaritoriItems) {
		this.yaritoriItems = yaritoriItems;
	}

	public String getHatudate() {
		return hatudate;
	}

	public void setHatudate(String hatudate) {
		this.hatudate = hatudate;
	}

	public String getHatumono() {
		return hatumono;
	}

	public void setHatumono(String hatumono) {
		this.hatumono = hatumono;
	}

	public String getYarimessege() {
		return yarimessege;
	}

	public void setYarimessege(String yarimessege) {
		this.yarimessege = yarimessege;
	}

	public Class<?> doUpdatemessage() {

		//やりとりメッセージ　エンティティー　
				TMessage ety_mes = new TMessage();

				ety_mes.setAdddate(CommonUtil.getNowUpdDate());

				ety_mes.setAddid(this.sessionDto.EmployeeID);

				ety_mes.setMessage(yMessage);

				ety_mes.setInputEmpid(this.sessionDto.EmployeeID);

				ety_mes.setKaihai(0);

				ety_mes.setSeikyuId(selectID);


				mesDao.insert(ety_mes);

				updateMes = "メッセージ追加しました。";
				yMessage = "";

				return Kor001g2Page.class;

	}

	public Class<?> doUpdate() {

		
		kogutiMes = "";
		
		updateMes = "";
		
		return Kor001g3Page.class;
	}

	public Class<?> doReturn() {

		selectyearID   = getYear();
		selectmonthID  = getMonth();
		selectstatusID = getStatus();
		kogutiMes = "";		
		updateMes = "";		
		return Kor001g1Page.class;
	}

	public Class<?> initialize() {

		try{

			//セッション情報チェック
			this.chkSession();

			//ログイン情報表示
			this.setLoginInfo();

			//経費エンティティ　取得
			TKeihi ety_keih = keiDao.selectById(selectID);

			//勘定科目エンティティ　取得
			MKanjo ety_kan = kanDao.selectById(ety_keih.getKanjoId());

			//請求日
			seikyudate = ety_keih.getSeikyuDate();
			//ステータス名取得
			List<ListDto> statusList = this.getStatusList(CommonConstants.STATUS_KO001);
			if(statusList != null){
				for(ListDto statusItem : statusList){
					if(statusItem.value.equals(ety_keih.getState().toString())){
						statusname = statusItem.label;
						break;
					}
				}
			}

			//使用日
			siyouday   = ety_keih.getSiyouDate();
			//プロジェクトコード
			pjcode     = ety_keih.getPjCode();
			//プロジェクト名
			Map<String, String> reqParam = this.getServiceValue(CommonConstants.SERVICE_TYPE_PJ_LIST);
			List<ListDto> pjList =  CommonUtil.getServiceToCombData(reqParam);

			if(pjList != null){
				for(ListDto listItem : pjList){
					if(ety_keih.getPjCode().equals(listItem.value)){
						pjname = listItem.label;
					}
				}
			}
			//勘定科目
			kanjoname = ety_kan.getName();
			//金額
			NumberFormat nfNum = NumberFormat.getNumberInstance();    //カンマ区切り形式
            kin        =  nfNum.format(ety_keih.getKingaku());
            //備考
            biko       = ety_keih.getBiko();

			//やりとりメッセージ編集
			yaritoriItems = yariFormat(mesDao.selectBySeikyuID(selectID));

		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;

		}catch(Exception e){
			//アベンド
			return AbendPage.class;
		}

		return null;
	}

	public Class<?> prerender() {




		//ログイン情報表示
		this.setLoginInfo();
		//申請情報表示
		setSeikyudate(seikyudate);
		setStatusname(statusname);
		setSiyouday(siyouday);
		setPjcode(pjcode);
		setPjname(pjname);
		setKanjoname(kanjoname);
		setKin(kin);
		setBiko(biko);

		//やりとりメッセージ編集
		try {
			yaritoriItems = yariFormat(mesDao.selectBySeikyuID(selectID));
		} catch (AnLoginException e) {
			//未ログインエラー
			return NonloginPage.class;

		}catch(Exception e){
			//アベンド
			return AbendPage.class;
		}

		return null;
	}

public List<yaritoriMessageDto> yariFormat(List<yaritoriMessageDto> yList) throws Exception{

		List<ListDto> empList = this.getEmpList();

		for(yaritoriMessageDto yItem : yList) {
			for(ListDto iItem : empList) {

				if(yItem.hatumono.equals(iItem.value)){
					yItem.hatumono = iItem.label;
					break;
				}
			}
		}
		return yList;
	}
/**
 * <p>画面登録/新規切り替え判定メソッド</p>
 * @return true 更新/false 新規
 *
 */
public boolean isUpdateok(){

	if(status != 1){
		return false;
	}else{
		return true;
	}

}
public String getKogutiMes() {
	return kogutiMes;
}
public void setKogutiMes(String kogutiMes) {
	this.kogutiMes = kogutiMes;
}

}

