package org.booker.controllers;

import org.booker.models.Book;
import org.booker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.add(book);
        return "redirect:/";
    }

    @ModelAttribute("books")
    public List<Book> books() {
        return bookService.get();
    }

}
