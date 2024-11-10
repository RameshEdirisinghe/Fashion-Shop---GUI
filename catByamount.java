import java.util.*;
import java.util.Locale.Category;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;



class catByamount extends JFrame {
    catByamount(List cus){
        setSize(400,400);
        setTitle("Categorized by Amount");
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);

        cus.sortByamount();
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial",Font.BOLD,15));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(255, 102, 102));
        back.setBounds(0,0,80,30);
        add(back);
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new Reports(cus).setVisible(true);
            }
        });

        String[] cols = {"Size","Qty","Amount"};
        DefaultTableModel tbl = new DefaultTableModel(cols,0);

        for(int i =0 ; i<6;i++){
            Object[] row = {cus.sortamount[i].getSize(),cus.sortamount[i].getqty(),cus.sortamount[i].getamount()};
            tbl.addRow(row);
        }

        JTable Table = new JTable(tbl);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40,40,300,300);
        add(scroll);
    }
    
}

