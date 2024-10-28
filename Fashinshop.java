import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class Fashionshop {

	
    public static void main(String[] args) {
        CustomerCollection cus=new CustomerCollection();
        new HomePage(cus).setVisible(true);
        
    }

}

class CustomerCollection{
    private Customer[] customerArray;
    private String OderID;
	private int orderNumber=0;
	private double amount;
	
	CustomerCollection(){
		customerArray=new Customer[0];
	}
    public String getOrderId(){
		orderNumber++;
		int tempOrderNumber = orderNumber;
        int[] tempNumOrder = new int[5];
        String idNum = "";
        String tag = "ODR#";
        OderID = "";
        for (int i = 4; tempOrderNumber > 0; i--) {
            tempNumOrder[i] = tempOrderNumber % 10;
            tempOrderNumber /= 10;
        }
        for (int i = 0; i < tempNumOrder.length; i++) {
            idNum += tempNumOrder[i];
        }
        OderID = tag + idNum;
        return OderID;
    }

	public double getamount(String tshirtsize,int QTY){
		amount=0;
		switch (tshirtsize) {
            case "XS":
                amount = 600 * QTY;
                break;
            case "S":
                amount = 800 * QTY;
                break;
            case "M":
                amount = 900 * QTY;
                break;
            case "L":
                amount = 1000 * QTY;
                break;
            case "XL":
                amount = 1100 * QTY;
            case "XXL":
                amount = 1200 * QTY;
            default:
                break;
        }
        System.out.println("Amount : " + amount);
		return amount;
	}
	public boolean addCustomer(Customer customer){
		extendsArray();
		customerArray[customerArray.length-1]=customer;
		return true;
	}
	public Customer searchCustomer(String id){
		for (int i = 0; i <customerArray.length; i++){
			if(customerArray[i].getId().equalsIgnoreCase(id)){
				return customerArray[i];
			}
		}
		return null;
	}
	private void extendsArray(){
		Customer[] tempCustomerArray=new Customer[customerArray.length+1];
		for (int i = 0; i < customerArray.length; i++){
			tempCustomerArray[i]=customerArray[i];
		}
		customerArray=tempCustomerArray;
	}	
	public void printCustomers(){
		System.out.printf("%-8s%-15s%-20s%10s\n","Id","Name","Address","Salary");
		System.out.println("-----------------------------------------------------");
		for(Customer c1 :customerArray){
			System.out.println(c1);
		}
	}	
}


class Customer{
	private String id;
	private String ContactNumber;
	private String TshirtSize;
    private int Qty;
	private double amount;
	
	Customer(){}
	Customer(String id, String ContactNumber, String TshirtSize, int Qty, double amount){
		this.id=id;
		this.ContactNumber=ContactNumber;
		this.TshirtSize=TshirtSize;
        this.Qty = Qty;
		this.amount=amount;
	}
	public String toString(){
		return String.format("%-8s%-15s%-20s%10d%010.2f",id,ContactNumber,TshirtSize,Qty,amount);
	}
	public String getId(){
		return id;
	}
	public String getNumber(){
		return ContactNumber;
	}
	public String getTshirtSize(){
		return TshirtSize;
	}
    public int getQty(){
        return Qty;
    }
	public double getamount(){
		return amount;
	}
	//getters
}

