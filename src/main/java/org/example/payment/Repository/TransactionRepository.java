package org.example.payment.Repository;

import org.example.payment.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdOrderByTransactionDateDesc(Long userId);

    @Query("""
        SELECT t FROM Transaction t
        WHERE t.user.id = :userId
        AND YEAR(t.transactionDate) = :year
        AND MONTH(t.transactionDate) = :month
        ORDER BY t.transactionDate DESC
    """)
    List<Transaction> findByUserIdAndMonth(Long userId, int year, int month);
}