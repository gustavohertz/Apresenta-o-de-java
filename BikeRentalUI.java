package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BikeRentalUI extends JFrame {

    private JTextField bikeNumberField;
    private JTextField daysField;
    private JTextField nameField;
    private JTextField postalCodeField;
    private JTextField telephoneField;
    private JTextArea resultArea;

    public BikeRentalUI() {
        super("Bike Rental System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criar componentes Swing
        JLabel bikeNumberLabel = new JLabel("Número da Bicicleta:");
        JLabel daysLabel = new JLabel("Número de Dias:");
        JLabel nameLabel = new JLabel("Nome do Cliente:");
        JLabel postalCodeLabel = new JLabel("Código Postal:");
        JLabel telephoneLabel = new JLabel("Telefone:");

        bikeNumberField = new JTextField(10);
        daysField = new JTextField(10);
        nameField = new JTextField(20);
        postalCodeField = new JTextField(10);
        telephoneField = new JTextField(10);

        JButton rentButton = new JButton("Alugar");
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentBike();
            }
        });

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        // Layout da interface usando GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margens

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(bikeNumberLabel, gbc);

        gbc.gridx = 1;
        panel.add(bikeNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(daysLabel, gbc);

        gbc.gridx = 1;
        panel.add(daysField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(postalCodeLabel, gbc);

        gbc.gridx = 1;
        panel.add(postalCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(telephoneLabel, gbc);

        gbc.gridx = 1;
        panel.add(telephoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Este componente ocupará duas colunas
        panel.add(rentButton, gbc);

        add(panel);
        add(new JScrollPane(resultArea));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void rentBike() {
        try {
            int bikeNumber = Integer.parseInt(bikeNumberField.getText());
            int days = Integer.parseInt(daysField.getText());
            String name = nameField.getText();
            String postalCode = postalCodeField.getText();
            int telephone = Integer.parseInt(telephoneField.getText());

            Bike rentedBike = Bike.findBikeByNumber(bikeNumber);

            if (rentedBike != null) {
                resultArea.setText(""); // Limpar o texto anterior
                resultArea.append("Detalhes da bicicleta número '" + bikeNumber + "':\n");
                resultArea.append("DEPOSIT: $" + rentedBike.getDeposit() + "\n");
                resultArea.append("RATE: $" + rentedBike.getRate() + "\n");
                resultArea.append("TOTAL COST: $"+(rentedBike.getDeposit()+ rentedBike.getRate())+"\n\n");

                rentedBike.calculateCost(days);

                Customer customer = new Customer(name, postalCode, telephone);
                Payment payment = new Payment(customer);
                Hire hire = new Hire(new java.util.Date(), days, rentedBike, customer);

                payment.calculateTotalPayment(hire);

                resultArea.append("\nDetalhes do Cliente:\n");
                resultArea.append("Nome: " + customer.getName() + "\n");
                resultArea.append("Código Postal: " + customer.getPostcode() + "\n");
                resultArea.append("Telefone: " + customer.getTelephone() + "\n");

                resultArea.append("\nAluguel efetuado com sucesso!\n");
            } else {
                resultArea.setText("Bicicleta não encontrada.\n");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Por favor, insira números válidos.\n");
        }
    }
}
