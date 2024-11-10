import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class viewCustomers extends JFrame {

    viewCustomers(List cus) {
        setTitle("View Customers");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        cus.getviewCustomers();
        

        JButton BackButton = new JButton("Back");
        BackButton.setBackground(new Color(255, 102, 102));
        BackButton.setFont(new Font("Arial", Font.BOLD, 15));
        BackButton.setForeground(Color.WHITE);
        BackButton.setBounds(0, 0, 80, 30);
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Reports(cus).setVisible(true);
            }
        });
        add(BackButton);

        String[] colnames = { "Number", "Quantity", "Amount" };

        DefaultTableModel dtm = new DefaultTableModel(colnames, 0);
        
        
        for (int i = 0; i < cus.validCount; i++) {
            Object[] rowData = { cus.viewReportarray[i].getNumber(), cus.viewReportarray[i].getQty(),cus.viewReportarray[i].getamount() };
            dtm.addRow(rowData);
        }

        JTable custable = new JTable(dtm);
        JScrollPane tablepane = new JScrollPane(custable);
        tablepane.setBounds(40, 40, 300, 300);
        add(tablepane);
    }
}
