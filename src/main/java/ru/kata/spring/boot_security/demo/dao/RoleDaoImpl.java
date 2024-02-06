package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;

@Repository
public class RoleDaoImpl implements RoleDao {
    EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findById(long l) {
        return entityManager.find(Role.class,l);
    }
}
