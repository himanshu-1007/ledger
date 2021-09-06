package dtos;

import lombok.*;

@Data
@AllArgsConstructor
public class LoanInputDto {
    private String bankName;
    private String borrowerName;
    private Double principal;
    private Integer term;
    //Add rate is not zero
    private Double rate;
}
