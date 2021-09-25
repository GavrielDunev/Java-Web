package bg.softuni.MobiLeLeLe.web;

import bg.softuni.MobiLeLeLe.model.binding.UserLoginBindingModel;
import bg.softuni.MobiLeLeLe.model.service.UserLoginServiceModel;
import bg.softuni.MobiLeLeLe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginBindingModel userLoginBindingModel) {

        boolean loginSuccessful = this.userService
                .login(new UserLoginServiceModel().setUsername(userLoginBindingModel.getUsername())
                        .setRawPassword(userLoginBindingModel.getPassword()));

        LOGGER.info("User tried to login. Username {}. Successful: {}",
                userLoginBindingModel.getUsername(),
                loginSuccessful);

        return "redirect:/users/login";
    }
}
