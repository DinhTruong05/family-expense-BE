package org.example.payment.Dto.request;

import org.example.payment.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionCreateRequest {
    private BigDecimal amount;
    private String note;
    private TransactionType type;
    private LocalDate transactionDate;
    private Long categoryId;
}
