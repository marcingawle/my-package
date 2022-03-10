package pl.pp.pack.ui;

import pl.pp.pack.api.dhl.services.StatusService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final StatusService statusService = new StatusService();

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Status paczki");

        JTextField idTextField = new JTextField();
        idTextField.setColumns(20);

        JButton searchButton = new JButton("Szukaj");
        JTextArea informationTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(informationTextArea);

        searchButton.addActionListener(e -> informationTextArea.setText(statusService.getStatusByID(idTextField.getText())));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(idTextField);
        panel.add(searchButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
