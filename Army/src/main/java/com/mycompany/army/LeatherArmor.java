package com.mycompany.army;

public class LeatherArmor implements Armor {
    private final String name;

    public LeatherArmor(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
