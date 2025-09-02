import model.Book;
import service.BookService;

import java.util.Arrays;
import java.util.Optional;

public class Controller {

    public static void main(String[] args) {
        BookService bookService = new BookService();

        System.out.println("\nbookService.getBooks: " + Arrays.toString(bookService.getBooks().toArray()));

        Book book = bookService.searchBook("De Hobbit", "John Ronald Reuel Tolkien", 2015);
        System.out.println("\nbookService.searchBook(title, author, yearPublished): " + book);

        book = bookService.searchBook("9789067282451");
        System.out.println("\nbookService.searchBook(isbn): " + book);

        System.out.println("\nbookService.countCopies: " + bookService.countCopies(book));

    }

}
