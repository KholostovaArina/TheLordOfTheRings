package com.mycompany.army.visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WindowLabDescription {
    protected JFrame frame;
    protected JButton okButton;
    protected JTextArea description;  // Изменяем JTextField на JTextArea

    public WindowLabDescription(Font myFont, Image myImage) {
        myFont = myFont.deriveFont(Font.BOLD, 16);
        frame = new JFrame("Лабораторная работа 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);  // помещаю в центр окно

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (myImage != null) {
                    g.drawImage(myImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Используем JTextArea для многострочного текста
        description = new JTextArea(
            "Саурон готовится к решающей битве за Средиземье. Ваша задача " +
            "— собрать армию орков из разных племен (Мордор, Дол Гулдур, " +
            "Мглистые Горы, Серые горы). Каждое племя имеет уникальное " +
            "снаряжение (оружие, броня, знамёна), а орки могут быть разных " +
            "типов (базовый, командир, разведчик)."
        );
        description.setFont(myFont);
        description.setForeground(Color.WHITE);
        description.setOpaque(false); // чтобы фон был виден
        description.setEditable(false); // делаем текстовое поле не редактируемым
        description.setWrapStyleWord(true); // позволяет переносить слова целиком
        description.setLineWrap(true); // включает автоматический перенос строки

        okButton = new JButton("OK");
        okButton.setFont(myFont);
        okButton.setBackground(new Color(0, 30, 0));
        okButton.setForeground(Color.WHITE);
        okButton.setOpaque(true);
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Центрируем кнопку
        final Font myFinalFont = myFont;
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               MainWindow mw = new MainWindow(myFinalFont);
            }
        });
        panel.add(description);
        panel.add(okButton);
        frame.add(panel);
        frame.setVisible(true);
    }
}
