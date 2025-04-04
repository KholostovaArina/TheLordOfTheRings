package com.mycompany.army.visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.*;

public class WindowLabDescription {
    protected JFrame frame;
    protected JButton okButton;
    protected JTextArea description;  

    public WindowLabDescription() {
        frame = new JFrame("Лабораторная работа 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);  // помещаю в центр окно

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (BeautyUtils.getNatureImage()!= null) {
                    g.drawImage(BeautyUtils.getNatureImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        description = new JTextArea(
            "Саурон готовится к решающей битве за Средиземье. Ваша задача " +
            "— собрать армию орков из разных племен (Мордор, Дол Гулдур, " +
            "Мглистые Горы, Серые горы). Каждое племя имеет уникальное " +
            "снаряжение (оружие, броня, знамёна), а орки могут быть разных " +
            "типов (базовый, командир, разведчик)."
        );

        description.setOpaque(false);
        description.setEditable(false); // делаем текстовое поле не редактируемым
        description.setWrapStyleWord(true); // позволяет переносить слова целиком
        description.setLineWrap(true); // включает автоматический перенос строки

        okButton = new JButton("OK");
        okButton.setBackground(new Color(0, 30, 0));
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Центрируем кнопку
        
        panel.add(Box.createVerticalStrut(70));
        panel.add(description);
        panel.add(okButton);
        
        BeautyUtils.setFontForAllComponents(panel, Color.WHITE);
        frame.add(panel);
        frame.setVisible(false);
    }
}