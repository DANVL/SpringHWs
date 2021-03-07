package org.booker.service;

import org.booker.models.Book;

import java.util.List;

public interface BookService {
    Book add(Book book);
    List<Book> get();
    Book getByIsbn(String isbn);
    List<Book> searchByNameAndISBNAndAuthor(String title);
}
