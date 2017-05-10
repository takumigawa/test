package ko.dao;


import ko.dto.KanjoListDto;
import ko.dto.KogutiListDto;
import ko.dto.ListDto;
import ko.dto.kanjyonameDto;



import ko.entity.TKoguti;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

@S2Dao(bean=TKoguti.class)
public interface TKogutiDao {

	public TKoguti[] selectAll();

	@Arguments("seikyu_ID")
	public TKoguti selectById(Integer seikyuId);

	public int insert(TKoguti tKoguti);

	public int update(TKoguti tKoguti);

	public int delete(TKoguti tKoguti);

	//追加メソッド//

	/**
	 * <p>小口一覧取得</p>
	 * @param 改廃(0:有効　1:無効　2:有効/無効)
	 * @return　サービス一覧
	 *
	 */
	//小口申請一覧一般社員用
	@Arguments({ "kaihai","year","month","state","employee" })
	public KanjoListDto[] selectGetKanjoList(Integer kaihai, String year, Integer month, String strlist, String employee_id);

	//小口申請一覧承認者用
	@Arguments({ "kaihai","year","month","state" })
	public KogutiListDto[] selectGetKogutiList(Integer kaihai);

	//小口申請一覧承認者更新用
	//@Arguments({"seikyuid","state"})
	//public void updateStatus(Integer seikyuid,Integer state);


	//勘定科目名取得
	public kanjyonameDto[] setkanjyoname(Integer  kanjyo_id);

	//選択年度
	public ListDto[] selectyearList();

	//選択月度
	public ListDto[] selectmonthList();

	//選択ステータス
	public ListDto[] getstatelist();

	//自動ID採番
	public Integer getMaxID();

}

