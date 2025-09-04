package Main;

import model.Loan;
import model.LoanStatus;
import model.Member;
import repository.BookRepository;
import service.BookService;
import model.Book;

import java.time.LocalDate;
import java.util.Date;

public class LibrarySystemMain {
    public static void main(String[] args) {
        // Maak repositories
        BookRepository bookRepository = new BookRepository();

        //Maak Services
        BookService bookService = new BookService(bookRepository);


        // Voeg enkele boeken toe
        Book b1 = new Book("OCA java 8");
        Book b2 = new Book("Harry Poter");
        Book b3 = new Book("Malcom x");

        bookService.addBook(b1);
        bookService.addBook(b2);
        bookService.addBook(b3);
        Member m1 = new Member("Bram", 64, "bram.labarque@gmail.com", "1234");
        Loan loan = new Loan(new Date(), new Date(2025,10,1),b1, m1);
        loan.setLoanDate(new Date());
        loan.setStatus(LoanStatus.RETURNED);

    }
}
