package com.mycompany.army;

public class LeatherArmor implements Armor {
    private final String name = "Кожаная броня";

    @Override
    public String getName() {
        return name;
    }
}
