import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

public class FormHandler implements HttpHandler{
	  public void handle(HttpExchange he) throws IOException {
		    String response = "Welcome to OOP";
		    he.sendResponseHeaders(200,0);
		    BufferedWriter out = new BufferedWriter(  
		        new OutputStreamWriter(he.getResponseBody() ));
		    
		    out.write(
		      "<html>" +
		      "<head> <title>My new Web Page</title> </head>" +
		      "<body>" +
		      "<form method=\"Post\" action=\"/formaction\">" +
		      "<label>Number 1</label>" +
		      "<input name=\"num1\"> <br \\> "+ 
		      "<label>Number 2</label>" +
		      "<input name=\"num2\">  "+ 
		      "<input type=\"submit\" value=\"Submit\">  "+
		      "</form>" + 
		      "<h1> Page2!</h1>"+
		      "<a href=\"/\"> click here </a" +   
		      "</body>" +
		      "</html>");
		    
		    out.close();

		  }

}