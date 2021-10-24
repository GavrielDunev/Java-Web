package com.example.exam.web;

import com.example.exam.model.binding.ShipAddBingingModel;
import com.example.exam.model.service.ShipServiceModel;
import com.example.exam.service.ShipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {

    private final ModelMapper modelMapper;
    private final ShipService shipService;

    public ShipController(ModelMapper modelMapper, ShipService shipService) {
        this.modelMapper = modelMapper;
        this.shipService = shipService;
    }

    @GetMapping("/ships/add")
    public String shipAdd() {
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String shipAddConfirm(@Valid ShipAddBingingModel shipAddBingingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipAddBingingModel", shipAddBingingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipAddBingingModel", bindingResult);

            return "redirect:/ships/add";
        }

        this.shipService.addShip(modelMapper.map(shipAddBingingModel, ShipServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public ShipAddBingingModel shipAddBingingModel() {
        return new ShipAddBingingModel();
    }

}
