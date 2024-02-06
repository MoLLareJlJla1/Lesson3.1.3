package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    //Привет! В этом коде создается класс SuccessUserHandler, который является компонентом Spring
    // и реализует интерфейс AuthenticationSuccessHandler.
    //
    //Данный класс предназначен для обработки успешной аутентификации пользователя. Когда пользователь успешно
    // проходит процесс аутентификации, этот класс будет вызываться.
    //
    //В методе onAuthenticationSuccess происходит обработка успешной аутентификации. Метод получает объект Authentication
    // , который содержит информацию о пользователе, авторизованном в текущей сессии.
    //
    //Далее, в этом методе, происходит проверка наличия роли "ROLEUSER" у пользователя. Если эта роль присутствует,
    // то пользователь будет перенаправлен на страницу "/user".
    // Если роль отсутствует, то пользователь будет перенаправлен на главную страницу "/".
    //
    //Иными словами, данный код определяет, куда перенаправлять пользователя после успешной аутентификации
    // в зависимости от его роли. Если у него есть роль "ROLEUSER", то он будет перенаправлен на страницу "/user",
    // в противном случае - на главную страницу "/".
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/");
        } else if (roles.contains("ROLE_USER")){
            httpServletResponse.sendRedirect("/user/");
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }
}