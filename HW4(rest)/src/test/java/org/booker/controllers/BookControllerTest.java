package org.booker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.booker.models.Book;
import org.booker.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllTest() throws Exception {
        Book book = Book.builder().name("Book1").author("Author1").ISBN("1234").build();
        final String expectedResponse = objectMapper.writeValueAsString(List.of(
                book));

        Mockito
                .when(bookService.get())
                .thenReturn(List.of(book));


        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse))
                .andExpect(status().is2xxSuccessful());

    }


    @Test
    public void getOneTest() throws Exception {
        Book book = Book.builder().name("Book1").author("Author1").ISBN("1234").build();
        final String expectedResponse = objectMapper.writeValueAsString(List.of(
                book));

        Mockito
                .when(bookService.searchByNameAndISBN("1"))
                .thenReturn(List.of(book));


        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void addOneTest() throws Exception {
        Book book = Book.builder().name("Book1").author("Author1").ISBN("1234").build();

        final String sentResponse = objectMapper.writeValueAsString(book);

        Mockito
                .when(bookService.add(book))
                .thenReturn(book);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sentResponse))
                .andExpect(status().is2xxSuccessful());

    }
}