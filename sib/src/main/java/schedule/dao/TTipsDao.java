package schedule.dao;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import schedule.dto.TipsListDto;
import schedule.entity.TTips;


@S2Dao(bean=TTips.class)
public interface TTipsDao {

	public TTips[] selectAll();

	@Arguments("tips_ID")
	public TTips selectById(Integer tipsId);
	
	/**
	 * <p>掲載期間中トピックス一覧取得</p>
	 * 
	 * @param nowDate 日付
	 * @return　トピックス一覧
	 */
	@Arguments("nowDate")
	public TipsListDto[] selectGetTipsList(String nowDate);
	
	/**
	 * <p>検索に該当するトピックス一覧取得</p>
	 * 
	 * @param startDate　掲載開始日
	 * @param endDate　掲載終了日
	 * @param empID　入力社員ID
	 * @return　該当トピックス一覧
	 */
	@Arguments({"sDate", "eDate", "empID"})
	public TipsListDto[] selectGetTipsListSearch(String sDate, String eDate, String empID);

	public int insert(TTips tips);

	public int update(TTips tips);

	public int delete(TTips tips);

}
