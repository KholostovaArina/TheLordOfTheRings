package com.mycompany.army.visual;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BeautyUtils {
    private static Font customFont;
    static{
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\tolkiencyr.ttf"));
            customFont = customFont.deriveFont(18f);
        } catch (IOException | FontFormatException e) {
            customFont = new Font("Serif", Font.PLAIN, 18);
        }
    }
    
    public static Font getFont(){
        return customFont;
    }
    private static Image natureImage;
    private static Image mramorImage;
    private static Image paperImage;   
    static {
        try {
            natureImage = ImageIO.read(new File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\лето.jpg"));
            paperImage = ImageIO.read(new File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\бумага.jpg"));
            mramorImage = ImageIO.read(new File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\мрамор.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image getNatureImage() {
        return natureImage;
    }
    
     public static Image getPaperImage() {
        return paperImage;
    }
     
      public static Image getMramorImage() {
        return mramorImage;
    }

    public static void setFontForAllComponents(Container container, Color color) {
        for (Component component : container.getComponents()) {
            component.setFont(customFont);
            component.setForeground(color);
            if (component instanceof Container) {
                setFontForAllComponents((Container) component, color);
            }
        }
    }

    public static void setFontForAllComponents(Container container) {
        for (Component component : container.getComponents()) {
            component.setFont(customFont);
            if (component instanceof Container) {
                setFontForAllComponents((Container) component);
            }
        }
    }
}