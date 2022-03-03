import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;


public class BookDAO {
	
	private static BookDAO instance;
	
	private BookDAO() {} 
	
	public static BookDAO getInstance() {
		if (instance == null) 
			instance = new BookDAO();
		return instance;
	}
	//above 3 functions are for lazy instantiation of singleton
	//only allow for generations of 1 BookDAO object

	private static Connection getDBConnection() { 
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		try {
			String dbURL = "jdbc:sqlite:mybooks2.db";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return dbConnection;
	}
	
	public ArrayList<Book> getAllBooks() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM books;";
		ArrayList<Book> books = new ArrayList<>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {
				int book_id = result.getInt("ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				int price = result.getInt("Price");
				String notes = result.getString("Notes");
				books.add(new Book(book_id,title,author,year,edition,publisher,isbn,cover,condition,price,notes));
			}
		} catch(SQLException e) {
			System.out.println("get all books: "+e);
		} finally {
			if (result != null)
				result.close();//SQLException thrown
			if (statement != null)
				statement.close();//SQLException thrown
			if (dbConnection != null)
				dbConnection.close();//SQLException thrown - ensure closure on all methods using it
		}
		return books;
	}
	
	public Book getBook(int id) throws SQLException {
		Book temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM books WHERE ID =" + id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			msg("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);
			while (result.next()) {
				int book_id = result.getInt("ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				int price = result.getInt("Price");
				String notes = result.getString("Notes");
				temp = new Book(book_id,title,author,year,edition,publisher,isbn,cover,condition,price,notes);
			}
		}catch(SQLException e) {
		} finally {
			if (result != null)
				result.close();//SQL Exception thrown
			if (statement != null)
				statement.close();//SQL Exception thrown
			if (dbConnection != null) 
				dbConnection.close();//SQL Exception thrown
		}
		return temp;
	}
	
	public Boolean deleteBook(int book_id) throws SQLException {
		System.out.println("Deleting book");
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM books WHERE ID = " + book_id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			result = statement.executeUpdate(query);
		} catch(SQLException e) {
			//do anything?
		}
		finally {
			if (statement != null) 
				statement.close();
			if (dbConnection != null)
				dbConnection.close();
		}
		if (result == 1) 
			return true;
		else 
			return false;
	}
	
	public boolean insertBook(Book in) throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String update = "INSERT INTO books (ID, Title, Author, Year, Edition, Publisher, ISBN, Cover, Condition, Price, Notes) " + 
		"VALUES ("+
				in.getBook_id()+",'"+
				in.getTitle()+"','"+
				in.getAuthor()+"',"+
				in.getYear()+","+
				in.getEdition()+",'"+
				in.getPublisher()+"','"+
				in.getIsbn()+"','"+
				in.getCover()+"','"+
				in.getCondition()+"',"+
				in.getPrice()+",'"+
				in.getNotes()+
				"');";
		boolean ok = false;
			try {
					dbConnection = getDBConnection();
					statement = dbConnection.createStatement();
					System.out.println(update);
					// execute SQL query
					statement.executeUpdate(update);
					ok = true;
				} catch (SQLException e) {
					msg(e.getMessage());
				} finally {
					if (statement != null) 
						statement.close();
					if (dbConnection != null) 
						dbConnection.close();
				}
			return ok;
	}
	
	public Boolean updateBook(Book book) throws SQLException { //probably only need to set price?
		Connection dbConnection = null;
		Statement statement = null;
		String query = "UPDATE books " +
				"SET ID = '" + book.getBook_id() + "'," + 
				"Title = '"+ book.getTitle() + "'," + 
				"Author= '" + book.getAuthor() + "'," + 
				"Year= '" + book.getYear() + "'," + 
				"Edition= '" + book.getEdition() + "'," +
				"Publisher= '" + book.getPublisher() + "'," +
				"ISBN= '" + book.getIsbn() + "'," +
				"Cover= '" + book.getCover() + "'," +
				"Condition= '" + book.getCondition() + "'," +
				"Price= '" + book.getPrice() + "'," +
				"Notes= '" + book.getNotes() + "'" +
				" WHERE ID = " + book.getBook_id()
				+ ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL update
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			if (statement != null) 
				statement.close();
			if (dbConnection != null) 
				dbConnection.close();
		}
		return true;
	}

	
	static <T> void msg(T t) {
		System.out.println(t);
	}

}
