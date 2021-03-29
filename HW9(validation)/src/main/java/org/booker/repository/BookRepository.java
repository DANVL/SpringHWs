package org.booker.repository;

import org.booker.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

//public interface BookRepository extends JpaRepository<Book, String> {
//    List<Book> findByNameContainsAndAuthorContainsAndIsbnContains(String name, String ISBN, String author);
//    Book findFirstByIsbn(String isbn);
//}

public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {

    Book findByIsbn(String isbn);
}
