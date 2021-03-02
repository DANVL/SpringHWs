package org.booker.service.impl;

import org.booker.models.Book;
import org.booker.repository.BookRepository;
import org.booker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book add(Book book) {
        return bookRepository.add(book);
    }

    @Override
    public List<Book> get() {
        return bookRepository.get();
    }

    @Override
    public List<Book> searchByNameAndISBN(String title) {
        return bookRepository.findByNameAndISBN(title);
    }
}
