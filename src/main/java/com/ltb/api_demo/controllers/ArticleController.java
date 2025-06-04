package com.ltb.api_demo.controllers;

import com.ltb.api_demo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articleList", articleService.getMostPopular());
        return "index";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @PostMapping("/search")
    public String searchResults(String searchText, Model model) {
        model.addAttribute("articleList", articleService.getSearchResults(searchText));
        return "search-results";
    }

}
