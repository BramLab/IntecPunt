//package service;
//import model.Book;
//import model.Loan;
//import model.LoanStatus;
//import model.Member;
//import repository.LoanRepository;
//import java.util.Date;
//
//public class LoanService {
//
//    private final LoanRepository loanRepository;
//
//    public LoanService(LoanRepository loanRepository) {
//        this.loanRepository = loanRepository;
//    }
//
//    public Loan createLoan(Book book, Member member, int loanDays) {
//        if (book.getAvailableCopies() <= 0) { // getters nodig van variabele AvailableCopies in Book
//            throw new IllegalStateException("Book unavailable");
//        }
//        Date loanDate = new Date();
//        Date dueDate = new Date(loanDate.getTime() + loanDays * 1000*60*60*24L);
//
//        Loan loan = new Loan(loanDate, dueDate, book, member);
//        //book.decrementAvailableCopies();
//        loanRepository.addLoan(loan);
//        return loan;
//    }
//
//    public void returnBook(Loan loan) {
//        loan.setReturnDate(new Date());
//        loan.setStatus(LoanStatus.RETURNED);
//        //loan.getBook().incrementAvailableCopies();
//    }
//
//    public double checkFine (Loan loan) {
//        return loanRepository.calculateFine(loan);
//    }
//}
//
///* CODE FOR BOOK CLASS
//
//    private int availableCopies;
//        +
//    getter en setter van dit member
//
//    public void decrementAvailableCopies() {
//        if (availableCopies > 0) {
//            availableCopies--;
//        } else {
//            throw new IllegalStateException("No more copies available");
//        }
//    }
//
//    public void incrementAvailableCopies() {
//        availableCopies++;
//    }
//
// */