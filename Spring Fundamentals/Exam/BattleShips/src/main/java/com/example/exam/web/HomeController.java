package com.example.exam.web;

import com.example.exam.model.binding.ShipFireBindingModel;
import com.example.exam.service.ShipService;
import com.example.exam.service.UserService;
import com.example.exam.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final UserService userService;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, UserService userService, ShipService shipService) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.shipService = shipService;
    }

    @GetMapping()
    public String index(Model model) {
        if (this.currentUser.getId() != null) {
            model.addAttribute("attackerShips", this.userService.getAllLoggedInUserShips());
            model.addAttribute("defenderShips", this.shipService.getAllOtherUsersShips(this.currentUser.getId()));
            model.addAttribute("allShips", this.shipService.findAllShipsOrderedById());

            return "home";
        }

        return "index";
    }

    @ModelAttribute
    public ShipFireBindingModel shipFireBindingModel() {
        return new ShipFireBindingModel();
    }
}
