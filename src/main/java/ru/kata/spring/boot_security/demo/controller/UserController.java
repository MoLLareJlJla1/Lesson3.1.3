package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/")
    public String getUser(Principal principal, Model model) {
        model.addAttribute("showAllAttributes", userService.loadUserByUsername(principal.getName()));
        return "showAllUser";
    }
}
