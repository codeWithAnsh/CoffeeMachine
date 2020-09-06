package com.coffeeMachine.coffeeMachineImplementation;

import com.coffeeMachine.coffeeMachineDatatypes.MixtureSpecification;
import com.coffeeMachine.coffeeMachineExceptions.InsufficientQuantityException;

import java.util.concurrent.atomic.AtomicInteger;

/*
   This class initialises one of the MixtureSpecification required to prepare the beverage
   having availableQuantity as a parameter which is AtomicInteger so latest write is visible to
   all threads and this is required as threads must required to know the quantity available
   in the inventory for this mixture.
 */
public class Mixture extends MixtureSpecification {

    private final AtomicInteger availableQuantity;

    public Mixture(String drinkType, int quantity) {
       super(drinkType, quantity);
       availableQuantity = new AtomicInteger(quantity);
    }

    /*
      This method ensures the quantity and if required quantity is available than it updates
      the availableQuantity by reducing the required quantity from it.
      As availableQuantity is final and belongs to AtomicInteger class so we can't change its reference but
      can change the value inside it.
     */
    public void prepareDrink(int requiredQuantity) throws InsufficientQuantityException {
        int prev = availableQuantity.getAndUpdate(x -> x >= requiredQuantity ? x - requiredQuantity : x);

        if(prev - requiredQuantity < 0) {
            throw new InsufficientQuantityException(
                    this.getDrinkType() + " is not available", new MixtureSpecification(this.getDrinkType(), prev));
        }
    }

    /*
       This method returns the quantity
     */
    public int getQuantity() {
        return availableQuantity.intValue();
    }

    /*
       This method validate the quantity i.e. if required quantity is available in the inventory
     */
    public void validateQuantity(int requiredQuantity) throws InsufficientQuantityException {
        int currentAvailableQuantity = this.getQuantity();
        if(currentAvailableQuantity < requiredQuantity) {
            throw new InsufficientQuantityException(
                    this.getDrinkType() + " is not available", new MixtureSpecification(this.getDrinkType(), currentAvailableQuantity));
        }
    }
}
