package Main;

import model.Member;
import model.Loan;
import model.Book;
import model.LoanStatus;
import repository.BookRepository;
import repository.MemberRepository;
import service.BookService;
import service.MemberService;

import java.time.LocalDate;
import java.util.Arrays;

public class LibrarySystemMain {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();

        BookService bookService = new BookService();


        System.out.println("\nbookService.getBooks: " + Arrays.toString(bookService.getBooks().toArray()));

        Book book = bookService.searchBook("De Hobbit", "John Ronald Reuel Tolkien", 2015);
        System.out.println("\n\uD83D\uDCD6 " + book);

        book = bookService.searchBook("9789067282451");
        System.out.println("\n\uD83D\uDD0Esearch with ISBN : " + book);

        System.out.println("\n\uD83D\uDCDAcountCopies: " + bookService.countCopies(book));


        MemberRepository memberRepository = new MemberRepository();
        MemberService memberService = new MemberService(memberRepository);
        Member member1 = new Member("Hajar",24, "hajar.benwahib@hotmail.com",1547854154L);
        memberService.addMember(member1);
        System.out.println("\uD83D\uDE4B " +member1);
        memberRepository.deleteById(1547854154L);
        System.out.println("\uD83D\uDCCB " +memberRepository);

        Loan loan = new Loan(123456L,member1,book);
        System.out.println("Status bij check: " + loan.getStatus());
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(loan.getLoanDate().plusDays(14));
        //loan.checkOvedue();
        System.out.println("Status na check: " + loan.getStatus());
        //loan.setStatus(LoanStatus.OVERDUE);
        System.out.println("\uD83D\uDCC5 " +loan);
        System.out.println(loan.getReturnDate());
        System.out.println("Status na teruggeven: " + loan.getStatus());



    }
}
