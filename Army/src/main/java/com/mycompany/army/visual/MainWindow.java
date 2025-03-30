package com.mycompany.army.visual;

import com.mycompany.army.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

public class MainWindow {
    private Image backgroundImage;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private Map<String, Map<String, Ork>> orksByTribe = new HashMap<>();

    public MainWindow(Font myFont) {
        try {
            backgroundImage = javax.imageio.ImageIO.read(
                new java.io.File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\бумага.jpg"));
        } catch (java.io.IOException e) {
            System.err.println("Ошибка загрузки фона: " + e.getMessage());
        }
        WelcomeWindow.setUIFont(new javax.swing.plaf.FontUIResource(myFont));

        JFrame frame = new JFrame("Создание армии орков");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        // Главная панель с фоном
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
        mainPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Компоненты интерфейса
        JLabel tribeLbl = new JLabel("Выберите племя орка:");
        tribeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(tribeLbl, gbc);

        JLabel roleLbl = new JLabel("Выберите роль в армии:");
        roleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        mainPanel.add(roleLbl, gbc);

        // Панели с радио-кнопками
        ButtonGroup tribeGroup = new ButtonGroup();
        JPanel tribePanel = createRadioPanel(tribeGroup, "Мордор", "Дол Гулдур", "Мглистые Горы");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(tribePanel, gbc);

        ButtonGroup roleGroup = new ButtonGroup();
        JPanel rolePanel = createRadioPanel(roleGroup, "базовый", "командир", "разведчик");
        gbc.gridx = 1;
        mainPanel.add(rolePanel, gbc);

        // Кнопка создания
        JButton createButton = new JButton("Создать");
        createButton.addActionListener(createOrkAction(frame, tribeGroup, roleGroup));
        createButton.setBackground(new Color(210, 180, 140));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(createButton, gbc);

        // Настройка дерева
        setupTree();
        JPanel treePanel = createTreePanel();

        // Добавление компонентов во фрейм
        frame.setLayout(new BorderLayout());
        frame.add(treePanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private ActionListener createOrkAction(JFrame frame, ButtonGroup tribeGroup, ButtonGroup roleGroup) {
        return e -> {
            String selectedTribe = getSelectedButtonText(tribeGroup);
            String selectedRole = getSelectedButtonText(roleGroup);

            if (selectedTribe == null || selectedRole == null) {
                JOptionPane.showMessageDialog(frame, "Пожалуйста, выберите племя и роль.", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Ork ork = createOrk(selectedTribe, selectedRole);
            addOrkToTree(ork, selectedTribe);
        };
    }

    private void setupTree() {
        root = new DefaultMutableTreeNode("Армия орков");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setBackground(new Color(210, 180, 140, 150));
        tree.setForeground(new Color(50, 50, 50));
        tree.setOpaque(false);
        tree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node == null || !node.isLeaf() || node.getParent() == root) return;
            
            String orkName = node.getUserObject().toString();
            String tribe = node.getParent().toString();
            Ork ork = findOrkByName(orkName, tribe);
            if (ork != null) showOrkInfoDialog(ork);
        });
    }

    private JPanel createTreePanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(250, 400));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scrollPane);
        return panel;
    }

    private void addOrkToTree(Ork ork, String tribe) {
        DefaultMutableTreeNode tribeNode = findOrCreateTribeNode(root, tribe);
        tribeNode.add(new DefaultMutableTreeNode(ork.getName()));
        treeModel.reload();
        tree.expandPath(new javax.swing.tree.TreePath(tribeNode.getPath()));
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

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private Ork createOrk(String tribe, String role) {
        OrkBuilderFactory factory = createFactoryForTribe(tribe);
        OrkBuilder builder = factory.createOrkBuilder();

        OrkDirector director = new OrkDirector(builder);
        Ork ork = null;
        switch (role) {
            case "базовый":
                ork = director.createBasicOrk();
                break;
            case "командир":
                ork = director.createLeaderOrk();
                break;
            case "разведчик":
                ork = director.createScoutOrk();
                break;
        }

        // Сохраняем орка в структуре данных
        orksByTribe
                .computeIfAbsent(tribe, k -> new HashMap<>())
                .put(ork.getName(), ork);

        return ork;
    }

    private OrkBuilderFactory createFactoryForTribe(String tribe) {
        switch (tribe) {
            case "Мордор":
                return new MordorOrkBuilderFactory();
            case "Дол Гулдур":
                return new DolGuldurOrkBuilderFactory();
            case "Мглистые Горы":
                return new MistyMountainsOrkBuilderFactory();
            default:
                throw new IllegalArgumentException("Неизвестное племя: " + tribe);
        }
    }

    private DefaultMutableTreeNode findOrCreateTribeNode(DefaultMutableTreeNode root, String tribe) {
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(i);
            if (child.getUserObject().equals(tribe)) {
                return child;
            }
        }
        DefaultMutableTreeNode newTribeNode = new DefaultMutableTreeNode(tribe);
        root.add(newTribeNode);
        return newTribeNode;
    }

    private Ork findOrkByName(String name, String tribe) {
        return orksByTribe.getOrDefault(tribe, new HashMap<>()).get(name);
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
                    bgImage = javax.imageio.ImageIO.read(new java.io.File("C:\\Users\\GOSPOGA\\OneDrive\\Рабочий стол\\lr\\мрамор.jpg"));
                } catch (Exception ex) {
                    System.err.println("Ошибка загрузки фона: " + ex.getMessage());
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
        contentPanel.setOpaque(false);

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
        addProgressBar(panel, "Сила          :", ork.getStrength(), 100, fireRed);
        addProgressBar(panel, "Ловкость  :", ork.getAgility(), 100, fireRed);
        addProgressBar(panel, "Интеллект:", ork.getIntelligence(), 100, fireRed);
        addProgressBar(panel, "Здоровье  :", ork.getHealth(), 100, fireRed);
        return panel;
    }

    private JPanel createGearPanel(Ork ork) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        TitledBorder border = BorderFactory.createTitledBorder("Снаряжение");
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD));
        panel.setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        addGearLabel(panel, "Оружие: " + ork.getWeapon().getName());
        addGearLabel(panel, "Броня: " + ork.getArmor().getName());
        addGearLabel(panel, "Знамя: " + ork.getBanner().getName());
        return panel;
    }

    private void addGearLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
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