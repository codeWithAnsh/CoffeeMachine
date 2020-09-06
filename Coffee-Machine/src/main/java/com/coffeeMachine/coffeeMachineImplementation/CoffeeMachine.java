package com.coffeeMachine.coffeeMachineImplementation;

import com.coffeeMachine.coffeeMachineDatatypes.Beverage;
import com.coffeeMachine.coffeeMachineDatatypes.MixtureSpecification;
import com.coffeeMachine.coffeeMachineExceptions.*;

import java.util.*;

/*
   This the CoffeeMachine class , it initialises the coffee machine with Mixtures available in inventory
   and beverages that can be prepared with this coffee machine with the number of slots.
   This is the immutable class i.e. its state can't be changed.
 */
public class CoffeeMachine {

    private final int numberOfSlots;

    private final Map<String, Mixture> inventory;

    private final Map<String, Beverage> beverages;


    public CoffeeMachine(int numberOfSlots, List<MixtureSpecification> mixturesSpecificationInInventory, List<Beverage> beveragesOffered) {
        this.numberOfSlots = numberOfSlots;
        this.inventory = new HashMap<>();
        this.beverages = new HashMap<>();

        for (MixtureSpecification mixtureSpecification : mixturesSpecificationInInventory) {
            inventory.put(mixtureSpecification.getDrinkType(), new Mixture(mixtureSpecification.getDrinkType(), mixtureSpecification.getTotalQuantity()));
        }

        for(Beverage beverage: beveragesOffered) {
            beverages.put(beverage.getName().toLowerCase(), beverage);
        }

    }

    /*
        This method prepares the beverage take the beverage name as input
     */
    public Beverage prepareBeverage(String beverageName) throws BeveragePreparationException {

        Beverage beverage = beverages.get(beverageName);
        this.processBeverage(beverage);

        // No exception means beverage is prepared.
        System.out.println(beverage.getName() + " is prepared");
        return beverage;
    }

    /*
        This method process the beverage takes the Beverage as Object having mixture details
        that are required to prepare the beverage.
        This is the synchronized method at a time only one thread can access this method
     */
    private synchronized void processBeverage(Beverage beverage) throws BeveragePreparationException {
        List<MixtureSpecification> requiredMixturesSpecification = beverage.getMixturesSpecification();

        // Validate all required ingredients to make mixture are present
        for (MixtureSpecification mixtureRequired : requiredMixturesSpecification) {
            if (!inventory.containsKey(mixtureRequired.getDrinkType())) {
                throw new BeveragePreparationException(beverage.getName() + " cannot be prepared because "
                        + mixtureRequired.getDrinkType() + " is not available", mixtureRequired.getDrinkType());
            }
        }

        // Validate all required ingredients to make mixture are present in required quantity
        for (MixtureSpecification mixtureRequired : requiredMixturesSpecification) {
            Mixture mixtureAvailable = inventory.get(mixtureRequired.getDrinkType());
            try {
                mixtureAvailable.validateQuantity(mixtureRequired.getTotalQuantity());
            } catch (InsufficientQuantityException e) {
                throw new BeveragePreparationException(beverage.getName() + " cannot be prepared because item "
                        + mixtureRequired.getDrinkType() + " is not sufficient", mixtureRequired.getDrinkType());
            }
        }

        // Prepare the drink using the mixture required to prepare the drink
        for (MixtureSpecification mixtureRequired : requiredMixturesSpecification) {
            Mixture mixture = inventory.get(mixtureRequired.getDrinkType());
            try {
                mixture.prepareDrink(mixtureRequired.getTotalQuantity());
            } catch (InsufficientQuantityException e) {
                System.err.println("Error occurred while preparing the " + beverage.getName());
                throw new BeveragePreparationException("Error occurred while preparing the " + beverage.getName() + "Try Again ", beverage.getName());
            }
        }
    }
}
