package ru.vova.springboot.todowebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String userName, String password) {
        boolean isValidName = userName.equalsIgnoreCase("Vova");
        boolean isValidPassword = password.equalsIgnoreCase("12345");

        return isValidName && isValidPassword;
    }
}
