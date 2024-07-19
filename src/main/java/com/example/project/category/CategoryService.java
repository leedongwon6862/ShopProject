package com.example.project.category;

import com.example.project.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getCategoryList() {
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

    public void deleteCategory(Long id) {
         categoryRepository.deleteById(id);
    }
}
