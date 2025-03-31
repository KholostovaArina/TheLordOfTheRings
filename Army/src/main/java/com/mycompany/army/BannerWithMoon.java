package com.mycompany.army;

public class BannerWithMoon implements Banner {
    private final String name = "Знамя с Луной";

    @Override
    public String getName() {
        return name;
    }
}