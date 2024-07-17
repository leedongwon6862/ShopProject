package com.example.project.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getCategoryList(String category) {
        return categoryRepository.findAll();
    }

    public Category updateCategory(String title, Long id) {
        Category newCategory = categoryRepository.findById(id).get();
        newCategory.setTitle(title);
        return categoryRepository.save(newCategory);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
