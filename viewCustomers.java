import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class viewCustomers extends JFrame {
    private Customer[] viewReportarray;
    private int validCount;
    List cusList = new List(100, 0.5);

    viewCustomers(List cus) {
        setTitle("View Customers");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getviewCustomers();

        JButton BackButton = new JButton("Back");
        BackButton.setBackground(new Color(255, 102, 102));
        BackButton.setFont(new Font("Arial", Font.BOLD, 15));
        BackButton.setForeground(Color.WHITE);
        BackButton.setBounds(0, 0, 80, 30);
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Reports(cus).setVisible(true);
            }
        });
        add(BackButton);

        try {
            BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
            String Line = br.readLine();
            while (Line != null) {
                String[] newar = Line.split(",");
                Customer cuss = new Customer(newar[0], newar[1], newar[2], Integer.parseInt(newar[3]),
                        Double.parseDouble(newar[4]), newar[5]);
                cusList.add(cuss);
                Line = br.readLine();
            }
        } catch (IOException ex) {
        }
        getviewCustomers();
        String[] colnames = { "Number", "Quantity", "Amount" };
        DefaultTableModel dtm = new DefaultTableModel(colnames, 0);

        for (int i = 0; i < validCount; i++) {
            Object[] rowData = { viewReportarray[i].getNumber(), viewReportarray[i].getQty(),
                    viewReportarray[i].getamount() };
            dtm.addRow(rowData);
        }

        JTable custable = new JTable(dtm);
        JScrollPane tablepane = new JScrollPane(custable);
        tablepane.setBounds(40, 40, 300, 300);
        add(tablepane);
    }

    public void getviewCustomers() {
        boolean[] processed = new boolean[cusList.size()];
        viewReportarray = new Customer[cusList.size()];

        validCount = 0;
        for (int i = 0; i < cusList.size(); i++) {
            if (processed[i]) {
                continue;
            }

            viewReportarray[validCount] = new Customer();
            int tempqty = cusList.getCustomerAr()[i].getQty();
            double tempamount = cusList.getCustomerAr()[i].getamount();
            processed[i] = true;

            for (int j = i + 1; j < cusList.size(); j++) {
                if (cusList.getCustomerAr()[i].getNumber().equals(cusList.getCustomerAr()[j].getNumber())) {
                    tempqty += cusList.getCustomerAr()[j].getQty();
                    tempamount += cusList.getCustomerAr()[j].getamount();
                    processed[j] = true;
                }
            }

            viewReportarray[validCount].setViewReportValues(cusList.getCustomerAr()[i].getNumber(), tempqty,
                    tempamount);
            System.out.println(Arrays.toString(viewReportarray));
            validCount++;
        }

    }
}
