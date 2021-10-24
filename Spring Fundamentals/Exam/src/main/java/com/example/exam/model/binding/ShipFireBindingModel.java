package com.example.exam.model.binding;

public class ShipFireBindingModel {

    private String attacker;
    private String defender;

    public String getAttacker() {
        return attacker;
    }

    public ShipFireBindingModel setAttacker(String attacker) {
        this.attacker = attacker;
        return this;
    }

    public String getDefender() {
        return defender;
    }

    public ShipFireBindingModel setDefender(String defender) {
        this.defender = defender;
        return this;
    }
}
