package org.booker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.booker.models.Book;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIntgTest {

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    void setPort(int port) {
        RestAssured.port = port;
    }

    @Test
    public void getAllTest() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/books")
                .then()
                .statusCode(200)
                .body("",
                        Matchers.hasItems(Matchers.hasEntry("author", "Author1")));
    }

    @Test
    public void getOneTest() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/books/1")
                .then()
                .statusCode(200)
                .body("",
                        Matchers.hasItems(Matchers.hasEntry("author", "Author1")));
    }

    @Test
    public void addOneTest() {
        Book book = Book.builder().author("1").name("1").isbn("1").build();


        RestAssured
                .given()
                .body(book)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/books")
                .then()
                .statusCode(200)
                .body("isbn", CoreMatchers.is("1"))
                .body("name", CoreMatchers.is("1"))
                .body("author", CoreMatchers.is("1"));
    }

}