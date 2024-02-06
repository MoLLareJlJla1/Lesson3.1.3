package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.Role;

public interface RoleDao {
    void save(Role role);
    Role findById(long l);
}
