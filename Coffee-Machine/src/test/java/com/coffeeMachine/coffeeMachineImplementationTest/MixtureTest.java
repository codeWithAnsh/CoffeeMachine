package com.coffeeMachine.coffeeMachineImplementationTest;

import com.coffeeMachine.coffeeMachineDatatypes.MixtureSpecification;
import com.coffeeMachine.coffeeMachineExceptions.InsufficientQuantityException;
import com.coffeeMachine.coffeeMachineImplementation.Mixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MixtureTest {

    @Test
    public void testPrepareDrink() {
        InsufficientQuantityException e = Assertions.assertThrows(InsufficientQuantityException.class, () -> {
            Mixture mixture = new Mixture("tea", 100);
            mixture.prepareDrink(105);
        });

        MixtureSpecification mixtureSpecification = e.getMixtureSpecification();
        assertEquals("tea", mixtureSpecification.getDrinkType());
        assertEquals(100, mixtureSpecification.getTotalQuantity());
    }

    @Test
    public void testPrepareDrinkBeverageNotPrepared() {
        Mixture mixture = new Mixture("", 100);
        Assertions.assertThrows(InsufficientQuantityException.class, () -> {
            mixture.prepareDrink(105);
        });
        assertEquals(100, mixture.getQuantity());
    }

    @Test
    public void testPrepareDrinkBeveragePrepared() throws InsufficientQuantityException {
        Mixture mixture = new Mixture("", 100);
        mixture.prepareDrink(95);
        assertEquals(5, mixture.getQuantity());
    }

    @Test
    public void testValidateQuantityMixtureNotPrepared() {
        InsufficientQuantityException e = Assertions.assertThrows(InsufficientQuantityException.class, () -> {
            Mixture mixture = new Mixture("hot_tea", 100);
            mixture.validateQuantity(120);
        });
        MixtureSpecification mixtureSpecification = e.getMixtureSpecification();
        assertEquals("hot_tea", mixtureSpecification.getDrinkType());
        assertEquals(100, mixtureSpecification.getTotalQuantity());
    }

    @Test
    public void testValidateQuantityMixturePrepared() throws InsufficientQuantityException {
        Mixture mixture = new Mixture("hot_tea", 100);
        mixture.validateQuantity(80);
        assertEquals(100, mixture.getQuantity());

    }

}