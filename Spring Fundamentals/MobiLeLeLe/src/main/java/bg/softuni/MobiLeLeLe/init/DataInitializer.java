package bg.softuni.MobiLeLeLe.init;

import bg.softuni.MobiLeLeLe.model.entity.BrandEntity;
import bg.softuni.MobiLeLeLe.model.entity.ModelEntity;
import bg.softuni.MobiLeLeLe.model.entity.UserEntity;
import bg.softuni.MobiLeLeLe.model.entity.enums.CategoryEnum;
import bg.softuni.MobiLeLeLe.repository.BrandRepository;
import bg.softuni.MobiLeLeLe.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(BrandRepository brandRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        initializeBrandsAndModels();
        initializeUsers();

    }

    public void initializeBrandsAndModels() {
        if (this.brandRepository.count() == 0) {
            BrandEntity bmw = new BrandEntity();
            bmw.setName("BMW");

            ModelEntity threeSeries = new ModelEntity();
            threeSeries.setBrand(bmw)
                    .setName("threeSeries")
                    .setCategory(CategoryEnum.CAR)
                    .setStartYear(1975)
                    .setImageUrl("https://en.wikipedia.org/wiki/BMW_3_Series#/media/File:BMW_G20_IMG_0167.jpg");

            ModelEntity fiveSeries = new ModelEntity();
            fiveSeries.setBrand(bmw)
                    .setName("fiveSeries")
                    .setCategory(CategoryEnum.CAR)
                    .setStartYear(1972)
                    .setImageUrl("https://en.wikipedia.org/wiki/BMW_5_Series#/media/File:2018_BMW_520d_M_Sport_Automatic_2.0_(1).jpg");

            bmw.setModels(List.of(threeSeries, fiveSeries));

            this.brandRepository.save(bmw);
        }
    }

    public void initializeUsers() {
        if (this.userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin")
                    .setPassword(passwordEncoder.encode("12345"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setActive(true);

            this.userRepository.save(admin);
        }
    }
}
