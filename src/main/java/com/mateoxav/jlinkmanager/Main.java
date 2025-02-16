package com.mateoxav.jlinkmanager;
import javax.swing.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.mateoxav.jlinkmanager.controller.Controller;
import com.mateoxav.jlinkmanager.model.LinkManager;
import com.mateoxav.jlinkmanager.view.MainView;

public class Main {
    public static void main(String[] args) {

        FlatMacDarkLaf.setup();

            SwingUtilities.invokeLater(() -> {
              LinkManager manager = new LinkManager();
              MainView view = new MainView();
              new Controller(manager, view);
              view.setVisible(true);
          });
    }
}
