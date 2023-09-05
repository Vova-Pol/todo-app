package ru.vova.springboot.todowebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Логгирование
    // private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLoginPage() {
        // Debugging Logs
        // Don't use System.out.print in PROD CODE
//        logger.debug("Request Parameter is {}", name);
//        logger.info("This will be printed at INFO level");
//        logger.warn("This will be printed at WARN level");

        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {

        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            model.put("password", password);
            return "welcome";
        }

        model.put("errorMessage", "Invalid credentials. Try again.");
        return "login";
    }
}
