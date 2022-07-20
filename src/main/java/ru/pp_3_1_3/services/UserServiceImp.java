package ru.pp_3_1_3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pp_3_1_3.models.Role;
import ru.pp_3_1_3.models.User;
import ru.pp_3_1_3.repositories.RoleRepository;
import ru.pp_3_1_3.repositories.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> users() {
        return userRepository.findAll();
    }

    @Override
    public void create(User user) {
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void edit(int id, User user) {
        User usr = userRepository.findById(id).orElse(null);

        usr.setUsername(user.getUsername());
        usr.setPassword(passwordEncoder.encode(user.getPassword()));
        usr.setRoles(user.getRoles());
        usr.setEmail(user.getEmail());

        userRepository.save(usr);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
    @Override
    public boolean userHaveRole(String username, String roleName) {
        return userRepository.findByUsername(username).getRoles().stream().anyMatch(t->t.getNameWithoutROLE().equals(roleName));
    }
}