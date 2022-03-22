import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class AdminOptionsHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	    he.sendResponseHeaders(200,0); //what does this mean?
	    BufferedWriter out = new BufferedWriter(  
	        new OutputStreamWriter(he.getResponseBody() ));
	    
	    out.write(
	    	      "<html>" +
	    	      "<head> <title>Admin Options</title> </head>" +
	    	      "<body>" +
	    	      "<h1> Administrator Options</h1>"+
	    	      "<a href=\"/DisplayBooks\"> Display all books </a><br>" +
	    	      "<a href=\"/Login\"> Add a book </a>" + 
	    	      "<a href=\"/Login\"> Delete a book </a>" +
	    	      "<a href=\"/Login\"> Edit a book </a>" +
	    	      "</body>" +
	    	      "</html>"
	    	      );
	    	    
	    out.close();

	    
	}
}
