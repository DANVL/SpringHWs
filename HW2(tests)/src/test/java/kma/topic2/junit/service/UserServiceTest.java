package kma.topic2.junit.service;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.exceptions.UserNotFoundException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void createNewUserTest(){
        final String login = "login";
        final String fullName = "fullName";
        final String password = "pssword";

        User user = User.builder()
                .login(login)
                .fullName(fullName)
                .password(password)
                .build();

        NewUser newUser = NewUser.builder()
                .login(login)
                .fullName(fullName)
                .password(password)
                .build();

        userService.createNewUser(newUser);

        User repoUser = userService.getUserByLogin(login);

        Assertions.assertThat(repoUser)
                .isEqualTo(user);
    }

    @Test
    public void userNotFoundTest(){
        final String login = "login";

        Assertions.assertThatThrownBy(() -> userService.getUserByLogin(login))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(login);
    }

    @Test
    public void createNewUserLoginExistsTest() {

        final String login = "login1";
        final String fullName = "fullName";
        final String password = "password";

        NewUser newUser = NewUser.builder()
                .login(login)
                .fullName(fullName)
                .password(password)
                .build();

        Assertions.assertThatThrownBy(() -> userService.createNewUser(newUser))
                .isInstanceOf(LoginExistsException.class)
                .hasMessageContaining(newUser.getLogin());
    }

    @Test
    public void createNewUserConstraintsViolationTest() {

        final String login = "loginNew";
        final String fullName = "fullName";
        final String password = "password";

        NewUser newUser = NewUser.builder()
                .login(login)
                .fullName(fullName)
                .password(password)
                .build();

        Assertions.assertThatThrownBy(() -> userService.createNewUser(newUser))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("You have errors in you object")
                .hasNoNullFieldsOrProperties();
    }

}