package koroot.dao;

import java.util.List;

import koroot.dto.KanjoListDto;
import koroot.entity.MKanjo;

public interface MKanjoDao {

	public static final Class<?> BEAN = MKanjo.class;
	public static final String selectById_ARGS = "Kanjo_ID";

	public MKanjo[] selectAll();

	public MKanjo selectById(Integer kanjoId);

	public int insert(MKanjo mKanjo);

	public int update(MKanjo mKanjo);

	public int delete(MKanjo mKanjo);
	
	public String getOrderSEQ(Object orderSEQ);
	
	public String getKanjoName(Object kanjoName);
	
	public List<KanjoListDto> getSelectByList(Integer kaihai);

	public Integer getNextOrderSEQ();

	public Integer getNextKanjoID();
	

}
