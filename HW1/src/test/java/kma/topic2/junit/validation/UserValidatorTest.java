package kma.topic2.junit.validation;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserValidatorTest {

    @Autowired
    UserValidator userValidator;

    @MockBean
    UserRepository userRepository;

    private static NewUser newUser;

    @BeforeAll
    private static void initUser() {
        final String login = "login";
        final String fullName = "fullName";
        final String password = "password";
        newUser = NewUser.builder()
                .login(login)
                .fullName(fullName)
                .password(password)
                .build();
    }


    @Test
    public void loginExistsTest() {

        Mockito.doReturn(true)
                .when(userRepository)
                .isLoginExists(newUser.getLogin());

        Assertions.assertThatThrownBy(() -> userValidator.validateNewUser(newUser))
                .isInstanceOf(LoginExistsException.class)
                .hasMessageContaining(newUser.getLogin());
    }

    @Test
    public void constraintsViolationTest() {
        Mockito.doReturn(false)
                .when(userRepository)
                .isLoginExists(newUser.getLogin());


        Assertions.assertThatThrownBy(() -> userValidator.validateNewUser(newUser))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("You have errors in you object")
                .hasNoNullFieldsOrProperties();
    }

}