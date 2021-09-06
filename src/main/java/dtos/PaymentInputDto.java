package dtos;

import lombok.*;

@Data
@AllArgsConstructor
public class PaymentInputDto {
    private String bankName;
    private String borrowerName;
    private Double lumpSum;
    private Integer eMINumber;

}
