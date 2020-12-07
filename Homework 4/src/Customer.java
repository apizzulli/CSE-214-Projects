/*
Anthony Pizzulli
111990335
R08
 */

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


    /**
     * Brief: Default constructor for Customer class
     */
    public Customer(){

    }

    /**
     * Brief: Alternate constructor for Customer class initialized with parameters
     * @param food String: The food that the customer will eat
     * @param priceOfFood int: The price of the corresponding food
     * @param timeToServe int: The total time it takes to cook the Customer's food
     */
    public Customer(String food, int priceOfFood, int timeToServe){
        this.food = food;
        this.priceOfFood = priceOfFood;
        this.timeToServe = timeToServe;
    }

    /**
     * Brief: Getter method for totalCustomers instance variable
     * @return int: The total customers that exist in the simulation
     */
    public static int getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * Brief: Setter method for totalCustomers instance variable
     * @param t int: The updated
     */
    public static void setTotalCustomers( int t ){
        totalCustomers = t;
    }

    /**
     * Brief: Setter method for tempTime variable
     * @param n int: Updated value of tempTime variable
     */
    public  void setTempTime(int n){
        this.tempTime = n;
    }

    /**
     * Brief: Getter method for tempTime variable
     * @return int: The time left for the given Customer to finish their food, updated every round of the simulation
     */
    public int getTempTime(){
        return this.tempTime;
    }

    /**
     * Brief: Setter method for assignedRestaurant instance variable
     * @param n int: Updated value of assignedRestaurant variable
     */
    public void setAssignedRestaurant(int n){
        this.assignedRestaurant = n;
    }

    /**
     * Brief: Getter method for assignedRestaurant instance variable
     * @return int: The index (1 based) of the Restaurant that the given Customer is to be assigned
     */
    public int getAssignedRestaurant(){
        return this.assignedRestaurant;
    }

    /**
     * Brief: Getter method for orderNumber instance variable
     * @return int: The orderNumber instance variable
     */
    public int getOrderNumber(){
        return this.orderNumber;
    }

    /**
     * Brief: Setter method for orderNumber instance variable
     * @param i int: Updated value for orderNumber instance variable
     */
    public void setOrderNumber( int i ){
        this.orderNumber = i;
    }

    /**
     * Brief: Getter method for food instance variable
     * @return String: The food that the Customer eats, which is randomly generated
     */
    public String getFood(){
        return this.food;
    }

    /**
     * Brief: Setter method for food instance variable
     * @param f String: Updated food instance variable
     */
    public void setFood( String f ){
        this.food = f;
    }

    /**
     * Brief: Getter method for priceOfFood instance variable
     * @return int: The price of the given Customer's food
     */
    public int getPriceOfFood(){
        return this.priceOfFood;
    }

    /**
     * Brief: Setter method for priceOfFood instance variable
     * @param p int: Updated priceOfFood instance variable
     */
    public void setPriceOfFood( int p ){
        this.priceOfFood = p;
    }

    /**
     * Brief: Getter method for timeArrived instance variable
     * @return int: The time value (second) that the given Customer arrived
     */
    public int getTimeArrived(){
        return this.timeArrived;
    }

    /**
     * Brief: Setter method for timeArrived instance variable
     * @param ta int: Updated timeArrived instance variable
     */
    public void setTimeArrived( int ta ){
        this.timeArrived = ta;
    }

    /**
     * Brief: Getter method for timeToServe instance variable
     * @return int: The total amount of time it takes to cook the food the given Customer is randomly assigned
     */
    public int getTimeToServe(){
        return this.timeToServe;
    }

    /**
     * Brief: Setter method for timeToServe instance variable
     * @param s int: Updated timeToServe instance variable
     */
    public void setTimeToServe( int s){
        this.timeToServe = s;
    }

    /**
     * Brief: Getter method for customerNumber instance variable
     * @return int: The number assigned to the given Customer (based on order)
     */
    public int getCustomerNumber(){
        return this.customerNumber;
    }

    /**
     * Brief: Setter method for customerNumber instance variable
     * @param s int: Updated customerNumber instance variable
     */
    public void setCustomerNumber(int s){
        this.customerNumber = s;
    }


    /**
     * Brief: toString method for the Customer class
     * @return String: A neatly formatted representation of the Customer's number, food, and food price
     */
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
