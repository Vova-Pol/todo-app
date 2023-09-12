package ru.vova.springboot.todowebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailManager() {

        UserDetails userDetails1 = createUser("Vladimir", "123");
        UserDetails userDetails2 = createUser("Bob", "123");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails createUser(String userName, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(userName)
                .password(password)
                .roles("USER", "ADMIN")
                .build();

        return userDetails;
    }

    // Чтобы подключиться к БД h2 надо настроить конфигурацию:
    // разрешить CSRF
    // разрешить Frames

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Все запросы защищены авторизацией
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        // Неавторизованый запрос отправляется на форму авторизации
        http.formLogin(Customizer.withDefaults());

        // CSRF и Frames
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}
