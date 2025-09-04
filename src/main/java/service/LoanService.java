package service;
import model.Book;
import model.Loan;
import model.LoanStatus;
import model.Member;
import repository.LoanRepository;
import java.util.Date;

public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(Book book, Member member, int loanDays) {
        BookService bookService = new BookService();
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

    public void returnBook(Loan loan) {
        loan.setReturnDate(new Date());
        loan.setStatus(LoanStatus.RETURNED);
    }

    public double checkFine (Loan loan) {
       if (!loan.isOverdue()) return 0.0;

       Date realReturnDate = loan.getReturnDate() != null ? loan.getReturnDate() : new Date();
       long overDueTime = realReturnDate.getTime() - loan.getDueDate().getTime();
       long overDueDate = overDueTime / (1000*60/60/24);

       return overDueDate * 0.20;
    }
}
