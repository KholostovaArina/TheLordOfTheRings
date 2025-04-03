package com.mycompany.army.visual;

import com.mycompany.army.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class MainWindow {
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private Map<String, Map<String, Ork>> orksByTribe = new HashMap<>();
    protected JFrame frame;

    public MainWindow() {
       
        frame = new JFrame("Создание армии орков");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        // Главная панель с фоном
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (BeautyUtils.getPaperImage() != null) {
                    g.drawImage(BeautyUtils.getPaperImage(), 0, 0, getWidth(), getHeight(), this);
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
        frame.setVisible(false);
        BeautyUtils.setFontForAllComponents(mainPanel);
        BeautyUtils.setFontForAllComponents(treePanel);
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
        
        tree.setBackground(new Color(210, 180, 140));
        tree.setForeground(new Color(50, 50, 50));
        tree.setOpaque(false);
        tree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node == null || !node.isLeaf() || node.getParent() == root) return;
            String orkName = node.getUserObject().toString();
            String tribe = node.getParent().toString();
            Ork ork = orksByTribe.getOrDefault(tribe, new HashMap<>()).get(orkName);
           
            OrkInfoWindow orkInfo = new OrkInfoWindow();
            if (ork != null) orkInfo.showOrkInfoDialog(ork);
        });
    }

    private JPanel createTreePanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (BeautyUtils.getPaperImage() != null) {
                    g.drawImage(BeautyUtils.getPaperImage(), 0, 0, getWidth(), getHeight(), this);
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
        OrkBuilder builder = OrkBuilderFactory.createOrkBuilder(tribe);
        OrkDirector director = new OrkDirector(builder);
        Ork ork = null;
        switch (role) {
            case "базовый" -> ork = director.createBasicOrk();
            case "командир" -> ork = director.createLeaderOrk();
            case "разведчик" -> ork = director.createScoutOrk();
        }

        // Сохраняем орка в структуре данных
        orksByTribe
                .computeIfAbsent(tribe, k -> new HashMap<>())
                .put(ork.getName(), ork);

        return ork;
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

}