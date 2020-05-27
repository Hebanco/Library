package eLibrary.service;

import eLibrary.domain.Book;
import eLibrary.domain.Role;
import eLibrary.domain.User;
import eLibrary.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, MailSender mailSender, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user, Map<String, String> form) {

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

            user.getRoles().clear();

        for (String key : form.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public List<User> findAll(String filter) {
        List<User> users;
        if(!filter.isEmpty()) {
            users = userRepo.findByName(filter);
        } else {
            users = userRepo.findAll();
        }
        return users;
    }

    public List<User> findTeacher() {
        return userRepo.findTeacher();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public boolean addUser(User user){
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB!=null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        return true;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void sendMail(String email) {
        User user = findByEmail(email);
        if (user!=null) {
            user.setActivationCode(UUID.randomUUID().toString());

            String message = String.format(
                    "Приетствую, %s! \n" +
                            "Ссылка для восстановления пароля: http://localhost:8080/recover/%s",
                    user.getFio(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Восстановление пароля", message);
        }
    }

    public User findByActivationCode(String code) {
        return userRepo.findByActivationCode(code);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user, String password,String activationCode) {
        userRepo.save(user);
        user.setPassword(passwordEncoder.encode(password));
        user.setActivationCode(activationCode);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void updateProfile(User user, String username, String password, String email, String fio) {
        if(!StringUtils.isEmpty(username) && !username.equals(user.getUsername())){
            user.setUsername(username);
        }

        if(!StringUtils.isEmpty(password) && !password.equals(user.getPassword())){
            user.setPassword(passwordEncoder.encode(password));
        }

        if(!StringUtils.isEmpty(email) && !email.equals(user.getEmail())){
            user.setEmail(email);
        }

        if(!StringUtils.isEmpty(fio) && !fio.equals(user.getFio())){
            user.setFio(fio);
        }

        userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}
