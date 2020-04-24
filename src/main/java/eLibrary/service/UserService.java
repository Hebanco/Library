package eLibrary.service;

import eLibrary.domain.Role;
import eLibrary.domain.User;
import eLibrary.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final MailSender mailSender;

    public UserService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

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

    public List<User> findAll() {
        return userRepo.findAll();
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
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return true;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void sendMail(User user) {
        String message = String.format(
                "Hello, %s! \n"+
                        "Link for password recover http://localhost:8080/recover/%s",
                user.getUsername(),
                user.getActivationCode()
        );

        mailSender.send(user.getEmail(), "Password recover", message);
    }

    public User findByActivationCode(String code) {
        return userRepo.findByActivationCode(code);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public void updateProfile(User user, String username, String password, String email) {
        if(!StringUtils.isEmpty(username) && !username.equals(user.getUsername())){
            user.setUsername(username);
        }

        if(!StringUtils.isEmpty(password) && !password.equals(user.getPassword())){
            user.setPassword(password);
        }

        if(!StringUtils.isEmpty(email) && !email.equals(user.getEmail())){
            user.setEmail(email);
        }

        userRepo.save(user);
    }
}
