package bg.softuni.MobiLeLeLe.service.impl;

import bg.softuni.MobiLeLeLe.model.entity.BrandEntity;
import bg.softuni.MobiLeLeLe.model.entity.ModelEntity;
import bg.softuni.MobiLeLeLe.model.entity.enums.CategoryEnum;
import bg.softuni.MobiLeLeLe.repository.ModelRepository;
import bg.softuni.MobiLeLeLe.service.BrandService;
import bg.softuni.MobiLeLeLe.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandService brandService;

    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
    }

    @Override
    public void initializeModels() {

        if (this.modelRepository.count() == 0) {
            BrandEntity bmw = this.brandService.getBrandByName("BMW");

            ModelEntity threeSeries = new ModelEntity();
            threeSeries.setBrand(bmw)
                    .setName("3 series")
                    .setCategory(CategoryEnum.CAR)
                    .setStartYear(1975)
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/8/8d/BMW_G20_IMG_0167.jpg");

            ModelEntity fiveSeries = new ModelEntity();
            fiveSeries.setBrand(bmw)
                    .setName("5 series")
                    .setCategory(CategoryEnum.CAR)
                    .setStartYear(1972)
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/7/7a/2020_BMW_530d_M_Sport_facelift.jpg");

            bmw.setModels(List.of(threeSeries, fiveSeries));

            this.modelRepository.saveAll(List.of(threeSeries, fiveSeries));
        }
    }

}
