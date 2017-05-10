package serviceadapter.web.adapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import serviceadapter.dao.MAdapterDao;
import serviceadapter.entity.MAdapter;
import serviceadapter.service.SibServiceAdapter;

public class AdapterPage {

	private String valueStr;
	private String adapterStr;
	private String resultStr;

	public String getValueStr() {
		return valueStr;
	}

	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

	public String getAdapterStr() {
		return adapterStr;
	}

	public void setAdapterStr(String adapterStr) {
		this.adapterStr = adapterStr;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public Class<?> initialize() {
		return null;
	}

	public MAdapterDao adpDao;
	
	/**
	 * 
	 * @return 自画面に遷移
	 */
	public Class<?> prerender() {
		
        Map<String, String> requestParams = new HashMap<String, String>();
        Map<String, String> resultParams;
        
        List<String> iValueList =(List<String>) Arrays.asList(valueStr.split(","));
       
        Integer i;
        i = 0;
        String Key = "";
        
        //
        for(String tmp : iValueList) {
        	if(i % 2 == 0){
        		Key = tmp;
        	} else {
        		requestParams.put(Key, tmp);
        	}
        	i++;
        }
        
        MAdapter ety_adp;
        
        ety_adp = adpDao.selectById(adapterStr);
        
        SibServiceAdapter SAdp = new SibServiceAdapter();
        
        try {
        	
        	SAdp.setServiceProvider(ety_adp.getServiceurl());
        	
        	resultParams = SAdp.postData(requestParams);
        	
        	resultStr = "";

        	resultStr +="<Data>\n";
        	
			for (Map.Entry<String, String> entry : resultParams.entrySet()) {
				
				resultStr += "\t<" + (String) entry.getKey() + ">";
				resultStr += (String) entry.getValue();
				resultStr += "</" + (String) entry.getKey() + ">\n";
			}
			resultStr +="</Data>\n";
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return null;
	}

	//レイアウトファイル適用を抑制する
	public Class<?> getLayout() {
		return null;
	}
	
}
