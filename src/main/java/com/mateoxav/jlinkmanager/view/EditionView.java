package com.mateoxav.jlinkmanager.view;
import com.mateoxav.jlinkmanager.model.Link;
import javax.swing.*;
import java.awt.*;

public class EditionView extends JDialog {
    private JTextField titleTxt, urlTxt, categoryTxt, notesTxt;
    private JButton acceptBtn, cancelBtn;
    private boolean accepted;
    private final Link link;

    public EditionView(JFrame parent, Link link) {
        super(parent, "Edit Link", true);
        this.link = link;
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        mainPanel.setBackground(new Color(10, 10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLbl = new JLabel("Title:");
        JLabel urlLbl = new JLabel("URL:");
        JLabel categoryLbl = new JLabel("Category:");
        JLabel notesLbl = new JLabel("Notes:");

        Color labelColor = Color.WHITE;
        titleLbl.setForeground(labelColor);
        urlLbl.setForeground(labelColor);
        categoryLbl.setForeground(labelColor);
        notesLbl.setForeground(labelColor);

        titleTxt = new JTextField(link.getTitle());
        urlTxt = new JTextField(link.getUrl());
        categoryTxt = new JTextField(link.getCategory());
        notesTxt = new JTextField(link.getNotes());

        Color textFieldBg = new Color(0x21, 0x21, 0x21);
        Color textFieldFg = Color.WHITE;

        titleTxt.setBackground(textFieldBg);
        urlTxt.setBackground(textFieldBg);
        categoryTxt.setBackground(textFieldBg);
        notesTxt.setBackground(textFieldBg);

        titleTxt.setForeground(textFieldFg);
        urlTxt.setForeground(textFieldFg);
        categoryTxt.setForeground(textFieldFg);
        notesTxt.setForeground(textFieldFg);

        titleTxt.setCaretColor(Color.WHITE);
        urlTxt.setCaretColor(Color.WHITE);
        categoryTxt.setCaretColor(Color.WHITE);
        notesTxt.setCaretColor(Color.WHITE);

        mainPanel.add(titleLbl);
        mainPanel.add(titleTxt);
        mainPanel.add(urlLbl);
        mainPanel.add(urlTxt);
        mainPanel.add(categoryLbl);
        mainPanel.add(categoryTxt);
        mainPanel.add(notesLbl);
        mainPanel.add(notesTxt);

        acceptBtn = new JButton("Accept");
        acceptBtn.setBackground(new Color(15, 130, 255));
        acceptBtn.setForeground(Color.WHITE);
        acceptBtn.addActionListener(e -> {
            link.setTitle(titleTxt.getText());
            link.setUrl(urlTxt.getText());
            link.setCategory(categoryTxt.getText());
            link.setNotes(notesTxt.getText());
            accepted = true;
            setVisible(false);
        });

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(231, 76, 60));
        cancelBtn.addActionListener(e -> {
            accepted = false;
            setVisible(false);
        });

        mainPanel.add(acceptBtn);
        mainPanel.add(cancelBtn);

        this.getContentPane().setBackground(Color.BLACK);
        this.add(mainPanel);
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getNewTitle() { return titleTxt.getText(); }
    public String getNewURL() { return urlTxt.getText(); }
    public String getNewCategory() { return categoryTxt.getText(); }
    public String getNewNotes() { return notesTxt.getText(); }
}