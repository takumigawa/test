package ko.dao;

import ko.dto.KanjoMasterDto;
import java.util.List;

import ko.entity.MKanjo;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

@S2Dao(bean=MKanjo.class)
public interface MKanjoDao {

	public MKanjo[] selectAll();

	@Arguments("Kanjo_ID")
	public MKanjo selectById(Integer kanjoId);

	
	@Arguments( "kaihai" )
	public KanjoMasterDto[] selectGetKanjoMaster(Integer kaihai);
	
	public KanjoMasterDto[] selectGetKanjoMaster2();
	
	@Arguments( "Kanjo_ID" )
	public KanjoMasterDto[] selectGetKanjoMaster3(Integer Kanjo_ID);
	
	public int getNextOrderSEQ();
	
	public int getMaxID();
	
	public String getMaxOrderSEQ();
	

	public String getOrderSEQ(Object orderSEQ);
	
	public String getKanjoName(Object kanjoName);
	
//	public KanjoMaster[] selectById(Integer id);

	public String getOrderSEQ(String orderSEQ);


	public int insert(MKanjo mKanjo);

	public int update(MKanjo mKanjo);

	public int delete(MKanjo mKanjo);

}
