package ste.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.S2Dao;

import ste.dto.transDto;
import ste.entity.TTighten;

@S2Dao(bean=TTighten.class)
public interface TTightenDao {

	public TTighten[] selectAll();

	@Arguments({ "empid", "empyear", "empmonth" })
	public TTighten selectById(Integer empid, Integer empyear, Integer empmonth);

	@Arguments({ "empid", "kaihai" })
	public List<transDto> selectByIdList(Integer empid,Integer kaihai);
	
	public int insert(TTighten tTighten);

	public int update(TTighten tTighten);

	public int delete(TTighten tTighten);

}
