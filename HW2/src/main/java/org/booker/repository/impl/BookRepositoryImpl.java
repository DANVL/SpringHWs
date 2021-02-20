package org.booker.repository.impl;

import org.booker.models.Book;
import org.booker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRepositoryImpl implements BookRepository {

    private final List<Book> books;

    @Autowired
    public BookRepositoryImpl(List<Book> books){
        this.books = books;
    }


    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> get() {
        return books;
    }
}
