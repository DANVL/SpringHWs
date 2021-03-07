package org.booker.repository;

import org.booker.models.Book;

import java.util.List;

public interface BookRepository {
    Book add(Book book);
    List<Book> get();
    List<Book> findByNameAndISBN(String title);

}
