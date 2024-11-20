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
    List getBestList = new List(100, 0.5);

    bestCustomer(List cus) {
        setSize(400, 400);
        setTitle("Best In Customers");
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

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
                cusList.addLast(newar[0], newar[1], newar[2], Integer.parseInt(newar[3]),
                        Double.parseDouble(newar[4]), newar[5]);

                Line = br.readLine();
            }
        } catch (IOException ex) {
        }
        getBestCustomer();

        String[] colsname = { "Contact", "Qty", "Amount" };
        DefaultTableModel tblm = new DefaultTableModel(colsname, 0);

        node temp = getBestList.start();
        for (int i = 0; i < validCount; i++) {
            Object[] row = {temp.ContactNumber,temp.Qty ,temp.amount};
            tblm.addRow(row);
            temp=temp.next;
        }

        JTable Table = new JTable(tblm);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40, 40, 300, 300);
        add(scroll);

    }

    public void getBestCustomer() {
        boolean[] processed = new boolean[cusList.size()];

        node temp = cusList.start();

        validCount = 0;
        for (int i = 0; i < cusList.size(); i++) {
            if (processed[i]) {
                continue;
            }
            node fortemp = temp.next;

            int tempqty = temp.Qty;
            double tempamount = temp.amount;
            processed[i] = true;

            for (int j = i + 1; j < cusList.size(); j++) {

                if (temp.ContactNumber.equals(fortemp.ContactNumber)) {
                    tempqty += fortemp.Qty;
                    tempamount += fortemp.amount;
                    processed[j] = true;
                }
                fortemp = fortemp.next;
            }

            
            getBestList.addLast(temp.ContactNumber,tempqty ,tempamount);
            validCount++;
            temp = temp.next;
        }
        
        node sorttemp = getBestList.start();
        
        for (int i = validCount - 1; i > 0; i--) {
            node sorttemp2 = sorttemp.next;
            for (int j = 0; j < i; j++) {
                if (sorttemp.amount < sorttemp2.amount) {
                    node tempory = sorttemp2;
                    sorttemp2 = sorttemp;
                    sorttemp = tempory;

                }
                sorttemp2=sorttemp2.next;
            }
            sorttemp = sorttemp.next;
        }

    }
}
