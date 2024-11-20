import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class AllOrder extends JFrame {
    private String[] newar;
    private int count=0;
    AllOrder(List cus) {
        setSize(400, 400);
        setTitle("All Orders");
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
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

        
            try {
                BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                String Line = br.readLine();
                while (Line != null) {
                    newar = Line.split(",");
                    Object[] row = { newar[0], newar[1], newar[2], newar[3],newar[4], newar[5] };
                    tbl.addRow(row);
                    Line = br.readLine();
                }
            } catch (IOException ex) {
            }
            

        JTable Table = new JTable(tbl);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40, 40, 300, 300);
        add(scroll);
    }

}
