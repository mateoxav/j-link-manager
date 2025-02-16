package com.mateoxav.jlinkmanager.controller;
import com.mateoxav.jlinkmanager.model.Link;
import com.mateoxav.jlinkmanager.model.LinkManager;
import com.mateoxav.jlinkmanager.view.*;


import javax.swing.*;

public class Controller {
    private final LinkManager manager;
    private final MainView view;

    public Controller(LinkManager manager, MainView view) {
        this.manager = manager;
        this.view = view;

        view.getListModel().clear();

        for (Link link : manager.getLinks()) {
            view.getListModel().addElement(link.getTitle() + " - " + link.getUrl());
        }

        view.getAddBtn().addActionListener(e -> addLink());
        view.getEditBtn().addActionListener(e -> editLink());
        view.getDeleteBtn().addActionListener(e -> deleteLink());
        view.getSearchBtn().addActionListener(e -> openSearchView());
        view.getSettingsBtn().addActionListener(e -> openSettingsView());

        view.getLinksList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedData();
            }
        });
    }

    private void addLink() {
        AddView addWindow = new AddView(view);
        addWindow.setVisible(true);

        if (addWindow.isAccepted()) {
            String title = addWindow.getNewTitle();
            String url = addWindow.getNewURL();
            String category = addWindow.getNewCategory();
            String notes = addWindow.getNewNotes();

            if (title.isEmpty() || url.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Title and URL are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Link newLink = new Link(title, url, category, notes);
            manager.addLink(newLink);
            manager.saveLinks();
            view.getListModel().addElement(newLink.getTitle() + " - " + newLink.getUrl());
        }
    }

    private void loadSelectedData() {
        int index = view.getLinksList().getSelectedIndex();

        if (index < 0 || index >= manager.getLinks().size()) {
            return;
        }

        Link link = manager.getLinks().get(index);
        view.getTitleLbl().setText(link.getTitle());
        view.getUrlLbl().setText(link.getUrl());
        view.getCategoryLbl().setText(link.getCategory());
        view.getNotesLbl().setText(link.getNotes());
    }

    private void editLink() {
        int index = view.getLinksList().getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(view, "Select a link to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Link link = manager.getLinks().get(index);
        EditionView editionView = new EditionView(view, link);
        editionView.setVisible(true);

        if (editionView.isAccepted()) {
            link.setTitle(editionView.getNewTitle());
            link.setUrl(editionView.getNewURL());
            link.setCategory(editionView.getNewCategory());
            link.setNotes(editionView.getNewNotes());

            manager.saveLinks();

            view.getListModel().setElementAt(link.getTitle() + " - " + link.getUrl(), index);
            view.getListModel().set(index, link.getTitle() + " - " + link.getUrl());

            view.getTitleLbl().setText(link.getTitle());
            view.getUrlLbl().setText(link.getUrl());
            view.getCategoryLbl().setText(link.getCategory());
            view.getNotesLbl().setText(link.getNotes());
        }
    }

    private void deleteLink() {
        int index = view.getLinksList().getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(view, "Select a link to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(view, "Are you sure you want to remove this link?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            manager.removeLink(index);
            view.getListModel().remove(index);
            cleanFields();
        }
    }

    private void cleanFields() {
        view.getTitleLbl().setText("");
        view.getUrlLbl().setText("");
        view.getCategoryLbl().setText("");
        view.getNotesLbl().setText("");
        view.getLinksList().clearSelection();
    }

    private void openSearchView() {
        SearchView searchWindow = new SearchView(view, manager);
        searchWindow.setVisible(true);
    }

    private void openSettingsView() {
        SettingsView settingsWindow = new SettingsView(view);
        settingsWindow.setVisible(true);
    }
}
