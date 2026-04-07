package org.example.payment.Dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.payment.enums.TransactionType;

@Data
public class CategoryRequest {

    @NotBlank
    private String name;

    @NotNull
    private TransactionType type;
}