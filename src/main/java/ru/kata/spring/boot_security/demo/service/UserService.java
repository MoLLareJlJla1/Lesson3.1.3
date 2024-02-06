package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;
import java.util.List;

public interface UserService {
    public void saveUser(User user);

    public List<User> showAll();

    public User getUserById(long id);

    public void deleteUser(long id);



}
