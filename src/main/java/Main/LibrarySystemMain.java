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

        // Maak repositories
        BookRepository bookRepository = new BookRepository();


        //Maak Services
        BookService bookService = new BookService();
//
//
//        System.out.println("\nbookService.getBooks: " + Arrays.toString(bookService.getBooks().toArray()));
//
//        Book book = bookService.searchBook("De Hobbit", "John Ronald Reuel Tolkien", 2015);
//        System.out.println("\nbookService.searchBook(title, author, yearPublished): " + book);
//
//        book = bookService.searchBook("9789067282451");
//        System.out.println("\nbookService.searchBook(isbn): " + book);
//
//        System.out.println("\nbookService.countCopies: " + bookService.countCopies(book));




        // Voeg enkele boeken toe
        Book b1 = new Book("OCA java 8");
        Book b2 = new Book("Harry Poter");
        Book b3 = new Book("Malcom x");

        bookService.addBook(b1);
        bookService.addBook(b2);
        bookService.addBook(b3);

        Member member1 = new Member("Hajar",24, "hajar.benwahib",1547854154L);
        Loan loan = new Loan(123456L,member1,b1);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(loan.getLoanDate());
        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);



    }
}
