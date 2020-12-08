package com.myapp.web.springboot.web;

import com.myapp.web.springboot.config.auth.LoginUser;
import com.myapp.web.springboot.config.auth.dto.SessionUser;
import com.myapp.web.springboot.service.PostsService;
import com.myapp.web.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        // CustomOAuth2UserService에서 로그인 성공시 세션에 SessionUser 저장하게 설정해둠
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // LoginUser 어노테이션으로 대체됨
        // 세션에 있으면 user 값을 가져옴
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        // 없으면 그냥 진행
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
