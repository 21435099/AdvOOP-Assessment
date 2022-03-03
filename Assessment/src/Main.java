import java.util.*;
import java.sql.SQLException;
import java.lang.reflect.*;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

public class Main {

//	static BookDAO bookdao = BookDAO.getInstance();	
//	static ArrayList<MenuFunction> mainMenu = new ArrayList<MenuFunction>();
//	static String lineBreaker = "-------------------------";
//	static int menuChoice;
//	static Scanner in;
	static final private int PORT = 8080;
	
	public static void main(String[] args) {
		
		  System.out.println("Hello world!");
		    
		    HttpServer server = null;  
			try{server = HttpServer.create(new InetSocketAddress(PORT),0);}
			catch(IOException e) {};
			server.createContext("/", new BootHandler() ); 
		    server.createContext("/form", new FormHandler() );
		    server.createContext("/formaction", new FormProcessHandler());
		    server.setExecutor(null);
		    server.start();
		    System.out.println("The server is listening on port " + PORT);
		  

		
		
		//Controller controller = new Controller();
		//controller.testFunction();
		
		
		
//		int id;	
//		addMenu();
//		
//		do {
//			msg(lineBreaker + "\nBook Inventory\nChoose from these options\n" + lineBreaker);
//			menuChoice = getMenuSelection();
//			switch (menuChoice) {
//			
//			case 1:
//				try{
//					ArrayList<Book> books = bookdao.getAllBooks(); //maybe do this as a stream?
//					for(Book b : books) {
//						msg(b.toString()); //works but i want to sort out the toString() to print nicer later on
//					}
//				}
//				catch(SQLException e) {}
//				break;
//				
//			case 2:
//				msg("Please enter the book ID you wish to search by");
//				int book_id = getInputIntFromUser();
//				try{msg(bookdao.getBook(book_id));}
//				catch(SQLException e) {}
//				break;
//				
//			case 3:
//				Book newbook = newBook();
//				try{bookdao.insertBook(newbook);}
//				catch(SQLException e ) {}
//				break;
//				
//			case 4:
//				msg("Please enter the Book ID");
//				id = getInputIntFromUser();
//				try{
//					ArrayList<Book> books = bookdao.getAllBooks(); //do this as a stream?
//					for(Book b : books) {
//						if(id == b.getBook_id()) {
//							msg("Please enter the new price");
//							int price = getInputIntFromUser();
//							b.setPrice(price);
//							bookdao.updateBook(b);
//						}
//					}
//				}
//				catch(SQLException e) {}
//				break;
//				
//			case 5:
//				msg("Please enter the Book ID");
//				id = getInputIntFromUser();
//				try {bookdao.deleteBook(id);} 
//				catch(SQLException e) {}
//				break;
//			}
//		}while(menuChoice != 6);
//		in.close();
	}
	
//	static <T> void msg(T t) {
//		System.out.println(t);
//	}
//	
//	static void addMenu() {
//		mainMenu.add(new MenuFunction(1, "Retrieve all books"));
//		mainMenu.add(new MenuFunction(2, "Search for a book"));
//		mainMenu.add(new MenuFunction(3, "Insert a new book into database"));
//		mainMenu.add(new MenuFunction(4, "Update existing book price details"));
//		mainMenu.add(new MenuFunction(5, "Delete a book from the database"));
//		mainMenu.add(new MenuFunction(6, "Exit"));
//	}
//	
//	static void printMenu() {
//		for(MenuFunction m : mainMenu) {
//			msg(m.toString());
//		}
//	}
//	
//	static String getInputStringFromUser() {
//		in = new Scanner(System.in);
//		String inputString = in.nextLine();
//		return inputString;
//	} 
//	
//	static boolean testStringToInt(String inputString) {
//		try {Integer.parseInt(inputString);}
//		catch (NumberFormatException e) {return false;}
//		return true;
//	}
//	
//	static int getInputIntFromUser() {
//		 boolean inputIsInt = false;
//		 int inputInt = 0;
//		 while(!inputIsInt) {
//			 String inputAsString = getInputStringFromUser();
//			 if(testStringToInt(inputAsString) == true) {
//				inputInt = Integer.parseInt(inputAsString);
//				inputIsInt = true;
//				break;
//			 } else
//				 msg("Please enter a number with no letters, symbols or spaces.");
//		 } return inputInt;
//	}
//	
//	static int getMenuSelection() {
//		boolean inputMenuSelectionExists = false;
//		int loopCount = 0;
//		int mainMenuSelection;
//		MenuFunction existingOption = null;		
//		while(!inputMenuSelectionExists){
//			if (loopCount >= 1) 
//				msg("\nInvalid menu choice, please try again:");
//			else  
//				printMenu();
//			 	msg(lineBreaker + "\nPlease enter your choice");
//			mainMenuSelection = getInputIntFromUser();
//			for (MenuFunction m : mainMenu) { 
//				if(mainMenuSelection == m.getMenuNum()) {
//					inputMenuSelectionExists = true;
//					existingOption = m;
//				 	break;
//			 	}
//			 } loopCount ++; 	
//		} return(existingOption.getMenuNum());
//	}
//	
//	static Book newBook() { //probably not the best way to go about it, was just playing around
//        Field[] fields = Book.class.getDeclaredFields();
//        String[] strings = new String[7];
//        int[] ints = new int[3];
//        int i = 0;
//        int j = 0;
//        for (Field field : fields) { //do this as a stream?
//            if(field.getName().toLowerCase().equals("book_id".toLowerCase())) { //ignore id, we can auto generate
//            	continue;
//            }
//            msg("Please enter the " + field.getName());
//        	if(field.getType() == Integer.TYPE) {
//            	ints[i] = getInputIntFromUser();
//            	i++;
//            } else {
//            	strings[j] = getInputStringFromUser();
//            	j++;
//            }
//        }
//        Book b = new Book(newBookId(), strings[0], strings[1], ints[0], ints[1], strings[2], strings[3], strings[4], strings[5], ints[2], strings[6]);
//        return b;
//    }
//	
//	static int newBookId() {
//		int i = 0;
//		try {
//			ArrayList<Book> books = bookdao.getAllBooks();
//			int j = books.size();
//			i = books.get(j-1).getBook_id() + 1;
//		} catch(SQLException e) {}
//		return i;
//	}
//	

}
