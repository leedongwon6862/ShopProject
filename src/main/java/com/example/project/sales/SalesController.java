package com.example.project.sales;

import com.example.project.member.CustomUser;
import com.example.project.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesRepository salesRepository;

    @PostMapping("/order")
    public String postOrder(@RequestParam String title,
                            @RequestParam Integer price,
                            @RequestParam  Long count,
                            Authentication auth) {

        Sales sales = new Sales();
        sales.setCount(count);
        sales.setPrice(price);
        sales.setItemName(title);
        CustomUser user =(CustomUser) auth.getPrincipal();
        var member = new Member();
        member.setId(1L);
        sales.setMember(member);
        sales.setCreateDate(LocalDateTime.now());
        salesRepository.save(sales);
        return  "redirect:/product/list";
    }

    @GetMapping("/order/all")
    public String getOrderAll(){
        List<Sales> result = salesRepository.findAll();
        System.out.println(result.get(0));
        return "list";
    }

}
