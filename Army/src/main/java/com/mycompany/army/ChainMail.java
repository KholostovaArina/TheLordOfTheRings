package com.mycompany.army;

public class ChainMail implements Armor {
    private final String name = "Кольчуга";

    @Override
    public String getName() {
        return name;
    }
}
