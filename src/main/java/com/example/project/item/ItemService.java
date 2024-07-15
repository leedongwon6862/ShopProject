package com.example.project.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //상품 생성 로직
    public void addPost(String title, Integer price) {
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    //상품 목록 로직
    public List<Item> list() {
        return itemRepository.findAll();
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




}
