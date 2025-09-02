package model;
import java.util.Date;
import java.util.Objects;

public class Loan {

    private String loanID;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private Book book;
    private Member member;

    public Loan(String loanID, Date loanDate, Date dueDate) {
        this.loanID = loanID;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public String getLoanID() {        return loanID;    }
    public void setLoanID(String loanID) {        this.loanID = loanID;    }
    public Date getLoanDate() {        return loanDate;    }
    public void setLoanDate(Date loanDate) {        this.loanDate = loanDate;    }
    public Date getDueDate() {        return dueDate;    }
    public void setDueDate(Date dueDate) {        this.dueDate = dueDate;    }
    public Date getReturnDate() {        return returnDate;    }
    public void setReturnDate(Date returnDate) {        this.returnDate = returnDate;    }


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
