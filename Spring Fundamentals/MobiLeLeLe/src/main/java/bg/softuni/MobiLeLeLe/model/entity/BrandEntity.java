package bg.softuni.MobiLeLeLe.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    private List<ModelEntity> models;

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }
}
