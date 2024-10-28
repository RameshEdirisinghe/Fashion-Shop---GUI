import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class placeOrder extends JFrame{

    private JLabel Ptitle;

    private JButton btnPlace;
    private JButton btnCancel;

    private JLabel lblId;
    private JLabel lblNumber;
    private JLabel lblTshirtSize;
    private JLabel lblQty;
    private JLabel lblamount;
    
    private JTextField txtId;
    private JTextField txtNumber;
    private JTextField txtTshirtSize;
    private JTextField txtQty;
    private JTextField txtamount;


    placeOrder(CustomerCollection cus ){
        
        setSize(600,500);
		setTitle("Fashion Shop");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

        Ptitle=new JLabel("Place Order");
		Ptitle.setFont(new Font("",1,30));
		Ptitle.setHorizontalAlignment(JLabel.CENTER);
		add("North",Ptitle);

        JPanel LabelPanel=new JPanel(new GridLayout(5,1));
        JPanel lblIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblId=new JLabel("Oder ID: ");
        lblId.setFont(new Font("",1,20));
        lblIdPanel.add("Left",lblId);
        LabelPanel.add(lblIdPanel);

        JPanel lblNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblNumber=new JLabel("Contact Number: ");
        lblNumber.setFont(new Font("",1,20));
        lblNumPanel.add("Left",lblNumber);
        LabelPanel.add(lblNumPanel);

        JPanel lblTshirtPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblTshirtSize=new JLabel("Tshirt-Size: ");
        lblTshirtSize.setFont(new Font("",1,20));
        lblTshirtPanel.add("Left",lblTshirtSize);
        LabelPanel.add(lblTshirtPanel);

        JPanel lblQtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblQty=new JLabel("Qty : ");
        lblQty.setFont(new Font("",1,20));
        lblQtyPanel.add("Left",lblQty);
        LabelPanel.add(lblQtyPanel);

        JPanel lblAmountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblamount=new JLabel("Amount : ");
        lblamount.setFont(new Font("",1,20));
        lblAmountPanel.add("Left",lblamount);
        LabelPanel.add(lblAmountPanel);

        add("West",LabelPanel);

        
        JPanel TextPanel=new JPanel(new GridLayout(5,1));
        JPanel TextIdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtId=new JTextField(20);
        txtId.setFont(new Font("",1,20));
        txtId.setText(cus.getOrderId());;
        TextIdPanel.add("Left",txtId);
        TextPanel.add(TextIdPanel);

        JPanel TextNumberPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtNumber=new JTextField(20);
        txtNumber.setFont(new Font("",1,20));
        TextNumberPanel.add("Left",txtNumber);
        TextPanel.add(TextNumberPanel);

        JPanel txtTshirtSizePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtTshirtSize=new JTextField(20);
        txtTshirtSize.setFont(new Font("",1,20));
        txtTshirtSizePanel.add("Left",txtTshirtSize);
        TextPanel.add(txtTshirtSizePanel);

        JPanel txtQtyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtQty=new JTextField(20);
        txtQty.setFont(new Font("",1,20));
        txtQtyPanel.add("Left",txtQty);
        TextPanel.add(txtQtyPanel);

        JPanel txtamountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtamount=new JTextField(20);
        txtamount.setFont(new Font("",1,20));
        int qty = Integer.parseInt(txtQty.getText());
        cus.getamount(txtTshirtSize.getText(),qty );
        txtamountPanel.add("Left",txtamount);
        TextPanel.add(txtamountPanel);

        add("East",TextPanel);



    }
}
