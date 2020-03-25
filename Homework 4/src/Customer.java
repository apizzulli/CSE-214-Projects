public class Customer {

    private static int totalCustomers;
    private int orderNumber;
    private String food;
    private int priceOfFood;
    private int timeArrived;
    private int timeToServe;
    private int assignedRestaurant;
    private int customerNumber;
    private int tempTime;

    public Customer(){
        food = "";

    }
    public Customer(String food, int priceOfFood, int timeToServe){
        this.food = food;
        this.priceOfFood = priceOfFood;
        this.timeToServe = timeToServe;
    }

    public static int getTotalCustomers() {
        return totalCustomers;
    }

    public  void setTempTime(int n){
        this.tempTime = n;
    }
    public int getTempTime(){
        return this.tempTime;
    }
    public static void setTotalCustomers( int t ){
        totalCustomers = t;
    }
    public void setAssignedRestaurant(int n){
        this.assignedRestaurant = n;
    }
    public int getAssignedRestaurant(){
        return this.assignedRestaurant;
    }
    public int getOrderNumber(){
        return this.orderNumber;
    }
    public void setOrderNumber( int i ){
        this.orderNumber = i;
    }
    public String getFood(){
        return this.food;
    }
    public void setFood( String f ){
        this.food = f;
    }
    public int getPriceOfFood(){
        return this.priceOfFood;
    }
    public void setPriceOfFood( int p ){
        this.priceOfFood = p;
    }
    public int getTimeArrived(){
        return this.timeArrived;
    }
    public void setTimeArrived( int ta ){
        this.timeArrived = ta;
    }

    public int getTimeToServe(){
        return this.timeToServe;
    }
    public void setTimeToServe( int s){
        this.timeToServe = s;
    }
    public void setCustomerNumber(int s){
        this.customerNumber = s;
    }

    public int getCustomerNumber(){
        return this.customerNumber;
    }
    public String toString(){
        String f ="";
        switch(this.food){
            case "Cheeseburger":
                f = "C";
                break;
            case "Steak":
                f = "S";
                break;
            case "Grilled Cheese":
                f = "GC";
                break;
            case "Chicken Wings":
                f = "CW";
                break;
            case "Chicken Tenders":
                f = "CT";
                break;
            default:
                break;

        }
        String s = "";
        s += "[ #" + this.customerNumber + ", " + f + ", " + this.tempTime + " min.]";
        return s;
    }
}
