package com.example.project.comment;

import com.example.project.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;




    @PostMapping("/comment")
    public  String postComment(@RequestParam  String content ,@RequestParam Long parentId , Authentication auth ){
        CustomUser user =(CustomUser)auth.getPrincipal();
      Comment data = new Comment();
        data.setContent(content);
        data.setUsername(user.getUsername());
        data.setParentId(parentId);
        commentRepository.save(data);
        return "redirect:/product/list";
    }
}
