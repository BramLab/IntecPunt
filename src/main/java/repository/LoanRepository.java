package repository;
import model.Loan;
import model.LoanStatus;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoanRepository {

    private final List<Loan> loans = new ArrayList<>();

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public List<Loan> getAllLoans() {
        return loans;
    }

    public List<Loan> getLoansByMemberId(String memberId) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan: loans) {
            if (loan.getMember().getMemberId().equals(memberId)) {
                result.add(loan);
            }
        }
        return result;
    }

    boolean isReturned(Loan loan) {
        return loan.getStatus() == LoanStatus.RETURNED;
    }

    public boolean isOverdue(Loan loan) {
        return loan.isOverdue();
    }

    public double calculateFine(Loan loan) {
        if (!loan.isOverdue()) return 0.0;
        long overdueDays = new Date().getTime() - loan.getDueDate().getTime() / (1000*60*60*24);
        return overdueDays * 0.20; // fine of 20 cents per day late
    }
}
