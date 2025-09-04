package service;
import model.Book;
import model.Loan;
import model.LoanStatus;
import model.Member;
import repository.LoanRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(Book book, Member member, int loanDays) {
//        if (book.getAvailableCopies() <= 0) { // getters nodig van variabele AvailableCopies in Book
//            throw new IllegalStateException("Book unavailable");
//        }
        Date loanDate = new Date();
        Date dueDate = new Date(loanDate.getTime() + loanDays * 1000*60*60*24L);

        Loan loan = new Loan(loanDate, dueDate, book, member);
        //book.decrementAvailableCopies();
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
        //loan.getBook().incrementAvailableCopies();
    }

    public double checkFine (Loan loan) {
        return 0;//loanRepository.calculateFine(loan);
    }
}

/* CODE FOR BOOK CLASS

    private int availableCopies;
        +
    getter en setter van dit member

    public void decrementAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        } else {
            throw new IllegalStateException("No more copies available");
        }
    }

    public void incrementAvailableCopies() {
        availableCopies++;
    }

 */