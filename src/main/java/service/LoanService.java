package service;
import model.Book;
import model.Loan;
import model.LoanStatus;
import model.Member;
import repository.BookRepository;
import repository.LoanRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LoanService {

    private final LoanRepository loanRepository;
    BookRepository bookRepository = new BookRepository();
    BookService bookService;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }

    public Loan createLoan(Book book, Member member, int loanDays) {
        //BookService bookService = new BookService(bookRepository);
        int availableCopies = bookService.countAvailableCopies(book.getIsbn());

        if (availableCopies <= 0) {
            throw new IllegalStateException("Book unavailable");
        }
        Date loanDate = new Date();
        Date dueDate = new Date(loanDate.getTime() + loanDays * 1000L*60*60*24);

        Loan loan = new Loan(loanDate, dueDate, book, member);

        loanRepository.addLoan(loan);
        return loan;
    }

    public List<Loan> findAll() {
        return loanRepository.getAllLoans();
    }

    public int countNonreturnedCopies(String isbn){
        return (int) loanRepository.getAllLoans().stream()
                .filter(l -> Objects.nonNull(l.getBook().getIsbn()))
                .filter(l -> l.getBook().getIsbn().equals(isbn) & l.getStatus() != LoanStatus.RETURNED)//or returnDate null?
                .count();
    }

    public boolean isBookInLoan(Long bookId){
        return loanRepository.getAllLoans().stream()
                .filter(l -> Objects.equals(l.getBook().getId(), bookId))
                .anyMatch(l -> l.getStatus() != LoanStatus.RETURNED);
    }

    public void returnBook(Loan loan) {
        loan.setReturnDate(new Date());
        loan.setStatus(LoanStatus.RETURNED);
    }

    public double checkFine (Loan loan) {
       if (!loan.isOverdue()) {
           System.out.println("No fine due, book returned in time");
           return 0.0;
       }

       Date realReturnDate = loan.getReturnDate() != null ? loan.getReturnDate() : new Date();
       long overDueTime = realReturnDate.getTime() - loan.getDueDate().getTime();
       long overDueDate = overDueTime / (1000*60/60/24);

       return overDueDate * 0.20;
    }
}
