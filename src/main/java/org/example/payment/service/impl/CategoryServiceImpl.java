package org.example.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.payment.Dto.request.CategoryRequest;
import org.example.payment.Dto.response.CategoryResponse;
import org.example.payment.Entity.Category;
import org.example.payment.Entity.User;
import org.example.payment.exception.ResourceNotFoundException;
import org.example.payment.mapper.CategoryMapper;
import org.example.payment.repository.CategoryRepository;
import org.example.payment.repository.UserRepository;
import org.example.payment.service.CategoryService;
import org.example.payment.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<CategoryResponse> getAll() {
        User user = getCurrentUser();
        return categoryRepository.findByUserId(user.getId())
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        User user = getCurrentUser();

        Category category = Category.builder()
                .name(request.getName())
                .type(request.getType())
                .user(user)
                .build();

        categoryRepository.save(category);
        return CategoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        User user = getCurrentUser();

        Category category = categoryRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setName(request.getName());
        category.setType(request.getType());

        categoryRepository.save(category);
        return CategoryMapper.toResponse(category);
    }

    @Override
    public void delete(Long id) {
        User user = getCurrentUser();

        Category category = categoryRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        categoryRepository.delete(category);
    }

    private User getCurrentUser() {
        String email = SecurityUtil.getCurrentUsername();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}