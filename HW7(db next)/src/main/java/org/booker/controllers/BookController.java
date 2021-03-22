package org.booker.controllers;

import org.booker.models.Book;
import org.booker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{title}")
    public Page<Book> search(@PathVariable(required = false) String title) {
        return bookService.get(title == null ? "" : title, PageRequest.of(0,10));
    }

    @GetMapping
    public Page<Book> getAll() {

        return bookService.get("", PageRequest.of(0,10));
    }

    @GetMapping("/isbn/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.getByIsbn(isbn);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.add(book);
    }
}
