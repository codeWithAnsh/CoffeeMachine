package com.coffeeMachine.coffeeMachineExceptions;

/*
   This Exception occurs when beverage cannot be prepared either due to unavailability of mixture or
   insufficient quantity of mixture
 */
public class BeveragePreparationException extends Exception {

    private final String drinkType;

    public BeveragePreparationException(String message, String drinkType) {
        super(message);
        this.drinkType = drinkType;
    }

    public String getDrinkType() { return drinkType; }
}
