package com.mateoxav.jlinkmanager.view;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class MainView extends JFrame {
    private JList<String> linksList;
    private DefaultListModel<String> listModel;
    private JButton searchBtn, addBtn, deleteBtn, editBtn, settingsBtn;
    private JLabel titleLbl, urlLbl, categoryLbl, notesLbl;

    public MainView() {
        setTitle("J Link Manager - Made By Mateo Xavier");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        try {
            Image icon = new ImageIcon(getClass().getResource("/icon.png")).getImage();
            setIconImage(icon);
        } catch (Exception e) {
            System.err.println("⚠️ Unable to load icon.");
        }

        listModel = new DefaultListModel<>();
        linksList = new JList<>(listModel);

        linksList.setBackground(new Color(2, 2, 2));
        linksList.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(linksList);
        scrollPane.setPreferredSize(new Dimension(250, getHeight()));
        add(scrollPane, BorderLayout.WEST);

        JPanel previewPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        previewPanel.setBackground(new Color(10,10,10));
        previewPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLbl = new JLabel("Title: ");
        urlLbl = new JLabel("URL: ");
        categoryLbl = new JLabel("Category: ");
        notesLbl = new JLabel("Notes: ");

        urlLbl.setForeground(new Color(15, 130, 255));
        urlLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        urlLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openLink(urlLbl.getText());
            }
        });

        titleLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        titleLbl.setForeground(new Color(149, 165, 166));
        titleLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                copyToClipboard(urlLbl.getText());
            }
        });

        previewPanel.add(titleLbl);
        previewPanel.add(urlLbl);
        previewPanel.add(categoryLbl);
        previewPanel.add(notesLbl);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(10,10,10));
        searchBtn = new JButton("🔍");
        searchBtn.setBackground(new Color(15, 130, 255));
        searchBtn.setForeground(Color.WHITE);
        addBtn = new JButton("Add");
        addBtn.setBackground(new Color(15, 130, 255));
        addBtn.setForeground(Color.WHITE);
        editBtn = new JButton("Edit");
        editBtn.setBackground(new Color(15, 130, 255));
        editBtn.setForeground(Color.WHITE);
        deleteBtn = new JButton("Delete");
        deleteBtn.setBackground(new Color(15, 130, 255));
        deleteBtn.setForeground(Color.WHITE);
        settingsBtn = new JButton("⚙");
        settingsBtn.setBackground(new Color(15, 130, 255));
        settingsBtn.setForeground(Color.WHITE);

        buttonPanel.add(searchBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(settingsBtn);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(10,10,10));
        rightPanel.add(previewPanel, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.CENTER);
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open link.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        JOptionPane.showMessageDialog(this, "URL copied to clipboard.", "Copied", JOptionPane.INFORMATION_MESSAGE);
    }

    public JButton getAddBtn() { return addBtn; }
    public JButton getDeleteBtn() { return deleteBtn; }
    public JButton getEditBtn() { return editBtn; }
    public JButton getSearchBtn() { return searchBtn; }
    public JButton getSettingsBtn() { return settingsBtn; }
    public DefaultListModel<String> getListModel() { return listModel; }
    public JList<String> getLinksList() { return linksList; }
    public JLabel getTitleLbl() { return titleLbl; }
    public JLabel getUrlLbl() { return urlLbl; }
    public JLabel getCategoryLbl() { return categoryLbl; }
    public JLabel getNotesLbl() { return notesLbl; }
}