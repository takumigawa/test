package schedule.entity;
import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="t_groupattach")
public class TGroupattach {

	public Integer groupId;
	public Integer employeeId;
	public Integer dummy;

}
