package schedule.entity;
import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="m_group")
public class MGroup {

	public Integer groupId;
	public String name;
	public Integer authority;
	public String nameKana;
	public Integer orderseq;
	public Integer kaihai;
	public String adddate;
	public Integer addid;
	public String upddate;
	public Integer updid;

}
