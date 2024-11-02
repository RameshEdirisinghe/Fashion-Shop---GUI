import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class OrderbyAmount extends JFrame {
    OrderbyAmount(CustomerCollection cus) {
        setSize(400, 400);
        setTitle("Categorized by Qty");
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

        for (int i = 0; i < cus.odramount.length; i++) {
            Object[] row = { cus.odramount[i].getId(), cus.odramount[i].getNumber(), cus.odramount[i].getTshirtSize(),cus.odramount[i].getQty(), cus.odramount[i].getamount(), cus.odramount[i].getstatus() };
            tbl.addRow(row);
        }

        JTable Table = new JTable(tbl);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40, 40, 300, 300);
        add(scroll);
    }

}
