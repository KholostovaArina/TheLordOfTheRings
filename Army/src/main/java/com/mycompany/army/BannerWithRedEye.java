package com.mycompany.army;

public class BannerWithRedEye implements Banner {
    private final String name;

    public BannerWithRedEye(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}