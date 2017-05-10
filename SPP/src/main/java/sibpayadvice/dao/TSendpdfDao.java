package sibpayadvice.dao;

import sibpayadvice.entity.TSendpdf;

public interface TSendpdfDao {

	public static final Class<TSendpdf> BEAN = TSendpdf.class;
	public static final String selectById_ARGS = "empid, sendyear, sendmonth";

	public TSendpdf[] selectAll();

	public TSendpdf selectById(Integer empid, Integer sendyear,
			Integer sendmonth);

	public int insert(TSendpdf tSendpdf);

	public int update(TSendpdf tSendpdf);

	public int delete(TSendpdf tSendpdf);

}
