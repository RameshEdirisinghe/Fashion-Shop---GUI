import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class HomePage extends JFrame{

    private JLabel Htitle;

    private JButton btnPlaceorder;
    private JButton btnSearch;
    private JButton btnSearchOid;
    private JButton btnViewReport;
    private JButton btnchangeStatus;
    private JButton btnDeleteOrder;

    private  CustomerCollection cus;
   

    HomePage( CustomerCollection cus){
        this.cus = cus;
        setSize(600,500);
		setTitle("Fashion Shop");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

        Htitle=new JLabel("Fashion Shop");
		Htitle.setFont(new Font("",1,30));
		Htitle.setHorizontalAlignment(JLabel.CENTER);
		add("North",Htitle);

        JPanel buttonPanel=new JPanel(new GridLayout(3,2));
        JPanel btnPlaceorderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPlaceorder=new JButton("Place Order");
		btnPlaceorder.setFont(new Font("",1,18));
        btnPlaceorderPanel.add("Center",btnPlaceorder);
        btnPlaceorder.addActionListener(new ActionListener(){
            
			public void actionPerformed(ActionEvent evt){
				new placeOrder(cus).setVisible(true);

			}
		});
        buttonPanel.add(btnPlaceorderPanel);

        btnSearch=new JButton("Search Customer");
        JPanel btnSearchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnSearch.setFont(new Font("",1,18));
        btnSearchPanel.add("Center",btnSearch);
        buttonPanel.add(btnSearchPanel);

        btnSearchOid=new JButton("Search Order");
        JPanel btnSearchOrderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnSearchOid.setFont(new Font("",1,18));
        btnSearchOrderPanel.add("Center",btnSearchOid);
        buttonPanel.add(btnSearchOrderPanel);

        btnViewReport=new JButton("View Report");
        JPanel btnViewReportPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnViewReport.setFont(new Font("",1,18));
        btnViewReportPanel.add("Center",btnViewReport);
        buttonPanel.add(btnViewReportPanel);

        btnchangeStatus=new JButton("Change Status");
        JPanel btnchangeStatusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnchangeStatus.setFont(new Font("",1,18));
        btnchangeStatusPanel.add("Center",btnchangeStatus);
        buttonPanel.add(btnchangeStatusPanel);

        btnDeleteOrder=new JButton("Delete Order");
        JPanel btnbtnDeleteOrderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnDeleteOrder.setFont(new Font("",1,18));
        btnbtnDeleteOrderPanel.add("Center",btnDeleteOrder);
        buttonPanel.add(btnbtnDeleteOrderPanel);

        add("Center",buttonPanel);
		
			
    }

}

