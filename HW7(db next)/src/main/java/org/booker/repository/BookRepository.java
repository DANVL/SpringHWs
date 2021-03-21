package org.booker.repository;

import org.booker.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByNameContainsAndAuthorContainsAndIsbnContains(String name, String ISBN, String author);
    Book findFirstByIsbn(String isbn);
}
