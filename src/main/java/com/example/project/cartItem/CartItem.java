package com.example.project.cartItem;

import com.example.project.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;


    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;



}
