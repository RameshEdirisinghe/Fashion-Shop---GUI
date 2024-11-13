import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class OrderbyAmount extends JFrame {
    private Customer[] odramount;
    List cusList = new List(100, 0.5);

    OrderbyAmount(List cus) {
        setSize(400, 400);
        setTitle("Categorized by Qty");
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);


        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 15));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(255, 102, 102));
        back.setBounds(0, 0, 80, 30);
        add(back);
        back.addActionListener(new ActionListener() {
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
        odramount();

        String[] cols = { "OrderID", "CustomerID", "Size", "QTY", "Amount", "Status" };
        DefaultTableModel tbl = new DefaultTableModel(cols, 0);

        for (int i = 0; i < odramount.length; i++) {
            Object[] row = { odramount[i].getId(), odramount[i].getNumber(), odramount[i].getTshirtSize(),odramount[i].getQty(), odramount[i].getamount(), odramount[i].getstatus() };
            tbl.addRow(row);
        }

        JTable Table = new JTable(tbl);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40, 40, 300, 300);
        add(scroll);
    }

    public void odramount() {

        odramount = new Customer[cusList.size()];

        for (int i = 0; i < cusList.size(); i++) {
            // copy previous data
            odramount[i] = cusList.getCustomerAr()[i];
        }

        for (int i = cusList.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (odramount[j].getamount() < odramount[j + 1].getamount()) {
                    Customer temp = odramount[j];
                    odramount[j] = odramount[j + 1];
                    odramount[j + 1] = temp;
                }
            }
        }
    }


}
