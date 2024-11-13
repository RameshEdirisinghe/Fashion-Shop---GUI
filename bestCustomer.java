import java.util.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class bestCustomer extends JFrame {
    private Customer[] bestCusarray;
    private int validCount;
    List cusList = new List(100, 0.5);

    bestCustomer(List cus) {
        setSize(400, 400);
        setTitle("Best In Customers");
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);
        Back.setBackground(new Color(255, 102, 102));
        Back.setBounds(0, 0, 80, 30);
        add(Back);
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Reports(cus).setVisible(true);
            }
        });

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
        getBestCustomer();

        String[] colsname = { "Contact", "Qty", "Amount" };
        DefaultTableModel tblm = new DefaultTableModel(colsname, 0);

        for (int i = 0; i < validCount; i++) {
            Object[] row = { bestCusarray[i].getNumber(), bestCusarray[i].getQty(), bestCusarray[i].getamount() };
            tblm.addRow(row);
        }

        JTable Table = new JTable(tblm);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40, 40, 300, 300);
        add(scroll);

    }

    public void getBestCustomer() {

        boolean[] processed = new boolean[cusList.size()];
        bestCusarray = new Customer[cusList.size()];

        validCount = 0;
        for (int i = 0; i < cusList.size(); i++) {
            if (processed[i]) {
                continue;
            }

            bestCusarray[validCount] = new Customer();
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

            bestCusarray[validCount].setViewReportValues(cusList.getCustomerAr()[i].getNumber(), tempqty, tempamount);
            System.out.println(Arrays.toString(bestCusarray));
            validCount++;
        }

        for (int i = validCount - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (bestCusarray[j].getamount() < bestCusarray[j + 1].getamount()) {
                    Customer temp = bestCusarray[j + 1];
                    bestCusarray[j + 1] = bestCusarray[j];
                    bestCusarray[j] = temp;
                }
            }
        }

    }
}
