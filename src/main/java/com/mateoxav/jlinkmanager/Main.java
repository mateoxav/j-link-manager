package com.mateoxav.jlinkmanager;
import javax.swing.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.mateoxav.jlinkmanager.view.MainView;

public class Main {
    public static void main(String[] args) {

        FlatMacDarkLaf.setup();

            SwingUtilities.invokeLater(() -> {
//            GestorEnlaces gestor = new GestorEnlaces();
              MainView view = new MainView();
//            new Controlador(gestor, vista);
              view.setVisible(true);
          });
    }
}
