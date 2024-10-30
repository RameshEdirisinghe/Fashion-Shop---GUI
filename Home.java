import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class HomePage extends JFrame{

    // private JLabel Htitle;
    // private JButton btnPlaceorder;
    // private JButton btnSearch;
    // private JButton btnSearchOid;
    // private JButton btnViewReport;
    // private JButton btnchangeStatus;
    // private JButton btnDeleteOrder;

    private  CustomerCollection cus;
   

 
    HomePage( CustomerCollection cus){
        this.cus = cus;
        
        setTitle("Fashion Shop");
        setSize(500, 700);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

       
        JLabel titleLabel = new JLabel("Fashion Shop", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 120, 215));
        titleLabel.setPreferredSize(new Dimension(400, 50));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 20, 20));  
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 70)); 
        buttonPanel.setBackground(Color.WHITE);

        
        Dimension buttonSize = new Dimension(400, 10);

        
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener(){
            
			public void actionPerformed(ActionEvent evt){
				String[] options = {"Search Customer", "Search Order", "Cancel"};
        
               
                int choice = JOptionPane.showOptionDialog(null,
                            "Please select the option",
                            "Search Options",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null,
                            options,
                            options[0]
                            );
        switch (choice) {
            case 0:
                System.out.println("Search Customer selected");
                new SearchCustomer(cus).setVisible(true);
                break;
            case 1:
                System.out.println("Search Order selected");
                // Add action for "Search Order"
                break;
            case 2:
                System.out.println("Cancel selected");
                // Add action for "Cancel"
                break;
            default:
                System.out.println("No option selected");
                break;
        }
			}
		});

        searchButton.setPreferredSize(buttonSize);
        
        JButton statusButton = new JButton("Status");
        statusButton.setPreferredSize(buttonSize);
        
        JButton reportsButton = new JButton("Reports");
        reportsButton.setPreferredSize(buttonSize);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(buttonSize);
        
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setPreferredSize(buttonSize);
        placeOrderButton.setBackground(new Color(0, 204, 204));  // Color for the Place Order button
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        placeOrderButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new placeOrder(cus).setVisible(true);

			}
		});

        
        buttonPanel.add(searchButton);
        buttonPanel.add(statusButton);
        buttonPanel.add(reportsButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(placeOrderButton);

        
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

       
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("D:\\iCET\\Demo\\images.png");
        imageLabel.setIcon(icon);
        imageLabel.setBounds(320, 100, 200, 350);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(imageLabel, BorderLayout.EAST);

        setContentPane(mainPanel);
    }

}

