import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HomePage extends JFrame {

    private CustomerCollection cus;

    HomePage(CustomerCollection cus) {
        // this.cus = cus;

        setTitle("Fashion Shop");
        setSize(500, 500);
        setDefaultCloseOperation(2);
        setLocationRelativeTo(null);

      
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        JLabel titleLabel = new JLabel("Fashion Shop", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 120, 215));
        titleLabel.setBounds(0, 0, 500, 50); 
        mainPanel.add(titleLabel);

       

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(60, 80, 160, 40);
        mainPanel.add(searchButton);
        searchButton.addActionListener(evt -> {
            String[] options = { "Search Customer", "Search Order", "Cancel" };
            int choice = JOptionPane.showOptionDialog(null,
                    "Please select the option",
                    "Search Options",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    options,
                    options[0]);
            switch (choice) {
                case 0:
                    System.out.println("Search Customer selected");
                    dispose();
                    new SearchCustomer(cus).setVisible(true);
                    break;
                case 1:
                    System.out.println("Search Order selected");
                    dispose();
                    new SearcherOrderID(cus).setVisible(true);
                    break;
                case 2:
                    System.out.println("Cancel selected");
                    // Add action for "Cancel"
                    break;
                default:
                    System.out.println("No option selected");
                    break;
            }
        });

 
        JButton statusButton = new JButton("Status");
        statusButton.setBounds(60, 150, 160, 40);
        mainPanel.add(statusButton);
        statusButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new status(cus).setVisible(true);
            }
        });

        JButton reportsButton = new JButton("Reports");
        reportsButton.setBounds(60, 220, 160, 40);
        mainPanel.add(reportsButton);
        reportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
                new Reports(cus).setVisible(true);
            }
        });

        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(60, 290, 160, 40);
        mainPanel.add(deleteButton);

        
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(60, 360, 160, 60);
        placeOrderButton.setBackground(new Color(0, 204, 204)); 
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        placeOrderButton.addActionListener(evt -> {
            dispose();
            new placeOrder(cus).setVisible(true);
         
        });
        mainPanel.add(placeOrderButton);

        
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("D:\\Icd\\OOP\\Coursework2\\pngwing.com (1).png");
        imageLabel.setIcon(icon);
        imageLabel.setBounds(250, 100, 300, 350); 
        mainPanel.add(imageLabel);
    }
}
