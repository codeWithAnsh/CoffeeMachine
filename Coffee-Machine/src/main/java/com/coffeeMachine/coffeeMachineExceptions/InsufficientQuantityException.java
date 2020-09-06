package com.coffeeMachine.coffeeMachineExceptions;

import com.coffeeMachine.coffeeMachineDatatypes.MixtureSpecification;

/*
   This Exception occurs when mixture is not present in sufficient quantity
 */
public class InsufficientQuantityException extends Exception {

    private final MixtureSpecification mixtureSpecification;

    public InsufficientQuantityException(String message, MixtureSpecification mixtureSpecification) {
        super(message);
        this.mixtureSpecification = mixtureSpecification;
    }

    public MixtureSpecification getMixtureSpecification() {
        return this.mixtureSpecification;
    }
}
