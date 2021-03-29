package org.booker.controllers;

import org.booker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final BookService bookService;

    @Autowired
    public IndexController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/book/{isbn}")
    public String bookPage(@PathVariable String isbn, Model model) {
        model.addAttribute("isbn", isbn);
        return "book";
    }

    @GetMapping("/favourites")
    public String favouritesPage(Authentication authentication, Model model) {
        model.addAttribute("books", bookService.getFavouritesByUsername(authentication.getName()));
        return "favorites";
    }

}
