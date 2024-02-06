package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findById(long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select distinct u from User u left join fetch u.roles ", User.class).getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.createQuery(
                "DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public User findByEmail(String user) {
       return entityManager.createQuery("select u FROM User u WHERe u.email = :name", User.class)
                .setParameter("name",user)
                .getResultList().stream().findAny().orElse(null);
    }
}
