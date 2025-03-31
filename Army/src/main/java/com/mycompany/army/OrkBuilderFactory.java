package com.mycompany.army;

public class OrkBuilderFactory {

    public static OrkBuilder createOrkBuilder(String tribe) {
        OrcGearFactory gearFactory = createGearFactory(tribe);
        return switch (tribe) {
            case "Мордор" -> new MordorOrkBuilder(gearFactory);
            case "Дол Гулдур" -> new DolGuldurOrkBuilder(gearFactory);
            case "Мглистые Горы" -> new MistyMountainsOrkBuilder(gearFactory);
            default -> throw new IllegalArgumentException("Неизвестное племя: " + tribe);
        };
    }

    private static OrcGearFactory createGearFactory(String tribe) {
        return switch (tribe) {
            case "Мордор" -> new MordorGearFactory();
            case "Дол Гулдур" -> new DolGuldurGearFactory();
            case "Мглистые Горы" -> new MistyMountainsGearFactory();
            default -> throw new IllegalArgumentException("Неизвестное племя: " + tribe);
        };
    }
}
