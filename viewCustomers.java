import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class viewCustomers extends JFrame {
  

    viewCustomers(CustomerCollection cus) {
        setTitle("All Customers");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton BackButton = new JButton("Back");
        BackButton.setBackground(new Color(255, 102, 102));
        BackButton.setForeground(Color.WHITE);
        BackButton.setBounds(0,0,80,30);
        add(BackButton);
        String[] colnames = {"Number", "Quantity", "Amount"};

       
        DefaultTableModel dtm = new DefaultTableModel(colnames, 0);

        
        for (int i = 0; i < cus.customerArray.length; i++) {
            Object[] rowData = {
                cus.customerArray[i].getNumber(),
                cus.customerArray[i].getQty(),
                cus.customerArray[i].getamount()
            };
            dtm.addRow(rowData);
        }

        
        JTable custable = new JTable(dtm);
        JScrollPane tablepane = new JScrollPane(custable);
        tablepane.setBounds(40, 40, 300, 300);
        add(tablepane); 
    }
}

