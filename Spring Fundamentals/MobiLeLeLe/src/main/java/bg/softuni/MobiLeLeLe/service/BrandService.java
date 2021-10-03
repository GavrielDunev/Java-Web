package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.model.entity.BrandEntity;

public interface BrandService {

    void initializeBrands();

    BrandEntity getBrandByName(String name);
}
