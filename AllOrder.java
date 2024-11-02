import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class AllOrder extends JFrame {
    AllOrder(CustomerCollection cus) {
        setSize(400, 400);
        setTitle("All Orders");
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);

        cus.odramount();
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

        String[] cols = { "OrderID", "CustomerID", "Size", "QTY", "Amount", "Status" };
        DefaultTableModel tbl = new DefaultTableModel(cols, 0);

        for (int i = (cus.customerArray.length)-1; i >=0; i--) {
            Object[] row = { cus.customerArray[i].getId(), cus.customerArray[i].getNumber(), cus.customerArray[i].getTshirtSize(),cus.customerArray[i].getQty(), cus.customerArray[i].getamount(), cus.customerArray[i].getstatus() };
            tbl.addRow(row);
        }

        JTable Table = new JTable(tbl);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40, 40, 300, 300);
        add(scroll);
    }

}
