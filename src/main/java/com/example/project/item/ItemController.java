package com.example.project.item;

import com.example.project.category.Category;
import com.example.project.category.CategoryService;
import com.example.project.comment.Comment;
import com.example.project.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ItemController {


    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final CategoryService categoryService;

    // /product/list 접속 하면 상품 목록 페이지 보내 주기
    // 똑같은 페이지 만 보이면 x ->서버/DB 데이터를 HTML 로 넣을 수 있다 -> (타임 리프 이용해) -> how? Mode 파라 미터 추가 -> templates 에서  th:text="${작명}"
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, String title) {
        List<Category> categories = categoryService.getCategoryList();
        Pageable pageable = PageRequest.of(page, 5);
        Page<Item> items = itemService.list(pageable);
        model.addAttribute("items", items);
        model.addAttribute("categories", categories);
        return "list";
    }

    // 상품 추가 폼 , 페이지 보여 주기
    @GetMapping("/write")
    public String write(Model model, String title) {
        List<Category> categories = categoryService.getCategoryList();
        model.addAttribute("categories", categories);
        return "write";
    }

    //상품 추가 기능
    @PostMapping("/add")
    public String addPost(String title, Integer price, Long categoryId) {
        itemService.addPost(title, price, categoryId);
        return "redirect:/product/list";
    }

    //상품 상세 보기
    @GetMapping("/detail/{id}")
    //@PathVariable 을 하면 유저가 url 파라 미터에  입력한 값 알 수있다
    public String detail(@PathVariable("id") Long id, Model model) {

        List<Comment> comments = commentRepository.findAllByParentId(id);
        model.addAttribute("comments", comments);

        Optional<Item> result = itemService.detail(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "detail";
        } else {
            return "redirect:/product/list";
        }
    }

    //상품 수정 하기 폼 페이지 보여 주기
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<Item> result = itemService.itemById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit";
        } else {
            return "redirect:/product/list";
        }
    }

    //상품 수정 기능
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, String title, Integer price) {
        itemService.update(id, title, price);
        return "redirect:/product/list";
    }

    //상품 삭제 폼 과 페이지
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        Optional<Item> result = itemService.itemById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
        } else {
            return "redirect:/product/list";
        }

        return "delete";
    }


    //상품 삭제 기능
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        itemService.delete(id);
        return "redirect:/product/list";
    }


    //검색 기능
    @PostMapping("/search")
    public String postSearch(@RequestParam String searchKeyword, Model model) {
        List<Item> searchedItemList = itemRepository.findByTitleContains(searchKeyword);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("searchedItemList", searchedItemList);
        return "search";
    }

    @GetMapping("/items/category/{categoryId}")
    public String itemsByCategory(@PathVariable("categoryId") Long categoryId, Model model ) {
        Category category = categoryService.getCategoryById(categoryId);
        List<Category> categories =categoryService.getCategoryList();
        List<Item> itemByCategory = itemService.itemsByCategory(category);
        model.addAttribute("categories" , categories);
        model.addAttribute("itemByCategory", itemByCategory);
        return "itemsByCategory";
    }
}



