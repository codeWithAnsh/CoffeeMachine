package com.coffeeMachine.coffeeMachineDatatypes;

import java.util.List;

/*
   This the Beverage class - which depicts the List of mixtures and its quantity required to prepare the
   particular beverage.
   Eg - Beverage - hot_tea(List of mixture required - hot_water - 200 , hot_milk - 100, ginger_syrup - 10,
   sugar_syrup - 10, tea_leaves_syrup - 10.
 */
public class Beverage {

    private final String name;

    private final List<MixtureSpecification> mixturesSpecification;

    public Beverage(String name, List<MixtureSpecification> mixturesSpecification) {
        this.name = name.toLowerCase();
        this.mixturesSpecification = mixturesSpecification;
    }


    public String getName() {
        return name;
    }

    public List<MixtureSpecification> getMixturesSpecification() { return mixturesSpecification; }
}
