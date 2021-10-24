package com.example.exam.model.binding;

import com.example.exam.model.entity.enums.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ShipAddBingingModel {

    @NotNull
    @Size(min = 2, max = 10)
    private String name;

    @NotNull
    @Positive
    private Integer power;

    @NotNull
    @Positive
    private Integer health;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    @NotNull
    private CategoryNameEnum category;

    public String getName() {
        return name;
    }

    public ShipAddBingingModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public ShipAddBingingModel setPower(Integer power) {
        this.power = power;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public ShipAddBingingModel setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipAddBingingModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ShipAddBingingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }
}
