import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCustomer extends JFrame {

    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private JScrollPane tableScrollPane;

    private int tempMcount =0;
	private int tempXScount =0;
	private int tempScount =0;
	private int tempLcount =0;
	private int tempXLcount =0;
	private int tempXXLcount=0;

	private double Mamount=0;
	private double XSamount=0;
	private double Samount=0;
	private double Lamount=0;
	private double XLamount=0;
	private double XXLamount=0;
	private double Totalamount=0;

    private String[] columns = new String[3];
    private Object[][] data = new Object[6][3];

    public SearchCustomer(CustomerCollection cus) {
        setTitle("Search Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(mainPanel);
        ;

        JPanel northPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 102, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        northPanel.add(backButton, BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Enter Customer ID : "));
        JTextField customerIdField = new JTextField(15);
        searchPanel.add(customerIdField);

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 153, 153));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchPanel.add(searchButton);
        northPanel.add(searchPanel, BorderLayout.SOUTH);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boolean istrue = cus.searchCustomer(customerIdField.getText());
                if (istrue) {
                    tempLcount=cus.tempLcount;
                    tempMcount=cus.tempMcount;
                    tempXScount=cus.tempXScount;
                    tempScount=cus.tempScount;
                    tempXLcount=cus.tempXLcount;
                    tempXXLcount=cus.tempXXLcount;

                    Lamount = cus.Lamount;
                    Mamount = cus.Mamount;
                    XSamount = cus.XSamount;
                    Samount = cus.Samount;
                    XLamount = cus.XLamount;
                    XXLamount = cus.XXLamount;

                    Totalamount= cus.Totalamount;
                }


            }
        });

        String[] columns = { "Size", "QTY", "Amount" };
        Object[][] data = {
                { "L", tempLcount, Lamount},
                { "M", tempMcount, Mamount },
                { "XS", tempXScount, XSamount },
                { "S", tempScount, Samount },
                { "XL", tempXLcount, XLamount},
                { "XXL", tempXXLcount, XXLamount}
        };
        tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.add(new JLabel("Total : "));
        totalLabel = new JLabel(String.valueOf(Totalamount));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        footerPanel.add(totalLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Button actions
        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Back button clicked");
                // Add back button action code here
            }
        });

        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String customerId = customerIdField.getText();
                System.out.println("Search button clicked. Customer ID: " + customerId);
                // Add search action code here
            }
        });

        setVisible(true);
    }

}
