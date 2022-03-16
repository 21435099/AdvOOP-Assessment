import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

public class RootHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    out.write(
      "<html>" +
      "<head> <title>My new Web Page</title> </head>" +
      "<body>" +
      "<h1> Test Test project launch page test</h1>"+
      "<a href=\"/DisplayBooks\"> Display all books </a><br>" +
      "<a href=\"/Login\"> Admin Login </a>" + 
      "</body>" +
      "</html>");
    
    out.close();

  }
}
