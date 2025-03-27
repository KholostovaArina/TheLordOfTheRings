package com.mycompany.army.visual;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainWindow {
    private Image backgroundImage; 

    public MainWindow(Font myFont) {
        
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\бумага.jpg"));
        } catch (IOException e) {
            System.err.println("Ошибка загрузки фона: " + e.getMessage());
        }

        WelcomeWindow.setUIFont(new FontUIResource(myFont));
        JFrame frame = new JFrame("Создание армии орков");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) { 
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Панель для меток вопросов и радиокнопок
        JLabel tribeLbl = new JLabel("Выберите племя орка:");
        tribeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(tribeLbl, gbc);

        JLabel roleLbl = new JLabel("Выберите роль в армии:");
        roleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        mainPanel.add(roleLbl, gbc);

        // Группы радиокнопок (оставил комментарии вашего кода)
        ButtonGroup leftGroup = new ButtonGroup();
        JPanel leftRadioPanel = createRadioPanel(leftGroup, "Мордор", "Дол Гулдур", "Мглистые Горы");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(leftRadioPanel, gbc);

        ButtonGroup rightGroup = new ButtonGroup();
        JPanel rightRadioPanel = createRadioPanel(rightGroup, "базовый", "командир", "разведчик");
        gbc.gridx = 1;
        mainPanel.add(rightRadioPanel, gbc);

        // Кнопка
        JButton createButton = new JButton("Создать");
        createButton.setBackground(new Color(210, 180, 140));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(createButton, gbc);

        // Дерево
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Армия орков");
        String[] tribes = {"Мордор", "Дол Гулдур", "Мглистые Горы"};
        for (String tribe : tribes) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(tribe);
            node.add(new DefaultMutableTreeNode("Воин 1"));
            node.add(new DefaultMutableTreeNode("Воин 2"));
            root.add(node);
        }

        JTree tree = new JTree(root);
        tree.setBackground(new Color(210, 180, 140));
        JScrollPane treeScrollPane = new JScrollPane(tree);

        frame.setLayout(new BorderLayout());
        frame.add(treeScrollPane, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createRadioPanel(ButtonGroup group, String... options) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (String option : options) {
            JRadioButton radio = new JRadioButton(option);
            radio.setOpaque(false);
            group.add(radio);
            panel.add(radio);
        }
        return panel;
    }
}