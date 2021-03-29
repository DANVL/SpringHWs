package org.booker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Pattern(regexp = "[A-Za-z]+")
    private String login;
    @Size(min=8, max=20)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favourite_book",
            joinColumns = {@JoinColumn(name = "login")},
            inverseJoinColumns = {@JoinColumn(name = "isbn")})
    private List<Book> favouriteBooks;
}
