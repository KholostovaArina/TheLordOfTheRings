package com.mycompany.army;

public class BannerWithRedEye implements Banner {
    private final String name = "Знамя с красным оком";

    @Override
    public String getName() {
        return name;
    }
}