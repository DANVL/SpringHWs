package org.booker.controllers;

import org.booker.models.Book;
import org.booker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Book> search(@PathVariable(required = false) String title){
        return bookService.searchByNameAndISBN(title == null ? "" : title);
    }

    @GetMapping
    public List<Book> getAll(){
        return bookService.get();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.add(book);
    }
}
