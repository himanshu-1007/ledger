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
        //return String.format("%s %s %s %s",bankName,borrowerName,amountPaid,eMILeft);
        //Note: Adding rounding of amount fix the https://codu.ai/ output match issue
        return String.format("%s %s %.0f %s",bankName,borrowerName,amountPaid,eMILeft);
    }
    
}
