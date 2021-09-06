package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceOutputDto {
    private String bankName;
    private String borrowerName;
    private Double amountPaid;
    private Integer eMILeft;

    @Override
    public String toString() {
        return String.format("%s %s %s %s",bankName,borrowerName,amountPaid,eMILeft);
    }


}
