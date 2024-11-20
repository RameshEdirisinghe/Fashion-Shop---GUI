import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



class viewCustomers extends JFrame {
    private Customer[] viewReportarray;
    private int validCount;
    List cusList = new List(100, 0.5);
    List getviewList = new List(100, 0.5);

    viewCustomers(List cus) {
        setTitle("View Customers");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getviewCustomers();
        setResizable(false);

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

        try {
            BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
            String Line = br.readLine();
            while (Line != null) {
                String[] newar = Line.split(",");
                Line = br.readLine();
                Customer customer = new Customer(newar[0], newar[1], newar[2], Integer.parseInt(newar[3]),
                Double.parseDouble(newar[4]), newar[5]);
                cusList.addLast(customer);

            }
        } catch (IOException ex) {
        }
        getviewCustomers();
        String[] colnames = { "Number", "Quantity", "Amount" };
        DefaultTableModel dtm = new DefaultTableModel(colnames, 0);

        
        for (int i = 0; i < validCount; i++) {
            Object[] rowData = {temp.ContactNumber,temp.Qty ,temp.amount};
            dtm.addRow(rowData);
            temp=temp.next;
        }

        JTable custable = new JTable(dtm);
        JScrollPane tablepane = new JScrollPane(custable);
        tablepane.setBounds(40, 40, 300, 300);
        add(tablepane);
    }

    public void getviewCustomers() {
        boolean[] processed = new boolean[cusList.size()];
        Customer[] cusarray = cusList.toArray();

        

        validCount = 0;
        for (int i = 0; i < cusList.size(); i++) {
            if (processed[i]) {
                continue;
            }
            

            int tempqty = cusarray[i].getQty();
            double tempamount = cusarray[i].getamount();
            processed[i] = true;

            for (int j = i + 1; j < cusList.size(); j++) {

                if (cusarray[i].getNumber().equals(cusarray[j].getNumber())) {
                    tempqty += cusarray[j].getQty();
                    tempamount += cusarray[j].getamount();
                    processed[j] = true;
                }
                
            }

            Customer[] viewReportarray = new Customer()
            getviewList.addLast(cusarray[i].getNumber(),tempqty ,tempamount);
            validCount++;
            
        }

    }

    // public int getqty(){
    // node temp=start;
    // int index=0;
    // while(temp!=null){
    // if(temp.ContactNumber.equals(ContactNumber)){

    // }
    // index++;
    // temp=temp.next;
    // }
    // return -1;
    // }

}
