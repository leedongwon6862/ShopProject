package com.example.project.member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;


    //회원 가입 페이지
    @GetMapping("/register")
    public String register(){
        return"register";
    }

    //회원 가입 기능
    @PostMapping("/member")
    public String addMember(String username , String password , String displayName){
       memberService.create(username , password ,displayName);
        return "redirect:/product/list";
    }

    //로그인 페이지
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //마이 페이지
    @GetMapping("/my-page")
    //Authentication auth 은 로그인 한 정보 담고 있다.
    public String myPage(Authentication auth){
        // html 안에 로그인 된 유저 정보 넣기 -> 타임 리프 문법 쓰면 편함

        MyUserDetailsService.CustomUser result =(MyUserDetailsService.CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
        return "mypage";
    }
}
