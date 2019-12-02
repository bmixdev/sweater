package ru.mbelin.sweater.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.mbelin.sweater.domain.Role;
import ru.mbelin.sweater.domain.User;
import ru.mbelin.sweater.repos.UserRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }
        return user;
    }

    public boolean addUser(User user)
    {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null)
        {
           return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        sendMessage(user);
        return true;
    }

    private void sendMessage(User user) {
        if(!StringUtils.isEmpty(user.getEmail()))
        {
            String hostName = "http://localhost:8080";
            String message = String.format(
                    "<html>Hello, %s!</br>" +
                            "Welcome to Sweater. Please, visit next link: <a href=\"%s/activate/%s"+"\"> Activated link </a></html>"
                    , user.getUsername()
                    , hostName
                    , user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null)
        {
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged)
        {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email))
            {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password))
        {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepo.save(user);
        if (isEmailChanged) {
            sendMessage(user);
        }

    }
}
