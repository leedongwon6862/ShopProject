package com.example.project.item;

import com.example.project.category.Category;
import com.example.project.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    //상품 생성 로직
    public void addPost(String title, Integer price ,Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setCategory(category);
        itemRepository.save(item);
    }

    //상품 목록 로직
    public Page<Item> list(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    //상품 상세 보기
    public Optional<Item> detail(Long id) {
        return itemRepository.findById(id);
    }

    //상품 수정 하기
    public Item update(Long id ,String title , Integer price) {
        Item newItem = itemRepository.findById(id).get();
        newItem.setTitle(title);
        newItem.setPrice(price);
        return itemRepository.save(newItem);
    }

    //상품 아이디 로 찾기
    public Optional<Item> itemById(Long id) {
       return  itemRepository.findById(id);
    }

    //상품 삭제
    public void delete(Long id) {
         itemRepository.deleteById(id);
    }


    public List<Item> itemsByCategory(Category category) {
        return itemRepository.findByCategory(category);
    }
}
