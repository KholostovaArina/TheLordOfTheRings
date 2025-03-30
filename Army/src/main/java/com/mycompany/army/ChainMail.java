
package com.mycompany.army;


public class ChainMail implements Armor {
    private final String name;

    public ChainMail(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
