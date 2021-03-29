package org.booker.controllers;

import org.booker.models.Book;
import org.booker.models.User;
import org.booker.service.BookService;
import org.booker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
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

    @GetMapping("/favorites")
    public List<Book> getFavorites(Authentication authentication){
        return bookService.getFavouritesByUsername(authentication.getName());
    }

    @PostMapping("/favorites/{isbn}")
    public ResponseEntity<?> addFavorites(@PathVariable String isbn, Authentication authentication){
        bookService.addToFavourite(isbn, authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/favorites/{isbn}")
    public ResponseEntity<?> removeFavorites(@PathVariable String isbn, Authentication authentication) {
        bookService.removeFromFavourite(isbn, authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
