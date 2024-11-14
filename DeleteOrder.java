import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class DeleteOrder extends JFrame {

    private JButton btnBack;
    private JButton btnSearch;
    private JButton btnDelete;
    private int index;

    private JLabel lblOrderId;
    private JLabel lblCustomerId;
    private JLabel lblSize;
    private JLabel lblQty;
    private JLabel lblAmount;
    private JLabel lblStatus;

    List cusList = new List(100, 0.5);
    private FileWriter fw;

    DeleteOrder(List cus) {
        setSize(500, 500);
        setTitle("Delete Order");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnBack = new JButton("BACK");
        btnBack.setBackground(Color.RED);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(0, 0, 80, 30);
        add(btnBack);

        btnSearch = new JButton("SEARCH");
        btnSearch.setBackground(new Color(4, 203, 201));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBounds(390, 45, 90, 30);
        add(btnSearch);

        btnDelete = new JButton("DELETE");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 15));
        btnDelete.setBackground(new Color(153, 51, 0));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBounds(380, 400, 100, 30);
        add(btnDelete);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new HomePage(cus).setVisible(true);
                ;
            }
        });

        lblOrderId = new JLabel("Enter Order ID: ");
        lblOrderId.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblOrderId.setBounds(40, 50, 200, 20);
        add(lblOrderId);

        JLabel CustomerIdField = new JLabel();
        CustomerIdField.setFont(new Font("SanSeriif", Font.BOLD, 15));
        CustomerIdField.setBounds(180, 100, 180, 30);
        add(CustomerIdField);

        lblCustomerId = new JLabel("Customer ID: ");
        lblCustomerId.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblCustomerId.setBounds(40, 100, 120, 30);
        add(lblCustomerId);

        lblSize = new JLabel("Size: ");
        lblSize.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblSize.setBounds(40, 150, 200, 30);
        add(lblSize);

        lblQty = new JLabel("QTY: ");
        lblQty.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblQty.setBounds(40, 200, 200, 30);
        add(lblQty);

        lblAmount = new JLabel("Amount: ");
        lblAmount.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblAmount.setBounds(40, 250, 200, 30);
        add(lblAmount);

        lblStatus = new JLabel("Status: ");
        lblStatus.setFont(new Font("SanSerif", Font.BOLD, 15));
        lblStatus.setBounds(40, 300, 200, 30);
        add(lblStatus);

        JTextField OrderIDField = new JTextField();
        OrderIDField.setFont(new Font("Arial", Font.BOLD, 15));
        OrderIDField.setBounds(180, 50, 200, 30);
        add(OrderIDField);

        JLabel SizeField = new JLabel();
        SizeField.setFont((new Font("Arial", Font.BOLD, 15)));
        SizeField.setBounds(180, 150, 200, 30);
        add(SizeField);

        JLabel qtyField = new JLabel();
        qtyField.setFont(new Font("Arial", Font.BOLD, 15));
        qtyField.setBounds(180, 200, 200, 30);
        add(qtyField);

        JLabel amountField = new JLabel();
        amountField.setFont(new Font("Arial", Font.BOLD, 15));
        amountField.setBounds(180, 250, 200, 30);
        add(amountField);

        JLabel statusField = new JLabel();
        statusField.setFont(new Font("Arial", Font.BOLD, 15));
        statusField.setBounds(180, 300, 200, 30);
        add(statusField);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String odrId = OrderIDField.getText();

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

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String[] option = { "Yes", "No" };
                int choice = JOptionPane.showOptionDialog(null, "Do You Want to Delete this Order ?",
                        "Delete Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option,
                        option[0]);

                switch (choice) {
                    case 0:

                        try {
                            BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                            String Line = br.readLine();
                            while (Line != null) {
                                String[] newar = Line.split(",");
                                Line = br.readLine();
                                if (newar[0].equalsIgnoreCase(OrderIDField.getText())) {
                                    continue;
                                }
                                    Customer cuss = new Customer(newar[0], newar[1], newar[2], Integer.parseInt(newar[3]),
                                        Double.parseDouble(newar[4]), newar[5]);
                                cusList.add(cuss);

                                

                            }

                            FileWriter fw = new FileWriter("Customer.txt");
                            for (int i = 0; i < cusList.size(); i++) {
                                System.out.println("hi");
                                Customer c1 = cusList.get(i);
                                fw.write(c1.toString() + "\n");
                            }
                            fw.close();

                        } catch (IOException ex) {
                        }

                        if (true) {
                            JOptionPane.showMessageDialog(null, "Order Delete Succesfull", "DeleteOrder",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case 1:
                        dispose();
                        new DeleteOrder(cus).setVisible(true);
                        break;
                    default:
                        break;
                }

            }
        });
    }

}
