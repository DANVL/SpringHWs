package org.booker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String login;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favourite_book",
            joinColumns = {@JoinColumn(name = "login")},
            inverseJoinColumns = {@JoinColumn(name = "isbn")})
    private List<Book> favouriteBooks;
}
