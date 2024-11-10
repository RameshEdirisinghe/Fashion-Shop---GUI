import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class SearcherOrderID extends JFrame {

    SearcherOrderID(List cus) {
        setTitle("Search order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.red);
        backButton.setBounds(0, 0, 80, 20);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomePage(cus).setVisible(true);
            }
        });

        JLabel oderJLabel = new JLabel("Enter Order ID :");
        oderJLabel.setFont(new Font("Arial", Font.BOLD, 15));
        oderJLabel.setBounds(16, 37, 200, 15);
        add(oderJLabel);

        JTextField OrderIDField = new JTextField();
        OrderIDField.setFont(new Font("Arial", Font.BOLD, 15));
        OrderIDField.setBounds(140, 35, 150, 25);
        add(OrderIDField);

        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(4, 203, 201));
        searchButton.setBounds(300, 37, 80, 20);
        add(searchButton);

        JLabel CustomerIdLabel = new JLabel("Customer ID: ");
        CustomerIdLabel.setFont(new Font("Arial", Font.BOLD, 15));
        CustomerIdLabel.setBounds(16, 95, 200, 20);
        add(CustomerIdLabel);

        JLabel SizeLabel = new JLabel("Size :");
        SizeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        SizeLabel.setBounds(16, 145, 200, 20);
        add(SizeLabel);

        JLabel qtyLabel = new JLabel("Qty :");
        qtyLabel.setFont(new Font("Arial", Font.BOLD, 15));
        qtyLabel.setBounds(16, 195, 200, 20);
        add(qtyLabel);

        JLabel amountLabel = new JLabel("Amount :");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 15));
        amountLabel.setBounds(16, 245, 200, 20);
        add(amountLabel);

        JLabel statusLabel = new JLabel("Status :");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 15));
        statusLabel.setBounds(16, 295, 200, 20);
        add(statusLabel);

        JLabel CustomerIdField = new JLabel("");
        CustomerIdField.setFont(new Font("Arial", Font.PLAIN, 15));
        CustomerIdField.setBounds(125, 95, 200, 20);
        add(CustomerIdField);

        JLabel SizeField = new JLabel("");
        SizeField.setFont(new Font("Arial", Font.PLAIN, 15));
        SizeField.setBounds(125, 145, 200, 20);
        add(SizeField);

        JLabel qtyField = new JLabel("");
        qtyField.setFont(new Font("Arial", Font.PLAIN, 15));
        qtyField.setBounds(125, 195, 200, 20);
        add(qtyField);

        JLabel amountField = new JLabel("");
        amountField.setFont(new Font("Arial", Font.PLAIN, 15));
        amountField.setBounds(125, 245, 200, 20);
        add(amountField);

        JLabel statusField= new JLabel("");
        statusField.setFont(new Font("Arial", Font.PLAIN, 15));
        statusField.setBounds(125, 295, 200, 20);
        add(statusField);


        searchButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent evt){
                String odrId= OrderIDField.getText();
                int index = cus.searchOrderId(odrId);

                String newLine = null;
                try{
                    BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));
                    String line = br.readLine();
                    while(line!=null){
                        String id = line.substring(0,9);
                        if(id.equalsIgnoreCase(OrderIDField.getText())){
                            newLine=line;
                            break;
                        }
                        line = br.readLine();
                    }
                }catch(IOException ex){

                }
                if(newLine!=null){
                    String[] cusDetails = newLine.split(",");
                    CustomerIdField.setText(cusDetails[1]);
                    SizeField.setText(cusDetails[2]);
                    qtyField.setText(cusDetails[3]);
                    amountField.setText(cusDetails[4]);
                    String status = null;
                    if (cusDetails[5].equals("0")) {
                        status="Processing";
                    }else if(cusDetails[5].equals("1")){
                        status="Delivering";
                    }else if(cusDetails[5].equals("2")){
                        status="Delivered";
                    }
                    statusField.setText(status);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Order ID","Error",JOptionPane.ERROR_MESSAGE);                    
                }
            }
        });

    }
}