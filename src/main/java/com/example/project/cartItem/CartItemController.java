package com.example.project.cartItem;

import com.example.project.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;
    private final ItemService itemService;


    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartItemService.getAllCartItems());
        return "cart";
    }

    @GetMapping("/cart/add")
    public String showAddToCartForm(@RequestParam Long itemId, Model model) {
        itemService.itemById(itemId).ifPresent(item -> model.addAttribute("item", item));
        return "addToCart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long itemId, @RequestParam int quantity) {
        cartItemService.addToCart(itemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long cartItemId) {
        cartItemService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }
}
