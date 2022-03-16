import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class LoginUtil {

	 public static HashMap<String, String> requestStringToMap(String request) {
		 
		 HashMap<String, String> map = new HashMap<String, String>();
		    String[] pairs = request.split("&");
		    for (int i = 0; i < pairs.length; i++) {
		      String pair = pairs[i]; 
		     
		      try {
		        String key = pair.split("=")[0];
		        key = URLDecoder.decode(key, "UTF-8");
		        
		        String value = "";
		        if(pair.length() > 9) { //accounting for empty data fields (both password and username are 8 characters plus 1 for =)
		        value = pair.split("=")[1];
	        	value = URLDecoder.decode(value, "UTF-8");
		        }
		        map.put(key, value);
		        
		      } catch (UnsupportedEncodingException e) {
		        System.err.println(e.getMessage());
		      }
		    }
		    return map;
	 }
	 
	
}
