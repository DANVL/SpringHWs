package org.booker.service;

import org.booker.models.Book;

import java.util.List;

public interface BookService {
    void add(Book book);
    List<Book> get();
}
