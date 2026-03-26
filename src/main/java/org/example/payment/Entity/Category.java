package org.example.payment.Entity;

import jakarta.persistence.*;
import org.example.payment.enums.TransactionType;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // INCOME / EXPENSE

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
