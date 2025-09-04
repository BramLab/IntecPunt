package Main;

import model.Book;
import model.Loan;
import model.LoanStatus;
import model.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;
import service.BookService;
import service.LoanService;
import service.MemberService;

import java.util.Arrays;
import java.util.Date;

public class LibrarySystemMain {

    public static void main(String[] args) {
        // Make repositories
        // The constructor of the BookRepository provides for some demo books.
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        LoanRepository loanRepository = new LoanRepository();

        // Make Services
        BookService bookService = new BookService(bookRepository);
        MemberService memberService = new MemberService(memberRepository);
        LoanService loanService = new LoanService(loanRepository);
        loanService.setBookService(bookService);
        bookService.setLoanService(loanService);

        bookService.addBook(new Book("De Hobbit", "John Ronald Reuel Tolkien", 2015, "9789022575512"));
        bookService.addBook(new Book("De Hobbit", "John Ronald Reuel Tolkien", 2015, "9789022575512"));
        bookService.addBook(new Book("De Hobbit", "John Ronald Reuel Tolkien", 2015, "9789022575512"));
        bookService.addBook(new Book("Heer Belisarius", "Robert Graves", 2010, "9789067282451"));

        System.out.println("\n\uD83D\uDCCBbookService.getBooks: " + Arrays.toString(bookService.getBooks().toArray()));


        Book book = bookService.searchBook("De Hobbit", "John Ronald Reuel Tolkien", 2015);
        System.out.println("\nbookService.searchBook(title, author, yearPublished): " + book);

        book = bookService.searchBook("9789067282451");
        System.out.println("\nbookService.searchBook(isbn): " + book);

        System.out.println("\nbookService.countAllCopies: " + bookService.countAllCopies("9789022575512"));

        System.out.println("\nbookService.countAllCopies(title, author, pubyear) : " + bookService.countAllCopies("De Hobbit", "John Ronald Reuel Tolkien", 2015));
        System.out.println("\nbookService.countAllCopies(title, author, pubyear) : " + bookService.countAllCopies("a habbit", "uuig", 2015));
        System.out.println("\nbookService.countAllCopies(title, author, pubyear) : " + bookService.countAllCopies("Malcom x", null, 0));

        // Test members:
        System.out.println("\nTest members:");
        System.out.println("\n\uD83D\uDE4B\u200BTest members:");
        Member m1 = new Member("Bram", 64, "bram.labarque@gmail.com", 1234L);
        Member m2 = new Member("Lucie",22,"lucie.monarchie@gmail.com",2468L);
        Member m3 = new Member("Lola",18,"lola.cawlo@gmail.com",4689L);
        Member m4 = new Member("Charlie",45,"charlie.chapline@gmail.com",7649L);
        memberService.addMember(m1);
        memberService.addMember(m2);
        memberService.addMember(m3);
        memberService.addMember(m4);

//        System.out.println(Arrays.toString(memberService.listMembers().toArray()));
        System.out.println(memberRepository.findAll());
        memberService.removeMember(1234L);
        System.out.println(" new member list after delete: " + memberRepository.findAll());


        // Test loans/books:
        System.out.println("\n\u200B\uD83E\uDDFE\u200BTest loans/books:");
        Book bookHobbit = bookService.searchBook("De Hobbit", "John Ronald Reuel Tolkien", 2015);
        loanService.createLoan(bookHobbit, m1, 10);

        System.out.println("loanService.findAll(): " + Arrays.toString(loanService.findAll().toArray()));
        System.out.println("bookService.countAllCopies of the hobbit: " + bookService.countAllCopies(bookHobbit.getIsbn()));
        System.out.println("loanService.countNonreturnedCopies hobbit: " + loanService.countNonreturnedCopies(bookHobbit.getIsbn()));
        System.out.println("bookService.countAvailableCopies hobbit: " + bookService.countAvailableCopies(bookHobbit.getIsbn()));

        System.out.println("bookService.countAllCopies of nonexisting: " + bookService.countAllCopies("123"));

        // Test softDelete (depends on isBookInLoan), so test that also:
        System.out.println("\nTest softDelete. Should not be possible if in loan & not RETURNED yet.");

        System.out.println("bookService.searchBook(5L): " + bookService.searchBook(5L));
        System.out.println("isBookInLoan 5L: " + loanService.isBookInLoan(5L));

        System.out.println("bookService.searchBook(6L): " + bookService.searchBook(6L));
        System.out.println("isBookInLoan 6L: " + loanService.isBookInLoan(6L));

        System.out.println("\nNow softdelete 5L - which is in loan: ");
        bookService.softDeleteBook(5L);
        System.out.println("bookService.searchBook(5L): " + bookService.searchBook(5L));
        System.out.println("isBookInLoan 5L: " + loanService.isBookInLoan(5L));
        System.out.println("softdelete 5L not succeeded, as expected as in loan!");

        System.out.println("\nNow softdelete 6L, which is not in loan: ");
        bookService.softDeleteBook(6L);
        System.out.println("bookService.searchBook(6L): " + bookService.searchBook(6L));
        System.out.println("isBookInLoan 6L: " + loanService.isBookInLoan(6L));
        System.out.println("softdelete 6L HAS succeeded!");


        System.out.println("\n\nbookService.getBooks: " + bookService.getBooks());
        System.out.println("\nbookService.getBooksIncludingSoftDeleted: " + bookService.getBooksIncludingSoftDeleted());

        System.out.println("\nloanService.findAll(): " + Arrays.toString(loanService.findAll().toArray()));
        System.out.println("bookService.countAllCopies of the hobbit: " + bookService.countAllCopies(bookHobbit.getIsbn()));
        System.out.println("loanService.countNonreturnedCopies hobbit: " + loanService.countNonreturnedCopies(bookHobbit.getIsbn()));
        System.out.println("bookService.countAvailableCopies hobbit: " + bookService.countAvailableCopies(bookHobbit.getIsbn()));


        System.out.println("LoanService tests ");

        Book b1 = bookService.searchBook(4L);
        //Member m1 = new Member("Bram", 64, "bram.labarque@gmail.com", 5L);
        Loan loan01 = new Loan(new Date(), new Date(2025,10,1),b1, m1);
        loan01.setLoanDate(new Date());
        loan01.setStatus(LoanStatus.RETURNED);
        //LoanService loanService01 = new LoanService(new LoanRepository());

        loanService.createLoan(b1,m1, 14);
        loanService.returnBook(loan01);
        double fine = loanService.checkFine(loan01);
        System.out.println("the fine is €"+fine+".");


    }
}

