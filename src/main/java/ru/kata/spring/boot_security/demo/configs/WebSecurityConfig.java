package ru.kata.spring.boot_security.demo.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserDetailsService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/user").hasAnyAuthority("ADMIN","USER")
               .antMatchers("/admin").hasAuthority("ADMIN")
               .anyRequest().authenticated()
               .and().formLogin().successHandler(successUserHandler).permitAll()
               .and()
               .logout().permitAll();


    }

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

}
//Привет! В этом коде создается конфигурационный класс WebSecurityConfig для Spring Security.
//
//@Configuration аннотация указывает, что это класс конфигурации.
//
//@EnableWebSecurity аннотация включает конфигурацию Spring Security для веб-приложения.
//
//        WebSecurityConfigurerAdapter - это базовый класс для создания конфигурации Spring Security.
//        Мы расширяем его, чтобы настроить наше приложение.
//
//        Метод configure(HttpSecurity http) - основной метод конфигурации, где мы настраиваем правила доступа к нашему веб-приложению.
//
//        http.authorizeRequests() - указывает, что будем настраивать правила доступа к запросам.
//
//        .antMatchers("/","/index").permitAll() - разрешает анонимный доступ к корневому пути и пути "/index".
//        .anyRequest().authenticated() - все остальные запросы должны быть аутентифицированы
//        (т.е. требуют аутентификации пользователя).
//
//        .formLogin().successHandler(successUserHandler).permitAll() - включает форму логина и указывает,
//        что после успешного входа, будет использован обработчик SuccessUserHandler для перенаправления пользователя.
//
//        .logout().permitAll() - разрешает доступ к странице выхода (логауту) всем пользователям.
//
//        Метод userDetailsService() настраивает аутентификацию в памяти. Здесь мы создаем пользователя с именем "user",
//        паролем "user" и ролью "USER", используя InMemoryUserDetailsManager, который хранит данные пользователей в памяти.
//
//        В целом, этот код настраивает базовую аутентификацию, разрешает анонимный доступ к некоторым путям, а остальные требуют аутентификации. После успешной аутентификации пользователь будет перенаправлен на указанный обработчик.

