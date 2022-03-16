import java.io.*;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

public class LoginProcessHandler implements HttpHandler {
	
	static BooksDAO bookdao = BooksDAO.getInstance();
	
	public void handle(HttpExchange he) throws IOException {
		
			
		msg("In LoginProcessHandler");
		
	    BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
	   
	    String line;
	    String request = "";
	    while( (line = in.readLine()) != null ){
	      request = request + line;
	    } 
	    
	    msg("request" +request);
	    HashMap<String,String> map = LoginUtil.requestStringToMap(request);

	    String username = map.get("username");
	    String password = MD5.getMd5(map.get("password"));
	    
	    if(username.isEmpty()){
	    	he.getResponseHeaders().add("Location", "/Login?err1");
	    	he.sendResponseHeaders(302, 0);
	    } else if(bookdao.usernameMatch(username) == false) {
	    	he.getResponseHeaders().add("Location", "/Login?err2");
	    	he.sendResponseHeaders(302, 0);
	    } else if (bookdao.passwordMatch(username, password) == false) {
	    	he.getResponseHeaders().add("Location", "/Login?err3");
	    	he.sendResponseHeaders(302, 0);
	    }
	    else {
	    	BufferedWriter out = new BufferedWriter(  
	    	        new OutputStreamWriter(he.getResponseBody() ));
	    	     
	    	     he.sendResponseHeaders(200,0);
	    	      out.write(
	    	      "<html>" +
	    	      "<head> <title>Calculator page</title> </head>" +
	    	      "<body>" +

	    	      "<h1> Calculator!</h1>" +
	    	      "<h1> and the result is </h1>" + 
	    	      "<a href=\"/\"> click here </a" +   
	    	      "</body>" +
	    	      "</html>");
	    	    
	    	    out.close();
	    }
	    
	    
	    
	    
	    
	    
	    
	}
	
	static <T> void msg(T t) {
		System.out.println(t);
	}

}
