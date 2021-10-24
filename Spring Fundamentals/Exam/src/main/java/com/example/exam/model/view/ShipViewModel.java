package com.example.exam.model.view;

public class ShipViewModel {

    private String name;
    private Integer health;
    private Integer power;

    public String getName() {
        return name;
    }

    public ShipViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public ShipViewModel setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public ShipViewModel setPower(Integer power) {
        this.power = power;
        return this;
    }
}
