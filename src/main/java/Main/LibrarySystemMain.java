package Main;

import model.Member;
import model.Loan;
import model.Book;
import model.LoanStatus;
import repository.BookRepository;
import service.BookService;

import java.time.LocalDate;
import java.util.Arrays;

public class LibrarySystemMain {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();

        BookService bookService = new BookService();


        System.out.println("\nbookService.getBooks: " + Arrays.toString(bookService.getBooks().toArray()));

        Book book = bookService.searchBook("De Hobbit", "John Ronald Reuel Tolkien", 2015);
        System.out.println("\nbookService.searchBook(title, author, yearPublished): " + book);

        book = bookService.searchBook("9789067282451");
        System.out.println("\nbookService.searchBook(isbn): " + book);

        System.out.println("\nbookService.countCopies: " + bookService.countCopies(book));



        Member member1 = new Member("Hajar",24, "hajar.benwahib",1547854154L);
        Loan loan = new Loan(123456L,member1,book);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(loan.getLoanDate().plusDays(14));
        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);
        System.out.println(loan);



    }
}
