package model;
import java.util.Date;
import java.util.Objects;

public class Loan {

    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private LoanStatus status;
    private Book book;
    private Member member;

    public Loan(Date loanDate, Date dueDate, Book book, Member member) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.book = book;
        this.member = member;
        this.status = LoanStatus.ACTIVE;
    }

    public Date getLoanDate() {        return loanDate;    }
    public void setLoanDate(Date loanDate) {        this.loanDate = loanDate;    }
    public Date getDueDate() {        return dueDate;    }
    public void setDueDate(Date dueDate) {        this.dueDate = dueDate;    }
    public Date getReturnDate() {        return returnDate;    }
    public LoanStatus getStatus() {        return status;    }
    public void setStatus(LoanStatus status) {        this.status = status;    }
    public Book getBook() {        return book;    }
    public Member getMember() {        return member;    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        this.status = LoanStatus.RETURNED;
    }

    public boolean isOverdue() {
        if (status == LoanStatus.RETURNED && returnDate != null) {
            return returnDate.after(dueDate);
        }
        return new Date().after(dueDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(loanDate, loan.loanDate) &&
                Objects.equals(dueDate, loan.dueDate) &&
                Objects.equals(returnDate, loan.returnDate) &&
                Objects.equals(book, loan.book) &&
                Objects.equals(member, loan.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanDate, dueDate, returnDate, status, book, member);
    }

    @Override
    public String toString() {
        return "LOAN of book: "+book+", loaned by " +member+", on "+ loanDate +", and due back: "+dueDate;
    }
}
