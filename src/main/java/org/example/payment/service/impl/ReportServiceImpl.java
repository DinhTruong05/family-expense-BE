package org.example.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.payment.Dto.response.MonthlyReportResponse;
import org.example.payment.Entity.User;
import org.example.payment.enums.TransactionType;
import org.example.payment.exception.ResourceNotFoundException;
import org.example.payment.repository.TransactionRepository;
import org.example.payment.repository.UserRepository;
import org.example.payment.service.ReportService;
import org.example.payment.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public MonthlyReportResponse getMonthlyReport(int year, int month) {
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var transactions = transactionRepository.findByUserIdOrderByTransactionDateDesc(user.getId())
                .stream()
                .filter(t -> t.getTransactionDate().getYear() == year
                        && t.getTransactionDate().getMonthValue() == month)
                .toList();

        BigDecimal totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .map(t -> t.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return MonthlyReportResponse.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .balance(totalIncome.subtract(totalExpense))
                .build();
    }
}