package koroot.dao;

import java.util.List;

import koroot.dto.KaribaraiDetailListDto;
import koroot.dto.ProvdetailListDto;
import koroot.dto.ProvsinnseiListDto;
import koroot.entity.TProvisionalDetale;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

@S2Dao(bean=TProvisionalDetale.class)
public interface TProvisionalDetaleDao {

	public TProvisionalDetale[] selectAll();

	@Arguments("Provisional_Detail_ID")
	public TProvisionalDetale selectById(Integer provisionalDetailId);

	public int insert(TProvisionalDetale tProvisionalDetale);

	public int update(TProvisionalDetale tProvisionalDetale);

	public int delete(TProvisionalDetale tProvisionalDetale);
	
	@Arguments("seikyuID")
	public List<KaribaraiDetailListDto> getKariDetailList(Integer seikyuID);
	
	@Arguments({"selectID", "kaihai"})
	public List<ProvdetailListDto> getProvdetailList(int selectID, int kaihai);


}
