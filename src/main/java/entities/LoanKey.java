package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanKey {
    private Bank bank;
    private Borrower borrower;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanKey loanKey = (LoanKey) o;

        if (!Objects.equals(bank.getName(), loanKey.bank.getName())) return false;
        return Objects.equals(borrower.getName(), loanKey.getBorrower().getName());
    }

    @Override
    public int hashCode() {
        int result = bank.getName() != null ? bank.getName().hashCode() : 0;
        result = 31 * result + (borrower.getName() != null ? borrower.getName().hashCode() : 0);
        return result;
    }
}
