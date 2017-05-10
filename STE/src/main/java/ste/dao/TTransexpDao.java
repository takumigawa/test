package ste.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import ste.dto.expDto;
import ste.entity.TTransexp;

@S2Dao(bean=TTransexp.class)
public interface TTransexpDao {

	public TTransexp[] selectAll();

	@Arguments({ "empid", "empyear", "empmonth", "branchno" })
	public TTransexp selectById(Integer empid, Integer empyear,
			Integer empmonth, Integer branchno);
	
	@Arguments({ "empid", "empyear", "empmonth" })
	public Integer selectByIdNextBrounch(Integer empid, Integer empyear,
			Integer empmonth);

	@Arguments({ "empid", "empyear", "empmonth" })
	public List<expDto> selectByIdSin(Integer empid, Integer empyear,
			Integer empmonth);
	
	public int insert(TTransexp tTransexp);

	public int update(TTransexp tTransexp);

	public int delete(TTransexp tTransexp);

	@Arguments({ "empid", "empyear", "empmonth" })
	public Integer deleteById(Integer empid, Integer empyear,Integer empmonth);
	
}
