import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Fashionshop {

	public static void main(String[] args) {
		CustomerCollection cus = new CustomerCollection();
		new HomePage(cus).setVisible(true);

	}

}

class CustomerCollection {
	private Customer[] customerArray;
	private String OderID;
	public int orderNumber = 1;
	private double amount;

	int tempMcount;
	int tempXScount;
	int tempScount;
	int tempLcount;
	int tempXLcount;
	int tempXXLcount;

	double Mamount;
	double XSamount;
	double Samount;
	double Lamount;
	double XLamount;
	double XXLamount;
	double Totalamount;

	CustomerCollection() {
		customerArray = new Customer[0];
	}

	public String getOrderId() {

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

	public boolean validateContact(String contact) {

		if (contact.length() != 10 || contact.charAt(0) != '0') {
			return false;
		} else {
			return true;
		}

	}

	public Boolean validateTsize(String tshirtsize) {
		if (!(tshirtsize.equals("XS") || tshirtsize.equals("S") || tshirtsize.equals("L") || tshirtsize.equals("XL")
				|| tshirtsize.equals("M") || tshirtsize.equals("XXL"))) {

			return false;
		} else {
			return true;
		}
	}

	public double getamount(String tshirtsize, int QTY) {
		amount = 0;
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
		return amount;
	}

	public boolean addCustomer(Customer customer) {
		extendsArray();
		customerArray[customerArray.length - 1] = customer;
		return true;

	}

	public boolean searchCustomer(String num) {
		boolean isCorrect = false;
		resetcount();
		
		for (int i = 0; i < customerArray.length; i++) {

			if (num.equals(customerArray[i].getNumber())) {
				isCorrect = true;
				if (customerArray[i].getTshirtSize().equals("M")) {
					tempMcount += customerArray[i].getQty();
				} else if (customerArray[i].getTshirtSize().equals("XS")) {
					tempXScount += customerArray[i].getQty();
				} else if (customerArray[i].getTshirtSize().equals("S")) {
					tempScount += customerArray[i].getQty();
				} else if (customerArray[i].getTshirtSize().equals("L")) {
					tempLcount += customerArray[i].getQty();
				} else if (customerArray[i].getTshirtSize().equals("XL")) {
					tempXLcount += customerArray[i].getQty();
				} else if (customerArray[i].getTshirtSize().equals("XXL")) {
					tempXXLcount += customerArray[i].getQty();
				}
			}

		}

		Mamount = tempMcount * 900;
		XLamount = tempXLcount * 1100;
		XXLamount = tempXXLcount * 1200;
		XSamount = tempXScount * 600;
		Samount = tempScount * 800;
		Lamount = tempLcount * 1000;
		Totalamount = Mamount + XLamount + XXLamount + XSamount + Samount + Lamount;

		return isCorrect;

	}

	public void resetcount() {
		tempMcount = 0;
		tempXScount = 0;
		tempScount = 0;
		tempLcount = 0;
		tempXLcount = 0;
		tempXXLcount = 0;

		Mamount = 0;
		XSamount = 0;
		Samount = 0;
		Lamount = 0;
		XLamount = 0;
		XXLamount = 0;
		Totalamount = 0;
	}

	private void extendsArray() {
		Customer[] tempCustomerArray = new Customer[customerArray.length + 1];
		for (int i = 0; i < customerArray.length; i++) {
			tempCustomerArray[i] = customerArray[i];
		}
		customerArray = tempCustomerArray;
	}

	public void printCustomers() {
		System.out.printf("%-8s%-15s%-20s%8s%10s\n", "Id", "Contact", "TshirtSize", "Qty", "amount");
		System.out.println("-----------------------------------------------------");
		for (Customer c1 : customerArray) {
			System.out.println(c1);
		}
	}

}

class Customer {
	private String id;
	private String ContactNumber;
	private String TshirtSize;
	private int Qty;
	private double amount;

	Customer() {
	}

	Customer(String id, String ContactNumber, String TshirtSize, int Qty, double amount) {
		this.id = id;
		this.ContactNumber = ContactNumber;
		this.TshirtSize = TshirtSize;
		this.Qty = Qty;
		this.amount = amount;
	}

	public String toString() {
		return String.format("%-8s%-15s%-20s%10d%010.2f", id, ContactNumber, TshirtSize, Qty, amount);
	}

	public String getId() {
		return id;
	}

	public String getNumber() {
		return ContactNumber;
	}

	public String getTshirtSize() {
		return TshirtSize;
	}

	public int getQty() {
		return Qty;
	}

	public double getamount() {
		return amount;
	}
	// getters
}
