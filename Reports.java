import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Reports extends JFrame {
    
    Reports(CustomerCollection cus){
        setTitle("View Reports");
        setDefaultCloseOperation(2);
        setSize(600,300);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton BackButton = new JButton("Back");
        BackButton.setFont(new Font("Arial",Font.BOLD,15));
        BackButton.setForeground(Color.WHITE);
        BackButton.setBackground(new Color(255, 102, 102));
       
        BackButton.setBounds(0,0,80,30);
        add(BackButton);

        JButton ViewButton = new JButton("View Customers");
        ViewButton.setFont(new Font("Arial",Font.BOLD,15));
        ViewButton.setForeground(Color.WHITE);
        ViewButton.setBackground(Color.GREEN);
        ViewButton.setBounds(10,65,180,30);
        add(ViewButton);
        ViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new viewCustomers(cus).setVisible(true);
            }
        });
        

        JButton BestButton = new JButton("Best In Customers");
        BestButton.setFont(new Font("Arial",Font.BOLD,15));
        BestButton.setForeground(Color.WHITE);
        BestButton.setBackground(Color.GREEN);
        BestButton.setBounds(10,115,180,30);
        add(BestButton);

        JButton allButton = new JButton("All Customers");
        allButton.setFont(new Font("Arial",Font.BOLD,15));
        allButton.setForeground(Color.WHITE);
        allButton.setBackground(Color.GREEN);
        allButton.setBounds(10,165,180,30);
        add(allButton);

        JButton ByqtyButton = new JButton("Categorized by Qty ");
        ByqtyButton.setFont(new Font("Arial",Font.BOLD,15));
        ByqtyButton.setForeground(Color.WHITE);
        ByqtyButton.setBackground(Color.BLUE);
        ByqtyButton.setBounds(200,90,180,40);
        add(ByqtyButton);

        JButton ByamntButton = new JButton("Categorized by Qty ");
        ByamntButton.setFont(new Font("Arial",Font.BOLD,15));
        ByamntButton.setForeground(Color.WHITE);
        ByamntButton.setBackground(Color.BLUE);
        ByamntButton.setBounds(200,140,180,40);
        add(ByamntButton);

        JButton OrderbyButton = new JButton("Categorized by Qty ");
        OrderbyButton.setFont(new Font("Arial",Font.BOLD,15));
        OrderbyButton.setForeground(Color.WHITE);
        OrderbyButton.setBackground(Color.GRAY);
        OrderbyButton.setBounds(400,90,180,40);
        add(OrderbyButton);

        JButton AllOrderButton = new JButton("Categorized by Qty ");
        AllOrderButton.setFont(new Font("Arial",Font.BOLD,15));
        AllOrderButton.setForeground(Color.WHITE);
        AllOrderButton.setBackground(Color.GRAY);
        AllOrderButton.setBounds(400,140,180,40);
        add(AllOrderButton);


        BackButton.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e){
                dispose();
                new HomePage(cus).setVisible(true);
            }
        });


    }
}
