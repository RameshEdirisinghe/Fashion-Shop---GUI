import java.util.*;
import java.util.Locale.Category;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



class catByamount extends JFrame {
    private sort[] sortamount;
    List cusList = new List(100, 0.5);
    catByamount(List cus){
        setSize(400,400);
        setTitle("Categorized by Amount");
        setDefaultCloseOperation(2);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
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
        sortByamount();
        String[] cols = {"Size","Qty","Amount"};
        DefaultTableModel tbl = new DefaultTableModel(cols,0);

        for(int i =0 ; i<6;i++){
            Object[] row = {sortamount[i].getSize(),sortamount[i].getqty(),sortamount[i].getamount()};
            tbl.addRow(row);
        }

        JTable Table = new JTable(tbl);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setBounds(40,40,300,300);
        add(scroll);
    }

    public void sortByamount() {
        sortamount = new sort[6];
        boolean[] processed = new boolean[cusList.size()];

        int Mqty = 0;
        int XSqty = 0;
        int Sqty = 0;
        int Lqty = 0;
        int XLqty = 0;
        int XXLqty = 0;

        double Mtotal = 0;
        double XStotal = 0;
        double Stotal = 0;
        double Ltotal = 0;
        double XLtotal = 0;
        double XXLtotal = 0;

        for (int i = 0; i < cusList.size(); i++) {

            if (cusList.getCustomerAr()[i].getTshirtSize().equals("M")) {
                Mqty += cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("XL")) {
                XLqty += cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("XS")) {
                XSqty += cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("S")) {
                Sqty += cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("XXL")) {
                XXLqty += cusList.getCustomerAr()[i].getQty();

            } else if (cusList.getCustomerAr()[i].getTshirtSize().equals("L")) {
                Lqty += cusList.getCustomerAr()[i].getQty();

            }

        }
        Mtotal = Mqty * 900;
        XLtotal = XLqty * 1100;
        XStotal = XSqty * 600;
        Stotal = Sqty * 900;
        Ltotal = Lqty * 1000;
        XXLtotal = XXLqty * 1200;

        sortamount[0] = new sort();
        sortamount[0].setByqty("XS", XSqty, XStotal);
        sortamount[1] = new sort();
        sortamount[1].setByqty("S", Sqty, Stotal);
        sortamount[2] = new sort();
        sortamount[2].setByqty("M", Mqty, Mtotal);
        sortamount[3] = new sort();
        sortamount[3].setByqty("L", Lqty, Ltotal);
        sortamount[4] = new sort();
        sortamount[4].setByqty("XL", XLqty, XLtotal);
        sortamount[5] = new sort();
        sortamount[5].setByqty("XXL", XXLqty, XXLtotal);
        for (int i = 5; i > 0; i--) {

            for (int j = 0; j < i; j++) {
                if (sortamount[j].getamount() < sortamount[j + 1].getamount()) {
                    sort temp = sortamount[j];
                    sortamount[j] = sortamount[j + 1];
                    sortamount[j + 1] = temp;
                }
            }
        }
    }
    
}

