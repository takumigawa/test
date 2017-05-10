package serviceadapter.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class SibServiceAdapter {

	private static String START_E = "<";
	private static String END_E = ">";
	private static String START_S = "<span";
	private static String END_S = "</span";
	private static String STR_EMPTY = "";
	private static String ID_Sep = "\"";
	
	private static HttpClient httpClient; 
	
	private String URLStr;
	
	public SibServiceAdapter(){
		
		httpClient = new DefaultHttpClient(); 
		
		URLStr = STR_EMPTY;
		
	}
	
	public String getServiceProvider() {
		return URLStr;
	}
	
	public void setServiceProvider(String iURL) {
		this.URLStr = iURL;
	}
		
	public Map<String, String> postData(Map<String, String> requestParams ) throws Exception {
		
		System.out.println("SibServiceAdapter postData Start");
		
		if(STR_EMPTY.equals(URLStr)){
			Exception ex = new Exception("damedesu");
			throw ex;
		}
		
		HttpPost httpPost = null;
		
		String resultStr = STR_EMPTY;
		
		try {             
			// リクエストパラメータの設定             
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			for (Map.Entry<String, String> entry : requestParams.entrySet()) {                 
				params.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
			}
			
			httpPost = new HttpPost(URLStr);
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			// レスポンスヘッダーの取得             
			HttpResponse response = httpClient.execute(httpPost);
			
			// レスポンスボディの取得             
			HttpEntity httpEntity = response.getEntity();
			
			resultStr = EntityUtils.toString(httpEntity);
			
			System.out.println();
			System.out.println("resultStr----------------Start");
			System.out.println(resultStr);
			System.out.println("resultStr----------------End");
	
		} catch (UnknownHostException e) {
			
			System.out.println();
			System.out.println("Exception----------------Start");
			System.out.println(e.getClass().toString());
			System.out.println("Exception----------------End");
			
			Map<String, String> resParam = new HashMap<String, String>();
			
			resParam.put(e.getClass().toString(), "サービスが見つかりません。");
			
			return resParam;
			
		} catch (Exception e) {
			
			System.out.println();
			System.out.println("Exception----------------Start");
			System.out.println(e.getClass().toString());
			System.out.println("Exception----------------End");
			
			Map<String, String> resParam = new HashMap<String, String>();
			
			resParam.put("SibServiceException", "例外が発生しました。(" + e.getClass().toString() + ")");
			
			return resParam;
			
		} finally {             
			if (httpPost != null) {                 
				httpPost.abort();
			}         
		} 
		
		System.out.println("SibServiceAdapter postData End");
		
		return getSpanValue(resultStr);
		
	}
	
	private Map<String, String> getSpanValue(String iResult) {
		
		System.out.println("SibServiceAdapter getSpanValue Start");
		
		Map<String, String> resultPrams = new HashMap<String, String>();
		
		Integer i;
		Integer END_i;
		
		boolean eMode;
		boolean vMode;
		boolean iMode;
		
		String rID;
		String rValue;
		
		rID = STR_EMPTY;
		rValue = STR_EMPTY;
		
		vMode = false;
		eMode = false;
		iMode = false;
		
		i = 0;
		END_i = 0;
		
		while(true){
			
			if(eMode) {
				
				if(iMode) {
					rID += iResult.substring(i,i + 1);
				}
				
				if(vMode){
					rValue += iResult.subSequence(i, i + 1);
				}
				
				if(ID_Sep.equals(iResult.subSequence(i, i + 1))) {
					if(iMode){
						iMode = false;
					} else {
						iMode = true;
					}
				} 

				//
				if(END_E.equals(iResult.substring(i, i + 1))) {
					vMode = true;
				}
				
				//
				if(START_E.equals(iResult.substring(i, i + 1))){
					vMode = false;
				}
				
				i++;
				
				if (0 == i.compareTo(END_i)) {
					if(!STR_EMPTY.equals(rID) && !STR_EMPTY.equals(rValue)){
						resultPrams.put(rID.replace("\"", ""), rValue);
					}
					iMode = false;
					vMode = false;
					eMode = false;

				}
				
				if(0 == i.compareTo(iResult.length())) {
								
					if(!STR_EMPTY.equals(rID) && !STR_EMPTY.equals(rValue)){
						resultPrams.put(rID, rValue);
					}
					
					break;
				}
				
			} else {
				
				rID = STR_EMPTY;
				rValue = STR_EMPTY;
				
				i = iResult.indexOf(START_S);
				
				if (i > -1){
					iResult = iResult.substring(i + START_E.length());
					i = 0;
					
					END_i = iResult.indexOf(END_S);
					
					if(END_i < 0) {
						break;
					}
					
					eMode = true;
				} else {
					break;
				}
				
			}
			
		}

		System.out.println("SibServiceAdapter getSpanValue End");
		
		return resultPrams;
		
	}
	
	/**
	 * 
	 * @param iResParam
	 * @param errMessege
	 * @return
	 */
	public boolean chkException(Map<String, String> iResParam, String errMessege) {
	
		for (Map.Entry<String, String> entry : iResParam.entrySet()) {                 
			if (((String) entry.getKey()).indexOf("Exception") > 0) {
				
				errMessege = (String) entry.getValue();
				
				return true;
			}
		}
		
		return false;
		
	}
}

