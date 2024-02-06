package ru.kata.spring.boot_security.demo.startTest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TestRun {
    private final RoleService roleService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void run() {
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");

        roleService.saveRole(roleUser);
        roleService.saveRole(roleAdmin);
        User admin = new User("Sergei", "Safronov", 30, "admin", "123", Set.of(roleAdmin, roleUser));
        User user = new User("Igor", "Brizgunov", 31, "user", "123", Set.of(roleUser));
        userService.saveUser(admin);
        userService.saveUser(user);

    }

}
