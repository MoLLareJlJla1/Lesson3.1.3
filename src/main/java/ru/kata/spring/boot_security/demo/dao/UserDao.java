package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDao {

    void save(User user);
    List<User> findAll();
    User findById(long id);
    void deleteById(long id);
    User findByEmail(String user);
}
