package com.squarehealth.iBlog.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.BlogPost;
import com.squarehealth.iBlog.services.AppUserService;

@Controller
@RequestMapping("/post")
public class PostController {
	
    private final AppUserService userService;

	@Autowired
    public PostController(AppUserService userService) {
		super();
		this.userService = userService;
	}


	@GetMapping(value = "/newPost")
    public String newPost(Principal principal,
                          Model model) {

        Optional<AppUsers> user = userService.findByUsername(principal.getName());

        if (user.isPresent()) {
            BlogPost post = new BlogPost();
            post.setUser(user.get());

            model.addAttribute("post", post);

            return "/postForm";

        } else {
            return "/error";
        }
    }

   
}
