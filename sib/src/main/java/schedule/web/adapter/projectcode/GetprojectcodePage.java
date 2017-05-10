package schedule.web.adapter.projectcode;

import schedule.dao.TProjectDao;
import schedule.entity.TProject;

public class GetprojectcodePage {

	private String projectcode;
	private String projectname;
	private String pcode;
	
	public TProjectDao proDao;

	public String getProjectcode() {
		return projectcode;
	}
	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> initialize() {
		
		TProject ety_pro = proDao.selectById(projectcode);

		projectcode = ety_pro.getPjCode();
		projectname = ety_pro.getPjName();
		
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> prerender() {
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
}
