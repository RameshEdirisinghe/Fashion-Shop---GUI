import java.util.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;


class bestCustomer extends JFrame{
    bestCustomer(CustomerCollection cus){
        setSize(400,400);
        setTitle("Best In Customers");
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);


        cus.getBestCustomer();

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


        String[] colsname = {"Contact","Qty","Amount"};
        DefaultTableModel tblm= new DefaultTableModel(colsname,0);
        
        for(int i=0;i<cus.validCount;i++){
            Object[] row = {cus.bestCusarray[i].getNumber(),cus.bestCusarray[i].getQty(),cus.bestCusarray[i].getamount()};
            tblm.addRow(row);
        }

        JTable Table = new JTable(tblm);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40,40,300,300);
        add(scroll);

    }
}
