package com.example.project.cartItem;

import com.example.project.item.Item;
import com.example.project.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    // 모든 장바구니 항목을 조회하는 메소드
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    // 장바구니에 아이템을 추가하는 메소드
    public void addToCart(Long itemId, int quantity) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    // 장바구니에서 특정 항목을 제거하는 메소드
    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}