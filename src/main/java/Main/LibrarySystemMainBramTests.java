package Main;

import model.Book;
import model.Loan;
import model.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;
import service.BookService;
import service.MemberService;

import java.util.Arrays;
import java.util.Date;

public class LibrarySystemMainBramTests {

    public static void main(String[] args) {
        // Maak repositories
        // The constructor of the BookRepository provides for some demo books.
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();

        Book book1 = new Book("OCA java 8");
        Member m1 = new Member("Bram", 64, "bram.labarque@gmail.com", "1234");
        Loan aLoan = new Loan(new Date(), new Date(2025, 10, 1), book1, m1);
        LoanRepository loanRepository = new LoanRepository();

        //Maak Services
        BookService bookService = new BookService(bookRepository);
        MemberService memberService = new MemberService(memberRepository);

        // Voeg enkele boeken toe zonder isbn
        bookService.addBook(new Book("OCA java 8"));
        bookService.addBook(new Book("Harry Poter"));
        bookService.addBook(new Book("Malcom x"));

        System.out.println("\n\uD83D\uDCCBbookService.getBooks: " + Arrays.toString(bookService.getBooks().toArray()));

        Book book = bookService.searchBook("De Hobbit", "John Ronald Reuel Tolkien", 2015);
        System.out.println("\nbookService.searchBook(title, author, yearPublished): " + book);

        book = bookService.searchBook("9789067282451");
        System.out.println("\nbookService.searchBook(isbn): " + book);

        System.out.println("\nbookService.countCopies: " + bookService.countCopies("9789022575512"));

//        Loan loan = new Loan(b1," ",b1);
//        loan.setLoanDate(LocalDate.now());
//        loan.setDueDate(loan.getLoanDate().plusDays(14));
//        loan.setReturnDate(LocalDate.now());
//        loan.setStatus(LoanStatus.RETURNED);


    }
}

