package bg.softuni.MobiLeLeLe.web;

import bg.softuni.MobiLeLeLe.model.binding.OfferUpdateBindingModel;
import bg.softuni.MobiLeLeLe.model.entity.enums.EngineEnum;
import bg.softuni.MobiLeLeLe.model.entity.enums.TransmissionEnum;
import bg.softuni.MobiLeLeLe.model.service.OfferUpdateServiceModel;
import bg.softuni.MobiLeLeLe.model.view.OfferDetailsView;
import bg.softuni.MobiLeLeLe.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OffersController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OffersController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", this.offerService.getAllOffers());

        return "offers";
    }

    @GetMapping("/offers/{id}/details")
    public String showOffer(@PathVariable Long id, Model model) {

        model.addAttribute("offer", this.offerService.getById(id));
        return "details";
    }

    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id) {

        this.offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/{id}/update")
    public String updateOffer(@PathVariable Long id, Model model) {

        OfferDetailsView offerDetailsView = this.offerService.getById(id);
        OfferUpdateBindingModel offerModel = modelMapper.map(offerDetailsView, OfferUpdateBindingModel.class);

        model.addAttribute("offerModel", offerModel);
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "update";
    }

    @GetMapping("/offers/{id}/update/errors")
    public String updateOfferErrors(@PathVariable Long id, Model model) {

        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "update";
    }

    @PatchMapping("/offers/{id}/update")
    public String updateConfirm(@PathVariable Long id,
                                @Valid OfferUpdateBindingModel offerModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/update/errors";
        }

        OfferUpdateServiceModel offerUpdateServiceModel = modelMapper.map(offerModel, OfferUpdateServiceModel.class);

        this.offerService.updateOffer(offerUpdateServiceModel);


        return "redirect:/offers/" + id + "/details";
    }
}
