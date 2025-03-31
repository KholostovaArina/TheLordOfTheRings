package com.mycompany.army;

public class HeavySword implements Weapon {
    private final String name;

    public HeavySword(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
