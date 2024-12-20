class List{
    private Customer[] customerArray;
    private int validCount;

    public Customer[] allCus;
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

    private Node start;

    List(int initSize, double loadFact) {
        this.initSize=initSize;
		customerArray=new Customer[initSize];

		nextIndex=0;
		this.loadFact=loadFact;

    }

    public boolean add(Customer customer) {
        if(nextIndex>=customerArray.length){
			extendsArray();
		}
		customerArray[nextIndex++]=customer;

        return true;

    }


    public void addLast(Customer customer){
        Node n1 = new Node(customer);
        n1.next=start;
        start=n1;
    }

    public int size(){
		Node temp=start;
		int count=0;
		while(temp!=null){
			count++;
			temp=temp.next;
		}		
		return count;
	}


    private void extendsArray(){
		Customer[] tempCustomerArray=new Customer[customerArray.length+(int)(customerArray.length*loadFact)];
		for (int i = 0; i < customerArray.length; i++){
			tempCustomerArray[i]=customerArray[i];
		}
		customerArray=tempCustomerArray;
	}

    public Customer[] getCustomerAr(){
        return customerArray;
    }
    public Node start(){
        return start;
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

    public Customer get(int index) {
        return customerArray[index];
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

    class Node{
        private Node next;
        private Customer customer;
    
        Node(Customer customer) {
            this.customer=customer;
        }
    
    }
    

}



class Customer {

    private String id;
    private String ContactNumber;
    private String TshirtSize;
    private int Qty;
    private double amount;
    private String status;
    private int XS;
    private int S;
    private int M;
    private int L;
    private int XL;
    private int XXL;

    Customer() {

    }

    Customer(String id, String ContactNumber, String TshirtSize, int Qty, double amount, String status) {
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
    public void setStatus(String status){
        this.status=status;
    }

    public String toString() {
        return id+","+ContactNumber+","+TshirtSize+","+String.valueOf(Qty)+","+String.valueOf(amount)+","+String.valueOf(status);

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

         return status;

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
