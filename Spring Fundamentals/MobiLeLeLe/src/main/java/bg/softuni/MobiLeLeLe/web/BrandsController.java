package bg.softuni.MobiLeLeLe.web;

import bg.softuni.MobiLeLeLe.model.view.BrandViewModel;
import bg.softuni.MobiLeLeLe.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BrandsController {

    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands/all")
    public String allBrands(Model model) {
        List<BrandViewModel> allBrands = this.brandService.getAllBrands();

        model.addAttribute("allBrands", allBrands);
        return "brands";
    }
}
