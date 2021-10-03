package bg.softuni.MobiLeLeLe.service.impl;

import bg.softuni.MobiLeLeLe.model.entity.BrandEntity;
import bg.softuni.MobiLeLeLe.repository.BrandRepository;
import bg.softuni.MobiLeLeLe.service.BrandService;
import org.springframework.stereotype.Service;


@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void initializeBrands() {
        if (this.brandRepository.count() == 0) {
            BrandEntity bmw = new BrandEntity();
            bmw.setName("BMW");

            this.brandRepository.save(bmw);
        }
    }

    @Override
    public BrandEntity getBrandByName(String name) {
        return this.brandRepository.findByName(name).orElse(null);
    }
}
