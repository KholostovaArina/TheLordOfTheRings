package com.mycompany.army.visual;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

   public class BeautyUtils {
       private static Font customFont;
       static {
           try (InputStream fontStream = BeautyUtils.class.getResourceAsStream("/tolkiencyr.ttf")) {
               customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
               customFont = customFont.deriveFont(18f);
           } catch (IOException | FontFormatException e) {
               customFont = new Font("Serif", Font.PLAIN, 18);
           }
       }

       private static Image natureImage;
       private static Image mramorImage;
       private static Image paperImage;
       static {
           try {
               natureImage = ImageIO.read(BeautyUtils.class.getResourceAsStream("/лето.jpg"));
               paperImage = ImageIO.read(BeautyUtils.class.getResourceAsStream("/бумага.jpg"));
               mramorImage = ImageIO.read(BeautyUtils.class.getResourceAsStream("/мрамор.jpg"));
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
      
    public static Font getFont() {
        return customFont;
    }

    public static void setFontForAllComponents(Container container, Color color) {
        for (Component component : container.getComponents()) {
            component.setFont(customFont);
            component.setForeground(color);
            if (component instanceof Container container1) {
                setFontForAllComponents(container1, color);
            }
        }
    }

    public static void setFontForAllComponents(Container container) {
        for (Component component : container.getComponents()) {
            component.setFont(customFont);
            if (component instanceof Container container1) {
                setFontForAllComponents(container1);
            }
        }
    }
}
