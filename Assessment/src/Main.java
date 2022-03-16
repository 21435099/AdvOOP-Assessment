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
		
		server.createContext("/", new RootHandler() );
		server.createContext("/DisplayBooks", new DisplayBooksHandler() );
		server.createContext("/Login", new LoginHandler() );
		server.createContext("/LoginAction", new LoginProcessHandler() );
	    server.setExecutor(null);
	    server.start();
	    msg("The server is listening on port " + PORT);
	}
	
	static <T> void msg(T t) {
		System.out.println(t);
	}

}
