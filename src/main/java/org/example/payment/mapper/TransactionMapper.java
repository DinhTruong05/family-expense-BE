package org.example.payment.mapper;

import org.example.payment.Dto.response.TransactionResponse;
import org.example.payment.Entity.Transaction;

public class TransactionMapper {

    public static TransactionResponse toResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .note(transaction.getNote())
                .type(transaction.getType().name())
                .transactionDate(transaction.getTransactionDate())
                .categoryId(transaction.getCategory().getId())
                .categoryName(transaction.getCategory().getName())
                .build();
    }
}