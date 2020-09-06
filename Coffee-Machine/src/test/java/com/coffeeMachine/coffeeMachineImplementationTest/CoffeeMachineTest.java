package com.coffeeMachine.coffeeMachineImplementationTest;

import com.coffeeMachine.coffeeMachineDatatypes.Beverage;
import com.coffeeMachine.coffeeMachineDatatypes.MixtureSpecification;
import com.coffeeMachine.coffeeMachineExceptions.BeveragePreparationException;
import com.coffeeMachine.coffeeMachineImplementation.CoffeeMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/*
   This class used to test the coffee machine - instance of coffee machine is initialised
   and machine is instructed to prepare the particular beverage
 */
class CoffeeMachineTest {

    private CoffeeMachine machine;

    @BeforeEach
    void instantiateMachine() {

        Beverage hotTea = new Beverage("hot_tea", Arrays.asList(
                new MixtureSpecification("hot_water", 200),
                new MixtureSpecification("hot_milk", 100),
                new MixtureSpecification("ginger_syrup", 10),
                new MixtureSpecification("sugar_syrup", 10),
                new MixtureSpecification("tea_leaves_syrup", 10)
        ));

        Beverage hotCoffee = new Beverage("hot_coffee", Arrays.asList(
                new MixtureSpecification("hot_water", 100),
                new MixtureSpecification("ginger_syrup", 30),
                new MixtureSpecification("hot_milk", 400),
                new MixtureSpecification("sugar_syrup", 50),
                new MixtureSpecification("tea_leaves_syrup", 30)
        ));

        Beverage blackTea = new Beverage("black_tea", Arrays.asList(
                new MixtureSpecification("hot_water", 300),
                new MixtureSpecification( "ginger_syrup", 30),
                new MixtureSpecification("sugar_syrup", 50),
                new MixtureSpecification("tea_leaves_syrup", 30)
        ));

        Beverage greenTea = new Beverage("green_tea", Arrays.asList(
                new MixtureSpecification("hot_water", 100),
                new MixtureSpecification("ginger_syrup", 30),
                new MixtureSpecification("green_mixture", 30)
        ));

        machine = new CoffeeMachine(1,Arrays.asList(
                new MixtureSpecification("hot_water", 500),
                new MixtureSpecification("hot_milk", 500),
                new MixtureSpecification("ginger_syrup", 100),
                new MixtureSpecification("sugar_syrup", 100),
                new MixtureSpecification("tea_leaves_syrup", 100)
            ), Arrays.asList(hotTea, hotCoffee, blackTea, greenTea)
        );
    }

    @Test
    public void testPrepareBeverage1() throws BeveragePreparationException {
        Beverage tea = machine.prepareBeverage("hot_tea");
        assertEquals("hot_tea", tea.getName());

        Beverage coffee = machine.prepareBeverage("hot_coffee");
        assertEquals("hot_coffee", coffee.getName());

        BeveragePreparationException e = Assertions.assertThrows(BeveragePreparationException.class, () -> {
           machine.prepareBeverage("green_tea");
        });
        assertEquals("green_mixture", e.getDrinkType());

        BeveragePreparationException e2 = Assertions.assertThrows(BeveragePreparationException.class, () -> {
           machine.prepareBeverage("black_tea");
        });
        assertEquals("hot_water", e2.getDrinkType());
    }

    @Test
    public void testPrepareBeverage2() throws BeveragePreparationException {
        Beverage tea = machine.prepareBeverage("hot_tea");
        assertEquals("hot_tea", tea.getName());

        Beverage blackTea = machine.prepareBeverage("black_tea");
        assertEquals("black_tea", blackTea.getName());

        BeveragePreparationException e = Assertions.assertThrows(BeveragePreparationException.class, () -> {
            machine.prepareBeverage("green_tea");
        });
        assertEquals("green_mixture", e.getDrinkType());

        BeveragePreparationException e2 = Assertions.assertThrows(BeveragePreparationException.class, () -> {
            machine.prepareBeverage("hot_coffee");
        });
        assertEquals("hot_water", e2.getDrinkType());
    }

    @Test
    public void testPrepareBeverage3() throws BeveragePreparationException {
        Beverage tea = machine.prepareBeverage("hot_coffee");
        assertEquals("hot_coffee", tea.getName());

        Beverage coffee = machine.prepareBeverage("black_tea");
        assertEquals("black_tea", coffee.getName());

        BeveragePreparationException e = Assertions.assertThrows(BeveragePreparationException.class, () -> {
            machine.prepareBeverage("green_tea");
        });
        assertEquals("green_mixture", e.getDrinkType());

        BeveragePreparationException e2 = Assertions.assertThrows(BeveragePreparationException.class, () -> {
            machine.prepareBeverage("hot_tea");
        });
        assertEquals("hot_water", e2.getDrinkType());
    }

    @Test
    public void testPrepareBeverage4() throws BeveragePreparationException {

        BeveragePreparationException e = Assertions.assertThrows(BeveragePreparationException.class, () -> {
            machine.prepareBeverage("green_tea");
        });
        assertEquals("green_mixture", e.getDrinkType());

        Beverage blackTea = machine.prepareBeverage("black_tea");
        assertEquals("black_tea", blackTea.getName());

        Beverage hotTea = machine.prepareBeverage("hot_tea");
        assertEquals("hot_tea", hotTea.getName());

        BeveragePreparationException e2 = Assertions.assertThrows(BeveragePreparationException.class, () -> {
            machine.prepareBeverage("hot_coffee");
        });
        assertEquals("hot_water", e2.getDrinkType());

    }

    @Test
    public void testPrepareBeverage5() throws BeveragePreparationException {

        Beverage hotCoffee = machine.prepareBeverage("hot_coffee");
        assertEquals("hot_coffee", hotCoffee.getName());

        BeveragePreparationException e = Assertions.assertThrows(BeveragePreparationException.class, () -> {
            machine.prepareBeverage("green_tea");
        });
        assertEquals("green_mixture", e.getDrinkType());

        Beverage coffee = machine.prepareBeverage("black_tea");
        assertEquals("black_tea", coffee.getName());

        BeveragePreparationException e2 = Assertions.assertThrows(BeveragePreparationException.class, () -> {
            machine.prepareBeverage("hot_tea");
        });
        assertEquals("hot_water", e2.getDrinkType());

    }
}