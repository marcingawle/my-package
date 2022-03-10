package pl.pp.pack.ui;

import java.awt.*;

public class Runner {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
