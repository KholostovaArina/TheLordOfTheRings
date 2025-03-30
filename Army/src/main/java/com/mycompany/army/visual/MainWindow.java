package com.mycompany.army.visual;

import com.mycompany.army.*;
import com.mycompany.army.MordorOrkBuilderFactory;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;

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
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //создать орка
                //добавить орка на JTree
            }
        });
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
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.isLeaf() && selectedNode.getParent() != root) {
//                //Ork ork = (Ork) selectedNode.getUserObject();
//                OrkBuilderFactory mordorFactory = new MordorOrkBuilderFactory();
//                OrkBuilder builder = mordorFactory.createOrkBuilder();
//
//                OrkDirector director = new OrkDirector();
//                Ork basicOrk = director.createBasicOrk(builder);
//                System.out.println(basicOrk);
//                showOrkInfoDialog(basicOrk);
            }
        });

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

    private void showOrkInfoDialog(Ork ork) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Информация об орке - " + ork.getName());
        dialog.setSize(450, 400); 
        dialog.setLayout(new BorderLayout(10, 10));

        // Фоновая панель с изображением
        JPanel backgroundPanel = new JPanel() {
            private Image bgImage;

            {
                try {
                    // Укажите правильный путь к вашему изображению
                    bgImage = ImageIO.read(new File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\мрамор.jpg"));
                } catch (IOException e) {
                    System.err.println("Ошибка загрузки фона: " + e.getMessage());
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout(10, 10));

        // Основной контент
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false); // Делаем панель прозрачной

        // Заголовок с именем
        JLabel nameLabel = new JLabel(ork.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        nameLabel.setForeground(Color.WHITE);
        
        // Панель характеристик
        JPanel statsPanel = createStatsPanel(ork);
        statsPanel.setOpaque(false);

        // Панель снаряжения
        JPanel gearPanel = createGearPanel(ork);
        gearPanel.setOpaque(false);

        contentPanel.add(nameLabel);
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
        addProgressBar(panel, "Сила     :", ork.getStrength(), 100, fireRed);
        addProgressBar(panel, "Ловкость :", ork.getAgility(), 100, fireRed);
        addProgressBar(panel, "Интеллект:", ork.getIntelligence(), 100, fireRed);
        addProgressBar(panel, "Здоровье :", ork.getHealth(), 100, fireRed);

        return panel;
    }

    private JPanel createGearPanel(Ork ork) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));

        // Создаем кастомную границу с белым текстом
        TitledBorder border = BorderFactory.createTitledBorder("Снаряжение");
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD)); // Жирный шрифт

        panel.setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        addGearLabel(panel, "Оружие: " + ork.getWeapon());
        addGearLabel(panel, "Броня: " + ork.getArmor());
        addGearLabel(panel, "Знамя: " + ork.getBanner());

        return panel;
    }

    private void addGearLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE); // Белый текст для лучшей читаемости
        panel.add(label);
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
