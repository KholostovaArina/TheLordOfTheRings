package com.mycompany.army.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.plaf.FontUIResource;

public class WelcomeWindow {
    protected JFrame frame;
    protected JButton yesButton;
    protected JButton noButton;
    protected JLabel hello;
    protected JLabel question;

    public WelcomeWindow() {
         // шрифт
        Font customFont;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\tolkiencyr.ttf"));
            customFont = customFont.deriveFont(Font.BOLD, 18);
        } catch (Exception e) {
            customFont = new Font("Serif", Font.BOLD, 18); // запасной вариант
            e.printStackTrace();
        }
        final Font finalCustomFont = customFont;
        setUIFont(new FontUIResource(customFont));
        
        frame = new JFrame("Лабораторная работа 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);  //помещаю в центр окно

        

        // Загрузка фона
        Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\лето.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Image finalBackgroundImage = backgroundImage; // Конечное изображение

        // Панель с фоном
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBackgroundImage != null) {
                    g.drawImage(finalBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        hello = new JLabel("Приветствую Вас!", SwingConstants.CENTER);
        question = new JLabel("<html>Хотите ли Вы узнать чуть больше<br>о моей лабораторной работе?</html>", SwingConstants.CENTER);
       
        hello.setForeground(Color.WHITE);
        question.setForeground(Color.WHITE);
        hello.setAlignmentX(Component.CENTER_ALIGNMENT);
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // Делаем панель прозрачной, чтобы фон был виден

        yesButton = new JButton("YES");
        yesButton.setBackground(new Color(0,30,0));
        yesButton.setForeground(Color.WHITE);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               WindowLabDescription wld = new WindowLabDescription(finalCustomFont, finalBackgroundImage);
            }
        });
        
        noButton = new JButton("SKIP");
        noButton.setBackground(new Color(0,30,0));
        noButton.setForeground(Color.WHITE);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               MainWindow mw = new MainWindow(finalCustomFont);
            }
        });
        
  
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(Box.createVerticalStrut(20));
        panel.add(hello);
        panel.add(Box.createVerticalStrut(10));
        panel.add(question);
        panel.add(Box.createVerticalStrut(30));
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);
    }
    
    public static void setUIFont(FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}