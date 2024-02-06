package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @GetMapping("")//Отобразил Начальную страницу,первая модель вывела всех пользователей,а вторая их Роли
    public String showAllUser(Model model) {
        model.addAttribute("showAllAttributes", userService.showAll());
        return "showAllUser";
    }

    @GetMapping("/add")//Открываем страницу с запросом /add
    public String addUser(Model model) {
        model.addAttribute("newUser",new User());
        return "add";
    }
    @PostMapping("/save")//Сохраняем юзера в базу на основе наших заполненых полей;
    public String saveUser(@ModelAttribute("currentUser")User user,
                           @RequestParam(required=false)String adminRoles,@RequestParam(required=false)String userRoles){
        if(userRoles!=null&&userRoles.equals("USER")){
            user.addRole(roleService.findById(2L));
        }
        if(adminRoles!=null&&adminRoles.equals("ADMIN")){
            user.addRole(roleService.findById(1L));
        }
        userService.saveUser(user);
        return "redirect:/admin/";
    }
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id")long id,Model model){
        model.addAttribute("newUser",userService.getUserById(id));
        return "add";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id")long id){
        userService.deleteUser(id);
        return "redirect:/admin/";
    }

}
