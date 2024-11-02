import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class allCustomer extends JFrame {
    allCustomer(CustomerCollection cus){
        setTitle("All Customers");
        setSize(400,400);
        setDefaultCloseOperation(2);
        setLocationRelativeTo(null);
        setLayout(null);


        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial",Font.BOLD,15));
        Back.setForeground(Color.WHITE);
        Back.setBackground(new Color(255, 102, 102));
        Back.setBounds(0,0,80,30);
        add(Back);
        Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new Reports(cus).setVisible(true);
            }
        });

        String[] cols = {"Customer ID","XS","S","M","L","XL","XXL","Amount"};
        DefaultTableModel tbl = new DefaultTableModel(cols,0);

        for(int i=0;i<cus.validCount;i++){
            Object[] row = {cus.allCus[i].getNumber(),cus.allCus[i].getNumber(),cus.allCus[i].getNumber(),cus.allCus[i].getNumber(),cus.allCus[i].getNumber(),cus.allCus[i].getNumber(),cus.allCus[i].getNumber(),cus.allCus[i].getamount()};
        }
    }
}
