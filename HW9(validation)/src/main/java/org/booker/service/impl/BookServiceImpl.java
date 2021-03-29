package org.booker.service.impl;

import org.booker.models.Book;
import org.booker.models.User;
import org.booker.repository.BookRepository;
import org.booker.repository.UserRepository;
import org.booker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Page<Book> get(String title, Pageable pageable) {
        return bookRepository.findAll(where(isbnContains(title)).and(nameContains(title)),pageable);
    }

    @Override
    public Book getByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getFavouritesByUsername(String login) {
        User user = userRepository.findById(login).orElseThrow();
        return user.getFavouriteBooks();
    }

    @Override
    public void addToFavourite(String isbn, String login) {
        User user = userRepository.findById(login).orElseThrow();
        user.getFavouriteBooks().add(bookRepository.findByIsbn(isbn));

        userRepository.save(user);
    }

    @Override
    public void removeFromFavourite(String isbn, String login) {
        User user = userRepository.findById(login).orElseThrow();
        user.getFavouriteBooks().removeIf(book -> book.getIsbn().equals(isbn));

        userRepository.save(user);
    }

    private Specification<Book> isbnContains(String isbn) {
        return (book, cq, cb) -> cb.like(book.get("isbn"), "%" + isbn + "%");
    }

    private Specification<Book> nameContains(String name) {
        return (book, cq, cb) -> cb.like(book.get("name"), "%" + name + "%");
    }
}
