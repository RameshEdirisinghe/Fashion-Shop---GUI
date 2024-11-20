import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


class allCustomer extends JFrame {

    private Customer[] allCus;
    private int validCount;
    List cusList = new List(100, 0.5);
    allCustomer(List cus){
        setTitle("All Customers");
        setSize(800,300);
        setDefaultCloseOperation(2);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        
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
        allcustomer();

        String[] cols = {"Customer ID","XS","S","M","L","XL","XXL","Amount"};
        DefaultTableModel tbl = new DefaultTableModel(cols,0);

        
        for(int i=0;i<validCount;i++){
            System.out.println(allCus[i].getNumber()+""+allCus[i].getXS());
            Object[] row = {allCus[i].getNumber(),allCus[i].getXS(),allCus[i].getS(),allCus[i].getM(),allCus[i].getL(),allCus[i].getXL(),allCus[i].getXXL(),allCus[i].getamount()};
            tbl.addRow(row);
        }

        JTable Table = new JTable(tbl);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40,40,700,200);
        add(scroll);
    }

    public void allcustomer() {
        allCus = new Customer[cusList.size()];
        boolean[] processed = new boolean[cusList.size()];
        validCount = 0;

        for (int i = 0; i < cusList.size() ; i++) {
            if (processed[i]) {
                continue;
            }

            allCus[validCount] = new Customer();
            int Mcount = 0;
            int XScount = 0;
            int Scount = 0;
            int Lcount = 0;
            int XLcount = 0;
            int XXLcount = 0;

            if (cusList.getCustomerAr()[i].getTshirtSize().equals("M")) {
                Mcount = cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("XS")) {
                XScount = cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("S")) {
                Scount = cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("L")) {
                Lcount = cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("XL")) {
                XLcount = cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("XXL")) {
                XXLcount = cusList.getCustomerAr()[i].getQty();

            }

            double tempamount = cusList.getCustomerAr()[i].getamount();
            processed[i] = true;

            for (int j = i + 1; j < cusList.size() ; j++) {
                if (cusList.getCustomerAr()[i].getNumber().equals(cusList.getCustomerAr()[j].getNumber())) {

                    if (cusList.getCustomerAr()[j].getTshirtSize().equals("M")) {
                        Mcount += cusList.getCustomerAr()[j].getQty();

                    } else if (cusList.getCustomerAr()[j].getTshirtSize().equals("XS")) {
                        XScount += cusList.getCustomerAr()[j].getQty();

                    } else if (cusList.getCustomerAr()[j].getTshirtSize().equals("S")) {
                        Scount += cusList.getCustomerAr()[j].getQty();

                    } else if (cusList.getCustomerAr()[j].getTshirtSize().equals("L")) {
                        Lcount += cusList.getCustomerAr()[j].getQty();

                    } else if (cusList.getCustomerAr()[j].getTshirtSize().equals("XL")) {
                        XLcount += cusList.getCustomerAr()[j].getQty();

                    } else if (cusList.getCustomerAr()[j].getTshirtSize().equals("XXL")) {
                        XXLcount += cusList.getCustomerAr()[j].getQty();
                    }
                    tempamount += cusList.getCustomerAr()[j].getamount();
                    processed[j] = true;
                }
            }

            allCus[validCount].setallcustomerValues(cusList.getCustomerAr()[i].getNumber(), XScount, Scount, Mcount, Lcount,
                    XLcount,
                    XXLcount, tempamount);

            // for (int j = 0; j < validCount; j++) {
            // System.out.println(allCus[i].getNumber()+""+allCus[i].getXL());
            // }
            validCount++;

        }
    }
}
