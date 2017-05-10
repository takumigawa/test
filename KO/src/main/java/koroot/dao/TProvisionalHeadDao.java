package koroot.dao;

import java.util.List;

import koroot.dto.ProvsinnseiListDto;
import koroot.dto.ListDto;
import koroot.entity.TProvisionalHead;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

@S2Dao(bean=TProvisionalHead.class)
public interface TProvisionalHeadDao {

	public TProvisionalHead[] selectAll();

	@Arguments("Provisional_Head_ID")
	public TProvisionalHead selectById(Integer provisionalHeadId);

	public int insert(TProvisionalHead tProvisionalHead);

	public int update(TProvisionalHead tProvisionalHead);

	public int delete(TProvisionalHead tProvisionalHead);
	
	@Arguments( { "nowYear", "zenYear" })
	public List<ListDto> getYearList(String nowYear, String zenYear);
	
	@Arguments(  { "fromDate", "toDate", "State", "kaihai", "seikyu_empID"})
	public List<ProvsinnseiListDto> getProvsinnseiList( String fromDate, String toDate, int State, int kaihai, int seikyu_empID);

	public Integer getMAXkaribaraiID();
}
