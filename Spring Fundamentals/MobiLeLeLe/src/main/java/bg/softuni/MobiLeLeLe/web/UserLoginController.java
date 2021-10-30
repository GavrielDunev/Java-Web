package bg.softuni.MobiLeLeLe.web;

import bg.softuni.MobiLeLeLe.service.impl.MobileleleUserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {


    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }
}
