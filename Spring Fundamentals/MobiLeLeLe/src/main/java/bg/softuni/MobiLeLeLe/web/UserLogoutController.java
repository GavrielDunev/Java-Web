package bg.softuni.MobiLeLeLe.web;

import bg.softuni.MobiLeLeLe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLogoutController {

    private final UserService userService;

    public UserLogoutController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/logout")
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }

}
