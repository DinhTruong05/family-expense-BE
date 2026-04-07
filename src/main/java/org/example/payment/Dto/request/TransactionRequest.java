package org.example.payment.Dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.payment.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionRequest {

    @NotNull
    private BigDecimal amount;

    private String note;

    @NotNull
    private TransactionType type;

    @NotNull
    private LocalDate transactionDate;

    @NotNull
    private Long categoryId;
}