class List{
    public Customer[] customerArray;
    public Customer[] viewReportarray;
    public Customer[] bestCusarray;
    public sort[] sortamount;
    public sort[] sortqty;
    public Customer[] odramount;
    public Customer[] allCus;
    int validCount;
    private String OderID;
    public int orderNumber = 1;
    private double amount;
    private int nextIndex;
	private double loadFact;
	private int initSize;


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

    List(int initSize, double loadFact) {
        this.initSize=initSize;
		customerArray=new Customer[initSize];
		
		nextIndex=0;
		this.loadFact=loadFact;

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

    public boolean add(Customer customer) {
        if(nextIndex>=customerArray.length){
			extendsArray();
		}
		customerArray[nextIndex++]=customer;

        return true;

    }

    private void extendsArray(){
		Customer[] tempCustomerArray=new Customer[customerArray.length+(int)(customerArray.length*loadFact)];
		for (int i = 0; i < customerArray.length; i++){
			tempCustomerArray[i]=customerArray[i];
		}
		customerArray=tempCustomerArray;
	}


    public boolean searchCustomer(String num) {
        boolean isCorrect = false;
        resetcount();

        for (int i = 0; i < nextIndex ; i++) {

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

    public void getviewCustomers() {
        boolean[] processed = new boolean[nextIndex];
        viewReportarray = new Customer[nextIndex];

        validCount = 0;
        for (int i = 0; i < nextIndex; i++) {
            if (processed[i]) {
                continue;
            }

            viewReportarray[validCount] = new Customer();
            int tempqty = customerArray[i].getQty();
            double tempamount = customerArray[i].getamount();
            processed[i] = true;

            for (int j = i + 1; j < nextIndex; j++) {
                if (customerArray[i].getNumber().equals(customerArray[j].getNumber())) {
                    tempqty += customerArray[j].getQty();
                    tempamount += customerArray[j].getamount();
                    processed[j] = true;
                }
            }

            viewReportarray[validCount].setViewReportValues(customerArray[i].getNumber(), tempqty, tempamount);

            validCount++;
        }

    }

    public void getBestCustomer() {
        boolean[] processed = new boolean[nextIndex];
        bestCusarray = new Customer[nextIndex];

        validCount = 0;
        for (int i = 0; i < nextIndex; i++) {
            if (processed[i]) {
                continue;
            }

            bestCusarray[validCount] = new Customer();
            int tempqty = customerArray[i].getQty();
            double tempamount = customerArray[i].getamount();
            processed[i] = true;

            for (int j = i + 1; j < nextIndex; j++) {
                if (customerArray[i].getNumber().equals(customerArray[j].getNumber())) {
                    tempqty += customerArray[j].getQty();
                    tempamount += customerArray[j].getamount();
                    processed[j] = true;
                }
            }

            bestCusarray[validCount].setViewReportValues(customerArray[i].getNumber(), tempqty, tempamount);

            validCount++;
        }

        for (int i = validCount - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (bestCusarray[j].getamount() < bestCusarray[j + 1].getamount()) {
                    Customer temp = bestCusarray[j + 1];
                    bestCusarray[j + 1] = bestCusarray[j];
                    bestCusarray[j] = temp;
                }
            }
        }

    }

    public void allcustomer() {
        allCus = new Customer[nextIndex];
        boolean[] processed = new boolean[nextIndex];
        validCount = 0;

        for (int i = 0; i < nextIndex ; i++) {
            if (processed[i]) {
                continue;
            }

            allCus[validCount] = new Customer();
            int Mcount = 0;
            int XScount = 0;
            int Scount = 0;
            int Lcount = 0;
            int XLcount = 0;
            int XXLcount = 0;

            if (customerArray[i].getTshirtSize().equals("M")) {
                Mcount = customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XS")) {
                XScount = customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("S")) {
                Scount = customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("L")) {
                Lcount = customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XL")) {
                XLcount = customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XXL")) {
                XXLcount = customerArray[i].getQty();

            }

            double tempamount = customerArray[i].getamount();
            processed[i] = true;

            for (int j = i + 1; j < nextIndex ; j++) {
                if (customerArray[i].getNumber().equals(customerArray[j].getNumber())) {

                    if (customerArray[j].getTshirtSize().equals("M")) {
                        Mcount += customerArray[j].getQty();

                    } else if (customerArray[j].getTshirtSize().equals("XS")) {
                        XScount += customerArray[j].getQty();

                    } else if (customerArray[j].getTshirtSize().equals("S")) {
                        Scount += customerArray[j].getQty();

                    } else if (customerArray[j].getTshirtSize().equals("L")) {
                        Lcount += customerArray[j].getQty();

                    } else if (customerArray[j].getTshirtSize().equals("XL")) {
                        XLcount += customerArray[j].getQty();

                    } else if (customerArray[j].getTshirtSize().equals("XXL")) {
                        tempXXLcount += customerArray[j].getQty();
                    }
                    tempamount += customerArray[j].getamount();
                    processed[j] = true;
                }
            }

            allCus[validCount].setallcustomerValues(customerArray[i].getNumber(), XScount, Scount, Mcount, Lcount,
                    XLcount,
                    XXLcount, tempamount);

            // for (int j = 0; j < validCount; j++) {
            // System.out.println(allCus[i].getNumber()+""+allCus[i].getXL());
            // }
            validCount++;

        }
    }

    public void categorizedByQty() {
        sortqty = new sort[6];
        boolean[] processed = new boolean[nextIndex];

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

        for (int i = 0; i < nextIndex ; i++) {

            if (customerArray[i].getTshirtSize().equals("M")) {
                Mqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XL")) {
                XLqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XS")) {
                XSqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("S")) {
                Sqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XXL")) {
                XXLqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("L")) {
                Lqty += customerArray[i].getQty();

            }

        }
        Mtotal = Mqty * 900;
        XLtotal = XLqty * 1100;
        XStotal = XSqty * 600;
        Stotal = Sqty * 900;
        Ltotal = Lqty * 1000;
        XXLtotal = XXLqty * 1200;

        sortqty[0] = new sort();
        sortqty[0].setByqty("XS", XSqty, XStotal);
        sortqty[1] = new sort();
        sortqty[1].setByqty("S", Sqty, Stotal);
        sortqty[2] = new sort();
        sortqty[2].setByqty("M", Mqty, Mtotal);
        sortqty[3] = new sort();
        sortqty[3].setByqty("L", Lqty, Ltotal);
        sortqty[4] = new sort();
        sortqty[4].setByqty("XL", XLqty, XLtotal);
        sortqty[5] = new sort();
        sortqty[5].setByqty("XXL", XXLqty, XXLtotal);

        for (int i = 5; i > 0; i--) {

            for (int j = 0; j < i; j++) {
                if (sortqty[j].getqty() < sortqty[j + 1].getqty()) {
                    sort temp = sortqty[j];
                    sortqty[j] = sortqty[j + 1];
                    sortqty[j + 1] = temp;
                }
            }
        }

    }

    public int searchOrderId(String oderID) {
        		

        for (int i = 0; i < nextIndex ; i++) {
            if (customerArray[i].getId().equals(oderID)) {
                return i;
            }
        }
        return -1;
    }

    public void odramount() {

        odramount = new Customer[nextIndex];
       



        for (int i = 0; i < nextIndex; i++) {
            // copy previous data
            odramount[i] = customerArray[i];
        }

        for (int i = nextIndex - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (odramount[j].getamount() < odramount[j + 1].getamount()) {
                    Customer temp = odramount[j];
                    odramount[j] = odramount[j + 1];
                    odramount[j + 1] = temp;
                }
            }
        }
    }


    public void sortByamount() {
        sortamount = new sort[6];
        boolean[] processed = new boolean[nextIndex];

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

        for (int i = 0; i < nextIndex; i++) {

            if (customerArray[i].getTshirtSize().equals("M")) {
                Mqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XL")) {
                XLqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XS")) {
                XSqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("S")) {
                Sqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("XXL")) {
                XXLqty += customerArray[i].getQty();

            } else if (customerArray[i].getTshirtSize().equals("L")) {
                Lqty += customerArray[i].getQty();

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
    public boolean remove(int deleteindex) {
        if(deleteindex>=0 && deleteindex<nextIndex){
			for(int i=deleteindex; i<nextIndex-1; i++){
				customerArray[i]=customerArray[i+1];

			}
			nextIndex--;
            return true;
		}
        return false;
    }

    public int size(){
		return nextIndex;
	}
	public boolean isEmpty(){
		return nextIndex<=0;
	}
	public int capacity(){
		return customerArray.length;
	}
	public void clear(){
		nextIndex=0;
		customerArray=new Customer[initSize];
	}

    public Customer[] toArray(){
		Customer[] tempCustomerArray=new Customer[nextIndex];
		for (int i = 0; i < nextIndex; i++){
			tempCustomerArray[i]=customerArray[(nextIndex-1)-i];
		}
		return tempCustomerArray;
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
    private int status;
    private int XS;
    private int S;
    private int M;
    private int L;
    private int XL;
    private int XXL;

    Customer() {
    }

    Customer(String id, String ContactNumber, String TshirtSize, int Qty, double amount, int status) {
        this.id = id;
        this.ContactNumber = ContactNumber;
        this.TshirtSize = TshirtSize;
        this.Qty = Qty;
        this.amount = amount;
        this.status = status;
    }

    public void setViewReportValues(String ContactNumber, int Qty, double amount) {

        this.ContactNumber = ContactNumber;
        this.Qty = Qty;
        this.amount = amount;

    }
    public void setStatus(int status){
        this.status=status;
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

    public String getstatus() {

        if (status == 0) {
            return "Processing";
        } else if (status == 1) {
            return "Delivering";

        } else if (status == 2) {
            return "Delivered";
        } else {
            return null;
        }
    }
    

    public void setallcustomerValues(String Contactnum, int XS, int S, int M, int L, int XL, int XXL, double amount) {

        this.ContactNumber = Contactnum;
        this.XS = XS;
        this.S = S;
        this.M = M;
        this.L = L;
        this.XL = XL;
        this.XXL = XXL;
        this.amount = amount;
    }

    public int getXS() {
        return XS;
    }

    public int getS() {
        return S;
    }

    public int getM() {
        return M;
    }

    public int getL() {
        return L;
    }

    public int getXL() {
        return XL;
    }

    public int getXXL() {
        return XXL;
    }

    // getters
}

class sort {
    private int qty;
    private String size;
    private double amount;

    public void setByqty(String size, int qty, double amount) {

        this.size = size;
        this.qty = qty;
        this.amount = amount;
    }

    public String getSize() {
        return size;
    }

    public int getqty() {
        return qty;
    }

    public double getamount() {
        return amount;
    }

}
