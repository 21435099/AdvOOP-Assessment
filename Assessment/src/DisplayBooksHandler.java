import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

public class DisplayBooksHandler implements HttpHandler{
  
	public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200,0); //what does this mean?
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    BooksDAO booksdb = BooksDAO.getInstance();
    ArrayList<Book> allBooks = booksdb.getAllBooks();
   
    out.write(
	    "<html>" +
	    "<head> <title>Book Library</title> "+
	    "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
	    "</head>" +
	    "<body>" +
	    "<div class=\"container\">" +
	    "<h1> Books in Stock!</h1>"+
	    "<table class=\"table\">" +
	    "<thead>" +
	    "  <tr>" +
	    "    <th>ID</th>" +
	    "    <th>Title</th>" +
	    "    <th>Author</th>" +
	    "    <th>Year</th>" +
	    "    <th>Edition</th>" +
	    "    <th>Publisher</th>" +
	    "    <th>ISBN</th>" +
	    "    <th>Cover</th>" +
	    "    <th>Condition</th>" +
	    "    <th>Price</th>" +
	    "    <th>Notes</th>" +
	    "  </tr>" +
	    "</thead>" +
	    "<tbody>"
    );

    for (Book b : allBooks){
        out.write(
		    "  <tr>"       +
		    "    <td>"+ b.getBook_id() + "</td>" +
		    "    <td>"+ b.getTitle() + "</td>" +
		    "    <td>"+ b.getAuthor() + "</td>" +
		    "    <td>"+ b.getYear() + "</td>" +
		    "    <td>"+ b.getEdition() + "</td>" +
		    "    <td>"+ b.getPublisher() + "</td>" +
		    "    <td>"+ b.getIsbn() + "</td>" +
		    "    <td>"+ b.getCover() + "</td>" +
		    "    <td>"+ b.getCondition() + "</td>" +
		    "    <td>"+ b.getPrice() + "</td>" +
		    "    <td>"+ b.getNotes() + "</td>" +
		    "  </tr>" 
        );
    }
    
    out.write(
		"</tbody>" +
		"</table>" +
		"<a href=\"/\">Back to List </a>"+
		"</div>" +   
		       
		"</body>" +
		"</html>");
    out.close();

  }

}