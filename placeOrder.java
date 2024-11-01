import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class placeOrder extends JFrame {

    private double amount;

    placeOrder(CustomerCollection cus) {

        setTitle("Place Order");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel backJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 102, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backJPanel.add("Left", backButton);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding around components
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(new JLabel("Order ID:"), gbc);

        gbc.gridx = 1;
        JLabel orderIdLabel = new JLabel(cus.getOrderId());
        orderIdLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        infoPanel.add(orderIdLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Customer ID:"), gbc);

        gbc.gridx = 1;
        JTextField customerIdField = new JTextField(15);
        infoPanel.add(customerIdField, gbc);

        customerIdField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                boolean isvalid = cus.validateContact(customerIdField.getText());

                if (!isvalid) {
                    JOptionPane.showMessageDialog(null, "Invalid Number.......");
                    customerIdField.setText("");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(new JLabel("Size:"), gbc);

        gbc.gridx = 1;
        JTextField sizeField = new JTextField(15);
        infoPanel.add(sizeField, gbc);
        sizeField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                boolean isCorrect = cus.validateTsize(sizeField.getText());

                if (!isCorrect) {
                    JOptionPane.showMessageDialog(null, "Invalid TshirtSize.......");
                    sizeField.setText("");
                }
            }
        });

        gbc.gridx = 2;
        // gbc.gridy = 2;
        infoPanel.add(new JLabel("(XS/S/M/L/XL/XXL)"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(new JLabel("QTY:"), gbc);

        gbc.gridx = 1;
        JTextField qtyField = new JTextField(15);

        infoPanel.add(qtyField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        infoPanel.add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        JLabel amountField = new JLabel(String.valueOf(amount));
        infoPanel.add(amountField, gbc);
        
        qtyField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                int qty = Integer.parseInt(qtyField.getText());
                double amount = cus.getamount(sizeField.getText(), qty);
                amountField.setText(String.valueOf(amount));
            }
        });

        mainPanel.add(backJPanel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // Place button
        JButton placeButton = new JButton("Place");
        placeButton.setBackground(new Color(0, 153, 153));
        placeButton.setForeground(Color.WHITE);
        placeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        placeButton.setFocusPainted(false);

        placeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                boolean isvalid = cus.validateContact(customerIdField.getText());
                boolean isCorrect = cus.validateTsize(sizeField.getText());

                if (!isvalid) {
                    JOptionPane.showMessageDialog(null, "Invalid Number.......");
                    customerIdField.setText("");
                } else if (!isCorrect) {
                    JOptionPane.showMessageDialog(null, "Invalid TshirtSize.......");
                    sizeField.setText("");
                } else {
                    int qty = Integer.parseInt(qtyField.getText());
                    double calculatedAmount = cus.getamount(sizeField.getText(), qty);
                    amountField.setText(String.valueOf(calculatedAmount)); 

                    JOptionPane.showMessageDialog(null, "Order Placed..");
                    String id = orderIdLabel.getText();
                    String number = customerIdField.getText();
                    String Tsize = sizeField.getText();

                    Customer c1 = new Customer(id, number, Tsize, qty, calculatedAmount ,0);
                    cus.addCustomer(c1);
                    cus.printCustomers();
                    cus.orderNumber++;
                    dispose();
                    new placeOrder(cus).setVisible(true);

                }

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(placeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                dispose();
                new HomePage(cus).setVisible(true);
            }
        });

       
        setContentPane(mainPanel);
    }

}
