package org.booker.configs;

import org.booker.models.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BookRepositoryConfig {
    @Bean
    public List<Book> bookListConfig() {
        return new ArrayList<>() {
            {
                add(Book.builder().name("Book1").author("Author1").isbn("1234").build());
            }
        };
    }
}
