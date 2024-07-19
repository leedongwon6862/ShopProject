package com.example.project.category;

import com.example.project.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    //카테고리 생성 페이지

    @GetMapping("/create")
    public String create() {
        return "categoryForm";
    }

    //카테고리 생성 기능
    @PostMapping("/create")
    public String create(String title) {

        Category category = new Category();
        category.setTitle(title);

        categoryService.save(category);
        return "redirect:/product/list";

    }

    //카테고리 목록 보여 주기
    @GetMapping("/list")
    public String categoryList(String title, Model model){
       List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "categoryList";
    }

    //카테고리 수정 폼 보여 주기
    @GetMapping("/update/{id}")
    public String categoryUpdate(Model model, @PathVariable("id")Long id){
       Category category =categoryService.getCategoryById(id);
        model.addAttribute("category" , category);
        return "categoryUpdate";
    }


    //카테고리 수정 하기
    @PostMapping("/update/{id}")
    public String categoryUpdate(String title , @PathVariable("id")Long id){
        categoryService.updateCategory(title ,id);
        return "redirect:/category/list";
    }

    //카테고리 삭제 폼 보여 주기
    @GetMapping("/delete/{id}")
    public String categoryDelete(@PathVariable("id")Long id,Model model){
       Category category = categoryService.getCategoryById(id);
        model.addAttribute("category" ,category);
        return "categoryDelete";
    }

    //카테고리 삭제 하기
    @PostMapping("/delete/{id}")
    public String categoryDelete(@PathVariable("id")Long id){
        categoryService.deleteCategory(id);
        return "redirect:/category/list";
    }



    }






