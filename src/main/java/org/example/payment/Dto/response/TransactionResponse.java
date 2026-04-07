package org.example.payment.Dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private String note;
    private String type;
    private LocalDate transactionDate;
    private Long categoryId;
    private String categoryName;
}