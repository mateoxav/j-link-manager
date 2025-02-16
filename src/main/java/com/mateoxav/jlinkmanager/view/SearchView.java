package com.mateoxav.jlinkmanager.view;
import com.mateoxav.jlinkmanager.model.Link;
import com.mateoxav.jlinkmanager.model.LinkManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SearchView extends JDialog {
    private JTextField searchTxt;
    private JButton searchBtn;
    private JList<String> resultsList;
    private DefaultListModel<String> resultsModel;
    private List<Link> links;
    private LinkManager manager;
    private MainView mainView;

    public SearchView(MainView parent, LinkManager manager) {
        super(parent, "Search Link", true);
        this.mainView = parent;
        this.manager = manager;
        this.links = manager.getLinks();

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel topPanel = new JPanel(new FlowLayout());
        searchTxt = new JTextField(20);
        searchBtn = new JButton("🔍");
        searchBtn.setBackground(new Color(15, 130, 255));
        searchBtn.setForeground(Color.WHITE);

        topPanel.setBackground(new Color(10, 10, 10));
        topPanel.add(searchTxt);
        topPanel.add(searchBtn);
        add(topPanel, BorderLayout.NORTH);
        
        resultsModel = new DefaultListModel<>();
        resultsList = new JList<>(resultsModel);
        resultsList.setBackground(new Color(2, 2, 2));
        resultsList.setForeground(Color.WHITE);

        add(new JScrollPane(resultsList), BorderLayout.CENTER);
        
        searchBtn.addActionListener(e -> searchLinks(searchTxt.getText()));
        
        resultsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    int index = resultsList.getSelectedIndex();
                    if (index >= 0) {
                        selectLink(index);
                    }
                }
            }
        });
    }

    private void searchLinks(String query) {
        resultsModel.clear();
        query = query.toLowerCase();

        for (Link link : links) {
            String title = link.getTitle().toLowerCase();
            String url = link.getUrl().toLowerCase();
            String category = link.getCategory().toLowerCase();
            String notes = link.getNotes().toLowerCase();
            
            if (title.contains(query) || url.contains(query) || category.contains(query) || notes.contains(query)) {
                resultsModel.addElement(link.getTitle() + " - " + link.getUrl());
            }
        }

        if (resultsModel.isEmpty()) {
            resultsModel.addElement("No results found.");
        }
    }

    private void selectLink(int index) {
        String selected = resultsModel.get(index);

        for (Link link : links) {
            String info = link.getTitle() + " - " + link.getUrl();
            if (info.equals(selected)) {
                mainView.getTitleLbl().setText(link.getTitle());
                mainView.getUrlLbl().setText(link.getUrl());
                mainView.getCategoryLbl().setText(link.getCategory());
                mainView.getNotesLbl().setText(link.getNotes());
                dispose();
                break;
            }
        }
    }
}