import model.Book;
import service.BookService;

import java.util.Arrays;

public class Controller {

    public static void main(String[] args) {
        BookService bookService = new BookService();

        System.out.println(Arrays.toString(bookService.getBooks().toArray()));
    }

}
