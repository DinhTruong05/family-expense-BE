package org.example.payment.mapper;

import org.example.payment.Dto.response.CategoryResponse;
import org.example.payment.Entity.Category;

public class CategoryMapper {

    public static CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType().name())
                .build();
    }
}