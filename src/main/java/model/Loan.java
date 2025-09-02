package model;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Loan {

    private Long loanID;
    private LocalDate loanDate;   // datum boek wordt ontleend
    private LocalDate dueDate;        // vervaldatum
    private LocalDate returnDate;    //datum terugbrengen
    private LoanStatus status;
    private Member member;
    private Book book;
    //private Member member;

    public Loan(Long loanID, Member member, Book book) {
        this.loanID = loanID;
        this.member = member;
        this.book= book;
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(14);
        this.status= LoanStatus.ACTIVE;
    }

    public Long getLoanID() {        return loanID;    }
    public void setLoanID(Long loanID) {        this.loanID = loanID;    }
    public LocalDate getLoanDate() {        return loanDate;    }
    public void setLoanDate(LocalDate loanDate) {        this.loanDate = loanDate;    }
    public LocalDate getDueDate() {        return dueDate;    }
    public void setDueDate(LocalDate dueDate) {        this.dueDate = dueDate;    }
    public LocalDate getReturnDate() {        return returnDate;    }
    public void setReturnDate(LocalDate returnDate) {        this.returnDate = returnDate;    }
    public LoanStatus getStatus() {  return status;     }
    public void setStatus(LoanStatus status) {  this.status = status;    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(loanID, loan.loanID) && Objects.equals(loanDate, loan.loanDate) && Objects.equals(dueDate, loan.dueDate) && Objects.equals(returnDate, loan.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanID, loanDate, dueDate, returnDate);
    }

    @Override
    public String toString() {
        return "Loan {loanID: "+loanID+", loanDate: "+loanDate+", dueDate: " + dueDate +
                '}';
    }
}
