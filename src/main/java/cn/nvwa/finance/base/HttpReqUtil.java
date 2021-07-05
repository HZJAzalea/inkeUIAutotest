package cn.nvwa.finance.base;

import cn.hutool.http.HttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * 
 * @ClassName:  HttpReqUtil   
 * @Description:TODO   
 * @author: huangzhijuan
 * @date:   2019年7月15日 下午4:26:55
 *     
 * @Copyright: 2019 www.inke.cn All rights reserved.
 */
public class HttpReqUtil {
	
	private static BasicCookieStore cookieStore = new BasicCookieStore();
    private static final int timeout = 10000;
	
	/**
	 * 
	 * @Title: httpReqConfig   
	 * @Description: TODO
	 * @param: @param httpRequestBase      
	 * @return: void      
	 * @throws
	 */
	public static void httpReqConfig(HttpRequestBase httpRequestBase,String name,String value){

        httpRequestBase.setHeader(name,value);
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(2000)
				.build();
		
		httpRequestBase.setConfig(requestConfig);
		
	}
	
	/**
	 * 
	 * @Title: sendGet   
	 * @Description: TODO
	 * @param: @param url
	 * @param: @param param
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String sendGet(String url,String param,String headName,String headValue){
		
		String result = null;
		CloseableHttpResponse response = null;
		String reqUrl = url + "?" + param;

        try {

            URL ur = new URL(reqUrl.toString());
            URI uri = new URI("http",ur.getUserInfo(),ur.getHost(),ur.getPort(),ur.getPath(),ur.getQuery(),null);

            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            //HttpGet httpGet = new HttpGet(reqUrl);
            HttpGet httpGet = new HttpGet(uri);

            httpReqConfig(httpGet,headName,headValue);
            response = httpclient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * @Title: sendPost   
	 * @Description: TODO
	 * @param: @param url
	 * @param: @param param
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public  static String sendPost(String url,String param,String headName,String headValue){
		
		String result = null;
		CloseableHttpResponse response = null;
		
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();

		HttpPost httpPost = new HttpPost(url);
		httpReqConfig(httpPost,headName,headValue);
		
		try {
			StringEntity stringEntity = new StringEntity(param);

			if(new ParseJsonToMapUtil().isJsonString(param)){
				//json
				stringEntity.setContentType("application/json; charset=UTF-8");
			}else{

				stringEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8"); 
			}
			
			httpPost.setEntity(stringEntity);
			
			response = httpclient.execute(httpPost);
			
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}


    public static String postJson(String url,String reqStr){
        String body = null;
        try{
            body = HttpRequest.post(url).body(reqStr).timeout(timeout).execute().body();
        }catch (Exception e){
            e.printStackTrace();
        }
        return body;
    }

}
