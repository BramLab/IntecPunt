package Main;

import model.Loan;
import model.LoanStatus;
import repository.BookRepository;
import service.BookService;
import model.Book;

import java.time.LocalDate;

public class LibrarySystemMain {
    public static void main(String[] args) {
        // Maak repositories
        BookRepository bookRepository = new BookRepository();

        //Maak Services
        BookService bookService = new BookService();


        // Voeg enkele boeken toe
        Book b1 = new Book("OCA java 8");
        Book b2 = new Book("Harry Poter");
        Book b3 = new Book("Malcom x");

        bookService.addBook(b1);
        bookService.addBook(b2);
        bookService.addBook(b3);

        Loan loan = new Loan(123456," ",b1);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(loan.getLoanDate().plusDays(14));
        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);


    }
}
