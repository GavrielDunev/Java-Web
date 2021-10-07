package bg.softuni.MobiLeLeLe.web;

import bg.softuni.MobiLeLeLe.model.binding.UserRegistrationBindingModel;
import bg.softuni.MobiLeLeLe.model.service.UserRegistrationServiceModel;
import bg.softuni.MobiLeLeLe.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }

    @GetMapping("/users/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String registerUser(@Valid UserRegistrationBindingModel userModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegistrationServiceModel serviceModel = modelMapper.map(userModel, UserRegistrationServiceModel.class);

        if (!this.userService.isUsernameFree(serviceModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("usernameBusy", true);

            return "redirect:/users/register";
        } else {
            this.userService.registerAndLoginUser(serviceModel);
        }

        return "redirect:/";
    }
}
