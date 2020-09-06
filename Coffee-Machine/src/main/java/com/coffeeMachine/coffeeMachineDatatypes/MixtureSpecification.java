package com.coffeeMachine.coffeeMachineDatatypes;

/*
   This class contains the particular mixture specification.
   Eg -  MixtureSpecification - drink_type - hot_water, totalQuantity in Coffee Machine - 500
 */
public class MixtureSpecification {

    private final String drinkType;

    private final int totalQuantity;

    public MixtureSpecification(String drinkType, int quantity) {
        this.drinkType = drinkType;
        this.totalQuantity = quantity;
    }

    public int getTotalQuantity() { return totalQuantity; }

    public String getDrinkType() { return drinkType; }
}
