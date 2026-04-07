package org.example.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.payment.Dto.request.TransactionRequest;
import org.example.payment.Dto.response.TransactionResponse;
import org.example.payment.Entity.Category;
import org.example.payment.Entity.Transaction;
import org.example.payment.Entity.User;
import org.example.payment.exception.BadRequestException;
import org.example.payment.exception.ResourceNotFoundException;
import org.example.payment.mapper.TransactionMapper;
import org.example.payment.repository.CategoryRepository;
import org.example.payment.repository.TransactionRepository;
import org.example.payment.repository.UserRepository;
import org.example.payment.service.TransactionService;
import org.example.payment.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<TransactionResponse> getAll() {
        User user = getCurrentUser();

        return transactionRepository.findByUserIdOrderByTransactionDateDesc(user.getId())
                .stream()
                .map(TransactionMapper::toResponse)
                .toList();
    }

    @Override
    public TransactionResponse getById(Long id) {
        User user = getCurrentUser();

        Transaction transaction = transactionRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        return TransactionMapper.toResponse(transaction);
    }

    @Override
    public TransactionResponse create(TransactionRequest request) {
        User user = getCurrentUser();
        Category category = getValidCategory(user.getId(), request.getCategoryId(), request.getType());

        Transaction transaction = Transaction.builder()
                .amount(request.getAmount())
                .note(request.getNote())
                .type(request.getType())
                .transactionDate(request.getTransactionDate())
                .category(category)
                .user(user)
                .build();

        transactionRepository.save(transaction);
        return TransactionMapper.toResponse(transaction);
    }

    @Override
    public TransactionResponse update(Long id, TransactionRequest request) {
        User user = getCurrentUser();

        Transaction transaction = transactionRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        Category category = getValidCategory(user.getId(), request.getCategoryId(), request.getType());

        transaction.setAmount(request.getAmount());
        transaction.setNote(request.getNote());
        transaction.setType(request.getType());
        transaction.setTransactionDate(request.getTransactionDate());
        transaction.setCategory(category);

        transactionRepository.save(transaction);
        return TransactionMapper.toResponse(transaction);
    }

    @Override
    public void delete(Long id) {
        User user = getCurrentUser();

        Transaction transaction = transactionRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        transactionRepository.delete(transaction);
    }

    private User getCurrentUser() {
        String email = SecurityUtil.getCurrentUsername();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Category getValidCategory(Long userId, Long categoryId, org.example.payment.enums.TransactionType type) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (category.getType() != type) {
            throw new BadRequestException("Loại category không khớp với loại giao dịch");
        }

        return category;
    }
}