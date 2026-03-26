package org.example.payment.Dto.respose;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private String note;
    private String type;
    private LocalDate transactionDate;
    private Long categoryId;
    private String categoryName;
}