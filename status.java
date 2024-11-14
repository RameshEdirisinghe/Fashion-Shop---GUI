import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

class status extends JFrame {
    private int index;
    List cusList = new List(100, 0.5);

    status(List cus) {
        setTitle("Status");
        setSize(400, 450);
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 102, 102));
        backButton.setBounds(0, 0, 80, 30);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomePage(cus).setVisible(true);
            }
        });

        JLabel oderJLabel = new JLabel("Enter Order ID :");
        oderJLabel.setFont(new Font("Arial", Font.BOLD, 15));
        oderJLabel.setBounds(16, 37, 200, 15);
        add(oderJLabel);

        JTextField OrderIDField = new JTextField();
        OrderIDField.setFont(new Font("Arial", Font.BOLD, 15));
        OrderIDField.setBounds(140, 35, 150, 25);
        add(OrderIDField);

        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(4, 203, 201));
        searchButton.setBounds(300, 37, 80, 20);
        add(searchButton);

        JLabel CustomerIdLabel = new JLabel("Customer ID: ");
        CustomerIdLabel.setFont(new Font("Arial", Font.BOLD, 15));
        CustomerIdLabel.setBounds(16, 95, 200, 20);
        add(CustomerIdLabel);

        JLabel SizeLabel = new JLabel("Size :");
        SizeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        SizeLabel.setBounds(16, 145, 200, 20);
        add(SizeLabel);

        JLabel qtyLabel = new JLabel("Qty :");
        qtyLabel.setFont(new Font("Arial", Font.BOLD, 15));
        qtyLabel.setBounds(16, 195, 200, 20);
        add(qtyLabel);

        JLabel amountLabel = new JLabel("Amount :");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 15));
        amountLabel.setBounds(16, 245, 200, 20);
        add(amountLabel);

        JLabel statusLabel = new JLabel("Status :");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 15));
        statusLabel.setBounds(16, 295, 200, 20);
        add(statusLabel);

        JLabel CustomerIdField = new JLabel("");
        CustomerIdField.setFont(new Font("Arial", Font.PLAIN, 15));
        CustomerIdField.setBounds(125, 95, 200, 20);
        add(CustomerIdField);

        JLabel SizeField = new JLabel("");
        SizeField.setFont(new Font("Arial", Font.PLAIN, 15));
        SizeField.setBounds(125, 145, 200, 20);
        add(SizeField);

        JLabel qtyField = new JLabel("");
        qtyField.setFont(new Font("Arial", Font.PLAIN, 15));
        qtyField.setBounds(125, 195, 200, 20);
        add(qtyField);

        JLabel amountField = new JLabel("");
        amountField.setFont(new Font("Arial", Font.PLAIN, 15));
        amountField.setBounds(125, 245, 200, 20);
        add(amountField);

        JLabel statusField = new JLabel("");
        statusField.setFont(new Font("Arial", Font.PLAIN, 15));
        statusField.setBounds(125, 295, 200, 20);
        add(statusField);

        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                String newLine = null;
                try {
                    BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                    String line = br.readLine();
                    while (line != null) {
                        String id = line.substring(0, 9);

                        if (id.equalsIgnoreCase(OrderIDField.getText())) {
                            newLine = line;
                        }

                        line = br.readLine();
                    }
                } catch (IOException ex) {

                }
                if (newLine != null) {
                    String[] cusDetails = newLine.split(",");
                    CustomerIdField.setText(cusDetails[1]);
                    SizeField.setText(cusDetails[2]);
                    qtyField.setText(cusDetails[3]);
                    amountField.setText(cusDetails[4]);
                    statusField.setText(cusDetails[5]);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Order ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton changeStatus = new JButton("Change Status");
        changeStatus.setFont(new Font("Arial", Font.PLAIN, 15));
        changeStatus.setForeground(Color.WHITE);
        changeStatus.setBackground(new Color(16, 162, 198));
        changeStatus.setBounds(220, 360, 150, 30);
        add(changeStatus);

        changeStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("button clicked");
                String newLine = null;
                String st = null;
                try {
                    BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                    String line = br.readLine();
                    while (line != null) {
                        String[] newar = line.split(",");

                        if (newar[0].equalsIgnoreCase(OrderIDField.getText())) {
                            st = newar[5];
                            newLine = line;
                        }

                        line = br.readLine();
                    }
                } catch (Exception ex) {
                }

                if (st.equals("Processing")) {
                    String[] option = { "Delivering", "Delivered" };
                    int choice = JOptionPane.showOptionDialog(null,
                            "Please select the option",
                            "Status Options",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null,
                            option,
                            option[0]);

                    switch (choice) {
                        case 0:
                            try {
                                BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                                String line = br.readLine();
                                while (line != null) {
                                    String[] newar = line.split(",");

                                    if (newar[0].equalsIgnoreCase(OrderIDField.getText())) {
                                        Customer cuss = new Customer(newar[0], newar[1], newar[2],Integer.parseInt(newar[3]), Double.parseDouble(newar[4]), "Delivering");
                                        cusList.add(cuss);
                                    } else {
                                        Customer cuss = new Customer(newar[0], newar[1], newar[2],Integer.parseInt(newar[3]), Double.parseDouble(newar[4]), newar[5]);
                                        cusList.add(cuss);
                                    }

                                    line = br.readLine();
                                }

                                FileWriter();

                            } catch (IOException ex) {
                            }

                            break;
                        case 1:
                            try {
                                BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                                String line = br.readLine();
                                while (line != null) {
                                    String[] newar = line.split(",");

                                    if (newar[0].equalsIgnoreCase(OrderIDField.getText())) {
                                        Customer cuss = new Customer(newar[0], newar[1], newar[2],
                                                Integer.parseInt(newar[3]), Double.parseDouble(newar[4]), "Delivered");
                                        cusList.add(cuss);
                                    } else {
                                        Customer cuss = new Customer(newar[0], newar[1], newar[2],
                                                Integer.parseInt(newar[3]), Double.parseDouble(newar[4]), newar[5]);
                                        cusList.add(cuss);
                                    }

                                    line = br.readLine();
                                }
                                FileWriter();
                            } catch (IOException ex) {
                            }
                            break;
                        default:
                            break;
                    }
                } else if (st.equals("Delivering")) {
                    String[] option = { "Delivered" };
                    int choice = JOptionPane.showOptionDialog(null, "Select Option", "Status Option",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

                    switch (choice) {
                        case 0:
                            try {
                                BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                                String line = br.readLine();
                                while (line != null) {
                                    String[] newar = line.split(",");

                                    if (newar[0].equalsIgnoreCase(OrderIDField.getText())) {
                                        Customer cuss = new Customer(newar[0], newar[1], newar[2],
                                                Integer.parseInt(newar[3]), Double.parseDouble(newar[4]), "Delivered");
                                        cusList.add(cuss);
                                    } else {
                                        Customer cuss = new Customer(newar[0], newar[1], newar[2],
                                                Integer.parseInt(newar[3]), Double.parseDouble(newar[4]), newar[5]);
                                        cusList.add(cuss);
                                    }

                                    line = br.readLine();
                                }
                                FileWriter();
                            } catch (IOException ex) {
                            }
                            break;

                        default:
                            break;
                    }
                }
            }
        });

    }

    public void FileWriter() {
        try {
            FileWriter fw = new FileWriter("Customer.txt");
            for (int i = 0; i < cusList.size(); i++) {
                fw.write(cusList.getCustomerAr()[i].getId() + "," + cusList.getCustomerAr()[i].getNumber() + "," + cusList.getCustomerAr()[i].getTshirtSize()  + "," + cusList.getCustomerAr()[i].getQty()  + "," + cusList.getCustomerAr()[i].getamount()  +","+ cusList.getCustomerAr()[i].getstatus()  + "\n");
                
            }
            fw.close();

        } catch (IOException ex) {
        }
    }
}
