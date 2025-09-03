package model;
import java.util.Date;
import java.util.Objects;

public class Loan {

    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private boolean status;
    private Book book;
    private Member member;

    public Loan(Book book, Member member, Date loanDate, Date dueDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public Date getLoanDate() {        return loanDate;    }
    public void setLoanDate(Date loanDate) {        this.loanDate = loanDate;    }
    public Date getDueDate() {        return dueDate;    }
    public void setDueDate(Date dueDate) {        this.dueDate = dueDate;    }
    public Date getReturnDate() {        return returnDate;    }
    public void setReturnDate(Date returnDate) {        this.returnDate = returnDate;    }
    public boolean isStatus() {        return status;    }
    public void setStatus(boolean status) {        this.status = status;    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return status == loan.status && Objects.equals(loanDate, loan.loanDate) && Objects.equals(dueDate, loan.dueDate) && Objects.equals(returnDate, loan.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanDate, dueDate, returnDate, status);
    }

    @Override
    public String toString() {
        return "Loan {loanDate: "+loanDate+", dueDate: " + dueDate;
    }
}
