package com.mycompany.army.visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow {
    protected JFrame frame;
    protected JButton yesButton;
    protected JButton noButton;
    protected JLabel hello;
    protected JLabel question;

    public WelcomeWindow() {
         
        frame = new JFrame("Лабораторная работа 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);  //помещает в центр окно

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

        hello = new JLabel("Приветствую Вас!", SwingConstants.CENTER);
        question = new JLabel("<html>Хотите ли Вы узнать чуть больше<br>о моей лабораторной работе?</html>", SwingConstants.CENTER);
       
        hello.setAlignmentX(Component.CENTER_ALIGNMENT);
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        yesButton = new JButton("YES");
        yesButton.setBackground(new Color(0, 30, 0));
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               WindowLabDescription wld = new WindowLabDescription();
            }
        });
        
        noButton = new JButton("SKIP");
        noButton.setBackground(new Color(0, 30, 0));
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               MainWindow mw = new MainWindow();
            }
        });
        
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(Box.createVerticalStrut(40));
        panel.add(hello);
        panel.add(Box.createVerticalStrut(50));
        panel.add(question);
        panel.add(Box.createVerticalStrut(70));
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());

        BeautyUtils.setFontForAllComponents(panel, Color.WHITE);
        frame.add(panel);
        frame.setVisible(true);
    }
}
