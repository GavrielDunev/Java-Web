package bg.softuni.MobiLeLeLe.init;

import bg.softuni.MobiLeLeLe.service.BrandService;
import bg.softuni.MobiLeLeLe.service.ModelService;
import bg.softuni.MobiLeLeLe.service.OfferService;
import bg.softuni.MobiLeLeLe.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BrandService brandService;
    private final UserService userService;
    private final ModelService modelService;
    private final OfferService offerService;

    public DataInitializer(BrandService brandService, UserService userService, ModelService modelService, OfferService offerService) {
        this.brandService = brandService;
        this.userService = userService;
        this.modelService = modelService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) {
        this.brandService.initializeBrands();
        this.modelService.initializeModels();
        this.userService.initializeUsersAndRoles();
        this.offerService.initializeOffers();
    }
}
