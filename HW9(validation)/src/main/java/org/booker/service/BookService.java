package org.booker.service;

import org.booker.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book add(Book book);

    Page<Book> get(String searchString, Pageable pageable);

    Book getByIsbn(String isbn);

    List<Book> getFavouritesByUsername(String username);

    void addToFavourite(String isbn, String username);

    void removeFromFavourite(String isbn, String username);
}
