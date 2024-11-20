import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchCustomer extends JFrame {

    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private JScrollPane tableScrollPane;

    private String newLine;
    private boolean isFound;
    private int tempMcount = 0;
    private int tempXScount = 0;
    private int tempScount = 0;
    private int tempLcount = 0;
    private int tempXLcount = 0;
    private int tempXXLcount = 0;

    private double Mamount = 0;
    private double XSamount = 0;
    private double Samount = 0;
    private double Lamount = 0;
    private double XLamount = 0;
    private double XXLamount = 0;
    private double Totalamount = 0;

    public SearchCustomer(List cus) {

        setTitle("Search Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(mainPanel);

        JPanel northPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 102, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        northPanel.add(backButton, BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Enter Customer ID : "));
        JTextField customerIdField = new JTextField(15);
        searchPanel.add(customerIdField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 153, 153));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchPanel.add(searchButton);
        northPanel.add(searchPanel, BorderLayout.SOUTH);

        mainPanel.add(northPanel, BorderLayout.NORTH);

        String[] columns = { "Size", "QTY", "Amount" };
        Object[][] initialData = {
                { "L", tempLcount, Lamount },
                { "M", tempMcount, Mamount },
                { "XS", tempXScount, XSamount },
                { "S", tempScount, Samount },
                { "XL", tempXLcount, XLamount },
                { "XXL", tempXXLcount, XXLamount }
        };
        tableModel = new DefaultTableModel(initialData, columns);
        JTable table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(new JLabel("Total : "));
        totalLabel = new JLabel(String.format("%.2f", Totalamount));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        footerPanel.add(totalLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetcount();
                String customerId = customerIdField.getText();
                System.out.println("Search button clicked. Customer ID: " + customerId);

                try {
                    BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                    String Line = br.readLine();

                    while (Line != null) {
                        String phoneNum = Line.substring(10, 20);
                        if (phoneNum.equalsIgnoreCase(customerIdField.getText())) {

                            String[] newar = Line.split(",");
                            isFound = true;
                            searchCustomer(newar[2], Integer.parseInt(newar[3]));
                        }
                        Line = br.readLine();
                    }

                } catch (IOException ex) {
                }

                if (isFound) {

                    tableModel.setValueAt(tempLcount, 0, 1);
                    tableModel.setValueAt(Lamount, 0, 2);
                    tableModel.setValueAt(tempMcount, 1, 1);
                    tableModel.setValueAt(Mamount, 1, 2);
                    tableModel.setValueAt(tempXScount, 2, 1);
                    tableModel.setValueAt(XSamount, 2, 2);
                    tableModel.setValueAt(tempScount, 3, 1);
                    tableModel.setValueAt(Samount, 3, 2);
                    tableModel.setValueAt(tempXLcount, 4, 1);
                    tableModel.setValueAt(XLamount, 4, 2);
                    tableModel.setValueAt(tempXXLcount, 5, 1);
                    tableModel.setValueAt(XXLamount, 5, 2);

                    totalLabel.setText(String.format("%.2f", Totalamount));

                    tableModel.fireTableDataChanged();
                    mainPanel.revalidate();

                    mainPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back button clicked");
                dispose();
                new HomePage(cus).setVisible(true);
            }
        });

        setVisible(true);
    }

    public boolean searchCustomer(String size, int qty) {
        boolean isCorrect = false;

        isCorrect = true;
        if (size.equals("M")) {
            tempMcount += qty;
        } else if (size.equals("XS")) {
            tempXScount += qty;
        } else if (size.equals("S")) {
            tempScount += qty;
        } else if (size.equals("L")) {
            tempLcount += qty;
        } else if (size.equals("XL")) {
            tempXLcount += qty;
        } else if (size.equals("XXL")) {
            tempXXLcount += qty;
        }

        Mamount = tempMcount * 900;
        XLamount = tempXLcount * 1100;
        XXLamount = tempXXLcount * 1200;
        XSamount = tempXScount * 600;
        Samount = tempScount * 800;
        Lamount = tempLcount * 1000;
        Totalamount = Mamount + XLamount + XXLamount + XSamount + Samount + Lamount;

        return isCorrect;

    }

    public void resetcount() {
        tempMcount = 0;
        tempXScount = 0;
        tempScount = 0;
        tempLcount = 0;
        tempXLcount = 0;
        tempXXLcount = 0;

        Mamount = 0;
        XSamount = 0;
        Samount = 0;
        Lamount = 0;
        XLamount = 0;
        XXLamount = 0;
        Totalamount = 0;
    }

}