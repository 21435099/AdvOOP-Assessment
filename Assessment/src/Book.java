
public class Book {

	private int book_id; 
	private String title;
	private String author;
	private int year;
	private int edition;
	private String publisher;
	private String isbn;
	private String cover;
	private String condition;
	private int price;
	private String notes;
	
	public Book (String title, String author, int year, int edition, String publisher, String isbn, String cover, String condition, int price, String notes) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.edition = edition;
		this.publisher = publisher;
		this.isbn = isbn;
		this.cover = cover;
		this.condition = condition;
		this.price = price;
		this.notes = notes;
		//don't want to add the book_id in constructor for insertion because the database table auto increments the id
	}
	
	public Book (int book_id, String title, String author, int year, int edition, String publisher, String isbn, String cover, String condition, int price, String notes) {
		this.book_id = book_id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.edition = edition;
		this.publisher = publisher;
		this.isbn = isbn;
		this.cover = cover;
		this.condition = condition;
		this.price = price;
		this.notes = notes;
		//need a second constructor for when fetching a book from database to make a temp book object
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) { //unsure if we should have this - table is set to auto increment
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", author=" + author + ", year=" + year + ", edition="
				+ edition + ", publisher=" + publisher + ", isbn=" + isbn + ", cover=" + cover + ", condition="
				+ condition + ", price=" + price + ", notes=" + notes + "]";
	}
	
	
	
}
