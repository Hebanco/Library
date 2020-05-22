package eLibrary.unitTests;

import eLibrary.domain.User;
import eLibrary.repos.UserRepo;
import eLibrary.service.MailSender;
import eLibrary.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class UserServiceTest {
    private UserRepo userRepo = mock(UserRepo.class);
    private MailSender mailSender = mock(MailSender.class);
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private UserService userService = new UserService(userRepo, mailSender, passwordEncoder);

    @Test
    public void addUserTest(){
        User user = new User();

        boolean isUserCreated = userService.addUser(user);

        Assertions.assertTrue(isUserCreated);

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    public void addUserFailedTest(){
        User user = new User();
        user.setUsername("John");

        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("John");

        boolean isUserCreated = userService.addUser(user);

        Assertions.assertFalse(isUserCreated);

        Mockito.verify(userRepo, Mockito.never()).save(ArgumentMatchers.any(User.class));
    }

    @Test
    public void sendMailTest(){
        User user = new User();
        user.setEmail("email@email.email");

        Mockito.doReturn(user)
                .when(userRepo)
                .findByEmail("email@email.email");

        userService.sendMail(user.getEmail());

        Mockito.verify(mailSender, Mockito.times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.eq("Восстановление пароля"),
                        ArgumentMatchers.contains("Приетствую,"));
    }

    @Test
    public void sendMailFailedTest(){
        User user = new User();
        user.setEmail("email@email.email");

        Mockito.doReturn(new User())
                .when(userRepo)
                .findByEmail("email@email.email");

        userService.sendMail(user.getEmail());

        Mockito.verify(mailSender, Mockito.never())
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString());
    }
}
