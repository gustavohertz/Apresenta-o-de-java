package org.example;

import javax.swing.*;

public class StartUp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BikeRentalUI();
            }
        });
    }
}
