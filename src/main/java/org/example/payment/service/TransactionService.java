package org.example.payment.service;

import org.example.payment.Dto.request.TransactionRequest;
import org.example.payment.Dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> getAll();
    TransactionResponse getById(Long id);
    TransactionResponse create(TransactionRequest request);
    TransactionResponse update(Long id, TransactionRequest request);
    void delete(Long id);
}