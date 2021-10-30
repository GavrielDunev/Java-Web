package bg.softuni.MobiLeLeLe.service.impl;

import bg.softuni.MobiLeLeLe.model.entity.BrandEntity;
import bg.softuni.MobiLeLeLe.model.entity.ModelEntity;
import bg.softuni.MobiLeLeLe.model.view.BrandViewModel;
import bg.softuni.MobiLeLeLe.model.view.ModelViewModel;
import bg.softuni.MobiLeLeLe.repository.BrandRepository;
import bg.softuni.MobiLeLeLe.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public List<BrandViewModel> getAllBrands() {
        return this.brandRepository.findAll()
                .stream()
                .map(brandEntity -> {
                    BrandViewModel brand = new BrandViewModel()
                            .setName(brandEntity.getName());
                    brand.setModels(brandEntity.getModels()
                            .stream()
                            .map(this::mapModel)
                            .collect(Collectors.toList()));

                    return brand;
                })
                .collect(Collectors.toList());
    }

    private ModelViewModel mapModel(ModelEntity modelEntity) {
        return this.modelMapper.map(modelEntity, ModelViewModel.class);
    }
}
