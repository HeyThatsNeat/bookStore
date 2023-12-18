import java.sql.Connection;
import java.util.Collections;
import java.util.Scanner;

public class BookStoreApp {

  private static BookDao bookDao = new BookDaoImpl(JDBConnection.getConnection());

  public static void main(String[] args) {////////////////START OF MAIN////////////////////////
//    Connection con = JDBConnection.getConnection();   USE THIS TO TEST IF IT WORKS
    Scanner input = new Scanner(System.in);

    int choice;
    do {
      System.out.println("""
              Welcome to BookStore
              Enter your Option please
              1- Add a new book
              2- Update a book
              3- Delete a book
              4- Display a book
              5- Display all books
              6- Exit the program
              """);

      choice = input.nextInt();
      input.nextLine();

      switch (choice) {
        case 1:
          addBook(input);
          break;
        case 2:
          updateBook(input);
          break;
        case 3:
          deleteBook(input);
          break;
        case 4:
          readBookById(input);
          break;
        case 5:
          displayBooks(input);
          break;
        case 6:
          System.out.println("Exiting the app");
          break;
        default:
          System.out.println("Wrong input");
      }

    }while (choice != 5);

  }////////////////////////////END OF MAIN////////////////////////////////////////////////////

  private static void displayBooks(Scanner input) {
    for (Book book : bookDao.getAllBooks()) {
      System.out.println(book);
    }
  }

  private static void readBookById(Scanner input) {
    System.out.println("Please enter book id ");
    int bookId = input.nextInt();
    Book book = bookDao.getBookById(bookId);
    if(book == null) {
      System.out.println("No book found ");
    }
    System.out.println(book);
  }

  private static void deleteBook(Scanner input) {
  }

  private static void updateBook(Scanner input) {
  }


  private static void addBook(Scanner input) {
    System.out.println("Please enter a book title");
    String title = input.nextLine();
    System.out.println("Please enter Book author");
    String author = input.nextLine();
    System.out.println("Please enter book genre");
    String genre = input.nextLine();
    System.out.println("Please enter Book price");
    double price = input.nextDouble();
    Book book = new Book(title, author, genre, price);
    bookDao.addBook(book);
    System.out.println("Book added successfully");
  }
}
