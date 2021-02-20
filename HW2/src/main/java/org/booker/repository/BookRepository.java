package org.booker.repository;

import org.booker.models.Book;

import java.util.List;

public interface BookRepository {
    void add(Book book);
    List<Book> get();
}
