package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.model.entity.BrandEntity;
import bg.softuni.MobiLeLeLe.model.view.BrandViewModel;

import java.util.List;

public interface BrandService {

    void initializeBrands();

    BrandEntity getBrandByName(String name);

    List<BrandViewModel> getAllBrands();
}
