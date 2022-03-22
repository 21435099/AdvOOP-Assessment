import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

public class Main {

	static final private int PORT = 8080;
	
	public static void main(String[] args) {
		
		msg("Hello world!");
		    
		HttpServer server = null;  
		
		try{
			server = HttpServer.create(new InetSocketAddress(PORT),0);
		} catch(IOException e) {
			msg(e.getMessage());
		};
		
		server.createContext("/", new LoginHandler() );
		server.createContext("/loginaction", new ProcessLoginHandler() );
		server.createContext("/displaybooks", new BooksHandler() );
	    server.createContext("/addbook", new AddBookHandler() );
	    server.createContext("/addbookaction", new ProcessAddBookHandler() );
	    server.createContext("/deletebookaction", new ProcessDeleteBookHandler() );
	    server.createContext("/editbook", new EditBookHandler() );
	    server.createContext("/editbookaction", new ProcessEditBookHandler() );
	    server.createContext("/success", new SuccessHandler() );
		server.createContext("/fail", new FailHandler() );
	    server.setExecutor(null);
	    server.start();
	    msg("The server is listening on port " + PORT);
	    
	    //Controller c = new Controller();
	    //c.testFunction();
	}
	
	static <T> void msg(T t) {
		System.out.println(t);
	}

}
