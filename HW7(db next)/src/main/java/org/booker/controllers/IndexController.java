package org.booker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/book/{isbn}")
    public String bookPage(@PathVariable String isbn, Model model) {
        model.addAttribute("isbn", isbn);
        return "book";
    }

}
