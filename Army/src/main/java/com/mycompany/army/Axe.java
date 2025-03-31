package com.mycompany.army;

public class Axe implements Weapon {
    private final String name = "Топор";

    @Override
    public String getName() {
        return name;
    }
}