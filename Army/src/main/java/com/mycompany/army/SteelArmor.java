
package com.mycompany.army;

public class SteelArmor implements Armor {
    private final String name;

    public SteelArmor(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
