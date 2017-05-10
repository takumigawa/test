package koroot.dao;

import java.util.List;

import koroot.dto.KanrishasinseiListDto;
import koroot.dto.KeihisinnseiListDto;
import koroot.dto.ListDto;
import koroot.dto.keihikyokaListDto;
import koroot.entity.TKeihi;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

@S2Dao(bean=TKeihi.class)
public interface TKeihiDao {

	public TKeihi[] selectAll();

	@Arguments("seikyu_ID")
	public TKeihi selectById(Integer seikyuId);

	public int insert(TKeihi tKeihi);

	public int update(TKeihi tKeihi);

	public int delete(TKeihi tKeihi);

	/**最大請求ID取得
	 * <p>経費テーブルの最大経費IDを取得する</p> 
	 * @return　最大値
	 */
	public Integer getMAXseikyuID();	

	/**年度コンボリスト取得
	 * <p>経費テーブル内で申請実績がある年度＋今年度より年度コンボリストを作成する</p>
	 * @param nowYear　今年度
	 * @param zenYear　前年度
	 * @return
	 */
	@Arguments( { "nowYear", "zenYear" })
	public List<ListDto> getYearList(String nowYear, String zenYear);

	/**申請者用申請一覧取得
	 * <p></p> 
	 * @param fromDate	対象日付from　　
	 * @param toDate	対象日付to
	 * @param State     対象ステータス　 全件指定：9
	 * @param Type		対象明細タイプ  一般：0　　仮払い明細：1　　全明細：2
	 * @param kaihai	改廃フラグ　　　　有効レコード：0　　論削レコード：1　　全レコード：2
	 * @param seikyu_empID	対象請求者ID
	 * @return			申請一覧
	 */
	@Arguments(  { "fromDate", "toDate", "State", "Type", "kaihai", "seikyu_empID"})
	public List<KeihisinnseiListDto> getsinnseiList( String fromDate, String toDate, int State, int Type, int kaihai, int seikyu_empID);

	/**PJL用申請一覧取得
	 * <p></p>  
	 * @param fromDate	対象日付from
	 * @param toDate	対象日付to
	 * @param State		対象ステータス  全件指定：9
	 * @param kaihai	改廃フラグ　　　有効レコード：0　　論削レコード：1　　全レコード：2
	 * @param seikyu_empID	対象請求者ID
	 * @return
	 */
	@Arguments(  { "fromDate", "toDate", "State", "kaihai", "seikyu_empID"})
	public List<KanrishasinseiListDto> getkanrishaList( String fromDate, String toDate, int State, int kaihai, int seikyu_empID);

	/**経理担当用申請一覧取得
	 * <p></p> 
	 * @param fromDate	対象日付from
	 * @param toDate	対象日付to
	 * @param inStatus	対象ステータス
	 * @param inPJCode	対象PJコード
	 * @return
	 */
	@Arguments( { "fromDate", "toDate" ,"inStatus" ,"inPJCode"})
	public List<keihikyokaListDto> getkeiritantoList(String fromDate, String toDate, String inStatus, String inPJCode);
	
//	@Arguments( { "fromDate", "toDate" ,"inStatus" ,"inPJCode"})
//	public List<keihikyokaListDto> getKogPayList(String fromDate, String toDate, String inStatus, String inPJCode);
	


	
	
	
}


