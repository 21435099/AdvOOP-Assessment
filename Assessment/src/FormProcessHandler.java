import java.io.*;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

public class FormProcessHandler implements HttpHandler {

	  public void handle(HttpExchange he) throws IOException {
		   
		    System.out.println("In FormProcessHandler");
		   
		  
		    BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		   
		    String line;
		    String request = "";
		    while( (line = in.readLine()) != null ){
		      request = request + line;
		    }

		    System.out.println("request" +request);
		    
		    HashMap<String,String> map = Util.requestStringToMap(request);

		    int num1 = Integer.parseInt(map.get("num1"));
		    int num2 = Integer.parseInt(map.get("num2"));

		    int result = num1 + num2;

		    BufferedWriter out = new BufferedWriter(  
		        new OutputStreamWriter(he.getResponseBody() ));
		     
		     he.sendResponseHeaders(200,0);
		      out.write(
		      "<html>" +
		      "<head> <title>Calculator page</title> </head>" +
		      "<body>" +

		      "<h1> Calculator!</h1>" +
		      "<h1> and the result is " + result + "</h1>" + 
		      "<a href=\"/\"> click here </a" +   
		      "</body>" +
		      "</html>");
		    
		    out.close();
		    

		  }
}
