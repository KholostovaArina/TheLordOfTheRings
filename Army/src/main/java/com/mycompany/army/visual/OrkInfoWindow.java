package com.mycompany.army.visual;

import com.mycompany.army.Ork;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;


public class OrkInfoWindow {
     protected void showOrkInfoDialog(Ork ork) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Информация об орке - " + ork.getRole() + " " + ork.getName());
        dialog.setSize(450, 400);
        dialog.setLayout(new BorderLayout(10, 10));

        // Фоновая панель с изображением
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (BeautyUtils.getMramorImage() != null) {
                    g.drawImage(BeautyUtils.getMramorImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout(10, 10));

        // Основной контент
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // Заголовок с именем
        JLabel nameLabel = new JLabel(ork.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        // Панель характеристик
        JPanel statsPanel = createStatsPanel(ork);
        statsPanel.setOpaque(false);

        // Панель снаряжения
        JPanel gearPanel = createGearPanel(ork);
        gearPanel.setOpaque(false);
        BeautyUtils.setFontForAllComponents(gearPanel, Color.WHITE);

        contentPanel.add(nameLabel);

        BeautyUtils.setFontForAllComponents(contentPanel, Color.WHITE);
        contentPanel.add(statsPanel);
        contentPanel.add(gearPanel);

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        dialog.add(backgroundPanel);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private JPanel createStatsPanel(Ork ork) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Color fireRed = new Color(128, 10, 0);
        addProgressBar(panel, "Сила         :", ork.getStrength(), 100, fireRed);
        addProgressBar(panel, "Ловкость  :", ork.getAgility(), 100, fireRed);
        addProgressBar(panel, "Интеллект:", ork.getIntelligence(), 100, fireRed);
        addProgressBar(panel, "Здоровье   :", ork.getHealth(), 100, fireRed);
        BeautyUtils.setFontForAllComponents(panel);
        return panel;
    }

    private JPanel createGearPanel(Ork ork) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        TitledBorder border = BorderFactory.createTitledBorder("Снаряжение");
        border.setTitleFont(BeautyUtils.getFont());
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD));
        panel.setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        panel.add(new JLabel("Оружие: " + ork.getWeapon().getName()));
        panel.add(new JLabel( "Броня: " + ork.getArmor().getName()));
        panel.add(new JLabel( "Знамя: " + ork.getBanner().getName()));

        if (ork.getAdditionalItem() != null) {
            panel.add(new JLabel( "Д О П: " + ork.getAdditionalItem()));
        }
        return panel;
    }

    private void addProgressBar(JPanel panel, String label, int value, int max, Color color) {
        JPanel row = new JPanel(new BorderLayout(10, 5));
        JLabel textLabel = new JLabel(label);
        JProgressBar progressBar = new JProgressBar(0, max);
        progressBar.setValue(value);
        progressBar.setString(value + "/" + max);
        progressBar.setStringPainted(true);
        progressBar.setForeground(color);
        progressBar.setBackground(new Color(240, 240, 240));
        row.add(textLabel, BorderLayout.WEST);
        row.add(progressBar, BorderLayout.CENTER);
        panel.add(row);
    }
}
