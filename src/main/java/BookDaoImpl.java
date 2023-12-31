import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao{

  private Connection connection;

  public BookDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void addBook(Book book) {
    //Try with resource, it will automatically close for you. its a try() with parenthesis
    try(PreparedStatement statement = connection.prepareStatement("INSERT INTO books (title, author, genre, price) VALUES(?, ?, ?, ?)");) {
      statement.setString(1, book.getTitle());
      statement.setString(2, book.getAuthor());
      statement.setString(3, book.getGenre());
      statement.setDouble(4, book.getPrice());
      statement.executeUpdate();
    }catch (SQLException e) {
      System.out.println(e.getMessage());
    }

  }

  @Override
  public void updateBook(Book book) {

  }

  @Override
  public void deleteBook(int bookId) {

  }

  @Override
  public Book getBookById(int bookId) {
    Book book = null;
    try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE bookId = ?")){
      statement.setInt(1, bookId);
      statement.executeQuery();

      try(ResultSet rs = statement.executeQuery()){
        while (rs.next()) {
          int id = rs.getInt("bookId");
          String title = rs.getString("title");
          String author = rs.getString("author");
          String genre = rs.getString("genre");
          double price = rs.getDouble("price");

          book = new Book(id, title, author, genre, price);
        }
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return book;
  }

  @Override
  public List<Book> getAllBooks() {
    List<Book> books = new ArrayList<>();
    try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE bookId = ?")){

      try(ResultSet rs = statement.executeQuery()){
        while (rs.next()) {
          int id = rs.getInt("bookId");
          String title = rs.getString("title");
          String author = rs.getString("author");
          String genre = rs.getString("genre");
          double price = rs.getDouble("price");

          Book book = new Book(id, title, author, genre, price);
          books.add(book);
        }
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return books;
  }
}
