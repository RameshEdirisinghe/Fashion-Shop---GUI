class CustomerCollection {
    public Customer[] customerArray;
    public Customer[] viewReportarray;
    public Customer[] bestCusarray;
    public Customer[] allCus ;
    int validCount;
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

    public void getviewCustomers() {
        boolean[] processed = new boolean[customerArray.length];
        viewReportarray = new Customer[customerArray.length];

        validCount = 0;
        for (int i = 0; i < customerArray.length; i++) {
            if (processed[i]) {
                continue;
            }

            viewReportarray[validCount] = new Customer();
            int tempqty = customerArray[i].getQty();
            double tempamount = customerArray[i].getamount();
            processed[i] = true;

            for (int j = i + 1; j < customerArray.length; j++) {
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
        boolean[] processed = new boolean[customerArray.length];
        bestCusarray = new Customer[customerArray.length];

        validCount = 0;
        for (int i = 0; i < customerArray.length; i++) {
            if (processed[i]) {
                continue;
            }

            bestCusarray[validCount] = new Customer();
            int tempqty = customerArray[i].getQty();
            double tempamount = customerArray[i].getamount();
            processed[i] = true;

            for (int j = i + 1; j < customerArray.length; j++) {
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
        allCus = new Customer[customerArray.length];
        boolean[] processed = new boolean[customerArray.length];
        int validCount = 0;

        for (int i = 0; i < customerArray.length; i++) {
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

            for (int j = i + 1; j < customerArray.length; j++) {
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

            allCus[validCount].setallcustomerValues(customerArray[i].getNumber(), XScount, Scount, Mcount, Lcount, XLcount,
                    XXLcount, tempamount);
            validCount++;

        }
    }

    public int searchOrderId(String oderID) {
        for (int i = 0; i < customerArray.length; i++) {
            if (customerArray[i].getId().equals(oderID)) {
                return i;
            }
        }
        return -1;
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
            return "Processsing";
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
