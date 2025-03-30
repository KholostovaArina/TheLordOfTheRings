
package com.mycompany.army;

import com.mycompany.army.visual.WelcomeWindow;
import java.awt.Font;
import java.io.File;


public class Army {

    public static void main(String[] args) {

//        WelcomeWindow ww = new WelcomeWindow();
        
       // Создаем фабрику строителей для Мордора
        OrkBuilderFactory mordorFactory = new MordorOrkBuilderFactory();

        // Создаем директора
        OrkDirector director = new OrkDirector(mordorFactory.createOrkBuilder());

        // Создаем орков
        Ork basicOrk = director.createBasicOrk();
        Ork leaderOrk = director.createLeaderOrk();
        Ork scoutOrk = director.createScoutOrk();

        // Выводим информацию об орках
        System.out.println(basicOrk);
        System.out.println(leaderOrk);
        System.out.println(scoutOrk);
    }
}
