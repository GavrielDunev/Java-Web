package bg.softuni.MobiLeLeLe.view;

import bg.softuni.MobiLeLeLe.model.entity.enums.EngineEnum;
import bg.softuni.MobiLeLeLe.model.entity.enums.TransmissionEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.Instant;

public class OfferSummaryView {

    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    @Column(nullable = false)
    private Integer year;

    @Column
    private String model;

    @Column
    private String brand;

    private Instant created;

    private Instant modified;

    private String sellerFirstName;

    private String sellerLastName;

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public OfferSummaryView setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
        return this;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public OfferSummaryView setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferSummaryView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferSummaryView setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferSummaryView setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferSummaryView setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferSummaryView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummaryView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferSummaryView setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferSummaryView setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferSummaryView setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferSummaryView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferSummaryView setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OfferSummaryView setCreated(Instant created) {
        this.created = created;
        return this;
    }
}
