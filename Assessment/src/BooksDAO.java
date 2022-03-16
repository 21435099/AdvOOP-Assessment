import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;


public class BooksDAO {
	
	private static BooksDAO instance;
	
	private BooksDAO() {} 
	
	public static BooksDAO getInstance() {
		if (instance == null) 
			instance = new BooksDAO();
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
	
	public ArrayList<Book> getAllBooks(){
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM books;";
		ArrayList<Book> books = new ArrayList<>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
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
				books.add(new Book(book_id,title,author,year,edition,publisher,isbn,cover,condition,price,notes));
			}
		} catch(SQLException e) {
			System.out.println("get all books: "+e);
		} finally {
			if (result != null)
				closeResultSet(result);
			if (statement != null)
				closeStatement(statement);
			if (dbConnection != null)
				closeConnection(dbConnection);
		}
		return books;
	}
	
	public Book getBook(int id) {
		Book temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM books WHERE ID =" + id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			msg("DBQuery: " + query);
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
				closeResultSet(result);
			if (statement != null)
				closeStatement(statement);
			if (dbConnection != null)
				closeConnection(dbConnection);
		}
		return temp;
	}
	
	public Boolean deleteBook(int book_id){
		System.out.println("Deleting book");
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM books WHERE ID = " + book_id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			msg(query);
			result = statement.executeUpdate(query);
		} catch(SQLException e){ msg(e.getMessage()); }
		finally {
			if (statement != null)
				closeStatement(statement);
			if (dbConnection != null)
				closeConnection(dbConnection);
		}
		return (result == 1); 
	}
	
	public boolean insertBook(Book in){
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
					statement.executeUpdate(update);
					ok = true;
				} catch (SQLException e) {
					msg(e.getMessage());
				} finally {
					if (statement != null)
						closeStatement(statement);
					if (dbConnection != null)
						closeConnection(dbConnection);
				}
			return ok;
	}
	
	public Boolean updateBook(Book book) { 
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
			msg(query);
			statement.executeUpdate(query);
		} catch (SQLException e) {
			msg(e.getMessage());
			return false;
		} finally {
			if (statement != null)
				closeStatement(statement);
			if (dbConnection != null)
				closeConnection(dbConnection);
		}
		return true;
	}
	
	public Boolean usernameMatch(String username) {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		Boolean exists;
		String query = "SELECT * FROM users WHERE username LIKE '" + username + "';";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			result = statement.executeQuery(query);
			msg(result.getString("username"));
			exists =  (result.getString("username").equals(username));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			if (result != null)
				closeResultSet(result);
			if (statement != null)
				closeStatement(statement);
			if (dbConnection != null) 
				closeConnection(dbConnection);
		} return exists;
	}
	
	public Boolean passwordMatch(String username, String password) {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		Boolean match;
		String query = "SELECT * FROM users WHERE username LIKE '" + username + "';";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			msg(query);
			result = statement.executeQuery(query);
			String dbPassword = MD5.getMd5(result.getString("password"));
			match = (dbPassword.equals(password));
		} catch (SQLException e) {
			msg(e.getMessage());
			return false;
		} finally {
			if (result != null)
				closeResultSet(result);
			if (statement != null)
				closeStatement(statement);
			if (dbConnection != null) 
				closeConnection(dbConnection);
		} return match;
	}
	
	private void closeResultSet(ResultSet result) {
		try {
			result.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage() );
		}
	}
	
	private void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage() );
		}
	}
	
	private void closeConnection(Connection dbConnection) {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage() );
		}
	}

	static <T> void msg(T t) {
		System.out.println(t);
	}

}
