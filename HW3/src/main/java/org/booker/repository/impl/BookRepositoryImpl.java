package org.booker.repository.impl;

import org.booker.models.Book;
import org.booker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookRepositoryImpl implements BookRepository {

    private final List<Book> books;

    @Autowired
    public BookRepositoryImpl(List<Book> books){
        this.books = books;
    }


    @Override
    public Book add(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public List<Book> get() {
        return books;
    }

    @Override
    public List<Book> findByNameAndISBN(String title) {
        return books.stream()
                .filter(
                        book -> book.getName().toLowerCase().contains(title.toLowerCase()) ||
                        book.getISBN().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
