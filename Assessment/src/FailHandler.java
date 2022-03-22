import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

public class FailHandler implements HttpHandler {
	
	@Override
	public void handle(HttpExchange he) throws IOException {
		
		he.sendResponseHeaders(200,0);
		
	    BufferedWriter out = new BufferedWriter(  
	        new OutputStreamWriter(he.getResponseBody() ));
	    
    	out.write(
	    	      "<html>" +
	    	      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
	    	      "<head> <title>Fail</title> </head>" +
	    	      "<body>" +
	    	      "<h1>Operation failed!</h1><br>" +
	    	      "<p>To return <a href=\"/displaybooks\">Click here</a></p>" +
	    	      "</body>" +
	    	      "</html>"
	    	      );
    	
	    out.close();
	}
	

}
