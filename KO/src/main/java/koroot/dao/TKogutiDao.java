package koroot.dao;

import java.util.List;

import koroot.dto.KanrishasinseiListDto;
import koroot.dto.KeihisinnseiListDto;
import koroot.dto.ListDto;
import koroot.dto.keihikyokaListDto;
import koroot.entity.TKoguti;
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

	@Arguments( { "nowYear", "zenYear" })
	public List<ListDto> getYearList(String nowYear, String zenYear);

	@Arguments( { "fromDate", "toDate" ,"inStatus" ,"inPJCode"})
	public List<keihikyokaListDto> getKogList(String fromDate, String toDate, String inStatus, String inPJCode);

	@Arguments( { "fromDate", "toDate" ,"inStatus" ,"inPJCode"})
	public List<keihikyokaListDto> getKogPayList(String fromDate, String toDate, String inStatus, String inPJCode);

	@Arguments(  { "fromDate", "toDate", "State" ,"kaihai","seikyu_empID"})
	public List<KeihisinnseiListDto> getsinnseiList( String fromDate, String toDate, int State, int kaihai, int seikyu_empID);
	
	@Arguments(  { "fromDate", "toDate", "State" ,"kaihai","seikyu_empID"})
	public List<KanrishasinseiListDto> getkanrishaList( String fromDate, String toDate, int State, int kaihai, int seikyu_empID);

	public Integer getMAXkogutiID();
}
