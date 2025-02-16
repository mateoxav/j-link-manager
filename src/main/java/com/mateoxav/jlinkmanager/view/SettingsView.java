package com.mateoxav.jlinkmanager.view;
import javax.swing.*;
import java.awt.*;

public class SettingsView extends JDialog {
    //private JCheckBox chkTemaOscuro;
    private JButton saveBtn, cancelBtn;

    public SettingsView(JFrame parent) {
        super(parent, "Settings", true);
        setSize(200, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 1));
        setResizable(false);

            //chkTemaOscuro = new JCheckBox("Activar Modo Claro");
            //add(chkTemaOscuro);
        JLabel creditsLbl = new JLabel("   Made with ❤️ by Mateo Xavier");
        add(creditsLbl);
        JLabel githubLbl = new JLabel("   Github😺: mateoxav");
        add(githubLbl);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(15, 130, 255));
        saveBtn.setForeground(Color.WHITE);
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(231, 76, 60));
        cancelBtn.setForeground(Color.WHITE);

        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        add(buttonPanel);

        saveBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Coming soon.");
            setVisible(false);
        });

        cancelBtn.addActionListener(e -> setVisible(false));
    }
}