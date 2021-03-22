package org.booker.service.impl;

import org.booker.models.Book;
import org.booker.repository.BookRepository;
import org.booker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    private Specification<Book> isbnContains(String isbn) {
        return (book, cq, cb) -> cb.like(book.get("isbn"), "%" + isbn + "%");
    }

    private Specification<Book> nameContains(String name) {
        return (book, cq, cb) -> cb.like(book.get("name"), "%" + name + "%");
    }
}
