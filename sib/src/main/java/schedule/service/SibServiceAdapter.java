package schedule.service;

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

import schedule.exception.AdapterSettingException;


/**
 * サービスプロバイダへの接続クラス
 * @author taku_fujimoto
 *　
 */
public class SibServiceAdapter {

	private static String START_D = "<Data>";
	private static String END_D = "</Data>";
	private static String START_S = "<";
	private static String END_S = ">";
	private static String SLASH = "/";
	private static String STR_EMPTY = "";
	private static String ADAPTER_TYPE = "adapterStr";
	private static String POST_VALUE = "valueStr";
	private static String STR_COMMA = ",";
	
	private static HttpClient httpClient; 
	
	/** サービスプロバイダ位置情報 */
	private String URLStr;
	/** サービスプロバイダタイプ */
	private String ServiceStr;
	
	/** 
	 * インスタンス
	 */
	public SibServiceAdapter(){
		
		httpClient = new DefaultHttpClient(); 
		
		URLStr = STR_EMPTY;
		ServiceStr = STR_EMPTY;
		
	}
	
	/** サービスプロバイダのURLを取得する */
	public String getServiceProvider() {
		return URLStr;
	}
	/** サービスプロバイダのURLを設定する */
	public void setServiceProvider(String iURL) {
		this.URLStr = iURL;
	}
	/** サービスプロバイダのタイプを取得する */
	public String getServiceProviderType() {
		return ServiceStr;
	}
	/** サービスプロバイダのタイプを設定する */
	public void setServiceProviderType(String iServiceStr) {
		this.ServiceStr = iServiceStr;
	}

	/**
	 * サービスプロバイダへデータをポストし、リターンを受け取るメソッド
	 * @param requestParams
	 * @return 
	 * @throws Exception URLアクセスにエラーがあった場合、発生する。
	 */
	public Map<String, String> postData(Map<String, String> requestParams ) throws Exception {
		
		//サービスプロバイダURLが未指定の場合、例外をスロー
		if(STR_EMPTY.equals(URLStr)){
			AdapterSettingException ex = new AdapterSettingException();
			throw ex;
		}

		//サービスプロバイダタイプが未指定の場合、例外をスロー
		if(STR_EMPTY.equals(ServiceStr)){
			AdapterSettingException ex = new AdapterSettingException();
			throw ex;
		}
		
		HttpPost httpPost = null;
		
		String resultStr;
		
		try {
	        
			String tmpValue = STR_EMPTY;
			
			// リクエストパラメータの設定             
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			for (Map.Entry<String, String> entry : requestParams.entrySet()) {
				if (!STR_EMPTY.equals(tmpValue)) {
					tmpValue += STR_COMMA;
				}
				
				tmpValue += (String) entry.getKey() + STR_COMMA + (String) entry.getValue();
				
			}
			
			params.add(new BasicNameValuePair(ADAPTER_TYPE, ServiceStr));
			params.add(new BasicNameValuePair(POST_VALUE, tmpValue));
			
			httpPost = new HttpPost(URLStr);
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			// レスポンスヘッダーの取得             
			HttpResponse response = httpClient.execute(httpPost);
			
			// レスポンスボディの取得             
			HttpEntity httpEntity = response.getEntity();
			
			resultStr = EntityUtils.toString(httpEntity);

		} finally {             
			if (httpPost != null) {                 
				httpPost.abort();
			}         
		} 
		
		//リザルトXMLをパースして、マップに詰めて返却
		return getSpanValue(resultStr);
	}
	
	/**
	 * サービスプロバイダからのレスポンスをパースして、マップに詰めるメソッド
	 * @param iResult
	 * @return
	 */
	private Map<String, String> getSpanValue(String iResult) {
		
		Map<String, String> resultPrams = new HashMap<String, String>();
		
		String xmlStr = iResult.substring(iResult.indexOf(START_D) + START_D.length(), iResult.indexOf(END_D) - 1);
		
		String keyStr;
		String valueStr ;
		String preKeyT;
		String sufKeyT;
		
		while (xmlStr.indexOf(START_S) >= 0){

			keyStr = STR_EMPTY;
			valueStr = STR_EMPTY;
			preKeyT = STR_EMPTY;
			sufKeyT = STR_EMPTY;
			
			keyStr = xmlStr.substring(xmlStr.indexOf(START_S) + 1, xmlStr.indexOf(END_S));
			
			preKeyT = START_S + keyStr + END_S;
			sufKeyT = START_S + SLASH + keyStr + END_S;
			
			valueStr = xmlStr.substring(xmlStr.indexOf(preKeyT) + preKeyT.length() ,xmlStr.indexOf(sufKeyT));
			
			resultPrams.put(keyStr, valueStr);
			
			xmlStr = xmlStr.substring(xmlStr.indexOf(sufKeyT) + sufKeyT.length());
			
		}

		return resultPrams;
		
	}
}