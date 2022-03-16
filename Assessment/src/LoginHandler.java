import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;



public class LoginHandler implements HttpHandler{

	
	@Override
	public void handle(HttpExchange he) throws IOException {
		
		String usernameError = "";
		String passwordError = ""; 
		
		if(he.getRequestURI().toString().equals("/Login?err1"))
			usernameError = "Username cannot be blank, please try again";
		
		if(he.getRequestURI().toString().equals("/Login?err2"))
			usernameError = "Username is not recognised, please try again";
		
		if(he.getRequestURI().toString().equals("/Login?err3"))
			passwordError = "Password is incorrect, please try again";

		he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter(  
	        new OutputStreamWriter(he.getResponseBody() ));
	    
    	out.write(
	    	      "<html>" +
	    	      "<head> <title>My new Web Page</title> </head>" +
	    	      "<body>" +
	    	      "<form method=\"Post\" action=\"/LoginAction\">" +
	    	      "<h1>Admin Login</h1>"+
	    	      "<label>Username: </label>" +
	    	      "<input name=\"username\"> " + usernameError + "<br> "+ 
	    	      "<label>Password: </label>" +
	    	      "<input name=\"password\"> " + passwordError +  "<br>"+ 
	    	      "<input type=\"submit\" value=\"Submit\">  "+
	    	      "</form>" + 
	    	      "<h1> Return to main page</h1>"+
	    	      "<a href=\"/\"> click here </a" +   
	    	      "</body>" +
	    	      "</html>"
	    	      );
	    out.close();
	}

}
