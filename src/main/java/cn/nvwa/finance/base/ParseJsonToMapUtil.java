package cn.nvwa.finance.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ParseJsonToMapUtil {
	

	private  Map<String, String> iResponJsonToMap = new HashMap<String, String>();
	

	private static Map<String,Map<String,String>> iNameAndIMap = new HashMap<String,Map<String,String>>();
	
	/**
	 * 
	 * @Title: getINameAndIMap   
	 * @Description: TODO
	 * @param: @return      
	 * @return: Map<String,Map<String,String>>      
	 * @throws
	 */
	public static Map<String,Map<String,String>> getINameAndIMap(){
		return iNameAndIMap;
	}
	
	/**
	 * 
	 * @Title: setINameAndIMap   
	 * @Description: TODO
	 * @param: @param reqInterface
	 * @param: @param responseJson      
	 * @return: void      
	 * @throws
	 */
	public void setINameAndIMap(String reqInterface,String responseJson){
		iNameAndIMap.put(reqInterface, parseJsonToMap(responseJson));
	}
	
	
	
	/**
	 * 
	 * @Title: isJsonString   
	 * @Description: TODO
	 * @param: @param jsonString
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public  boolean isJsonString(String jsonString) {
        boolean flag = true;
        try {
            JSON.parseObject(jsonString);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
	
	/**
	 * 
	 * @Title: isJsonArray   
	 * @Description: TODO
	 * @param: @param jsonArrayString
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
    public  boolean isJsonArray(String jsonArrayString) {
        boolean flag = true;
        try {
            JSONArray.parseArray(jsonArrayString);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    
	/**
	 * 
	 * @Title: isString   
	 * @Description: TODO
	 * @param: @param str
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
    public  boolean isString(String str) {
        return !isJsonString(str) & !isJsonArray(str);
    }
    
    
	/**
	 * 
	 * @Title: parseJsonArray   
	 * @Description: TODO
	 * @param: @param jsonArrayString      
	 * @return: void      
	 * @throws
	 */
    public  void parseJsonArray(String jsonArrayString){
    	try{
    		JSONArray jsonArray = JSONArray.parseArray(jsonArrayString);
    		for(Object json:jsonArray){
    			String jsonItem = json.toString();
    			if(isJsonString(jsonItem)){
    				parseJsonToMap(jsonItem);
        		}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
    /**
     * 
     * @Title: parseJsonToMap   
     * @Description: TODO
     * @param: @param responseJson
     * @param: @return      
     * @return: Map<String,String>      
     * @throws
     */
	public Map<String, String> parseJsonToMap(String responseJson){
		
		try{
			JSONObject json = JSON.parseObject(responseJson);
			for(Entry<String, Object> entry:json.entrySet()){
				
				
				String key = entry.getKey();
				String value = String.valueOf(entry.getValue());

				
				if(value.length()==0 || value.equals("null")){
					iResponJsonToMap.put(key, "null");
					continue;
				}

			
	            if (isJsonString(value)){
	            	iResponJsonToMap.put(key,value);
	            	parseJsonToMap(value);
	            }
				
	          
	            if (isJsonArray(value)) {
	            	iResponJsonToMap.put(key, value);
	                parseJsonArray(value);
	            }
	            
	          
				if (isString(value)){
					if(iResponJsonToMap.containsKey(key)){
						iResponJsonToMap.put(key,iResponJsonToMap.get(key) +"," + value);
					}else{
						iResponJsonToMap.put(key, value);}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return iResponJsonToMap;
	}
	
	public static void main(String[] args) {

	}

}
