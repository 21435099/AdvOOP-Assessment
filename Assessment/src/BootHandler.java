import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class BootHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	String response = "Welcome to OOP";
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    out.write(
      "<html>" +
      "<head> <title>My new Web Page</title> </head>" +
      "<body>" +
      "<h1> Hello World Wide Web World!</h1>"+
       "<a href=\"/page1\"> click here </a" + 
      "</body>" +
      "</html>");
    
    out.close();

  }
}
