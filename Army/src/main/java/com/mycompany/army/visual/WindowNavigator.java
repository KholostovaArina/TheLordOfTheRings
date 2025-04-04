package com.mycompany.army.visual;

public class WindowNavigator {
    private final WelcomeWindow welcomeWindow;
    private final WindowLabDescription descriptionWindow;
    private final MainWindow mainWindow;

    public WindowNavigator() {
        welcomeWindow = new WelcomeWindow();
        welcomeWindow.frame.setVisible(true);
        descriptionWindow = new WindowLabDescription();
        mainWindow = new MainWindow();

        setupNavigation();
    }

    private void setupNavigation() {
        // WelcomeWindow -> DescriptionWindow
        welcomeWindow.yesButton.addActionListener(e -> {
            welcomeWindow.frame.dispose();
            descriptionWindow.frame.setVisible(true);
        });

        // WelcomeWindow -> MainWindow
        welcomeWindow.noButton.addActionListener(e -> {
            welcomeWindow.frame.dispose();
            mainWindow.frame.setVisible(true);
        });

        // DescriptionWindow -> MainWindow
        descriptionWindow.okButton.addActionListener(e -> {
            descriptionWindow.frame.dispose();
            mainWindow.frame.setVisible(true);
        });
    }
}